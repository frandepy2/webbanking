package proyectofinal.funciones;

import proyectofinal.LayoutPrincipal;

public class FuncionesPagoServicios {

    public static int idServicio;
    public static Double monto;

    public static void setServicio(int idServicio) throws Exception {
        FuncionesPagoServicios.idServicio = idServicio;
        BaseDatos db = new BaseDatos();
        if (db.getServicio() == null) {
            throw new Exception("No existe el servicio");
        }
    }

    public static void setMonto(Double monto) throws Exception {
        if(monto<=0.0){
            throw new Exception("Monto no valido");
        }
        FuncionesPagoServicios.monto = monto;
    }

    public static void verificarSaldo() throws Exception {
        if (FuncionesPagoServicios.monto > LayoutPrincipal.cuenta.getSaldoEnCuenta()) {
            throw new Exception("No tiene saldo suficiente para pagar este servicio");
        }
    } 
}
/*@author jlgut*/
