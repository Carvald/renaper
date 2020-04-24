package ar.com.bbva.arq.renaper.services;


import org.springframework.stereotype.Service;

import ar.com.bbva.arq.renaper.model.Person;
import ar.com.bbva.arq.renaper.model.PersonAltaDatos;
import ar.com.bbva.arq.renaper.model.RawResponse;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.itrsa.sam.TransactionException;

@Service
public class RenaperService extends AbstractSamService {

	@Override
	protected boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje) {
		return true;
	}

	public RenaperDataDTO getRenaperDatosPersona(Person person) throws ServiceException {
		try {			
			RawResponse respuestaWs = null;
			respuestaWs = (RawResponse) ejecutar(crearServiceAccessManagerContext(),
					"TM_SERVICIO_INICIAL.RENAPER_DATOS_PERSONA", person, Person.class, RawResponse.class, true, false, null);
			return  new RenaperDataDTO().build(respuestaWs);			
		} catch (TransactionException exception) {
			throw crearErrorGenerico();
		}
	}
	
	public RawResponse orquestarDatosPersona(Person person) throws ServiceException {
		try {			
			RawResponse respuestaWs = null;
			RenaperDataDTO renaperDataDTO =getRenaperDatosPersona(person);
			PersonAltaDatos personAltaDatos=new PersonAltaDatos().buildFromRenaper(renaperDataDTO.getPerson());
			respuestaWs = (RawResponse) ejecutar(crearServiceAccessManagerContext(),
					"TM_SERVICIO_INICIAL.ALTA_MODIFICACION_CLIENTES_DESDE_RENAPER ", personAltaDatos, PersonAltaDatos.class, RawResponse.class, true, false, null);
			return respuestaWs;			
		} catch (TransactionException exception) {
			throw crearErrorGenerico();
		}
	}


}
