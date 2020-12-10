package ar.com.bbva.arq.renaper.model;


public class PersonAltaDatos {

	private String numeroDocumento;
	private String nombre;
	private String apellido;
	private String sexo;
	private String fechaNacimiento;
	private String numeroCopia;
	private String fechaExpiracionDocumento;
	private String fechaEmisionDocumento;
	private String numeroCUIL;
	private String calleDomicilio;
	private String numeroDomicilio;
	private String pisoDomicilio;
	private String departamentoDomicilio;
	private String codigoPostalDomicilio;
	private String ciudadDomicilio;
	private String municipalidadDomicilio;
	private String provinciaDomicilio;
	private String paisDomicilio;
	private String nacionalidad;
	private String paisNacimiento;
	private String marcaFallecido;
	private String opcion;
	private String nombreInformado;
	private String apellidoInformado;
	private String fechaNacimientoInformado;



	public PersonAltaDatos buildFromRenaper(PersonResponseDTO person,UpdateClientDataDTO updateClientDataDTO) {
		this.numeroDocumento=checkForNull(person.getNumber());
		this.nombre=checkForNull(person.getNames());
		this.apellido=checkForNull(person.getLastNames());
		this.sexo=checkForNull(person.getGender());
		this.fechaNacimiento=checkForNull(person.getBirthdate());
		this.numeroCopia=checkForNull(person.getCopy());
		this.fechaExpiracionDocumento=checkForNull(person.getExpirationDate());
		this.fechaEmisionDocumento=checkForNull(person.getCreationDate());
		this.numeroCUIL=checkForNull(person.getCuil());
		this.calleDomicilio=checkForNull(person.getStreetAddress());
		this.numeroDomicilio=checkForNull(person.getNumberStreet());
		this.pisoDomicilio=checkForNull(person.getFloor());
		this.departamentoDomicilio=checkForNull(person.getDepartment());
		this.codigoPostalDomicilio=checkForNull(person.getZipCode());
		this.ciudadDomicilio=checkForNull(person.getCity());
		this.municipalidadDomicilio=checkForNull(person.getMunicipality());
		this.provinciaDomicilio=checkForNull(person.getProvince());
		this.paisDomicilio=checkForNull(person.getCountry());
		this.nacionalidad=checkForNull(person.getNationality());
		this.paisNacimiento=checkForNull(person.getCountryBirth());
		this.marcaFallecido=checkForNull(person.getMessageOfDeath());
		this.opcion=(updateClientDataDTO.getOpcion()==null||updateClientDataDTO.getOpcion().equals(""))?"01":updateClientDataDTO.getOpcion();
		this.nombreInformado=updateClientDataDTO.getNombreInformado().toUpperCase();
		this.apellidoInformado=updateClientDataDTO.getApellidoInformado().toUpperCase();
		this.fechaNacimientoInformado=updateClientDataDTO.getFechaNacimientoInformado();
		return this;
	}
	
