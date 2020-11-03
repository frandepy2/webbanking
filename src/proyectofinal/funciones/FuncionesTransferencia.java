
package proyectofinal.funciones;

import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;
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
    public static void realizarTransferencia(String nroCuentaDestino, String montoTransferir) throws IllegalArgumentException{
        BaseDatos bd = new BaseDatos();
        Double montoT = Double.parseDouble(montoTransferir);
        
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
            
            //Aqui poner el tema de PIN de Transaccion recordar Agregar 
            
            //Fin Logica de PIN de Transaccion, recordar Agregar
            
            //Aumenta en la cuenta Destino y disminuye en la cuenta de Origen
            cuentaDest.addMonto(montoTransferir);
            LayoutPrincipal.cuenta.disminuirSaldo(montoTransferir);
            
            //Se registra los daltos de Transferencia
            transferencia.setCuentaDestino(cuentaDest);
            transferencia.setCuentaOrigen(LayoutPrincipal.cuenta );
            transferencia.setPinTransaccion(0000);
            
            
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
            
            JOptionPane.showMessageDialog(null, "Se realizo la transferencia correctamente");
            
            
        }
            
    }
    
    private static Boolean compararSaldo(Double saldoEnCuenta, Double montoT){
        return saldoEnCuenta - montoT >= 0;
    }
        
}

