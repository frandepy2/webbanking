package proyectofinal.funciones;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import proyectofinal.LayoutPrincipal;

public class FuncionesDeposito {

    public static void realizarDeposito(String nroCheque, String monto) {
        int hh = LocalTime.now().getHour();
        int mm = LocalTime.now().getMinute();
        int ss = LocalTime.now().getSecond();
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

            JOptionPane.showMessageDialog(null, "Deposito Realizado \n"
                    + "Se depositó : " + monto + " Gs. \n"
                    + "Metodo de Deposito : "+ deposito.getTipoDeposito()+"\n"
                    + "Nro Cheque : "+ deposito.getNroCheque()+"\n"
                    + "Fecha :" + transaccion.getFechaTransaccion() + "\n"
                    + "Hora : " + hh + ":" + mm + ":" + ss + "\n"
                    + "Gracias por utilizar el Sistema", "Ticket", INFORMATION_MESSAGE);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Datos introducidos Invalidos");
        }

    }

}
