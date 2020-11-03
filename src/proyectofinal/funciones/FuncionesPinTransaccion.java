package proyectofinal.funciones;

import static java.lang.Math.random;
import javax.swing.JOptionPane;

public class FuncionesPinTransaccion {
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