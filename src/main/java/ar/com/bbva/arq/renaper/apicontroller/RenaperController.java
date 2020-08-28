package ar.com.bbva.arq.renaper.apicontroller;


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
import ar.com.bbva.arq.renaper.model.BarcodeResponseDTO;
import ar.com.bbva.arq.renaper.model.PersonRequestDTO;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.model.RenaperResponse;
import ar.com.bbva.arq.renaper.model.UpdateClientDataDTO;
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
	public RenaperResponse<BarcodeResponseDTO> extraerDatosDni(@RequestPart(value = "file", required = true) List<MultipartFile> files) {
		RenaperResponse<BarcodeResponseDTO> response = new RenaperResponse<>();
		try {
			if (files.isEmpty()) {
				throw new BadRequestException(Constants.UPLOAD_INVALID);
			}
			byte[] documentacionClienteBytes = PDFUtils.concatenarAdjuntosComoPDF(files,
					pdf417Configuration.getMegasdni(), pdf417Configuration.getMegasfile());
			BarcodeResponseDTO personRequestDTO = pdf147Service.obtenerDatosDNI(documentacionClienteBytes);
			response.setResult(personRequestDTO);
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

}