
package proyectofinal.funciones;

public class PagoServicios {
    private int idServicio;
    private String nombreServicio;
    private int pinTransaccion;
    private int codigoServicio;

    public PagoServicios(int idServicio, String nombreServicio, int pinTransaccion, int codigoServicio) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.pinTransaccion = pinTransaccion;
        this.codigoServicio = codigoServicio;
    }

    public PagoServicios() {
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public int getPinTransaccion() {
        return pinTransaccion;
    }

    public void setPinTransaccion(int pinTransaccion) {
        this.pinTransaccion = pinTransaccion;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }
    
    
}
