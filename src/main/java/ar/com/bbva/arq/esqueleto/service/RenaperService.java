package ar.com.bbva.arq.esqueleto.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import ar.com.bbva.arq.esqueleto.model.RenaperResponse;
import ar.com.bbva.arq.esqueleto.model.Response;
import ar.com.bbva.arq.esqueleto.model.Information;
import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.itrsa.sam.TransactionException;

@Service
public class RenaperService extends AbstractSamService {

	@Override
	protected boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje) {
		return true;
	}

	public ModelAndView getRenaperDatosPersona(Information information) throws ServiceException {
		try {
			ModelAndView mav = new ModelAndView();
			Response respuestaWs = null;
			information.setOrder(completaCerosIzq(11,information.getOrder().length(),information.getOrder()));
			respuestaWs = (Response) ejecutar(crearServiceAccessManagerContext(),
					"TM_SERVICIO_INICIAL.RENAPER_DATOS_PERSONA", information, Information.class, Response.class, true, false, null);
			RenaperResponse consulta= new RenaperResponse().build(respuestaWs.getMensaje());
			mav.addObject("information",consulta);
			mav.setViewName(consulta.getCode().equals("10001")?"successContents":"errorContents");
			return mav;
		} catch (TransactionException exception) {
			throw crearErrorGenerico();
		}
	}
	
	public static String completaCerosIzq(int pTamMax, int pLargoCadena, String pCadena) {
		return completaCharIzq(pTamMax, pLargoCadena, pCadena, "0");
	}

	public static String completaCharIzq(int pTamMax, int pLargoCadena, String pCadena, String Char) {
		for (int i = pLargoCadena; i < pTamMax; i++) {
			pCadena = Char + pCadena;
		}
		return pCadena;
	}
}
