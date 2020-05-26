package ar.com.bbva.arq.renaper.services;

import org.springframework.stereotype.Service;

import ar.com.bbva.arq.renaper.model.PersonRequestDTO;
import ar.com.bbva.arq.renaper.model.PersonAltaDatos;
import ar.com.bbva.arq.renaper.model.EsbResponse;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.model.UpdateClientDataDTO;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.arq.renaper.utils.HTTPResponseCodesEnum;
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
			RenaperDataDTO renaperData = new RenaperDataDTO().build(esbResponse);
			if (!renaperData.getValid().equals(Constants.VIGENTE_RENAPER)
					|| !renaperData.getCode().equals(Constants.SUCCESS_RENAPER_CODE)) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), Constants.GET_DATA_FAIL_MESSAGE);
			} else {
				return renaperData;
			}
		} catch (TransactionException exception) {
			throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(),
					Constants.SERVER_FAIL_MESSAGE);
		}
	}

	public String orquestarDatosPersona(UpdateClientDataDTO updateClientDataDTO) throws ServiceException {
		try {
			EsbResponse esbResponse;
			RenaperDataDTO renaperDataDTO = getRenaperDatosPersona(updateClientDataDTO.getRenaperPersonRequest());
			PersonAltaDatos personAltaDatos = new PersonAltaDatos().buildFromRenaper(renaperDataDTO.getPerson(),
					updateClientDataDTO);
			esbResponse = (EsbResponse) ejecutar(crearServiceAccessManagerContext(), Constants.UPDATE_DATA_ESB_SERVICE,
					personAltaDatos, PersonAltaDatos.class, EsbResponse.class, true, false, null);
			if (!esbResponse.getCodigoRetorno().equals(Constants.SUCCESS_UPDATE)) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), Constants.UPDATE_FAIL_MESSAGE+"- "+esbResponse.getCodigoError());
			} else {
				return Constants.SUCCESS_MESSAGE;
			}
		} catch (TransactionException exception) {
			throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(),
					Constants.SERVER_FAIL_MESSAGE);
		}
	}

}
