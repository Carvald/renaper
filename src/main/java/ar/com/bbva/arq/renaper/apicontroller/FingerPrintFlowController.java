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
			AttemptstRequestDTO attemptstRequestDTO = new AttemptstRequestDTO().buildFromSimpleDtoFlowCircuit(
					fingerPrintFlowCircuitDTO.getAttemptsRequestSimpleDTO(), "", Constants.PCHU_OPCION_IDA);
			
			/*AttemptstResponseDTO atr= new AttemptstResponseDTO();
			atr.setCoderr("e1111");
			atr.setRetorno("retorno code");*/
			fingerPrintFlowCircuitDTO
					.setAttemptstResponseIdaDTO(renaperService.intentosDisponiblesSubmit(attemptstRequestDTO));
			
			fingerPrintFlowCircuitDTO
			.setAttemptstResponseIdaDTO(atr);
			FingerPrintRequestDTO fingerPrintRequestDTO = new FingerPrintRequestDTO();
			fingerPrintRequestDTO.buildFromCircuitRequest(fingerPrintFlowCircuitDTO.getAfisrequest());
			/*
			 * fingerPrintRequestDTO.getAfisrequest()
			 * .setNumber(fingerPrintFlowCircuitDTO.getAttemptsRequestSimpleDTO().getNrodoc(
			 * ));
			 */
			fingerPrintRequestDTO.getAfisrequest().setNumber("17396778");
			fingerPrintFlowCircuitDTO
					.setFingerPrintResponseDTO(renaperService.identificacionPorhuella(fingerPrintRequestDTO));

			attemptstRequestDTO.setOpcion(Constants.PCHU_OPCION_VUELTA);
			attemptstRequestDTO.setRenaper(FormatUtils.completaCerosIzq(2,
					fingerPrintFlowCircuitDTO.getFingerPrintResponseDTO().getCode().length(),
					fingerPrintFlowCircuitDTO.getFingerPrintResponseDTO().getCode()));
			fingerPrintFlowCircuitDTO
					.setAttemptstResponseVueltaDTO(renaperService.intentosDisponiblesSubmit(attemptstRequestDTO));
			/*fingerPrintFlowCircuitDTO
			.setAttemptstResponseVueltaDTO(atr);*/

			return "fingerPrintResult";
		} catch (ServiceException e) {
			fingerPrintFlowCircuitDTO.setCode(e.getCodigo());
			fingerPrintFlowCircuitDTO.setMessage(e.getMessage());
			return "fingerPrintError";
		}

	}
}