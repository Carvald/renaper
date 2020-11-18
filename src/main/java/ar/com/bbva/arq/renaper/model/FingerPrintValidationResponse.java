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
public class FingerPrintValidationResponse {

	private static Logger logger = Logger.getLogger(FingerPrintValidationResponse.class);

	private String statusCode;
	private String statusText;
	private String particularCode;
	private String nroclie;
	private String tipdoc;
	private String nrodoc;
	private String retorno;
	private String coderr;
	private String matchScore;
	private String matchType;
	private String code;
	private String message;
	private String result;
	

	public String getNroclie() {
		return nroclie;
	}

	public void setNroclie(String nroclie) {
		this.nroclie = nroclie;
	}

	public String getTipdoc() {
		return tipdoc;
	}

	public void setTipdoc(String tipdoc) {
		this.tipdoc = tipdoc;
	}

	public String getNrodoc() {
		return nrodoc;
	}

	public void setNrodoc(String nrodoc) {
		this.nrodoc = nrodoc;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public String getCoderr() {
		return coderr;
	}

	public void setCoderr(String coderr) {
		this.coderr = coderr;
	}

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

	public String getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(String matchScore) {
		this.matchScore = matchScore;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}



}
