package ar.com.bbva.arq.renaper.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.arq.renaper.exceptions.BadRequestException;
import ar.com.bbva.arq.renaper.exceptions.InternalServerException;
import ar.com.bbva.arq.renaper.model.OrquestadorResponse;
import ar.com.bbva.arq.renaper.model.Record;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.services.OrquestadorYQEFService;
import ar.com.bbva.arq.renaper.services.ServiceException;
import ar.com.bbva.arq.renaper.utils.HTTPResponseCodesEnum;
import io.swagger.annotations.*;

@RestController
public class OrquestadorYQEFController {

	@Autowired
	private OrquestadorYQEFService orquestadorYQEFService;

	@ApiOperation(value = "Orquestador YQEF", response = RenaperDataDTO.class)
	@PostMapping(value = "/orquestadoryqef", produces = "application/json;charset=UTF-8")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operación realizada con éxito", response = String.class),
			@ApiResponse(code = 400, message = "Error técnico de negocio"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })
	public OrquestadorResponse orquestadorYQEF(@RequestBody Record record) {
		OrquestadorResponse orquestadorResponse = new OrquestadorResponse();
		try {

			orquestadorResponse
					.setYqefResponseDTO(orquestadorYQEFService.orquestarYQEF(record));
			orquestadorResponse
					.setFormularioOrqResponseDTO(orquestadorYQEFService.generarFormulario(orquestadorResponse.getYqefResponseDTO().getIdentificador_formulario(),record.getNumero_cliente()));
			orquestadorResponse.setStatusCode(HTTPResponseCodesEnum.STATUS_200.getStatusCode());
			orquestadorResponse.setStatusText(HTTPResponseCodesEnum.STATUS_200.getStatusText());

		} catch (ServiceException e) {
			if (e.getCodigo().equals(HTTPResponseCodesEnum.STATUS_400.getStatusCode())) {
				throw new BadRequestException(e.getMessage());
			} else {
				throw new InternalServerException(e.getMessage());
			}
		}

		return orquestadorResponse;
	}

}