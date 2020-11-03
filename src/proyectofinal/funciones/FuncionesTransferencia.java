
package proyectofinal.funciones;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import password_hashing.PasswordStorage;
import proyectofinal.LayoutPrincipal;

/**
 * Aqui toda la logica de la Funcion de Transferencia
 * @author FRAND
 */
public class FuncionesTransferencia {
    /**
     * Realizamos la transferencia
     * @param nroCuentaDestino
     * @param montoTransferir 
     */
    public static void realizarTransferencia(String nroCuentaDestino, String montoTransferir) throws IllegalArgumentException, PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException{
        int hh = LocalTime.now().getHour();
        int mm = LocalTime.now().getMinute();
        int ss = LocalTime.now().getSecond();
        BaseDatos bd = new BaseDatos();
        Double montoT;
        try{
            montoT = Double.parseDouble(montoTransferir);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Dato Introducido Invalido");
        }
        if (LayoutPrincipal.cuenta.getNroCuenta().equals(nroCuentaDestino)){
            throw new IllegalArgumentException("No puedes introducir tu numero de Cuenta como cuenta Destino");
        }
        
        if (montoT<0){
            throw new IllegalArgumentException("Monto Introducido Invalido");
        }
        
        Transferencia transferencia = new Transferencia();
        Transaccion transaccion = new Transaccion();
        Transaccion transaccionDest = new Transaccion();
        
        Cuenta cuentaDest = bd.getCuentaDestino(nroCuentaDestino);
        
        if (cuentaDest==null){
            throw new IllegalArgumentException("La cuenta Destino seleccionada no existe o fue dada de Baja");
        }else{
            
            Double saldoEnCuenta = LayoutPrincipal.cuenta.getSaldoEnCuenta();
            Boolean saldoDisponible = compararSaldo(saldoEnCuenta,montoT);
            
            if (!saldoDisponible) throw new IllegalArgumentException("No posee saldo Disponible para realizar la operación");
            
            //PIN de Transaccion
            String pinHasheado = FuncionesPinTransaccion.generarPIN();
                        
            if (!FuncionesPinTransaccion.compararPIN(pinHasheado)) {
                throw new IllegalArgumentException("PIN de Transaccion Invalido");
            }
            
            //Aumenta en la cuenta Destino y disminuye en la cuenta de Origen
            cuentaDest.addMonto(montoTransferir);
            LayoutPrincipal.cuenta.disminuirSaldo(montoTransferir);
            
            //Se registra los daltos de Transferencia
            transferencia.setCuentaDestino(cuentaDest);
            transferencia.setCuentaOrigen(LayoutPrincipal.cuenta );
            transferencia.setPinTransaccion(pinHasheado);
            
            
            //Se registran los datos de Transaccion de la cuenta Emisora
            transaccion.setDescripcion("Transferencia a la cuenta "+nroCuentaDestino+" monto transferido "+montoTransferir);
            transaccion.setMonto(montoT*(-1));
            transaccion.setFechaTransaccion(Date.valueOf(LocalDate.now()));
            transaccion.setTipo("Transferencia");
            
            //Se registran los datos de Transaccion de la cuenta Reseptora
            transaccionDest.setDescripcion("Transferencia recibida de cuenta "+ LayoutPrincipal.cuenta.getNroCuenta()+" la suma de "+montoTransferir);
            transaccionDest.setFechaTransaccion(Date.valueOf(LocalDate.now()));
            transaccionDest.setMonto(montoT);
            transaccionDest.setTipo("Transferencia");
            
            
            
            
            //Se guardan los datos en la Base de Datos 
            bd.guardarDatosTransferencia(transaccion, transferencia,transaccionDest);
            //Ticket
            JOptionPane.showMessageDialog(null, "Transferencia Realizada \n"
                    + "Se Transfirió "+montoTransferir+" Gs. \n"
                    + "Cuenta Destino : "+nroCuentaDestino+" \n"
                    + "Fecha :"+transaccion.getFechaTransaccion()+"\n"
                    + "Hora : " + hh+":"+mm+":"+ss +"\n" 
                    + "Gracias por utilizar el Sistema","Ticket",INFORMATION_MESSAGE);
            
            
        }
            
    }
    
    private static Boolean compararSaldo(Double saldoEnCuenta, Double montoT){
        return saldoEnCuenta - montoT >= 0;
    }
        
}

