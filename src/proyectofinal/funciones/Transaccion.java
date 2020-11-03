package proyectofinal.funciones;

import java.util.Date;

public class Transaccion {
    private int idTranccion;
    private Double monto;
    private Date fechaTransaccion;
    private String descripcion;
    private String tipo;

    public Transaccion() {
    }

    public Transaccion(int idTranccion, Double monto, Date fechaTransaccion, String descripcion, String tipo) {
        this.idTranccion = idTranccion;
        this.monto = monto;
        this.fechaTransaccion = fechaTransaccion;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public int getIdTranccion() {
        return idTranccion;
    }

    public void setIdTranccion(int idTranccion) {
        this.idTranccion = idTranccion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
