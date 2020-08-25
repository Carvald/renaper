package ar.com.bbva.arq.renaper.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;


/**
 * Clase utilitaria para el manejo de fechas
 * 
 * @author squintanilla
 *
 */
public abstract class FechaUtils {
	public static final String DATE_FORMAT_DD = "dd";
	public static final String DATE_FORMAT_MM = "MM";
	public static final String DATE_FORMAT_AAAA = "yyyy";
	public static final String DATE_FORMAT_AAAAMMDD = "yyyyMMdd";
	public static final String DATE_FORMAT_DEFAULT = "dd/MM/yyyy";
	public static final String DATE_FORMAT_AAAA_MM_DD = "yyyy/MM/dd";
	public static final String DATE_FORMAT_AAAA_MM_DD_GUION = "yyyy-MM-dd";
	public static final String DATE_FORMAT_DD_MM_AAAA_GUION = "dd-MM-yyyy";
	public static final String DATE_FORMAT_DD_MM_AAAA_HHMMSS = "dd/MM/yyyy HH:mm:ss";
	public static final String DATE_FORMAT_TIMESTAMP = "dd/MM/yyyy HH:mm:ss.SSS";
	public static final String DATE_FORMAT_TIMESTAMP_YYYY_MM_DD = "yyyy-MM-dd'T'HHmmss";
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_DDMMAAAA = "ddMMyyyy";
	public static final String DATE_FORMAT_AAAAMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss.0";
	public static final String DATE_FORMAT_DD_MM_AA = "dd/MM/yy";
	public static final String DATE_FORMAT_DD_MM_AA_GUION = "dd-MM-yy";
	public static final String DATE_FORMAT_YYYYMMDDHH_MM_SS = "yyyyMMdd HH:mm:ss";
	public static final String DATE_FORMAT_MM_YYYY = "MM/yyyy";
	public static final String DATE_FORMAT_MMYYYY = "MMyyyy";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSSMM = "yyyyMMddHHmmssSS";
	public static final String DATE_FORMAT_HHMMSSMMMMMM = "HHmmssSSSSSS";
	public static final String DATE_FORMAT_HHMM = "HHmm";
	public static final String DATE_FORMAT_HHMMSS = "HHmmss";
	public static final String DATE_FORMAT_HH_MM_SS = "HH:mm:ss";
	public static final String DATE_FORMAT_HH_MM_SS_THUBAN = "HH.mm.ss";
	public static final String DATE_FORMAT_TIMESTAM_AAAA_MM_DD_HHMMSS = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATE_FORMAT_AAAA_MM_DD_HHMMSSMMM_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String DATE_FORMAT_AAAA_MM_DD_HHMMSSMMM_COELSA = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String DATE_FORMAT_DAY_NUMBER_WEEK = "u";
	public static final String DATE_FORMAT_MMYY = "MMyy";
	public static final String DATE_FORMAT_TIMESTAMP_YYYY_MM_DD_SIN_GUION = "yyyyMMdd'T'HHmm";

