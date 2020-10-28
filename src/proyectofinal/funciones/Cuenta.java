
package proyectofinal.funciones;

import java.util.Date;

public class Cuenta {
    private int idCuenta;
    private Cliente cliente;
    private String nroCuenta;
    private int pinCuenta;
    private int estadoCuenta;
    private Date fechaAlta;
    private Date fechaBaja;
    private Double saldoEnCuenta;

    public Cuenta() {
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public int getPinCuenta() {
        return pinCuenta;
    }

    public void setPinCuenta(int pinCuenta) {
        this.pinCuenta = pinCuenta;
    }

    public int getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(int estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Double getSaldoEnCuenta() {
        return saldoEnCuenta;
    }

    public void setSaldoEnCuenta(Double saldoEnCuenta) {
        this.saldoEnCuenta = saldoEnCuenta;
    }
    
    
    
}
