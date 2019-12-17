package ar.com.bbva.arq.esqueleto.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.com.bbva.arq.esqueleto.model.Lista;
import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.itrsa.sam.TransactionException;

@Service
public class TablasCorporativasService extends AbstractSamService {

	@Override
	protected boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje) {
		return true;
	}

    public Lista getCodigosTipoDoc() throws ServiceException {
        try {
	    	Lista respuesta = null;
	
	        Map additionalParameters = new HashMap();
	
	        additionalParameters.put("filtrarPorRegexp", "^'(?!PASAPORTE).{71}00001.*");
	
	        respuesta = (Lista) ejecutar(crearServiceAccessManagerContext(), "TM_SERVICIO_INICIAL.CONSULTA_CODIGOS_TIPO_DOCUMENTO", null, null, Lista.class, true, true, additionalParameters);
	
	        return respuesta;
        } catch (TransactionException exception) {
        	throw crearErrorGenerico();
        }
    }
}
