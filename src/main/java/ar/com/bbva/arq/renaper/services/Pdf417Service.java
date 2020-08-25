package ar.com.bbva.arq.renaper.services;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ar.com.bbva.arq.renaper.config.Pdf417Configuration;
import ar.com.bbva.arq.renaper.model.BarcodeRequestDTO;
import ar.com.bbva.arq.renaper.model.BarcodeResponseDTO;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.arq.renaper.utils.HTTPResponseCodesEnum;
import ar.com.bbva.arq.renaper.utils.ImagenesUtils;
import ar.com.bbva.arq.renaper.utils.PDFUtils;
import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.itrsa.sam.TransactionException;

@Service
public class Pdf417Service extends AbstractSamService {

	@Value("${pdf417.dni.max.size.kb}")
	private static String maxSizePdf417;
	
	@Autowired
	Pdf417Configuration pdf417Configuration;

	@Override
	protected boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje) {
		return true;
	}

	public BarcodeResponseDTO obtenerDatosDNI(byte[] doc) {
		byte[] docBytes;
		
		try {
			docBytes = PDFUtils.formatearImagenesPDFParaPDF417(doc,pdf417Configuration.getMaximagenes());
		} catch (ServiceException e) {
			throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), e.getMessage());
		}

		try {
			if (docBytes.length > Integer.valueOf(pdf417Configuration.getKb()) * 1024)
				docBytes = ImagenesUtils.comprimirImagen(docBytes);
			String b64Dni = Base64.getEncoder().encodeToString(docBytes);
			BarcodeRequestDTO barcodeRequestDTO = new BarcodeRequestDTO().build(b64Dni);
			@SuppressWarnings("unchecked")
			Map map = (Map) ejecutar(crearServiceAccessManagerContext(), Constants.BARCODE_READ_ESB_SERVICE,
					barcodeRequestDTO, BarcodeRequestDTO.class, null, true, false, null);
			BarcodeResponseDTO renaperData = new BarcodeResponseDTO().buildFrommBarcodeRead(map);
			return renaperData;
		} catch (ServiceException | TransactionException e) {
			if (e instanceof ServiceException) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), ((ServiceException) e).getCodigo() + " " + e.getMessage());
			} else {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
			}

		}
	}

}
