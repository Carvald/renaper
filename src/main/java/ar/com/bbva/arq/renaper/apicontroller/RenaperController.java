package ar.com.bbva.arq.renaper.apicontroller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ar.com.bbva.arq.renaper.config.Pdf417Configuration;
import ar.com.bbva.arq.renaper.exceptions.BadRequestException;
import ar.com.bbva.arq.renaper.exceptions.InternalServerException;
import ar.com.bbva.arq.renaper.model.AltaDatosResponseDTO;
import ar.com.bbva.arq.renaper.model.AttemptstRequestDTO;
import ar.com.bbva.arq.renaper.model.AttemptstResponseDTO;
import ar.com.bbva.arq.renaper.model.BarcodeResponseDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitResponseDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitUnifiedRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitUnifiedResponseDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintResponseDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintValidationRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintValidationResponse;
import ar.com.bbva.arq.renaper.model.OrquestarDataDTO;
import ar.com.bbva.arq.renaper.model.PersonAltaDatos;
import ar.com.bbva.arq.renaper.model.PersonRequestDTO;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.model.RenaperResponse;
import ar.com.bbva.arq.renaper.model.UpdateClientDataDTO;
import ar.com.bbva.arq.renaper.model.afisrequest;
import ar.com.bbva.arq.renaper.services.Pdf417Service;
import ar.com.bbva.arq.renaper.services.RenaperService;
import ar.com.bbva.arq.renaper.services.ServiceException;
import ar.com.bbva.arq.renaper.services.ThubanService;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.arq.renaper.utils.HTTPResponseCodesEnum;
import ar.com.bbva.arq.renaper.utils.PDFUtils;
import io.swagger.annotations.*;

@RestController
public class RenaperController {

	@Autowired
	private RenaperService renaperService;

	@Autowired
	Pdf417Service pdf147Service;

	@Autowired
	ThubanService thubanService;

	@Autowired
	Pdf417Configuration pdf417Configuration;

	@ApiOperation(value = "Consultar datos cliente en Renaper", response = RenaperDataDTO.class)
	@RequestMapping(method = RequestMethod.GET, value = "/api/renaper/consulta/{genero}/{numeroDocumento}/{expediente}", produces = "application/json;charset=UTF-8")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta realizada con éxito", response = RenaperDataDTO.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })
	public RenaperResponse<RenaperDataDTO> consultaRenaper(@PathVariable("genero") String genero,
			@PathVariable("numeroDocumento") String documentNumber, @PathVariable("expediente") String expediente) {
		RenaperResponse<RenaperDataDTO> response = new RenaperResponse<>();
		try {
			response.setResult(renaperService.getRenaperDatosPersona(
					new PersonRequestDTO().buildSearch(genero.toUpperCase(), documentNumber, expediente)));
			response.setStatusCode(HTTPResponseCodesEnum.STATUS_200.getStatusCode());
			response.setStatusText(HTTPResponseCodesEnum.STATUS_200.getStatusText());
		} catch (ServiceException e) {
			response.setStatusCode(e.getCodigo());
			response.setStatusText(e.getMessage());
		}
		return response;

	}

