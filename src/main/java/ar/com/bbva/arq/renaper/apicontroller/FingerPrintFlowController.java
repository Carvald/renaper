package ar.com.bbva.arq.renaper.apicontroller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ar.com.bbva.arq.renaper.model.AttemptstRequestDTO;
import ar.com.bbva.arq.renaper.model.AttemptstResponseDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintFlowCircuitDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintResponseDTO;
import ar.com.bbva.arq.renaper.services.RenaperService;
import ar.com.bbva.arq.renaper.services.ServiceException;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.arq.renaper.utils.FormatUtils;

@Controller
@RequestMapping(value = "/fingerprintflow")
public class FingerPrintFlowController {

	@Autowired
	private RenaperService renaperService;

	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String fingerprintForm(Model model) {
		model.addAttribute("fingerPrintFlowCircuitDTO", new FingerPrintFlowCircuitDTO());
		return "fingerPrintForm";
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String fingerprintSubmit(RedirectAttributes attributes,
			@ModelAttribute FingerPrintFlowCircuitDTO fingerPrintFlowCircuitDTO, Model model) throws IOException {

		try {
			if (fingerPrintFlowCircuitDTO.getCircuito().equals("I")) {
				AttemptstRequestDTO attemptstRequestDTO = new AttemptstRequestDTO().buildFromSimpleDtoFlowCircuit(
						fingerPrintFlowCircuitDTO.getAttemptsRequestSimpleDTO(), "", Constants.PCHU_OPCION_IDA);
				fingerPrintFlowCircuitDTO
						.setAttemptstResponseIdaDTO(renaperService.intentosDisponiblesSubmit(attemptstRequestDTO));
				fingerPrintFlowCircuitDTO
						.setAttemptstResponseIdaDTO(renaperService.intentosDisponiblesSubmit(attemptstRequestDTO));
				
				fingerPrintFlowCircuitDTO
				.setFingerPrintResponseDTO(new FingerPrintResponseDTO());
				
				fingerPrintFlowCircuitDTO
				.setAttemptstResponseVueltaDTO(new AttemptstResponseDTO());
		
				fingerPrintFlowCircuitDTO.setCompleto(false);
				fingerPrintFlowCircuitDTO.setPchuvuelta(false);
				fingerPrintFlowCircuitDTO.setPchuida(true);
				
				
				return "fingerPrintResult";
			} else if (fingerPrintFlowCircuitDTO.getCircuito().equals("V")) {

				AttemptstRequestDTO attemptstRequestDTO = new AttemptstRequestDTO().buildFromSimpleDtoFlowCircuit(
						fingerPrintFlowCircuitDTO.getAttemptsRequestSimpleDTO(), "", Constants.PCHU_OPCION_VUELTA);

				attemptstRequestDTO.setRenaper(
						FormatUtils.completaCerosIzq(2, fingerPrintFlowCircuitDTO.getRespuestaRenaper().length(),
								fingerPrintFlowCircuitDTO.getRespuestaRenaper()));
				fingerPrintFlowCircuitDTO
						.setAttemptstResponseIdaDTO(renaperService.intentosDisponiblesSubmit(attemptstRequestDTO));
				
				fingerPrintFlowCircuitDTO
				.setAttemptstResponseIdaDTO(new AttemptstResponseDTO());
				
				
				fingerPrintFlowCircuitDTO
				.setFingerPrintResponseDTO(new FingerPrintResponseDTO());
				fingerPrintFlowCircuitDTO.setCompleto(false);
				fingerPrintFlowCircuitDTO.setPchuida(false);
				fingerPrintFlowCircuitDTO.setPchuvuelta(true);

				return "fingerPrintResult";

			} else {
				
				AttemptstRequestDTO attemptstRequestDTO = new AttemptstRequestDTO().buildFromSimpleDtoFlowCircuit(
						fingerPrintFlowCircuitDTO.getAttemptsRequestSimpleDTO(), "", Constants.PCHU_OPCION_IDA);
				fingerPrintFlowCircuitDTO
						.setAttemptstResponseIdaDTO(renaperService.intentosDisponiblesSubmit(attemptstRequestDTO));
				fingerPrintFlowCircuitDTO
						.setAttemptstResponseIdaDTO(renaperService.intentosDisponiblesSubmit(attemptstRequestDTO));
				
				FingerPrintRequestDTO fingerPrintRequestDTO = new FingerPrintRequestDTO();
				fingerPrintRequestDTO.buildFromCircuitRequest(fingerPrintFlowCircuitDTO.getAfisrequest());

				fingerPrintRequestDTO.getAfisrequest()
						.setNumber(fingerPrintFlowCircuitDTO.getAttemptsRequestSimpleDTO().getNrodoc());

				fingerPrintFlowCircuitDTO
						.setFingerPrintResponseDTO(renaperService.identificacionPorhuella(fingerPrintRequestDTO));

				attemptstRequestDTO.setOpcion(Constants.PCHU_OPCION_VUELTA);
				attemptstRequestDTO.setRenaper(FormatUtils.completaCerosIzq(2,
						fingerPrintFlowCircuitDTO.getFingerPrintResponseDTO().getCode().length(),
						fingerPrintFlowCircuitDTO.getFingerPrintResponseDTO().getCode()));
				fingerPrintFlowCircuitDTO
						.setAttemptstResponseVueltaDTO(renaperService.intentosDisponiblesSubmit(attemptstRequestDTO));
				fingerPrintFlowCircuitDTO.setCompleto(true);
				return "fingerPrintResult";	
				
			}

			

		} catch (ServiceException e) {
			fingerPrintFlowCircuitDTO.setCode(e.getCodigo());
			fingerPrintFlowCircuitDTO.setMessage(e.getMessage());
			return "fingerPrintError";
		}

	}

}

