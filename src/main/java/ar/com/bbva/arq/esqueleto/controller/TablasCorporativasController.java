package ar.com.bbva.arq.esqueleto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.arq.esqueleto.model.Lista;
import ar.com.bbva.arq.esqueleto.service.TablasCorporativasService;

@RestController
@RequestMapping(value = "/tablasCorporativas")
public class TablasCorporativasController {

	@Autowired
	private TablasCorporativasService tablasCorporativasService;

	@RequestMapping(value = "/codigosTipoDocumentos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Lista getCodigosTipoDoc() {
		return tablasCorporativasService.getCodigosTipoDoc();
	}
}