	@ApiOperation(value = "Alta modificación datos cliente", response = RenaperDataDTO.class)
	@PostMapping(value = "/api/renaper/altamodificacion", produces = "application/json;charset=UTF-8")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = String.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })
	public RenaperResponse<AltaDatosResponseDTO> altaModifiacion(@RequestBody UpdateClientDataDTO updateClientDataDTO) {
		RenaperResponse<AltaDatosResponseDTO> response = new RenaperResponse<>();
		try {
			response.setResult(renaperService.orquestarDatosPersona(updateClientDataDTO));
			response.setStatusCode(HTTPResponseCodesEnum.STATUS_200.getStatusCode());
			response.setStatusText(HTTPResponseCodesEnum.STATUS_200.getStatusText());
		} catch (ServiceException e) {
			response.setStatusCode(e.getCodigo());
			response.setStatusText(e.getMessage());
		}
		return response;
	}

	@ApiOperation(value = "Alta modificación datos cliente", response = RenaperDataDTO.class)
	@PostMapping(value = "/api/renaper/obtenerdatosporimagen", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json;charset=UTF-8")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = BarcodeResponseDTO.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })
	public RenaperResponse<BarcodeResponseDTO> extraerDatosDni(
			@RequestPart(value = "file", required = true) List<MultipartFile> files) {
		RenaperResponse<BarcodeResponseDTO> response = new RenaperResponse<>();
		RenaperDataDTO renaperResponse = null;
		try {
			if (files.isEmpty()) {
				throw new BadRequestException(Constants.UPLOAD_INVALID);
			}
			byte[] documentacionClienteBytes = PDFUtils.concatenarAdjuntosComoPDF(files,
					pdf417Configuration.getMegasdni(), pdf417Configuration.getMegasfile());
			BarcodeResponseDTO barcodeResponseDTO = pdf147Service.obtenerDatosDNI(documentacionClienteBytes);
				renaperResponse = renaperService.getRenaperDatosPersona(barcodeResponseDTO);
				PersonAltaDatos personAltaDatos = new PersonAltaDatos().buildFromRenaper(renaperResponse.getPerson(),
						barcodeResponseDTO,"02");
			String 	numeroCliente = renaperService.actualizarDatosPersona(personAltaDatos);
			barcodeResponseDTO.setNumeroCliente(numeroCliente);
			barcodeResponseDTO.setResultadoActualizacion(Constants.SUCCESS_UPDATE);
			String documentacionClienteStringBase64=Base64.getEncoder().encodeToString(documentacionClienteBytes);
			barcodeResponseDTO.setIdThuban(thubanService.publicar(documentacionClienteStringBase64, numeroCliente,barcodeResponseDTO));
			response.setResult(barcodeResponseDTO);
			response.setStatusCode(HTTPResponseCodesEnum.STATUS_200.getStatusCode());
			response.setStatusText(HTTPResponseCodesEnum.STATUS_200.getStatusText());
			
		} catch (ServiceException e) {
			if (e.getCodigo().equals(HTTPResponseCodesEnum.STATUS_400.getStatusCode())) {
				throw new BadRequestException(e.getMessage());
			} else {
				throw new InternalServerException(e.getMessage());
			}
		}
		return response;
	}

	@ApiOperation(value = "Identificación por huella", response = RenaperDataDTO.class)
	@PostMapping(value = "/api/renaper/identificacionporhuella", produces = "application/json;charset=UTF-8")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = String.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })
	public RenaperResponse<FingerPrintResponseDTO> identificacionPorHuella(
			@RequestBody FingerPrintRequestDTO fingerPrintDataDTO) {
		RenaperResponse<FingerPrintResponseDTO> response = new RenaperResponse<>();
		try {
			response.setResult(renaperService.identificacionPorhuella(fingerPrintDataDTO));
			response.setStatusCode(HTTPResponseCodesEnum.STATUS_200.getStatusCode());
			response.setStatusText(HTTPResponseCodesEnum.STATUS_200.getStatusText());
		} catch (ServiceException e) {
			if (e.getCodigo().equals(HTTPResponseCodesEnum.STATUS_400.getStatusCode())) {
				throw new BadRequestException(e.getMessage());
			} else {
				throw new InternalServerException(e.getMessage());
			}
		}
		return response;
	}

	@ApiOperation(value = "Intentos disponibles Identificación por huella", response = RenaperDataDTO.class)
	@PostMapping(value = "/api/renaper/intentosdisponibles", produces = "application/json;charset=UTF-8")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = String.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })
	public RenaperResponse<AttemptstResponseDTO> intentosDisponibles(
			@RequestBody AttemptstRequestDTO attemptstRequestDTO) {
		RenaperResponse<AttemptstResponseDTO> response = new RenaperResponse<>();
		try {

			response.setResult(renaperService.intentosDisponibles(attemptstRequestDTO));
			response.setStatusCode(HTTPResponseCodesEnum.STATUS_200.getStatusCode());
			response.setStatusText(HTTPResponseCodesEnum.STATUS_200.getStatusText());
		} catch (ServiceException e) {
			if (e.getCodigo().equals(HTTPResponseCodesEnum.STATUS_400.getStatusCode())) {
				throw new BadRequestException(e.getMessage());
			} else {
				throw new InternalServerException(e.getMessage());
			}
		}
		return response;
	}

	@ApiOperation(value = "Identificación por huella integrada con pchu", response = RenaperDataDTO.class)
	@PostMapping(value = "/api/renaper/actualizarintentosidentificacionporhuella", produces = "application/json;charset=UTF-8")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = String.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })
	public RenaperResponse<FingerPrintCircuitResponseDTO> actualizarIntentosIdentificacionPorHuella(
			@RequestBody FingerPrintCircuitRequestDTO fingerPrintCircuitRequestDTO) {
		RenaperResponse<FingerPrintCircuitResponseDTO> response = new RenaperResponse<>();
		try {

			if (fingerPrintCircuitRequestDTO.getAttemptsRequestSimpleDTO() == null
					|| fingerPrintCircuitRequestDTO.getAfisrequest() == null) {
				throw new BadRequestException(HTTPResponseCodesEnum.STATUS_400.getStatusText());
			}
			response.setResult(renaperService.actualizarIntentosIdentificacionPorHuella(fingerPrintCircuitRequestDTO));
			response.setStatusCode(HTTPResponseCodesEnum.STATUS_200.getStatusCode());
			response.setStatusText(HTTPResponseCodesEnum.STATUS_200.getStatusText());
		} catch (ServiceException e) {
			if (e.getCodigo().equals(HTTPResponseCodesEnum.STATUS_400.getStatusCode())) {
				throw new BadRequestException(e.getMessage());
			} else {
				throw new InternalServerException(e.getMessage());
			}
		}
		return response;
	}

	@ApiOperation(value = "Alta modificación datos cliente", response = RenaperDataDTO.class)
	@PostMapping(value = "/api/renaper/altamodificacioncondatarenaper", produces = "application/json;charset=UTF-8")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = String.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })
	public RenaperResponse<AltaDatosResponseDTO> altaModificacionConDataRenaper(
			@RequestBody OrquestarDataDTO orquestarDataDTO) {
		RenaperResponse<AltaDatosResponseDTO> response = new RenaperResponse<>();
		try {
			response.setResult(renaperService.orquestarDatosPersonaConDataRenaper(orquestarDataDTO));
			response.setStatusCode(HTTPResponseCodesEnum.STATUS_200.getStatusCode());
			response.setStatusText(HTTPResponseCodesEnum.STATUS_200.getStatusText());
		} catch (ServiceException e) {
			if (e.getCodigo().equals(HTTPResponseCodesEnum.STATUS_400.getStatusCode())) {
				throw new BadRequestException(e.getMessage());
			} else {
				throw new InternalServerException(e.getMessage());
			}
		}
		return response;
	}

	@ApiOperation(value = "Identificación por huella integrada con pchu general", response = RenaperDataDTO.class)
	@PostMapping(value = "/api/renaper/identificacionporhuellabundle", produces = "application/json;charset=UTF-8")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = String.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })
	public FingerPrintValidationResponse identificacionPorHuellaUnificado(
			@RequestBody FingerPrintValidationRequestDTO fingerPrintValidatioRequestDTO) {
		FingerPrintValidationResponse fingerPrintValidationResponse = new FingerPrintValidationResponse();
		try {
			
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
			fingerPrintValidationResponse.setRetorno(e.getCodigo());
			fingerPrintValidationResponse.setStatusCode(e.getCodigo());
			fingerPrintValidationResponse.setStatusText(e.getMessage());
		}
		return fingerPrintValidationResponse;
	}

}