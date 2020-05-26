package ar.com.bbva.arq.renaper.apicontroller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.arq.renaper.model.PersonRequestDTO;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.model.RenaperResponse;
import ar.com.bbva.arq.renaper.model.UpdateClientDataDTO;
import ar.com.bbva.arq.renaper.services.RenaperService;
import ar.com.bbva.arq.renaper.services.ServiceException;
import ar.com.bbva.arq.renaper.utils.HTTPResponseCodesEnum;
import io.swagger.annotations.*;

@RestController
public class RenaperController {

	@Autowired
	private RenaperService renaperService;

	@ApiOperation(value = "Consultar datos cliente en Renaper", response = RenaperDataDTO.class)
	@RequestMapping(method = RequestMethod.GET, value = "/api/renaper/consulta/{genero}/{numeroDocumento}/{expediente}",  produces = "application/json;charset=UTF-8")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta realizada con éxito", response = RenaperDataDTO.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio", response = RenaperDataDTO.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = RenaperDataDTO.class) })
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
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = RenaperDataDTO.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio", response = RenaperDataDTO.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = RenaperDataDTO.class) })
	public RenaperResponse<String> infoSubmit(@RequestBody UpdateClientDataDTO updateClientDataDTO) {
		RenaperResponse<String> response = new RenaperResponse<>();
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

}