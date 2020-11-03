package proyectofinal.funciones;

import static java.lang.Math.random;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class FuncionesPinTransaccion {
    public static int generarPIN() {
        int pinTran = (int) (random() * 10000);
        if (pinTran < 1000) {
            pinTran += 1000;
        }
        JOptionPane.showMessageDialog(null, "El PIN de transacción es: "+pinTran,"Transacción",INFORMATION_MESSAGE);
        return pinTran;
    }

    public static boolean compararPIN(int pinTran) {
        String aux = JOptionPane.showInputDialog(null, "Ingrese el PIN de transacción","PIN de Transacción",WARNING_MESSAGE);
        if (aux != null) {
            int pin = Integer.parseInt(aux);
            while (pin != pinTran) {
                JOptionPane.showMessageDialog(null, "El pin ingresado no es correcto. Por favor intente de nuevo.", "Pin Incorrecto", JOptionPane.ERROR_MESSAGE);
                pin = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el PIN de transacción","PIN de Transacción",WARNING_MESSAGE));
            }
            return true;
        } else {
            return false;
        }
    }
    
    public static String compararPIN(int pinTran,String pinIntroducido) {
        if (pinIntroducido != null) {
            int pin = Integer.parseInt(pinIntroducido);
            while (pin != pinTran) {
                JOptionPane.showMessageDialog(null, "El pin ingresado no es correcto. Por favor intente de nuevo.", "Pin Incorrecto", JOptionPane.ERROR_MESSAGE);
                pin = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el PIN de transacción","PIN de Transacción",WARNING_MESSAGE));
            }
            return pinIntroducido;
        } else {
            return "";
        }
    }
}
/*@author jlgut*/