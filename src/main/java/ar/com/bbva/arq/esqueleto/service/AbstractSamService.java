package ar.com.bbva.arq.esqueleto.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.bbva.sam.bbvaPaq.BbvaPaqConstants;

import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.bbva.soa.conectores.BbvaSoaStatus;
import ar.com.itrsa.sam.IContext;
import ar.com.itrsa.sam.IServiceAccessManager;
import ar.com.itrsa.sam.TransactionException;
import ar.org.bbva.util.ConvertUtils;
import ar.org.bbva.util.converters.IConvertionManager;

public abstract class AbstractSamService {
	private static final Log log = LogFactory.getLog(AbstractSamService.class);

	@Autowired
	private IConvertionManager convertionManager;

	@Autowired
	private IServiceAccessManager serviceAccessManager;

	@Value("${abstractService.errorCode:99}")
	private String defaultErrorCode;

	@Value("${abstractService.errorMessage:ERROR AL EJECUTAR EL SERVICIO}")
	private String defaultErrorMessage;

	protected IServiceAccessManager getServiceAccessManager() {
		return serviceAccessManager;
	}

	protected void setServiceAccessManager(IServiceAccessManager serviceAccessManager) {
		this.serviceAccessManager = serviceAccessManager;
	}

	protected IConvertionManager getConvertionManager() {
		return convertionManager;
	}

	protected void setConvertionManager(IConvertionManager convertionManager) {
		this.convertionManager = convertionManager;
	}

	protected IContext crearServiceAccessManagerContext() throws TransactionException {
		return getServiceAccessManager().getContextManager().createContext("DEFAULT", new HashMap());
	}

	protected abstract boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje);

	protected ServiceException crearErrorGenerico() {
		return new ServiceException(defaultErrorCode, defaultErrorMessage, null);
	}

	protected void checkSoaStatus(Map parameters) throws ServiceException {
		BbvaSoaStatus status = (parameters == null) ? null : (BbvaSoaStatus) parameters.get(BbvaPaqConstants.NOMBRE_PARAM_STATUS);
		
		BbvaSoaMensaje message = null;

		String errorMessage = null;
		String errorCode = null;

		if (status != null && !status.isOk()) {
			log.error(status);
			if (status.getListaErrores() != null && status.getListaErrores().size() > 0) {
				message = (BbvaSoaMensaje) status.getListaErrores().get(0);
				if (noEnmascararEnErrorGenerico(message)) {
					errorMessage = message.getDescripcion();
					errorCode = message.getCodigo();
				}
			}
			throw (errorMessage != null ? new ServiceException(errorCode, errorMessage, null) : crearErrorGenerico());
		}
	}

	protected Object ejecutar(IContext serviceAccessManagerContext, String nombreServicio, Object beanEntrada, Class claseBeanEntrada, Class claseBeanSalida, boolean respuestaCaseInsensitive, boolean entradaCaseInsensitive, Map additionalParameters) throws ServiceException {
		Object respuesta = null;
		try {
			if (beanEntrada != null && claseBeanEntrada != null && !claseBeanEntrada.isInstance(beanEntrada))
				throw crearErrorGenerico();

			Map parametersExecute = null;

			if (entradaCaseInsensitive) {
				parametersExecute = new CaseInsensitiveMap();
			} else {
				parametersExecute = new HashMap();
			}

			if (beanEntrada != null) {
				if (entradaCaseInsensitive) {
					parametersExecute = (Map) ConvertUtils.convert(getConvertionManager(), CaseInsensitiveMap.class,
							beanEntrada);
				} else {
					parametersExecute = (Map) ConvertUtils.convert(getConvertionManager(), java.util.HashMap.class,
							beanEntrada);
				}
			}

			if (additionalParameters != null)
				// Agrego parametros adicionales que no forman parte del BEAN de entrada
				parametersExecute.putAll(additionalParameters);

			getServiceAccessManager().execute(nombreServicio, serviceAccessManagerContext, parametersExecute);

			if (respuestaCaseInsensitive) {
				parametersExecute = (Map) ConvertUtils.convertMapToMapCaseInsensitive(parametersExecute);
			}

			checkSoaStatus(parametersExecute);

			if (claseBeanSalida != null) {
				respuesta = ConvertUtils.convert(getConvertionManager(), claseBeanSalida, parametersExecute);
			}

		} catch (ServiceException e) {
			log.error("Error al ejecutar " + nombreServicio + ": " + e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error("Error al ejecutar " + nombreServicio + ": " + e.getMessage(), e);
			throw crearErrorGenerico();
		}

		return respuesta;
	}
}
