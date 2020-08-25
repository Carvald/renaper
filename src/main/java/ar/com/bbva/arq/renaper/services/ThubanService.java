package ar.com.bbva.arq.renaper.services;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ar.com.bbva.arq.renaper.model.ThubanUploadRequestDTO;
import ar.com.bbva.arq.renaper.utils.Constants;
import ar.com.bbva.arq.renaper.utils.HTTPResponseCodesEnum;
import ar.com.bbva.arq.renaper.utils.FechaUtils;
import ar.com.bbva.soa.conectores.BbvaSoaMensaje;
import ar.com.itrsa.sam.TransactionException;

@Service
public class ThubanService extends AbstractSamService {

	@Value("${thuban.user}") 
	String user;
	@Value("${thuban.pass}") 
	String pass;
	
	@Override
	protected boolean noEnmascararEnErrorGenerico(BbvaSoaMensaje mensaje) {
		return true;
	}

	@SuppressWarnings("unchecked")
	public String publicar(byte[] documento,String numeroCliente) {
		try {
			ThubanUploadRequestDTO thubanUploadRequestDTO = new ThubanUploadRequestDTO().build(documento,
					armarClaseDocumentalCLILegajo(numeroCliente), user, pass,numeroCliente);
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

	private String armarClaseDocumentalCLILegajo(String numeroCliente) {
		StringBuilder campos = new StringBuilder();
		String periodo = "";
		String estado = "CONFIRMAR";
		campos.append("T_DOCUMENTO=0001|");
		campos.append("N_PERIODO=" + periodo + "|");
		campos.append("E_ESTADO=" + estado + "|");
		campos.append("N_CLIENTE="+numeroCliente+"|");
		campos.append("D_NOMBRE=CLIENTE PRUEBA|");
		campos.append("N_TIPO_DOC=00|");
		campos.append("N_DOC_CUIL=201627685|");
		campos.append("D_PREPARADOR=GPROBANK|");
		campos.append("F_EMISION=" + FechaUtils.getFechaSistema(FechaUtils.DATE_FORMAT_AAAA_MM_DD_GUION) + "|");
		campos.append("H_EMISION="
				+ FechaUtils.getFechaSistema(FechaUtils.DATE_FORMAT_HH_MM_SS).toString().replace(":", ".") + "|");
		campos.append("D_TRANSACCION=" + "APD" + "|");
		campos.append("N_SUCURSAL=100");
		return campos.toString();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	

}
