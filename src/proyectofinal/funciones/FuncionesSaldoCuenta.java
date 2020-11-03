package proyectofinal.funciones;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import proyectofinal.LayoutPrincipal;

/**
 * Aqui toda la logica para el saldo de la cuenta
 *
 * @author Jovana
 */
public class FuncionesSaldoCuenta {

    public static String mostrarSaldo() {
        int hh = LocalTime.now().getHour();
        int mm = LocalTime.now().getMinute();
        int ss = LocalTime.now().getSecond();
        JOptionPane.showMessageDialog(null, "Saldo Solicitado \n"
                + "Fecha :" +Date.valueOf(LocalDate.now()) + "\n"
                + "Hora : " + hh + ":" + mm + ":" + ss + "\n"
                + "Saldo : " +LayoutPrincipal.cuenta.getSaldoEnCuenta()+" Gs. \n"
                + "Gracias por utilizar el Sistema", "Ticket", INFORMATION_MESSAGE);
        return (LayoutPrincipal.cuenta.getSaldoEnCuenta()).toString();
    }
}
