package ar.com.bbvz.arq.esqueleto.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ar.com.bbva.arq.esqueleto.model.Information;
import ar.com.bbva.arq.esqueleto.model.RenaperResponse;
import ar.com.bbva.arq.esqueleto.service.RenaperService;


@RestController
public class RenaperController {
	
	@Autowired
	private RenaperService renaperService;

    @RequestMapping(method = RequestMethod.GET, value = "/api/renaper/consulta")
    public String infoSubmit(@ModelAttribute Information information) {
    	ModelAndView modelAndView=renaperService.getRenaperDatosPersona(information);
    	RenaperResponse renaperResponse=(RenaperResponse)modelAndView.getModel().get("information");
    	information.setPerson(renaperResponse.getPersonRenaper());
    	information.setCode(renaperResponse.getCode());
    	information.setMessage(renaperResponse.getMessage());
    	information.setValid(renaperResponse.getValid());
        return modelAndView.getViewName().equals("successContents")&& information.getValid().equals("Vigente")?"infoResult":"infoError";
    }
}