package ar.com.bbva.arq.renaper.apicontroller;

import java.io.IOException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ar.com.bbva.arq.renaper.config.Pdf417Configuration;
import ar.com.bbva.arq.renaper.model.BarcodeResponseDTO;
import ar.com.bbva.arq.renaper.model.Information;
import ar.com.bbva.arq.renaper.model.PersonAltaDatos;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.services.Pdf417Service;
import ar.com.bbva.arq.renaper.services.RenaperService;
import ar.com.bbva.arq.renaper.services.ServiceException;
import ar.com.bbva.arq.renaper.services.ThubanService;
import ar.com.bbva.arq.renaper.utils.PDFUtils;

@Controller
@RequestMapping(value = "/barcoderead")
public class UploadController {

	@Autowired
	private RenaperService renaperService;

	@Autowired
	Pdf417Service pdf147Service;

	@Autowired
	ThubanService thubanService;

	@Autowired
	Pdf417Configuration pdf417Configuration;

	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String infoForm(Model model) {
		model.addAttribute("information", new Information());
		return "infoFormOp";
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String infoSubmit(@RequestParam("file") List<MultipartFile> files, RedirectAttributes attributes,
			@ModelAttribute Information information, Model model) throws IOException {
		try {
			if (files.isEmpty()) {
				attributes.addFlashAttribute("message", "Por favor seleccione un documento o imagen para procesar");
				return "redirect:/barcoderead";
			}
			String numeroCliente = null;
			RenaperDataDTO renaperResponse = null;
			byte[] documentacionClienteBytes = PDFUtils.concatenarAdjuntosComoPDF(files,
					pdf417Configuration.getMegasdni(), pdf417Configuration.getMegasfile());
			BarcodeResponseDTO barcodeResponseDTO = pdf147Service.obtenerDatosDNI(documentacionClienteBytes);
			if (information.getRenaper()) {
				renaperResponse = renaperService.getRenaperDatosPersona(barcodeResponseDTO);
				PersonAltaDatos personAltaDatos = new PersonAltaDatos().buildFromRenaper(renaperResponse.getPerson(),
						barcodeResponseDTO);
			if (information.getHost() )
				numeroCliente = renaperService.actualizarDatosPersona(personAltaDatos);
			
			information.setPerson(renaperResponse.getPerson());
			information.setCode(renaperResponse.getCode());
			information.setMessage(renaperResponse.getMessage());
			information.setValid(renaperResponse.getValid());
			}
			information.setBarcodeResponseDTO(barcodeResponseDTO);			
			if (information.getThuban())
				information.setIdThuban(thubanService.publicar(documentacionClienteBytes, numeroCliente));
			return "infoResult";
		} catch (ServiceException e) {
			information.setCode(e.getCodigo());
			information.setMessage(e.getMessage());
			return "infoError";
		}

	}
}