package cr.ac.ulead.entities;

import java.time.LocalDate;

public class Persona implements Comparable<Persona> {

 
	private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String cedula;
    private Integer nacimiento;
    private String direccion;
    private String provincia;
    private String canton;
    private String distrito;
	public int value;
    

   
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Integer getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Integer nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCanton() {
		return canton;
	}
	public void setCanton(String canton) {
		this.canton = canton;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + ", cedula=" + cedula + ", nacimiento=" + nacimiento + ", direccion=" + direccion
				+ ", provincia=" + provincia + ", canton=" + canton + ", distrito=" + distrito + "]";
	}

	   public Persona(String nombre, String primerApellido, String segundoApellido, String cedula, Integer nacimiento2,
				String direccion, String provincia, String canton, String distrito) {
			super();
			this.nombre = nombre;
			this.primerApellido = primerApellido;
			this.segundoApellido = segundoApellido;
			this.cedula = cedula;
			this.nacimiento = nacimiento2;
			this.direccion = direccion;
			this.provincia = provincia;
			this.canton = canton;
			this.distrito = distrito;
		}
	@Override
	public int compareTo(Persona t) {
		// TODO Auto-generated method stub
		return nacimiento.compareTo(t.getNacimiento());
	}
	
    
}