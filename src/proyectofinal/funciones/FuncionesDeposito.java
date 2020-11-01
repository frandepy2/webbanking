
package proyectofinal.funciones;

import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import proyectofinal.LayoutPrincipal;

public class FuncionesDeposito {
    public static void realizarDeposito(String nroCheque, String monto){
        Deposito deposito = new Deposito();
        Transaccion transaccion = new Transaccion();
        BaseDatos bd = new BaseDatos();
        try{
            System.out.println("Monto Anterior en Cuenta: "+ LayoutPrincipal.cuenta.getSaldoEnCuenta());
            LayoutPrincipal.cuenta.addMonto(monto);
            System.out.println("Nuevo Monto en Cuenta: "+ LayoutPrincipal.cuenta.getSaldoEnCuenta());
            
            transaccion.setTipo("Deposito");
            if ("".equals(nroCheque)){ //En Efectivo
                deposito.setTipoDeposito("Efectivo");
                
                transaccion.setDescripcion("Deposito en Efectivo monto: "+monto+" en fecha "+LocalDate.now());
            }else{ //En Cheque 
                deposito.setTipoDeposito("Cheque");
                deposito.setNroCheque(Integer.parseInt(nroCheque));
                transaccion.setDescripcion("Deposito en Cheque monto: "+monto+" Cheque nro :"+nroCheque+" en fecha "+LocalDate.now());
            }
            
            transaccion.setMonto(Double.parseDouble(monto));
            transaccion.setFechaTransaccion(Date.valueOf(LocalDate.now()));
            
            bd.actualizarDatosDeposito(deposito,transaccion,LayoutPrincipal.cuenta); //Actualizamos la base de Datos
            
        }catch (IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null,"Datos introducidos Invalidos");
        }
        
    }
    

}
