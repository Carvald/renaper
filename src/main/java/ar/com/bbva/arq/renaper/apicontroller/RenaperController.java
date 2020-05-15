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
		RenaperResponse<RenaperDataDTO> response = new RenaperResponse<>();
		response.setResult(renaperService
				.getRenaperDatosPersona(new PersonRequestDTO().buildSearch(genero.toUpperCase(), documentNumber, expediente)));
		return response.toStatus200OK();

	}

	@ApiOperation(value = "Alta modificación datos cliente", response = RenaperDataDTO.class)
	@PostMapping( value = "/api/renaper/altamodificacion", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = RenaperDataDTO.class),
			@ApiResponse(code = 500, message = "Error interno del servidor", response = RenaperDataDTO.class) })
	public RenaperResponse<String> infoSubmit(@RequestBody UpdateClientDataDTO updateClientDataDTO) {
		RenaperResponse<String> response = new RenaperResponse<>();
		response.setResult(renaperService
				.orquestarDatosPersona(updateClientDataDTO));
		return response.toStatus200OK();

	}

}