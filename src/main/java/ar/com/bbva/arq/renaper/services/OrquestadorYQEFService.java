package ar.com.bbva.arq.renaper.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.com.bbva.arq.renaper.model.FormularioOrqResponseDTO;
import ar.com.bbva.arq.renaper.model.GenerarFormularioRequestDTO;
import ar.com.bbva.arq.renaper.model.OrquestadorYQEFRequestDTO;
import ar.com.bbva.arq.renaper.model.Record;
import ar.com.bbva.arq.renaper.model.YQEFResponseDTO;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.arq.renaper.utils.HTTPResponseCodesEnum;
import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.itrsa.sam.IContext;
import ar.com.itrsa.sam.TransactionException;

@Service
public class OrquestadorYQEFService extends AbstractSamService {

	@Autowired
	MailService mailService;

	@Value("${buzon.falla.orquestador}")
	private String buzon;

	@Override
	protected boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje) {
		return true;
	}

	public YQEFResponseDTO orquestarYQEF(Record record) {
		OrquestadorYQEFRequestDTO orquestadorYQEFRequestDTO = new OrquestadorYQEFRequestDTO();
		orquestadorYQEFRequestDTO.setRecord(record);
		YQEFResponseDTO yqefResponseDTO = new YQEFResponseDTO();
		try {

			Map additionalParameters = new HashMap<String, Record>();
			additionalParameters.put("BBVAPAQ-MODULO", "DIGITALIZACION");
			additionalParameters.put("BBVAPAQ-APLICACION", "FORMULARIOS");
			yqefResponseDTO = (YQEFResponseDTO) ejecutarYQEF(crearServiceAccessManagerContext(),
					Constants.SERVICIOS_XEROX, orquestadorYQEFRequestDTO, OrquestadorYQEFRequestDTO.class,
					YQEFResponseDTO.class, true, false, additionalParameters);

		} catch (TransactionException | ServiceException exception) {
			mailService.sendSimpleMessage(buzon, "Falla con SERVICIOS_XEROX(Generación ID de Formulario)",
					"Saludos, este es un mensaje automático generado por el orquestador de CLIENTES_API, ocurrió un fallo en la generación de id de formulario, los datos de entrada que fueron enviados y produjeron el fallo se muestran a continuación:\n\n\\n"
							+ record.toString()+
							"\n\nDetalle del error ocurrido: "+exception.getMessage());
			if (exception instanceof ServiceException) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), exception.getMessage());
			} else {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
			}
		}
		return yqefResponseDTO;

	}

	public FormularioOrqResponseDTO generarFormulario(String idForm,String numeroCliente) {
		GenerarFormularioRequestDTO generarFormularioRequestDTO = new GenerarFormularioRequestDTO();
		generarFormularioRequestDTO.setIdForm(idForm);
		FormularioOrqResponseDTO formularioOrqResponseDTO = new FormularioOrqResponseDTO();
		try {

			Map additionalParameters = new HashMap<String, Record>();
			additionalParameters.put("BBVAPAQ-MODULO", "INSPIREHOST");
			additionalParameters.put("BBVAPAQ-APLICACION", "FNET");
			IContext serviceAccessManagerContext = crearServiceAccessManagerContext();
			serviceAccessManagerContext.setUserName("UDESEWEB");
			formularioOrqResponseDTO = (FormularioOrqResponseDTO) ejecutar(serviceAccessManagerContext,
					Constants.FFDD_WFD_CONNECT, generarFormularioRequestDTO, GenerarFormularioRequestDTO.class,
					FormularioOrqResponseDTO.class, true, false, additionalParameters);
			formularioOrqResponseDTO.setContentType(Constants.APPLICATION_PDF);
			formularioOrqResponseDTO.setContentTransferEncoding(Constants.BASE_64);
		} catch (TransactionException | ServiceException exception) {
			mailService.sendSimpleMessage(buzon, "Falla con FFDD_WFD_CONNECT(Generación de Formulario)",
					"Saludos, este es un mensaje automático generado por el orquestador de CLIENTES_API, ocurrió un fallo en la generación y colocación en THUBAN del formulario, el id de formulario que fue enviado como dato de  entrada  y produjero el fallo se muestra a continuación:\n\n\\n"
							+ idForm+"\n"+
							"Número cliente: "+numeroCliente+
							"\n\nDetalle del error ocurrido: "+exception.getMessage());
			
			if (exception instanceof ServiceException) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), exception.getMessage());
			} else {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
			}
		}
		return formularioOrqResponseDTO;
	}

}