	public PersonAltaDatos buildFromRenaper(PersonResponseDTO person,OrquestarDataDTO orquestarDataDTO) {
		
		this.numeroDocumento=checkForNull(person.getNumber());
		this.nombre=checkForNull(person.getNames());
		this.apellido=checkForNull(person.getLastNames());
		this.sexo=checkForNull(person.getGender());
		this.fechaNacimiento=checkForNull(person.getBirthdate());
		this.numeroCopia=checkForNull(person.getCopy());
		this.fechaExpiracionDocumento=checkForNull(person.getExpirationDate());
		this.fechaEmisionDocumento=checkForNull(person.getCreationDate());
		this.numeroCUIL=checkForNull(person.getCuil());
		this.calleDomicilio=checkForNull(person.getStreetAddress());
		this.numeroDomicilio=checkForNull(person.getNumberStreet());
		this.pisoDomicilio=checkForNull(person.getFloor());
		this.departamentoDomicilio=checkForNull(person.getDepartment());
		this.codigoPostalDomicilio=checkForNull(person.getZipCode());
		this.ciudadDomicilio=checkForNull(person.getCity());
		this.municipalidadDomicilio=checkForNull(person.getMunicipality());
		this.provinciaDomicilio=checkForNull(person.getProvince());
		this.paisDomicilio=checkForNull(person.getCountry());
		this.nacionalidad=checkForNull(person.getNationality());
		this.paisNacimiento=checkForNull(person.getCountryBirth());
		this.marcaFallecido=checkForNull(person.getMessageOfDeath());
		this.opcion=(orquestarDataDTO.getOpcion()==null||orquestarDataDTO.getOpcion().equals(""))?"01":orquestarDataDTO.getOpcion();
		this.nombreInformado=orquestarDataDTO.getNombreInformado().toUpperCase();
		this.apellidoInformado=orquestarDataDTO.getApellidoInformado().toUpperCase();
		this.fechaNacimientoInformado=orquestarDataDTO.getFechaNacimientoInformado();
		return this;

	}
	
	
	
	
	public PersonAltaDatos buildFromRenaper(PersonResponseDTO person,BarcodeResponseDTO barcodeResponseDTO,String opcion) {
		this.numeroDocumento=checkForNull(person.getNumber());
		this.nombre=checkForNull(person.getNames());
		this.apellido=checkForNull(person.getLastNames());
		this.sexo=checkForNull(person.getGender());
		this.fechaNacimiento=checkForNull(person.getBirthdate());
		this.numeroCopia=checkForNull(person.getCopy());
		this.fechaExpiracionDocumento=checkForNull(person.getExpirationDate());
		this.fechaEmisionDocumento=checkForNull(person.getCreationDate());
		this.numeroCUIL=checkForNull(person.getCuil());
		this.calleDomicilio=checkForNull(person.getStreetAddress());
		this.numeroDomicilio=checkForNull(person.getNumberStreet());
		this.pisoDomicilio=checkForNull(person.getFloor());
		this.departamentoDomicilio=checkForNull(person.getDepartment());
		this.codigoPostalDomicilio=checkForNull(person.getZipCode());
		this.ciudadDomicilio=checkForNull(person.getCity());
		this.municipalidadDomicilio=checkForNull(person.getMunicipality());
		this.provinciaDomicilio=checkForNull(person.getProvince());
		this.paisDomicilio=checkForNull(person.getCountry());
		this.nacionalidad=checkForNull(person.getNationality());
		this.paisNacimiento=checkForNull(person.getCountryBirth());
		this.marcaFallecido=checkForNull(person.getMessageOfDeath());
		this.opcion=checkForNull(opcion);
		this.nombreInformado=barcodeResponseDTO.getNombreInformado().toUpperCase();
		this.apellidoInformado=barcodeResponseDTO.getApellidoInformado().toUpperCase();
		this.fechaNacimientoInformado=barcodeResponseDTO.getFechaNacInformado();
		return this;
	}
	
	
	public PersonAltaDatos buildFromRenaper(PersonResponseDTO person) {
		this.numeroDocumento=checkForNull(person.getNumber());
		this.nombre=checkForNull(person.getNames());
		this.apellido=checkForNull(person.getLastNames());
		this.sexo=checkForNull(person.getGender());
		this.fechaNacimiento=checkForNull(person.getBirthdate());
		this.numeroCopia=checkForNull(person.getCopy());
		this.fechaExpiracionDocumento=checkForNull(person.getExpirationDate());
		this.fechaEmisionDocumento=checkForNull(person.getCreationDate());
		this.numeroCUIL=checkForNull(person.getCuil());
		this.calleDomicilio=checkForNull(person.getStreetAddress());
		this.numeroDomicilio=checkForNull(person.getNumberStreet());
		this.pisoDomicilio=checkForNull(person.getFloor());
		this.departamentoDomicilio=checkForNull(person.getDepartment());
		this.codigoPostalDomicilio=checkForNull(person.getZipCode());
		this.ciudadDomicilio=checkForNull(person.getCity());
		this.municipalidadDomicilio=checkForNull(person.getMunicipality());
		this.provinciaDomicilio=checkForNull(person.getProvince());
		this.paisDomicilio=checkForNull(person.getCountry());
		this.nacionalidad=checkForNull(person.getNationality());
		this.paisNacimiento=checkForNull(person.getCountryBirth());
		this.marcaFallecido=checkForNull(person.getMessageOfDeath());
		this.opcion="01";
		return this;
	}
	
	
	public String checkForNull(String attr) {
		return attr==null ||attr.equals("null")?"":attr.toUpperCase();
	}


