package ar.com.bbva.arq.renaper.model;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



/**
 * Objeto de respuesta por defecto para todas las comunicaciones
 *
 */
@JsonInclude(Include.NON_NULL)
public class OrquestadorResponse {

	private static Logger logger = Logger.getLogger(OrquestadorResponse.class);

	private String statusCode;
	private String statusText;
	private String particularCode;
	private YQEFResponseDTO yqefResponseDTO;
	private FormularioOrqResponseDTO FormularioOrqResponseDTO; 
	private String message;
	private String result;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	

	public String getParticularCode() {
		return particularCode;
	}

	public void setParticularCode(String particularCode) {
		this.particularCode = particularCode;
	}

	

	@Override
	public String toString() {
		// Separo el resultado, porque si es null, el json que genera la respuesta no es
		// valido.
		String resultString = (this.result != null) ? ",\"result\":{\"" + this.result + "\"}" : "";
		return "{\"statusCode\":\"" + this.statusCode + "\",\"statusText\":\"" + this.statusText + "\"" + resultString + "}";
	}

	/**
	 * Loguea la respuesta del servicio junto con la uri
	 */
	public void loggingTheResponse() {
		logger.info(MessageFormat.format("REST-RESPONSE: status:{0} - uri:{1}", this.getStatusCode(), getRequestUri()));
	}
	
	public static String getRequestUri() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		return request.getRequestURI();
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public YQEFResponseDTO getYqefResponseDTO() {
		return yqefResponseDTO;
	}

	public void setYqefResponseDTO(YQEFResponseDTO yqefResponseDTO) {
		this.yqefResponseDTO = yqefResponseDTO;
	}

	public FormularioOrqResponseDTO getFormularioOrqResponseDTO() {
		return FormularioOrqResponseDTO;
	}

	public void setFormularioOrqResponseDTO(FormularioOrqResponseDTO formularioOrqResponseDTO) {
		FormularioOrqResponseDTO = formularioOrqResponseDTO;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}





}
