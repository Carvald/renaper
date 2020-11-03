package ar.com.bbva.arq.renaper.apicontroller;

import java.io.IOException;
import java.util.Base64;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.com.bbva.arq.renaper.config.ClavesConfiguration;
import ar.com.bbva.arq.renaper.config.Pdf417Configuration;
import ar.com.bbva.arq.renaper.model.AttemptstRequestDTO;
import ar.com.bbva.arq.renaper.model.BarcodeResponseDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintFlowCircuitDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintRequestDTO;
import ar.com.bbva.arq.renaper.model.PersonAltaDatos;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.services.Pdf417Service;
import ar.com.bbva.arq.renaper.services.RenaperService;
import ar.com.bbva.arq.renaper.services.ServiceException;
import ar.com.bbva.arq.renaper.services.ThubanService;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.arq.renaper.utils.PDFUtils;

@Controller
@RequestMapping(value = "/fingerprintflow")
public class FingerPrintFlowController {

	@Autowired
	private RenaperService renaperService;
	
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String fingerprintForm(Model model) {
		model.addAttribute("fingerprintflowcircuitDTO", new FingerPrintFlowCircuitDTO());
		return "fingerPrintForm";
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String fingerprintSubmit(RedirectAttributes attributes,
			@ModelAttribute FingerPrintFlowCircuitDTO fingerPrintFlowCircuitDTO, Model model) throws IOException {
	
		try {
		
			AttemptstRequestDTO attemptstRequestDTO =new AttemptstRequestDTO().buildFromSimpleDto(fingerPrintFlowCircuitDTO.getAttemptsRequestSimpleDTO(),"",Constants.PCHU_OPCION_IDA);
			fingerPrintFlowCircuitDTO.setAttemptstResponseDTO(renaperService.intentosDisponibles(attemptstRequestDTO));
			
			FingerPrintRequestDTO fingerPrintRequestDTO = new FingerPrintRequestDTO();
			fingerPrintRequestDTO.buildFromCircuitRequest(fingerPrintFlowCircuitDTO.getAfisrequest());
			
			fingerPrintFlowCircuitDTO.setFingerPrintResponseDTO(renaperService.identificacionPorhuella(fingerPrintRequestDTO));
			
			attemptstRequestDTO.setOpcion(Constants.PCHU_OPCION_VUELTA);
			attemptstRequestDTO.setOpcion(fingerPrintFlowCircuitDTO.getFingerPrintResponseDTO().getCode());
			fingerPrintFlowCircuitDTO.setAttemptstResponseDTO(renaperService.intentosDisponibles(attemptstRequestDTO));
			
			return "fingerPrintResult";
		} catch (ServiceException e) {
			fingerPrintFlowCircuitDTO.setCode(e.getCodigo());
			fingerPrintFlowCircuitDTO.setMessage(e.getMessage());
			return "fingerPrintError";
		}

	}
}