
package proyectofinal.funciones;

/**
 *
 * @author FRAND
 */
public class Deposito {
    private int idDeposito;
    private String tipoDeposito;
    private int NroCheque;

    public Deposito(int idDeposito, String tipoDeposito, int NroCheque) {
        this.idDeposito = idDeposito;
        this.tipoDeposito = tipoDeposito;
        this.NroCheque = NroCheque;
    }

    public Deposito() {
    }

    public int getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(int idDeposito) {
        this.idDeposito = idDeposito;
    }

    public String getTipoDeposito() {
        return tipoDeposito;
    }

    public void setTipoDeposito(String tipoDeposito) {
        this.tipoDeposito = tipoDeposito;
    }

    public int getNroCheque() {
        return NroCheque;
    }

    public void setNroCheque(int NroCheque) {
        this.NroCheque = NroCheque;
    }
    
}
