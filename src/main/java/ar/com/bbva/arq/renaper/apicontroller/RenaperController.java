package ar.com.bbva.arq.renaper.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.arq.renaper.model.Person;
import ar.com.bbva.arq.renaper.model.RawResponse;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.model.RenaperResponse;
import ar.com.bbva.arq.renaper.services.RenaperService;
import io.swagger.annotations.*;

@RestController
public class RenaperController {

	@Autowired
	private RenaperService renaperService;

	@ApiOperation(value = "Consultar datos cliente en Renaper", response = RenaperDataDTO.class)
	@RequestMapping(method = RequestMethod.GET, value = "/api/renaper/consulta/{genero}/{numeroDocumento}/{expediente}", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta realizada con éxito", response = RenaperDataDTO.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = RenaperDataDTO.class) })
	public RenaperResponse<RenaperDataDTO> consultaRenaper(@PathVariable("genero") String genero,
			@PathVariable("numeroDocumento") String documentNumber, @PathVariable("expediente") String expediente) {
		RenaperResponse<RenaperDataDTO> response = new RenaperResponse<RenaperDataDTO>();
		response.setResult(renaperService
				.getRenaperDatosPersona(new Person().buildSearch(genero.toUpperCase(), documentNumber, expediente)));
		return response.toStatus200OK();

	}

	@ApiOperation(value = "Alta modificación datos cliente", response = RenaperDataDTO.class)
	@RequestMapping(method = RequestMethod.GET, value = "/api/renaper/altamodificacion/{genero}/{numeroDocumento}/{expediente}", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = RenaperDataDTO.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = RenaperDataDTO.class) })
	public RenaperResponse<RawResponse> infoSubmit(@PathVariable("genero") String genero,
			@PathVariable("numeroDocumento") String documentNumber, @PathVariable("expediente") String expediente) {
		RenaperResponse<RawResponse> response = new RenaperResponse<RawResponse>();
		response.setResult(renaperService
				.orquestarDatosPersona(new Person().buildSearch(genero.toUpperCase(), documentNumber, expediente)));
		return response.toStatus200OK();

	}

}