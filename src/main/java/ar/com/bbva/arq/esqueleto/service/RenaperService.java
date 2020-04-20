package ar.com.bbva.arq.esqueleto.service;

import org.springframework.stereotype.Service;

import ar.com.bbva.arq.esqueleto.model.RenaperResponse;
import ar.com.bbva.arq.esqueleto.model.Response;
import ar.com.bbva.arq.esqueleto.model.Person;
import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.itrsa.sam.TransactionException;

@Service
public class RenaperService extends AbstractSamService {

	@Override
	protected boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje) {
		return true;
	}

	public RenaperResponse getRenaperDatosPersona(Person person) throws ServiceException {
		try {			
			Response respuestaWs = null;
			respuestaWs = (Response) ejecutar(crearServiceAccessManagerContext(),
					"TM_SERVICIO_INICIAL.RENAPER_DATOS_PERSONA", person, Person.class, Response.class, true, false, null);
			return  new RenaperResponse().build(respuestaWs.getMensaje());
			
		} catch (TransactionException exception) {
			throw crearErrorGenerico();
		}
	}
	
	
}
