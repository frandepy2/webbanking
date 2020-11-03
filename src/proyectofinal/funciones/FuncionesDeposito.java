package proyectofinal.funciones;

import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import proyectofinal.LayoutPrincipal;

public class FuncionesDeposito {

    public static void realizarDeposito(String nroCheque, String monto) {
        Deposito deposito = new Deposito();
        Transaccion transaccion = new Transaccion();
        BaseDatos bd = new BaseDatos();
        try {
            LayoutPrincipal.cuenta.addMonto(monto);
            transaccion.setTipo("Deposito");

            if ("".equals(nroCheque)) { //En Efectivo
                deposito.setTipoDeposito("Efectivo");

                transaccion.setDescripcion("Deposito en Efectivo monto: " + monto + " en fecha " + LocalDate.now());
            } else { //En Cheque 
                deposito.setTipoDeposito("Cheque");
                deposito.setNroCheque(Integer.parseInt(nroCheque));
                transaccion.setDescripcion("Deposito en Cheque monto: " + monto + " Cheque nro :" + nroCheque + " en fecha " + LocalDate.now());
            }

            transaccion.setMonto(Double.parseDouble(monto));
            transaccion.setFechaTransaccion(Date.valueOf(LocalDate.now()));

            bd.actualizarDatosDeposito(deposito, transaccion, LayoutPrincipal.cuenta); //Actualizamos la base de Datos

            JOptionPane.showMessageDialog(null, "Se realizó un deposito por monto de " + Double.parseDouble(monto), "Deposito", INFORMATION_MESSAGE);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Datos introducidos Invalidos");
        }

    }

}
