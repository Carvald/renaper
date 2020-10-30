package ar.com.bbva.arq.renaper.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import ar.com.bbva.arq.renaper.model.PersonRequestDTO;
import ar.com.bbva.arq.renaper.model.PersonAltaDatos;
import ar.com.bbva.arq.renaper.model.AltaDatosResponseDTO;
import ar.com.bbva.arq.renaper.model.AttemptstRequestDTO;
import ar.com.bbva.arq.renaper.model.AttemptstResponseDTO;
import ar.com.bbva.arq.renaper.model.BarcodeResponseDTO;
import ar.com.bbva.arq.renaper.model.EsbResponse;
import ar.com.bbva.arq.renaper.model.FingerPrintRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintResponseDTO;
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
			ModelAndView mav = new ModelAndView();
			EsbResponse esbResponse;
			esbResponse = (EsbResponse) ejecutar(crearServiceAccessManagerContext(), Constants.RENAPER_ESB_SERVICE,
					person, PersonRequestDTO.class, EsbResponse.class, true, false, null);
			RenaperDataDTO renaperData = new RenaperDataDTO().build(esbResponse);
			if (!renaperData.getValid().equals(Constants.VIGENTE_RENAPER)
					|| !renaperData.getCode().equals(Constants.SUCCESS_RENAPER_CODE)) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), Constants.GET_DATA_FAIL_MESSAGE);
			} else {
				mav.addObject("information", renaperData);
				return renaperData;
			}
		} catch (TransactionException | ServiceException exception) {
			if (exception instanceof ServiceException) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), exception.getMessage());
			} else {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
			}
		}
	}

	public RenaperDataDTO getRenaperDatosPersona(BarcodeResponseDTO person) throws ServiceException {
		try {
			ModelAndView mav = new ModelAndView();
			EsbResponse esbResponse;
			esbResponse = (EsbResponse) ejecutar(crearServiceAccessManagerContext(), Constants.RENAPER_ESB_SERVICE,
					person, BarcodeResponseDTO.class, EsbResponse.class, true, false, null);
			RenaperDataDTO renaperData = new RenaperDataDTO().build(esbResponse);
			if (!renaperData.getValid().equals(Constants.VIGENTE_RENAPER)
					|| !renaperData.getCode().equals(Constants.SUCCESS_RENAPER_CODE)) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), Constants.GET_DATA_FAIL_MESSAGE);
			} else {
				mav.addObject("information", renaperData);
				return renaperData;
			}
		} catch (TransactionException | ServiceException exception) {
			if (exception instanceof ServiceException) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), exception.getMessage());
			} else {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
			}
		}
	}

	public AltaDatosResponseDTO orquestarDatosPersona(UpdateClientDataDTO updateClientDataDTO) throws ServiceException {
		AltaDatosResponseDTO altaDatosResponseDTO = new AltaDatosResponseDTO();
		try {
			EsbResponse esbResponse;
			RenaperDataDTO renaperDataDTO = getRenaperDatosPersona(updateClientDataDTO.getRenaperPersonRequest());
			altaDatosResponseDTO.setRenaperDataDTO(renaperDataDTO);
			if (updateClientDataDTO.getFlag() == null) {
				PersonAltaDatos personAltaDatos = new PersonAltaDatos().buildFromRenaper(renaperDataDTO.getPerson(),
						updateClientDataDTO);
				esbResponse = (EsbResponse) ejecutar(crearServiceAccessManagerContext(),
						Constants.UPDATE_DATA_ESB_SERVICE, personAltaDatos, PersonAltaDatos.class, EsbResponse.class,
						true, false, null);
				if (!esbResponse.getCodigoRetorno().equals(Constants.SUCCESS_UPDATE)) {
					throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(),
							Constants.UPDATE_FAIL_MESSAGE + " - " + esbResponse.getCodigoError());
				} else {
					altaDatosResponseDTO.setAltaDatosResult(Constants.SUCCESS_UPDATE);

				}
			}
		} catch (TransactionException | ServiceException exception) {
			if (exception instanceof ServiceException) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), exception.getMessage());
			} else {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
			}
		}
		return altaDatosResponseDTO;
	}

	public String actualizarDatosPersona(PersonAltaDatos personAltaDatos) throws ServiceException {
		try {
			EsbResponse esbResponse;
			esbResponse = (EsbResponse) ejecutar(crearServiceAccessManagerContext(), Constants.UPDATE_DATA_ESB_SERVICE,
					personAltaDatos, PersonAltaDatos.class, EsbResponse.class, true, false, null);
			if (!esbResponse.getCodigoRetorno().equals(Constants.SUCCESS_UPDATE)) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(),
						Constants.UPDATE_FAIL_MESSAGE + " - " + esbResponse.getCodigoError());
			} else {
				return esbResponse.getNumeroCliente();
			}
		} catch (TransactionException | ServiceException exception) {
			if (exception instanceof ServiceException) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(), exception.getMessage());
			} else {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
			}
		}

	}

	public FingerPrintResponseDTO identificacionPorhuella(FingerPrintRequestDTO fingerPrintDataDTO) {
		try {
			FingerPrintResponseDTO fingerPrintResponseDTO;
			fingerPrintResponseDTO = (FingerPrintResponseDTO) ejecutar(crearServiceAccessManagerContext(),
					Constants.RENAPER_FINGER_AUTH_ESB_SERVICE, fingerPrintDataDTO, FingerPrintRequestDTO.class,
					FingerPrintResponseDTO.class, true, false, null);
			if (fingerPrintResponseDTO.getCode().equals("0")) {
				return fingerPrintResponseDTO;
			} else {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(),
						fingerPrintResponseDTO.getMessage());
			}
		} catch (TransactionException exception) {
			throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
		}
		
	}



	public AttemptstResponseDTO intentosDisponibles(AttemptstRequestDTO attemptstRequestDTO) {
		try {
			AttemptstResponseDTO attemptstResponseDTO;
			attemptstResponseDTO = (AttemptstResponseDTO) ejecutar(crearServiceAccessManagerContext(),
					Constants.RENAPER_FINGER_TRX_ESB_SERVICE, attemptstRequestDTO, AttemptstRequestDTO.class,
					FingerPrintResponseDTO.class, true, false, null);
			return attemptstResponseDTO;
		} catch (TransactionException exception) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
			}
		}

	}


