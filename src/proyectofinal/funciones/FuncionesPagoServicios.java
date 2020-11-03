package proyectofinal.funciones;

import static java.lang.Math.random;
import javax.swing.JOptionPane;
import proyectofinal.LayoutPrincipal;

public class FuncionesPagoServicios {

    public static int idServicio;
    public static Double monto;

    public static void setServicio(int idServicio) throws Exception {
        FuncionesPagoServicios.idServicio = idServicio;
        BaseDatos db = new BaseDatos();
        if (db.getServicio() == null) {
            throw new Exception("No existe el servicio");
        } else {
            db.getServicio();
        }
    }

    public static void setMonto(Double monto) {
        FuncionesPagoServicios.monto = monto;
    }

    public static void verificarSaldo() throws Exception {
        if (FuncionesPagoServicios.monto > LayoutPrincipal.cuenta.getSaldoEnCuenta()) {
            throw new Exception("No tiene saldo suficiente para pagar este servicio");
        }
    }

    public static int generarPIN() {
        int pinTran = (int) (random() * 10000);
        if (pinTran < 1000) {
            pinTran += 1000;
        }
        JOptionPane.showMessageDialog(null, pinTran);
        return pinTran;
    }

    public static boolean compararPIN(int pinTran) {
        String aux = JOptionPane.showInputDialog(null, "Ingrese el PIN de transacción");
        if (aux != null) {
            int pin = Integer.parseInt(aux);
            while (pin != pinTran) {
                JOptionPane.showMessageDialog(null, "El pin ingresado no es correcto. Por favor intente de nuevo.", "Pin Incorrecto", JOptionPane.ERROR_MESSAGE);
                pin = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el PIN de transacción"));
            }
            return true;
        } else {
            return false;
        }

    }
}
/*@author jlgut*/
