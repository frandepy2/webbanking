package proyectofinal.funciones;

import static java.lang.Math.random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.sqlite.date.DateFormatUtils;
import proyectofinal.Gui_PagoServicios;
import proyectofinal.LayoutPrincipal;

public class FuncionesPagoServicios {

    public static int idServicio;
    public static Double monto;

    public static void setServicio(int idServicio) throws Exception {
        FuncionesPagoServicios.idServicio = idServicio;
        FuncionesPagoServicios funciones = new FuncionesPagoServicios();
        if (funciones.getServicio() == null) {
            throw new Exception("No existe el servicio");
        } else {
            funciones.getServicio();
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

    public static int generarPIN() {
        int pinTran = (int) (random() * 10000);
        if (pinTran < 1000) {
            pinTran += 1000;
        }
        JOptionPane.showMessageDialog(null, pinTran);
        return pinTran;
    }

    public static boolean compararPIN(int pinTran) {
        String aux = JOptionPane.showInputDialog(null, "Ingrese el PIN de transacción");
        if (aux != null) {
            int pin = Integer.parseInt(aux);
            while (pin != pinTran) {
                JOptionPane.showMessageDialog(null, "El pin ingresado no es correcto. Por favor intente de nuevo.", "Pin Incorrecto", JOptionPane.ERROR_MESSAGE);
                pin = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el PIN de transacción"));
            }
            return true;
        } else {
            return false;
        }

    }

    //PARA BASE DE DATOS
    public Integer getServicio() {
        int servicio = FuncionesPagoServicios.idServicio;
        String sql = "SELECT servicio_id FROM servicio WHERE servicio_id = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, servicio);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return servicio;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void pagarServicio(int ID_Servicio, Double Monto) {
        final java.util.Date date = new java.util.Date();
        Transaccion transaccion = new Transaccion(0, Monto, date, "Pago de servicio " + Gui_PagoServicios.servicios[ID_Servicio - 1], "Pago de servicios");
        String sql = "INSERT INTO transaccion ( cuenta_id, transaccion_monto, transaccion_fech, transaccion_desc, transaccion_tipo, servicio_id ) VALUES (?,?,?,?,?,?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, LayoutPrincipal.cuenta.getIdCuenta());
            pstmt.setDouble(2, transaccion.getMonto());
            pstmt.setString(3, FormatoFechaHora(date));
            pstmt.setString(4, transaccion.getDescripcion());
            pstmt.setString(5, transaccion.getTipo());
            pstmt.setInt(6, ID_Servicio);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disminuirSaldo(Monto);
    }

    public void disminuirSaldo(Double Monto) {
        String sql = "UPDATE cuenta SET cuenta_saldo = ? WHERE cuenta_id = ?;";
        Double nuevoSaldo = LayoutPrincipal.cuenta.getSaldoEnCuenta() - Monto;
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, nuevoSaldo);
            pstmt.setInt(2, LayoutPrincipal.cuenta.getIdCuenta());
            LayoutPrincipal.cuenta.setSaldoEnCuenta(nuevoSaldo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String FormatoFechaHora(java.util.Date fecha) {
        return DateFormatUtils.format(fecha, "yyyy-MM-dd HH:mm:ss");
    }

    private Connection connect() {
        String url = "jdbc:sqlite:DB/webbanking.db";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
/*@author jlgut*/
