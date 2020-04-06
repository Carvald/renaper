package ar.com.bbva.arq.esqueleto.service;

import org.springframework.stereotype.Service;

import ar.com.bbva.arq.esqueleto.model.Lista;
import ar.com.bbva.arq.esqueleto.model.Persona;
import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.itrsa.sam.TransactionException;

@Service
public class RenaperService extends AbstractSamService {

	@Override
	protected boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje) {
		return true;
	}

    public Object getRenaperDatosPersona(Persona persona) throws ServiceException {
        try {
	    	Object respuesta = null;

	        respuesta =  ejecutar(crearServiceAccessManagerContext(), "TM_SERVICIO_INICIAL.RENAPER_DATOS_PERSONA", null, null, Lista.class, true, true, null);
	        //respuesta = (Lista) ejecutar(crearServiceAccessManagerContext(), "TM_SERVICIO_INICIAL.RENAPER_DATOS_PERSONA", null, null, Lista.class, true, true, null);
	
	        return respuesta;
        } catch (TransactionException exception) {
        	throw crearErrorGenerico();
        }
    }
}
