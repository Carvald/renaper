package ar.com.bbva.arq.renaper.apicontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.arq.renaper.config.Pdf417Configuration;
import ar.com.bbva.arq.renaper.exceptions.BadRequestException;
import ar.com.bbva.arq.renaper.model.AttemptstRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitResponseDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitUnifiedRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitUnifiedResponseDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintValidationRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintValidationResponse;
import ar.com.bbva.arq.renaper.model.Record;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.model.afisrequest;
import ar.com.bbva.arq.renaper.services.OrquestadorYQEFService;
import ar.com.bbva.arq.renaper.services.Pdf417Service;
import ar.com.bbva.arq.renaper.services.RenaperService;
import ar.com.bbva.arq.renaper.services.ServiceException;
import ar.com.bbva.arq.renaper.services.ThubanService;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.arq.renaper.utils.HTTPResponseCodesEnum;
import io.swagger.annotations.*;

@RestController
public class OrquestadorYQEFController {


	
	@Autowired
	private OrquestadorYQEFService orquestadorYQEFService;

	@Autowired
	Pdf417Service pdf147Service;

	@Autowired
	ThubanService thubanService;

	@Autowired
	Pdf417Configuration pdf417Configuration;

	
	@ApiOperation(value = "Identificación por huella integrada con pchu general", response = RenaperDataDTO.class)
	@PostMapping(value = "/orquestadoryqef", produces = "application/json;charset=UTF-8")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = String.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })
	public FingerPrintValidationResponse identificacionPorHuellaUnificado(
			@RequestBody Record record) {
		FingerPrintValidationResponse fingerPrintValidationResponse = new FingerPrintValidationResponse();
		record.setAprovisionamiento("000000001223100118000000010510146CAPES");
		record.setCod_origen("AH");
		record.setIdentificador("ID0070");
		record.setNumero_tramite("0000000012231");
		record.setOpcion("G");
		record.setTrx_base("WPUB");
		
		
		orquestadorYQEFService.generarFormulario(record);
		//orquestadorYQEFService.orquestarYQEF(record);
		
		
		
		
		/*try {
			
			FingerPrintCircuitUnifiedRequestDTO fingerPrintCircuitUnifiedRequestDTO = new FingerPrintCircuitUnifiedRequestDTO();
			fingerPrintCircuitUnifiedRequestDTO.setAttemptsRequestDTO(new AttemptstRequestDTO().buildFromCheckAttempts(
					fingerPrintValidatioRequestDTO.getOpcion(), fingerPrintValidatioRequestDTO.getNumeroCliente(),
					fingerPrintValidatioRequestDTO.getNumeroDocumento(),
					fingerPrintValidatioRequestDTO.getTipoDocumento()));

			FingerPrintCircuitUnifiedResponseDTO fingerPrintCircuitUnifiedResponseDTO = new FingerPrintCircuitUnifiedResponseDTO();

			if (fingerPrintCircuitUnifiedRequestDTO.getAttemptsRequestDTO().getOpcion()
					.equals(Constants.PCHU_OPCION_IDA)) {
				fingerPrintCircuitUnifiedResponseDTO.setAttemptsResponseDTO(renaperService
						.intentosDisponibles(fingerPrintCircuitUnifiedRequestDTO.getAttemptsRequestDTO()));

			} else {
				
				if(fingerPrintValidatioRequestDTO.getFingerPrintAfisRequestDTO()==null)
					throw new BadRequestException("Missing FingerPrintAfisRequestDTO()");
				
				fingerPrintCircuitUnifiedRequestDTO.setAfisrequest(new afisrequest().buildFromValidationRequest(
						fingerPrintValidatioRequestDTO.getFingerPrintAfisRequestDTO(),
						fingerPrintValidatioRequestDTO.getNumeroDocumento()));
				FingerPrintCircuitResponseDTO fingerPrintCircuitResponseDTO = renaperService
						.actualizarIntentosIdentificacionPorHuellaUnificado(fingerPrintCircuitUnifiedRequestDTO);
				fingerPrintCircuitUnifiedResponseDTO
						.setFingerPrintResponseDTO(fingerPrintCircuitResponseDTO.getFingerPrintResponseDTO());
				fingerPrintCircuitUnifiedResponseDTO
						.setAttemptsResponseDTO(fingerPrintCircuitResponseDTO.getVueltaAttemptstResponseDTO());
			}

			
			if(fingerPrintCircuitUnifiedResponseDTO.getAttemptsResponseDTO()!=null)
			{fingerPrintValidationResponse
					.setNroclie(fingerPrintCircuitUnifiedResponseDTO.getAttemptsResponseDTO().getNroclie());
			fingerPrintValidationResponse
					.setTipdoc(fingerPrintCircuitUnifiedResponseDTO.getAttemptsResponseDTO().getTipdoc());
			fingerPrintValidationResponse
					.setNrodoc(fingerPrintCircuitUnifiedResponseDTO.getAttemptsResponseDTO().getNrodoc());
			fingerPrintValidationResponse
					.setRetorno(fingerPrintCircuitUnifiedResponseDTO.getAttemptsResponseDTO().getRetorno());
			fingerPrintValidationResponse
					.setCoderr(fingerPrintCircuitUnifiedResponseDTO.getAttemptsResponseDTO().getCoderr());
			}
			
			if(fingerPrintCircuitUnifiedResponseDTO.getFingerPrintResponseDTO()!=null)
			{fingerPrintValidationResponse
					.setCode(fingerPrintCircuitUnifiedResponseDTO.getFingerPrintResponseDTO().getCode());
			fingerPrintValidationResponse
					.setMatchScore(fingerPrintCircuitUnifiedResponseDTO.getFingerPrintResponseDTO().getMatchScore());
			fingerPrintValidationResponse
					.setMatchType(fingerPrintCircuitUnifiedResponseDTO.getFingerPrintResponseDTO().getMatchType());
			fingerPrintValidationResponse
					.setMessage(fingerPrintCircuitUnifiedResponseDTO.getFingerPrintResponseDTO().getMessage());
			fingerPrintValidationResponse
					.setResult(fingerPrintCircuitUnifiedResponseDTO.getFingerPrintResponseDTO().getResult());
			}
			fingerPrintValidationResponse.setStatusCode(HTTPResponseCodesEnum.STATUS_200.getStatusCode());
			fingerPrintValidationResponse.setStatusText(HTTPResponseCodesEnum.STATUS_200.getStatusText());

		} catch (ServiceException e) {
			fingerPrintValidationResponse.setStatusCode(e.getCodigo());
			fingerPrintValidationResponse.setStatusText(e.getMessage());
		}*/
		return fingerPrintValidationResponse;
	}

}