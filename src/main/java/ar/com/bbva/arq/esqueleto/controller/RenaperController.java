package ar.com.bbva.arq.esqueleto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.arq.esqueleto.model.Persona;
import ar.com.bbva.arq.esqueleto.service.RenaperService;


@RestController
@RequestMapping(value = "/renaper")
public class RenaperController {

	@Autowired
	private RenaperService renaperService;

	@RequestMapping(value = "/datosPersona", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getDatosPersona(@PathVariable String gender, @PathVariable String documentNumber,
			@PathVariable String order) {
		return renaperService.getRenaperDatosPersona(new Persona(gender,documentNumber,order));
	}
}
