package ar.com.bbva.arq.renaper.model;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ar.com.bbva.arq.renaper.utils.HTTPResponseCodesEnum;

/**
 * Objeto de respuesta por defecto para todas las comunicaciones
 *
 */
@JsonInclude(Include.NON_NULL)
public class RenaperResponse<T> {

	private static Logger logger = Logger.getLogger(RenaperResponse.class);

	private String statusCode;
	private String statusText;
	private String particularCode;
	private T result;

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

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getParticularCode() {
		return particularCode;
	}

	public void setParticularCode(String particularCode) {
		this.particularCode = particularCode;
	}

	/**
	 * Seteta el response en modo OK
	 * 
	 * @return el response
	 */
	public RenaperResponse<T> toStatus200OK() {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_200.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_200.getStatusText());
		this.loggingTheResponse();
		return this;
	}

	/**
	 * Setea el response en modo de error bad request
	 * 
	 * @return el response
	 */
	public RenaperResponse<T> toStatus400BadRequest() {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_400.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_400.getStatusText());
		this.loggingTheResponse();
		return this;
	}

	/**
	 * Setea el response en modo de error no unauthorized
	 * 
	 * @return el response
	 */
	public RenaperResponse<T> toStatus401Unauthorized() {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_401.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_401.getStatusText());
		this.loggingTheResponse();
		return this;
	}

	/**
	 * Setea el response en modo de error forbidden
	 * 
	 * @return el response
	 */
	public RenaperResponse<T> toStatus403Forbidden() {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_403.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_403.getStatusText());
		this.loggingTheResponse();
		return this;
	}

	/**
	 * Setea el response en modo de error internal server error
	 * 
	 * @return el response
	 */
	public RenaperResponse<T> toStatus500InternalServerError() {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_500.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_500.getStatusText());
		this.loggingTheResponse();
		return this;
	}

	/**
	 * Setea el response en modo de error, pero retorna un codigo para que la vista
	 * corrija el flujo
	 * 
	 * @param code
	 * @return
	 */
	public RenaperResponse<T> toStatus402PaymentRequired(String code) {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_402.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_402.getStatusText());
		this.particularCode = code;
		return this;
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


}
