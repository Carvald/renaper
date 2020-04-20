package ar.com.bbva.arq.esqueleto.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.arq.esqueleto.model.Person;
import ar.com.bbva.arq.esqueleto.model.RenaperResponse;
import ar.com.bbva.arq.esqueleto.service.RenaperService;

@RestController
public class RenaperController {

	@Autowired
	private RenaperService renaperService;

	@RequestMapping(method = RequestMethod.GET, value = "/api/renaper/consulta/{genero}/{numeroDocumento}/{expediente}",produces="application/json")
	public RenaperResponse infoSubmit(@PathVariable("genero") String genero,
			@PathVariable("numeroDocumento") String documentNumber, @PathVariable("expediente") String expediente) {
		return renaperService.getRenaperDatosPersona(new Person().buildSearch(genero.toUpperCase(), documentNumber, expediente));
		
	}
}