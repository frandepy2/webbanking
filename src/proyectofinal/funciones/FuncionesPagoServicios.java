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
        } else {
            db.getServicio();
        }
    }

    public static void setMonto(Double monto) {
        FuncionesPagoServicios.monto = monto;
    }

    public static void verificarSaldo() throws Exception {
        if (FuncionesPagoServicios.monto > LayoutPrincipal.cuenta.getSaldoEnCuenta()) {
            throw new Exception("No tiene saldo suficiente para pagar este servicio");
        }
    } 
}
/*@author jlgut*/
