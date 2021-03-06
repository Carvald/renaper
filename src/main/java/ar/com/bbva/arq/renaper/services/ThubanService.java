package ar.com.bbva.arq.renaper.services;

import java.util.HashMap;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import ar.com.bbva.arq.renaper.config.ClavesConfiguration;
import ar.com.bbva.arq.renaper.model.BarcodeResponseDTO;
import ar.com.bbva.arq.renaper.model.ThubanUploadRequestDTO;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.arq.renaper.utils.HTTPResponseCodesEnum;
import ar.com.bbva.arq.renaper.utils.FechaUtils;
import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.itrsa.sam.TransactionException;

@Service
public class ThubanService extends AbstractSamService {

	private static final Logger log = LoggerFactory.getLogger(AbstractSamService.class);
	
	
	@Autowired
	ClavesConfiguration clavesConfiguration;
	
	@Autowired
	private Environment environment;
	
	@Override
	protected boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje) {
		return true;
	}

	@SuppressWarnings("unchecked")
	public String publicar(String documento,String numeroCliente,BarcodeResponseDTO barcodeResponseDTO ) {
		try {
			ThubanUploadRequestDTO thubanUploadRequestDTO = new ThubanUploadRequestDTO().build(documento,
					armarClaseDocumentalCLILegajo(numeroCliente,barcodeResponseDTO), clavesConfiguration.getUsuario(), environment.getProperty("password"),numeroCliente);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("Usuario", thubanUploadRequestDTO.getUsuario());
			parameters.put("Clave", thubanUploadRequestDTO.getClave());
			parameters.put("ClaseDocumental", thubanUploadRequestDTO.getClaseDocumental());
			parameters.put("NombreArchivo", thubanUploadRequestDTO.getNombreArchivo());
			parameters.put("ListaCampos", thubanUploadRequestDTO.getListaCampos());
			parameters.put("Documento", thubanUploadRequestDTO.getDocumento());
			@SuppressWarnings("unchecked")
			Map map = (Map) ejecutar(crearServiceAccessManagerContext(), Constants.THUBAN_UPLOAD_SERVICE, null, null,
					null, true, false, parameters);
			return (String) map.get("idthuban");
		} catch (ServiceException |
				TransactionException e) {
			if (e instanceof ServiceException) {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(),
						((ServiceException) e).getCodigo() + " " + e.getMessage());
			} else {
				throw crearExcepcion(HTTPResponseCodesEnum.STATUS_500.getStatusCode(), Constants.SERVER_FAIL_MESSAGE);
			}

		}

	}

	private String armarClaseDocumentalCLILegajo(String numeroCliente,BarcodeResponseDTO barcodeResponseDTO ) {
		StringBuilder campos = new StringBuilder();
		String periodo = "";
		String estado = "CONFIRMAR";
		campos.append("T_DOCUMENTO=0001|");
		campos.append("N_PERIODO=" + periodo + "|");
		campos.append("E_ESTADO=" + estado + "|");
		campos.append("N_CLIENTE="+numeroCliente+"|");
		campos.append("D_NOMBRE="+barcodeResponseDTO.getNombreInformado()+" "+barcodeResponseDTO.getApellidoInformado()+"|");
		campos.append("N_TIPO_DOC=00|");
		campos.append("N_DOC_CUIL="+barcodeResponseDTO.getDocumentNumber()+"|");
		campos.append("D_PREPARADOR=GPROBANK|");
		campos.append("F_EMISION=" + FechaUtils.getFechaSistema(FechaUtils.DATE_FORMAT_AAAA_MM_DD_GUION) + "|");
		campos.append("H_EMISION="
				+ FechaUtils.getFechaSistema(FechaUtils.DATE_FORMAT_HH_MM_SS).toString().replace(":", ".") + "|");
		campos.append("D_TRANSACCION=" + "APD" + "|");
		campos.append("N_SUCURSAL=100");
		return campos.toString();
	}



}
