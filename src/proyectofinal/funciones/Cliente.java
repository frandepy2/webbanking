
package proyectofinal.funciones;

import java.util.Date;

public class Cliente {
    private int idPersona;
    private String nombre;
    private String apellido;
    private String cIdentidad;
    private Date fNac;
    private String email;
    private String direccion;
    private String telefono;
    private String celular;
    private String ruc;

    public Cliente(int idPersona, String nombre, String apellido, String cIdentidad, Date fNac, String email, String direccion, String telefono, String celular, String ruc) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cIdentidad = cIdentidad;
        this.fNac = fNac;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.ruc = ruc;
    }

    public Cliente() {
    }

    public int getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getcIdentidad() {
        return cIdentidad;
    }

    public Date getfNac() {
        return fNac;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCelular() {
        return celular;
    }

    public String getRuc() {
        return ruc;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setcIdentidad(String cIdentidad) {
        this.cIdentidad = cIdentidad;
    }

    public void setfNac(Date fNac) {
        this.fNac = fNac;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    
}
