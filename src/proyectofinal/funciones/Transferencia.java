
package proyectofinal.funciones;

public class Transferencia {
    private int idTransferencia;
    private String pinTransaccion;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    public Transferencia(){
        
    }
    
    public Transferencia(int idTransferencia, String pinTransaccion, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        this.idTransferencia = idTransferencia;
        this.pinTransaccion = pinTransaccion;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public String getPinTransaccion() {
        return pinTransaccion;
    }

    public void setPinTransaccion(String pinTransaccion) {
        this.pinTransaccion = pinTransaccion;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
    
    
}
