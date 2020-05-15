package ar.com.bbva.arq.renaper.services;

import org.springframework.stereotype.Service;

import ar.com.bbva.arq.renaper.model.PersonRequestDTO;
import ar.com.bbva.arq.renaper.model.PersonAltaDatos;
import ar.com.bbva.arq.renaper.model.EsbResponse;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.model.UpdateClientDataDTO;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.itrsa.sam.TransactionException;

@Service
public class RenaperService extends AbstractSamService {

	@Override
	protected boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje) {
		return true;
	}

	public RenaperDataDTO getRenaperDatosPersona(PersonRequestDTO person) throws ServiceException {
		try {
			EsbResponse esbResponse;
			esbResponse = (EsbResponse) ejecutar(crearServiceAccessManagerContext(), Constants.RENAPER_ESB_SERVICE,
					person, PersonRequestDTO.class, EsbResponse.class, true, false, null);
			return new RenaperDataDTO().build(esbResponse);
		} catch (TransactionException exception) {
			throw crearErrorGenerico();
		}
	}

	public String orquestarDatosPersona(UpdateClientDataDTO updateClientDataDTO) throws ServiceException {
		try {
			EsbResponse esbResponse = new EsbResponse();
			RenaperDataDTO renaperDataDTO = getRenaperDatosPersona(updateClientDataDTO.getRenaperPersonRequest());
			PersonAltaDatos personAltaDatos = new PersonAltaDatos().buildFromRenaper(renaperDataDTO.getPerson());
			if (renaperDataDTO.getCode().equals(Constants.SUCCESS_RENAPER_CODE)
					&& renaperDataDTO.getValid().equals(Constants.VIGENTE_RENAPER)) {
				esbResponse = (EsbResponse) ejecutar(crearServiceAccessManagerContext(),
						Constants.UPDATE_DATA_ESB_SERVICE, personAltaDatos, PersonAltaDatos.class, EsbResponse.class,
						true, false, null);
			}
			return esbResponse.getCodigoError().equals(Constants.SUCCESS_RENAPER_CODE)?Constants.SUCCESS_MESSAGE:Constants.UPDATE_FAIL_MESSAGE
					+esbResponse.getCodigoRetorno();
		} catch (TransactionException exception) {
			throw crearErrorGenerico();
		}
	}

}
