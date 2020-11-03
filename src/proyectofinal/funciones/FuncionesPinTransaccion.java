package proyectofinal.funciones;

import static java.lang.Math.random;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import password_hashing.PasswordStorage;
public class FuncionesPinTransaccion {
    public static String generarPIN() throws PasswordStorage.CannotPerformOperationException {
        Integer pinTran = (int) (random() * 10000);
        if (pinTran < 1000) {
            pinTran += 1000;
        }
        JOptionPane.showMessageDialog(null, "El PIN de transacción es: "+pinTran,"Transacción",INFORMATION_MESSAGE);
        String pinHasheado = PasswordStorage.createHash(pinTran.toString());
        return pinHasheado;
    }

    public static boolean compararPIN(String pinTranHasheado) throws PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException {
        Integer aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el PIN de transacción","PIN de Transacción",WARNING_MESSAGE));
        if (aux>-1) {
            while (!PasswordStorage.verifyPassword(aux.toString(), pinTranHasheado)) {
                JOptionPane.showMessageDialog(null, "El pin ingresado no es correcto. Por favor intente de nuevo.", "Pin Incorrecto", JOptionPane.ERROR_MESSAGE);
                aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el PIN de transacción","PIN de Transacción",WARNING_MESSAGE));
            }
            return true;
        } else {
            return false;
        }
    }
}
/*@author jlgut*/