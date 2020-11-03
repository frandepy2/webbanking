package proyectofinal.funciones;

import static java.lang.Math.random;
import javax.swing.JOptionPane;
import proyectofinal.LayoutPrincipal;

/**
 * Aqui toda la logica para el saldo de la cuenta
 *
 * @author Jovana
 */
public class FuncionesSaldoCuenta {

    public static String mostrarSaldo() {
        return (LayoutPrincipal.cuenta.getSaldoEnCuenta()).toString();
    }
}