	/**
	 * Dada una fecha en formato string y un formato nos la retorna en Date
	 * 
	 * @param fecha
	 * @param formato
	 * @return
	 * @throws Exception 
	 */
	public static Date fechaStringToDate(String fecha, String formato) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		Date fechaFormateada = null;
		try {
			if (StringUtils.isNotBlank(fecha)) {
				fechaFormateada = formatter.parse(fecha);
			}
		} catch (ParseException e) {
			throw new Exception();
		}
		return fechaFormateada;
	}

	/**
	 * Convierte un objeto Date a un formato de fecha pasado como parametro.
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateByFormat(Date date, String format) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * Convierte un String a un formato de fecha pasado como parametro.
	 * 
	 * @param fecha
	 * @param format
	 * @return
	 * @throws Exception 
	 */
	public static String formatStringByFormat(String fecha, String format) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date fechaFormateada = null;
		try {
			if (StringUtils.isNotBlank(fecha)) {
				fechaFormateada = formatter.parse(fecha);
			}
		} catch (ParseException e) {
			throw new Exception();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(fechaFormateada);
	}

	/**
	 * @param formato
	 * @return
	 */
	public static String getFechaSistema(String formato) {
		return getFechaSistema(formato, 0);
	}

	/**
	 * Devuelve un String con la fecha del sistema desplazada <b>offset</b> dias para adelante con el <b>formato</b> indicada
	 * 
	 * @param formato
	 * @param offset
	 * @return
	 */
	public static String getFechaSistema(String formato, int offset) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		Date d1 = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d1);
		calendar.add(Calendar.DATE, offset);
		d1 = calendar.getTime();
		return sdf.format(d1);
	}

	/**
	 * Devuelve un boolean indicando si la fecha del sistema es un dia habil
	 * 
	 * @return
	 */
	public static boolean isDiaHabil() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return (dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY);
	}

	/**
	 * Devuelve un Date con horas minutos y segundos seteados (Calendar)
	 *
	 * @param hora
	 * @return
	 */
	public static Date setHoraMinutos(String hora) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora.substring(0, 2)));
		calendar.set(Calendar.MINUTE, Integer.parseInt(hora.substring(2, 4)));
		calendar.set(Calendar.SECOND, 00);
		return calendar.getTime();

	}

	/**
	 * Compara una fecha para ver si la misma esta entre dos fechas dadas. Si fechaDesde es null, compara solo por la fecha superior, si la fechaHasta es null, compara solo por la
	 * fecha inferior. Devuelve Boolean.TRUE si la fecha buscada esta entre fechaDesde y fechaHasta, sino devuelve Boolean.FALSE.
	 * 
	 * @param fecha
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	public static Boolean entreFechas(Date fecha, Date fechaDesde, Date fechaHasta) {
		if (fecha == null) {
			return Boolean.FALSE;
		}
		Boolean entreFechas = Boolean.FALSE;
		Calendar fechaComp = Calendar.getInstance();
		fechaComp.setTime(fecha);
		Calendar desde = null;
		Calendar hasta = null;
		if (fechaDesde != null) {
			desde = Calendar.getInstance();
			desde.setTime(fechaDesde);
		}
		if (fechaHasta != null) {
			hasta = Calendar.getInstance();
			hasta.setTime(fechaHasta);
		}
		if ((desde != null) && (hasta != null)) {
			entreFechas = desde.before(fechaComp) && hasta.after(fechaComp);
		} else if ((desde != null) && (hasta == null)) {
			entreFechas = desde.before(fechaComp);
		} else if ((desde == null) && (hasta != null)) {
			entreFechas = hasta.after(fechaComp);
		}
		return entreFechas;
	}

	/**
	 * Compara una fecha para ver si la misma esta entre dos fechas dadas o si es igual a una de las fecha dadas. Devuelve Boolean.TRUE si la fecha buscada esta entre fechaDesde y
	 * fechaHasta o es igual a una de ellas, sino devuelve Boolean.FALSE.
	 * 
	 * @param fecha
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	public static Boolean entreFechasInclusive(Date fecha, Date fechaDesde, Date fechaHasta) {
		if (fecha == null) {
			return Boolean.FALSE;
		}
		Boolean entreFechas = Boolean.FALSE;
		Calendar fechaComp = Calendar.getInstance();
		fechaComp.setTime(fecha);
		Calendar desde = null;
		Calendar hasta = null;
		if (fechaDesde != null) {
			desde = Calendar.getInstance();
			desde.setTime(fechaDesde);
		}
		if (fechaHasta != null) {
			hasta = Calendar.getInstance();
			hasta.setTime(fechaHasta);
		}
		if ((desde != null) && (hasta != null)) {
			entreFechas = (desde.before(fechaComp) && hasta.after(fechaComp)) || (desde.equals(fechaComp) || hasta.equals(fechaComp));
		}
		return entreFechas;
	}

	/**
	 * Suma la cantidad de dias, pasados por parametro, a la fecha solicitada.
	 * 
	 * @param fecha
	 * @param cantidadDias
	 * @return
	 */
	public static Date sumarDias(Date fecha, int cantidadDias) {
		if (fecha == null) {
			return null;
		}
		Calendar nuevaFecha = Calendar.getInstance();
		nuevaFecha.setTime(fecha);
		nuevaFecha.add(Calendar.DAY_OF_MONTH, cantidadDias);
		return nuevaFecha.getTime();
	}

	/**
	 * Suma una cantidad de horas informada por parametro a una fecha
	 * 
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public static Date sumarHoras(Date fecha, int hora) {
		Calendar ahora = Calendar.getInstance();
		if (fecha != null) {
			ahora.setTime(fecha);
		}
		ahora.add(Calendar.HOUR, hora);
		return ahora.getTime();
	}

	/**
	 * Suma la cantidad de minutos, pasados por parametro, a la fecha solicitada.
	 * 
	 * @param fecha
	 * @param cantidadDias
	 * @return
	 */
	public static Date sumarMinutos(Date fecha, int cantidadMinutos) {
		if (fecha == null) {
			return null;
		}
		Calendar nuevaFecha = Calendar.getInstance();
		nuevaFecha.setTime(fecha);
		nuevaFecha.add(Calendar.MINUTE, cantidadMinutos);
		return nuevaFecha.getTime();
	}

	/**
	 * Suma la cantidad de segundos, pasados por parametro, a la fecha solicitada.
	 * 
	 * @param fecha
	 * @param cantidadDias
	 * @return
	 */
	public static Date sumarSegundos(Date fecha, int cantidadSegundos) {
		if (fecha == null) {
			return null;
		}
		Calendar nuevaFecha = Calendar.getInstance();
		nuevaFecha.setTime(fecha);
		nuevaFecha.add(Calendar.SECOND, cantidadSegundos);
		return nuevaFecha.getTime();
	}

	/**
	 * Suma la cantidad de meses, pasados por parametros, a la fecha solicitada.
	 * 
	 * @param fecha
	 * @param cantidadMeses
	 * @return
	 */
	public static Date sumarMeses(Date fecha, int cantidadMeses) {
		if (fecha == null) {
			return null;
		}
		Calendar nuevaFecha = Calendar.getInstance();
		nuevaFecha.setTime(fecha);
		nuevaFecha.add(Calendar.MONTH, cantidadMeses);
		return nuevaFecha.getTime();
	}

	/**
	 * Compara dos fechas para ver si la primera es anterior a la segunda (incluyendo, opcionalmente, la hora en la comparacion)
	 * 
	 * @param fechaAnterior
	 * @param fechaPosterior
	 * @param conHora
	 * @return
	 */
	public static boolean isAnterior(Date fechaAnterior, Date fechaPosterior, boolean conHora) {
		if ((fechaAnterior == null) || (fechaPosterior == null)) {
			return false;
		}
		Calendar fechaAnt = Calendar.getInstance();
		fechaAnt.setTime(fechaAnterior);
		Calendar fechaPost = Calendar.getInstance();
		fechaPost.setTime(fechaPosterior);
		if (!conHora) {
			fechaAnt.set(Calendar.HOUR_OF_DAY, 0);
			fechaAnt.set(Calendar.MINUTE, 0);
			fechaAnt.set(Calendar.SECOND, 0);
			fechaAnt.set(Calendar.MILLISECOND, 0);
			fechaPost.set(Calendar.HOUR_OF_DAY, 0);
			fechaPost.set(Calendar.MINUTE, 0);
			fechaPost.set(Calendar.SECOND, 0);
			fechaPost.set(Calendar.MILLISECOND, 0);
		}
		return fechaAnt.before(fechaPost);
	}

	/**
	 * Formatea una fecha utilizando un formato de entrada y un formato de salida
	 * 
	 * @param date
	 *            la fecha en cuestion
	 * @param inputFormat
	 *            el formato que se encuentra la fecha
	 * @param outputFormat
	 *            el formato en el que se desea retornar la fecha
	 * @return the date in the outputFormat
	 * @throws Exception 
	 * @throws TechnicalException
	 */
	public static String formatearFecha(String date, String inputFormat, String outputFormat) throws Exception {
		try {
			if (StringUtils.isBlank(date)) {
				return null;
			}
			final SimpleDateFormat formatter = new SimpleDateFormat(inputFormat);
			Date aDate = formatter.parse(date);
			formatter.applyPattern(outputFormat);
			return formatter.format(aDate);
		} catch (ParseException e) {
			throw new Exception();
		}
	}

	/**
	 * Convierte un string numerico a formato hh:mm:ss
	 * 
	 * @param horaTransaccion
	 * @return
	 */
	public static String format2Hour(String horaTransaccion) {
		return horaTransaccion != null && !horaTransaccion.isEmpty() ? horaTransaccion.substring(0, 2) + ":" + horaTransaccion.substring(2, 4) + ":" + horaTransaccion.substring(4)
				: "";
	}

	/**
	 * Calcula la diferencia entre fechas, pasando la unidad de tiempo como parametro
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param unidadTiempo
	 * @return
	 */
	public static long getDiferenciaFechas(Date fechaInicial, Date fechaFinal, TimeUnit unidadTiempo) {
		long diffInMillies = fechaFinal.getTime() - fechaInicial.getTime();
		return unidadTiempo.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	/**
	 * Suma a una fecha dada una cierta cantidad de dias informada por parametro
	 * 
	 * @param fecha
	 * @param formato
	 * @param cantidadDias
	 * @return
	 * @throws Exception 
	 */
	public static String sumarDiasAFecha(String fecha, String formato, int cantidadDias) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(fecha));
			c.add(Calendar.DATE, cantidadDias);
			return sdf.format(c.getTime());
		} catch (ParseException e) {
			throw new Exception();
		}
	}

	/**
	 * Este metodo retorna true si la fecha que se pasa por parametro no es mas antigua que 90 dias con respecto a la fecha del dia de hoy
	 * 
	 * @param fechaAlta
	 * @return
	 * @throws Exception 
	 */
	public static boolean esFechaValida(String fechaAlta) throws Exception {
		Date hoy = new Date();
		Calendar ca = new GregorianCalendar();
		ca.setTime(hoy);
		ca.add(Calendar.DAY_OF_MONTH, -90);
		hoy = ca.getTime();
		Date fechaAltaDate = fechaStringToDate(fechaAlta, FechaUtils.DATE_FORMAT_DEFAULT);
		return isAnterior(hoy, fechaAltaDate, false);
	}

	/**
	 * Recibe dos fechas String y la mascara de la fecha para validar que sean iguales
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @param mascara
	 * @return
	 * @throws Exception 
	 */
	public static boolean esMismaFecha(String fecha1, String fecha2, String mascara) throws Exception {
		return (fechaStringToDate(fecha1, mascara).compareTo(fechaStringToDate(fecha2, mascara)) == 0);
	}

	public static String formatearFecha(Date date, String inputFormat, String outputFormat) {
		if (date == null) {
			return null;
		}
		final SimpleDateFormat formatter = new SimpleDateFormat(inputFormat);
		long fecha = date.getTime();

		return formatter.format(fecha);
	}

	/**
	 * Devuelve el dia de la semana de la fecha dada.
	 * 
	 * @param fecha
	 * @param formato
	 *            return dia de la semana
	 * @throws Exception 
	 */
	public static int getDiaDeLaSemana(String fecha, String formato) throws Exception {
		Date date = fechaStringToDate(fecha, formato);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Devuelve la fecha del proximo día
	 * 
	 * @param fecha
	 * @param formato
	 *            return dia de la semana
	 */

	public static Calendar nextDayOfWeek(int dow) {
		Calendar date = Calendar.getInstance();
		int diff = dow - date.get(Calendar.DAY_OF_WEEK);
		boolean isToday = false;
		if (diff <= 0) {
			diff += 7;
			isToday = true;
		}
		if ((dow == 5 || dow == 6) && !isToday)
			date.add(Calendar.DAY_OF_MONTH, diff + 7);
		else {
			date.add(Calendar.DAY_OF_MONTH, diff);
		}

		return date;
	}

	/**
	 * Devuelve un Booleano que evalua si la <b>fecha</b> pasada por parametro con su <b>formato</b> esta incluida en el rango desde <b>offset</b> dias atras
	 * 
	 * @param fecha
	 * @param formato
	 * @param offset
	 * @return Boolean
	 * @throws Exception 
	 */
	public static Boolean estaEnRangoDeNDiasAtras(String fecha, String formato, int offset) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		Date fechaBusqueda = null;
		try {
			fechaBusqueda = sdf.parse(fecha);
		} catch (ParseException e) {
			throw new Exception();
		}
		Date desde = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(desde);
		calendar.add(Calendar.DATE, -offset);
		desde = calendar.getTime();
		return isAnterior(desde, fechaBusqueda, false);
	}

}
