package ar.com.bbva.arq.renaper.services;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.springframework.stereotype.Service;
import ar.com.bbva.arq.renaper.model.AttemptstRequestDTO;
import ar.com.bbva.arq.renaper.model.AttemptstResponseDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitResponseDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitUnifiedRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintResponseDTO;
import ar.com.bbva.arq.renaper.model.GenerarFormularioRequestDTO;
import ar.com.bbva.arq.renaper.model.OrquestadorYQEFRequestDTO;
import ar.com.bbva.arq.renaper.model.Record;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.arq.renaper.utils.FormatUtils;
import ar.com.bbva.arq.renaper.utils.HTTPResponseCodesEnum;
import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.itrsa.sam.IContext;
import ar.com.itrsa.sam.TransactionException;

@Service
public class OrquestadorYQEFService extends AbstractSamService {

	@Override
	protected boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje) {
		return true;
	}

	public FingerPrintCircuitResponseDTO orquestarYQEF(
			Record record) {
		OrquestadorYQEFRequestDTO orquestadorYQEFRequestDTO = new OrquestadorYQEFRequestDTO();
		orquestadorYQEFRequestDTO.setRecord(record);
		FingerPrintCircuitResponseDTO fingerPrintCircuitResponseDTO = new FingerPrintCircuitResponseDTO();
		try {
			
			Map additionalParameters = new HashMap<String,Record>();
			
			additionalParameters.put("BBVAPAQ-MODULO","DIGITALIZACION");
			additionalParameters.put("BBVAPAQ-APLICACION","FORMULARIOS");
			additionalParameters.put("BBVAPAQ-USUARIO","UDESEWEB");
			FingerPrintResponseDTO fingerPrintResponseDTO = (FingerPrintResponseDTO) ejecutarYQEF(crearServiceAccessManagerContext(),
					Constants.SERVICIOS_XEROX, orquestadorYQEFRequestDTO, OrquestadorYQEFRequestDTO.class,
					FingerPrintResponseDTO.class, true, false,additionalParameters);
	
						
		} catch (TransactionException | ServiceException exception) {
			if (exception instanceof ServiceException) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), exception.getMessage());
			} else {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
			}	
		}
		return fingerPrintCircuitResponseDTO;

	}
	
	
	
	public FingerPrintCircuitResponseDTO generarFormulario(
			Record record) {
		GenerarFormularioRequestDTO generarFormularioRequestDTO = new GenerarFormularioRequestDTO();
		generarFormularioRequestDTO.setIdForm("AH000000000000023209");
		FingerPrintCircuitResponseDTO fingerPrintCircuitResponseDTO = new FingerPrintCircuitResponseDTO();
		try {
			
			Map additionalParameters = new HashMap<String,Record>();
			
			additionalParameters.put("BBVAPAQ-MODULO","INSPIREHOST");
			additionalParameters.put("BBVAPAQ-APLICACION","FNET");
			
			IContext serviceAccessManagerContext  = crearServiceAccessManagerContext();
			serviceAccessManagerContext.setUserName("UDESEWEB");
			
			FingerPrintResponseDTO fingerPrintResponseDTO = (FingerPrintResponseDTO) ejecutar(serviceAccessManagerContext,
					Constants.FFDD_WFD_CONNECT, generarFormularioRequestDTO, GenerarFormularioRequestDTO.class,
					FingerPrintResponseDTO.class, true, false,additionalParameters);
	
						
		} catch (TransactionException | ServiceException exception) {
			if (exception instanceof ServiceException) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), exception.getMessage());
			} else {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
			}	
		}
		return fingerPrintCircuitResponseDTO;

	}



}
