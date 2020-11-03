
package proyectofinal.funciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import proyectofinal.LayoutPrincipal;

public class FuncionesTransacciones {
    public static DefaultTableModel poblarTabla() throws SQLException{
        
        BaseDatos bd = new BaseDatos();
        DefaultTableModel modeloTabla = bd.traerTransacciones(LayoutPrincipal.cuenta.getIdCuenta());
        
        return modeloTabla;
    }
}
