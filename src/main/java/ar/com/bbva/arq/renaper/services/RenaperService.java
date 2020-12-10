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
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitResponseDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintCircuitUnifiedRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintRequestDTO;
import ar.com.bbva.arq.renaper.model.FingerPrintResponseDTO;
import ar.com.bbva.arq.renaper.model.OrquestarDataDTO;
import ar.com.bbva.arq.renaper.model.RenaperDataDTO;
import ar.com.bbva.arq.renaper.model.UpdateClientDataDTO;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.arq.renaper.utils.FormatUtils;
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
			if (updateClientDataDTO.getFlag() == null || updateClientDataDTO.getFlag().equals("")) {
				PersonAltaDatos personAltaDatos = new PersonAltaDatos().buildFromRenaper(renaperDataDTO.getPerson(),
						updateClientDataDTO);
				esbResponse = (EsbResponse) ejecutar(crearServiceAccessManagerContext(),
						Constants.UPDATE_DATA_ESB_SERVICE, personAltaDatos, PersonAltaDatos.class, EsbResponse.class,
						true, false, null);
				if (!esbResponse.getCodigoRetorno().equals(Constants.SUCCESS_UPDATE)) {
					throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(),
							Constants.UPDATE_FAIL_MESSAGE + " - codigo retornado: " + esbResponse.getCodigoRetorno()
									+ " ref:" + esbResponse.getCodigoError());
				} else {
					altaDatosResponseDTO.setAltaDatosResult(Constants.SUCCESS_UPDATE);
					altaDatosResponseDTO.setNumeroCliente(esbResponse.getNumeroCliente());

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
			attemptstRequestDTO.setNroclie(FormatUtils.completaCerosIzq(8, attemptstRequestDTO.getNroclie().length(),
					attemptstRequestDTO.getNroclie()));
			attemptstRequestDTO.setNrodoc((FormatUtils.completaCerosIzq(13, attemptstRequestDTO.getNrodoc().length(),
					attemptstRequestDTO.getNrodoc())));
			attemptstResponseDTO = (AttemptstResponseDTO) ejecutar(crearServiceAccessManagerContext(),
					Constants.RENAPER_FINGER_TRX_ESB_SERVICE, attemptstRequestDTO, AttemptstRequestDTO.class,
					AttemptstResponseDTO.class, true, false, null);
			return attemptstResponseDTO;

		} catch (TransactionException | ServiceException exception) {
			if (exception instanceof ServiceException) {
				throw crearExcepcion(Constants.ERROR_CON_PCHU, exception.getMessage());
			} else {
				throw crearExcepcion(Constants.ERROR_CON_PCHU, Constants.SERVER_FAIL_MESSAGE);
			}
		}
	}

	public AttemptstResponseDTO intentosDisponiblesSubmit(AttemptstRequestDTO attemptstRequestDTO) {
		try {
			AttemptstResponseDTO attemptstResponseDTO;
			attemptstResponseDTO = (AttemptstResponseDTO) ejecutar(crearServiceAccessManagerContext(),
					Constants.RENAPER_FINGER_TRX_ESB_SERVICE, attemptstRequestDTO, AttemptstRequestDTO.class,
					AttemptstResponseDTO.class, true, false, null);
			return attemptstResponseDTO;

		} catch (TransactionException exception) {
			throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
		}
	}

	public FingerPrintCircuitResponseDTO actualizarIntentosIdentificacionPorHuella(
			FingerPrintCircuitRequestDTO fingerPrintCircuitRequestDTO) {
		FingerPrintCircuitResponseDTO fingerPrintCircuitResponseDTO = new FingerPrintCircuitResponseDTO();
		try {
			FingerPrintResponseDTO fingerPrintResponseDTO;
			FingerPrintRequestDTO fingerPrintRequestDTO = new FingerPrintRequestDTO();
			fingerPrintRequestDTO.buildFromRequestComposed(fingerPrintCircuitRequestDTO);
			fingerPrintResponseDTO = (FingerPrintResponseDTO) ejecutar(crearServiceAccessManagerContext(),
					Constants.RENAPER_FINGER_AUTH_ESB_SERVICE, fingerPrintRequestDTO, FingerPrintRequestDTO.class,
					FingerPrintResponseDTO.class, true, false, null);
			AttemptstResponseDTO attemptstResponseDTO;
			AttemptstRequestDTO attemptstRequestDTO = new AttemptstRequestDTO().buildFromSimpleDto(
					fingerPrintCircuitRequestDTO.getAttemptsRequestSimpleDTO(), fingerPrintResponseDTO.getCode(),
					Constants.PCHU_OPCION_VUELTA);
			attemptstResponseDTO = (AttemptstResponseDTO) ejecutar(crearServiceAccessManagerContext(),
					Constants.RENAPER_FINGER_TRX_ESB_SERVICE, attemptstRequestDTO, AttemptstRequestDTO.class,
					AttemptstRequestDTO.class, true, false, null);

			fingerPrintCircuitResponseDTO.setVueltaAttemptstResponseDTO(attemptstResponseDTO);
			fingerPrintCircuitResponseDTO.setFingerPrintResponseDTO(fingerPrintResponseDTO);
			return fingerPrintCircuitResponseDTO;

		} catch (TransactionException exception) {
			throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
		}

	}

	public FingerPrintCircuitResponseDTO actualizarIntentosIdentificacionPorHuellaUnificado(
			FingerPrintCircuitUnifiedRequestDTO fingerPrintCircuitUnifiedRequestDTO) {
		FingerPrintCircuitResponseDTO fingerPrintCircuitResponseDTO = new FingerPrintCircuitResponseDTO();
		FingerPrintResponseDTO fingerPrintResponseDTO = new FingerPrintResponseDTO();
		try {
			FingerPrintRequestDTO fingerPrintRequestDTO = new FingerPrintRequestDTO();
			fingerPrintRequestDTO.buildFromRequestComposed(fingerPrintCircuitUnifiedRequestDTO);
			fingerPrintResponseDTO = (FingerPrintResponseDTO) ejecutar(crearServiceAccessManagerContext(),
					Constants.RENAPER_FINGER_AUTH_ESB_SERVICE, fingerPrintRequestDTO, FingerPrintRequestDTO.class,
					FingerPrintResponseDTO.class, true, false, null);

			if (fingerPrintResponseDTO.getCode().equals(Constants.WRONG_FINGERPRINT_HEIGHT))
				throw crearExcepcion(Constants.ERROR_CON_RENAPER, fingerPrintResponseDTO.getMessage());

		} catch (TransactionException | ServiceException exception) {
			if (exception instanceof ServiceException) {
				if (((ServiceException) exception).getCodigo().equals(Constants.WRONG_INPUT_DATA)) {
					fingerPrintResponseDTO.setMatchType(Constants.NO_HIT);
				} else {
					throw crearExcepcion(Constants.ERROR_CON_RENAPER, exception.getMessage());
				}
			} else {
				throw crearExcepcion(Constants.ERROR_CON_RENAPER, Constants.SERVER_FAIL_MESSAGE);
			}
		}
		try {
			AttemptstResponseDTO attemptstResponseDTO;
			AttemptstRequestDTO attemptstRequestDTO = fingerPrintCircuitUnifiedRequestDTO.getAttemptsRequestDTO();
			if (fingerPrintResponseDTO.getMatchType().equals(Constants.HIT))
				attemptstRequestDTO.setRenaper(Constants.SUCCESS_FINGERPRINT_MATCH);
			else if (fingerPrintResponseDTO.getMatchType().equals(Constants.NO_HIT))
				attemptstRequestDTO.setRenaper(Constants.WRONG_FINGERPRINT_MATCH);

			attemptstResponseDTO = (AttemptstResponseDTO) ejecutar(crearServiceAccessManagerContext(),
					Constants.RENAPER_FINGER_TRX_ESB_SERVICE, attemptstRequestDTO, AttemptstRequestDTO.class,
					AttemptstResponseDTO.class, true, false, null);

			fingerPrintCircuitResponseDTO.setVueltaAttemptstResponseDTO(attemptstResponseDTO);
			fingerPrintCircuitResponseDTO.setFingerPrintResponseDTO(fingerPrintResponseDTO);
			return fingerPrintCircuitResponseDTO;

		} catch (TransactionException | ServiceException exception) {
			if (exception instanceof ServiceException) {
				throw crearExcepcion(Constants.ERROR_CON_PCHU, exception.getMessage());
			} else {
				throw crearExcepcion(Constants.ERROR_CON_PCHU, Constants.SERVER_FAIL_MESSAGE);
			}
		}

	}

	public AltaDatosResponseDTO orquestarDatosPersonaConDataRenaper(OrquestarDataDTO orquestarDataDTO) {
		AltaDatosResponseDTO altaDatosResponseDTO = new AltaDatosResponseDTO();
		try {
			EsbResponse esbResponse;
			PersonAltaDatos personAltaDatos = new PersonAltaDatos().buildFromRenaper(orquestarDataDTO.getPerson(),
					orquestarDataDTO);
			esbResponse = (EsbResponse) ejecutar(crearServiceAccessManagerContext(), Constants.UPDATE_DATA_ESB_SERVICE,
					personAltaDatos, PersonAltaDatos.class, EsbResponse.class, true, false, null);
			if (!esbResponse.getCodigoRetorno().equals(Constants.SUCCESS_UPDATE)) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_400.getStatusCode(),
						Constants.UPDATE_FAIL_MESSAGE + " - codigo retornado: " + esbResponse.getCodigoRetorno()
								+ " ref:" + esbResponse.getCodigoError());
			} else {
				altaDatosResponseDTO.setAltaDatosResult(Constants.SUCCESS_UPDATE);
				altaDatosResponseDTO.setNumeroCliente(esbResponse.getNumeroCliente());
			}
		} catch (TransactionException exception) {
			throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);

		}
		return altaDatosResponseDTO;
	}

}
