package ar.com.bbva.arq.renaper.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ar.com.bbva.arq.renaper.config.ClavesConfiguration;
import ar.com.bbva.arq.renaper.config.Pdf417Configuration;
import ar.com.bbva.arq.renaper.model.BarcodeResponseDTO;
import ar.com.bbva.arq.renaper.model.Information;
import ar.com.bbva.arq.renaper.model.PersonAltaDatos;
import ar.com.bbva.arq.renaper.model.PersonRequestDTO;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.services.Pdf417Service;
import ar.com.bbva.arq.renaper.services.RenaperService;
import ar.com.bbva.arq.renaper.services.ServiceException;
import ar.com.bbva.arq.renaper.services.ThubanService;
import ar.com.bbva.arq.renaper.utils.Constants;

@Controller
@RequestMapping(value = "/inputdata")
public class InputDataController {

	@Autowired
	private RenaperService renaperService;

	@Autowired
	Pdf417Service pdf147Service;

	@Autowired
	ThubanService thubanService;

	@Autowired
	Pdf417Configuration pdf417Configuration;

	@Autowired
	ClavesConfiguration clavesConfiguration;

	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String infoForm(Model model) {
		model.addAttribute("information", new Information());
		return "inputDataForm";
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String infoSubmit(@ModelAttribute Information information, RedirectAttributes attributes, Model model) {

		try {
			RenaperDataDTO renaperResponse = null;
			PersonRequestDTO personRequestDTO = new PersonRequestDTO();
			personRequestDTO.setDocumentNumber(information.getDocumentNumber());
			personRequestDTO.setGender(information.getGender());
			personRequestDTO.setOrder(information.getOrder());
			renaperResponse = renaperService.getRenaperDatosPersona(personRequestDTO);
			if (information.getHost()) {
				BarcodeResponseDTO barcodeResponseDTO = new BarcodeResponseDTO();
				barcodeResponseDTO.buildFrommInputInfo(information);
				PersonAltaDatos personAltaDatos = new PersonAltaDatos().buildFromRenaper(renaperResponse.getPerson(),
						barcodeResponseDTO);
				renaperService.actualizarDatosPersona(personAltaDatos);
			}
			information.setPerson(renaperResponse.getPerson());
			information.setCode(Constants.SUCCESS_UPDATE);
			information.setMessage(Constants.SUCCESS_MESSAGE);
			information.setValid(renaperResponse.getValid());
			return "infoResultData";
		} catch (

		ServiceException e) {
			information.setCode(e.getCodigo());
			information.setMessage(e.getMessage());
			return "infoError";
		}

	}
}