	public String getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getNumeroCopia() {
		return numeroCopia;
	}


	public void setNumeroCopia(String numeroCopia) {
		this.numeroCopia = numeroCopia;
	}


	public String getFechaExpiracionDocumento() {
		return fechaExpiracionDocumento;
	}


	public void setFechaExpiracionDocumento(String fechaExpiracionDocumento) {
		this.fechaExpiracionDocumento = fechaExpiracionDocumento;
	}


	public String getFechaEmisionDocumento() {
		return fechaEmisionDocumento;
	}


	public void setFechaEmisionDocumento(String fechaEmisionDocumento) {
		this.fechaEmisionDocumento = fechaEmisionDocumento;
	}


	public String getNumeroCUIL() {
		return numeroCUIL;
	}


	public void setNumeroCUIL(String numeroCUIL) {
		this.numeroCUIL = numeroCUIL;
	}


	public String getCalleDomicilio() {
		return calleDomicilio;
	}


	public void setCalleDomicilio(String calleDomicilio) {
		this.calleDomicilio = calleDomicilio;
	}


	public String getNumeroDomicilio() {
		return numeroDomicilio;
	}


	public void setNumeroDomicilio(String numeroDomicilio) {
		this.numeroDomicilio = numeroDomicilio;
	}


	public String getPisoDomicilio() {
		return pisoDomicilio;
	}


	public void setPisoDomicilio(String pisoDomicilio) {
		this.pisoDomicilio = pisoDomicilio;
	}


	public String getDepartamentoDomicilio() {
		return departamentoDomicilio;
	}


	public void setDepartamentoDomicilio(String departamentoDomicilio) {
		this.departamentoDomicilio = departamentoDomicilio;
	}


	public String getCodigoPostalDomicilio() {
		return codigoPostalDomicilio;
	}


	public void setCodigoPostalDomicilio(String codigoPostalDomicilio) {
		this.codigoPostalDomicilio = codigoPostalDomicilio;
	}


	public String getCiudadDomicilio() {
		return ciudadDomicilio;
	}


	public void setCiudadDomicilio(String ciudadDomicilio) {
		this.ciudadDomicilio = ciudadDomicilio;
	}


	public String getMunicipalidadDomicilio() {
		return municipalidadDomicilio;
	}


	public void setMunicipalidadDomicilio(String municipalidadDomicilio) {
		this.municipalidadDomicilio = municipalidadDomicilio;
	}


	public String getProvinciaDomicilio() {
		return provinciaDomicilio;
	}


	public void setProvinciaDomicilio(String provinciaDomicilio) {
		this.provinciaDomicilio = provinciaDomicilio;
	}


	public String getPaisDomicilio() {
		return paisDomicilio;
	}


	public void setPaisDomicilio(String paisDomicilio) {
		this.paisDomicilio = paisDomicilio;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public String getPaisNacimiento() {
		return paisNacimiento;
	}


	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}


	public String getMarcaFallecido() {
		return marcaFallecido;
	}


	public void setMarcaFallecido(String marcaFallecido) {
		this.marcaFallecido = marcaFallecido;
	}


	public String getOpcion() {
		return opcion;
	}


	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getNombreInformado() {
		return nombreInformado;
	}

	public void setNombreInformado(String nombreInformado) {
		this.nombreInformado = nombreInformado;
	}

	public String getApellidoInformado() {
		return apellidoInformado;
	}

	public void setApellidoInformado(String apellidoInformado) {
		this.apellidoInformado = apellidoInformado;
	}

	public String getFechaNacimientoInformado() {
		return fechaNacimientoInformado;
	}

	public void setFechaNacimientoInformado(String fechaNacimientoInformado) {
		this.fechaNacimientoInformado = fechaNacimientoInformado;
	}

	

}
