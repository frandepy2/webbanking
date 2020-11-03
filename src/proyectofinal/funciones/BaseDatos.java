package proyectofinal.funciones;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.sqlite.date.DateFormatUtils;
import proyectofinal.Gui_PagoServicios;
import proyectofinal.LayoutPrincipal;

public class BaseDatos {
   
    //Coneccion con la Base de Datos
    private Connection connect(){
        String url = "jdbc:sqlite:DB/webbanking.db";
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        return conn;
    }
    
    
    //Funciones de Incicio  de Seccion
    public Cuenta getDatoCuenta(String nroCuenta){
        Cuenta cuenta = new Cuenta();
        String sql = "SELECT cuenta_nro, cuenta_est,cuenta_pin FROM cuenta WHERE cuenta_nro = ?";
        
        try(Connection conn = this.connect();
           PreparedStatement pstmt = conn.prepareStatement(sql)){
           pstmt.setString(1,nroCuenta);
           ResultSet rs = pstmt.executeQuery();
           
           if (rs == null){
               return null;
           }else{
               cuenta.setNroCuenta(nroCuenta);
               cuenta.setEstadoCuenta(rs.getInt("cuenta_est"));
               cuenta.setPinCuenta(rs.getString("cuenta_pin"));
               
               if (cuenta.getEstadoCuenta()==1){
                   return null;
               }else{
                   return cuenta;
               }
           }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println(sql);
        return null;
    }
    
    //Recuperar todos los datos de una cuenta logueada
    public Cuenta getAllDataCuenta(String nroCuenta){
        Cuenta cuenta = new Cuenta();
        String sql = "SELECT * FROM cuenta WHERE cuenta_nro = ?";
        
        try(Connection conn = this.connect();
           PreparedStatement pstmt = conn.prepareStatement(sql)){
           pstmt.setString(1,nroCuenta);
           ResultSet rs = pstmt.executeQuery();
           
           if (rs == null){
               return null;
           }else{
               cuenta.setNroCuenta(nroCuenta);
               cuenta.setEstadoCuenta(rs.getInt("cuenta_est"));
               cuenta.setPinCuenta(rs.getString("cuenta_pin"));
               cuenta.setFechaAlta(convertirFecha(rs.getString("cuenta_fech_alta")));
               cuenta.setIdCuenta(rs.getInt("cuenta_id"));
               cuenta.setSaldoEnCuenta(rs.getDouble("cuenta_Saldo"));
               cuenta.setCliente(getCliente(rs.getInt("cliente_id")));
               
               if (cuenta.getEstadoCuenta()==1){
                   return null;
               }else{
                   return cuenta;
               }
           }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        
        System.out.println(sql);
        return null;
    }
    
    //Recupera un cliente del Cliente actual
    public Cliente getCliente(int idCliente){
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM cliente WHERE cliente_id = ?";
        
        try(Connection conn = this.connect();
           PreparedStatement pstmt = conn.prepareStatement(sql)){
           pstmt.setInt(1, idCliente);
           ResultSet rs = pstmt.executeQuery();
           
           if(rs == null){
               JOptionPane.showMessageDialog(null,"Error, no se encontro el dato en la base de Datos");
               return null;
           }else{
               cliente.setNombre(rs.getString("cliente_nombre"));
               cliente.setApellido(rs.getString("cliente_apellido"));
               cliente.setcIdentidad(rs.getString("cliente_ci"));
               cliente.setfNac(convertirFecha(rs.getString("cliente_fnac")));
               cliente.setEmail(rs.getString("cliente_email"));
               cliente.setDireccion(rs.getString("cliente_direccion"));
               cliente.setTelefono(rs.getString("cliente_telefono"));
               cliente.setCelular(rs.getString("cliente_celular"));
               cliente.setRuc(rs.getString("cliente_ruc"));
               
               return cliente;
           }
           
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    //Conversor de Fechas
    private Date convertirFecha(String fecha) throws ParseException{
        return new SimpleDateFormat("yyyy-MM-dd").parse(fecha.substring(0,fecha.indexOf(" ")));
    }
    //Conversor de Fecha y hora exacta ahora mismo
    public String FormatoFechaHora (Date fecha){
        return DateFormatUtils.format(fecha, "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * FUNCIONES ACTUALIZA DATO DE DEPOSITO
     * @param deposito
     * @param transaccion
     * @param cuentaDestino 
     */
    public void actualizarDatosDeposito(Deposito deposito, Transaccion transaccion, Cuenta cuentaDestino) {
        final java.util.Date date=new java.util.Date();
        addDeposito(deposito,cuentaDestino.getIdCuenta(),date);
        int depositoID = obtenerID(deposito,date,cuentaDestino.getIdCuenta());
        addTransaccion(transaccion,cuentaDestino.getIdCuenta(),depositoID,date);
        actualizarSaldoCuenta(cuentaDestino);
    }
    
    //Agrega un deposito a la Base de Datos 
    private void addDeposito(Deposito deposito,int cuenta_id, Date tiempo){
        
        String sql = "INSERT INTO deposito ( cuenta_id,deposito_tipo,deposito_numeroCheque,deposito_fecha) VALUES (?,?,?,?)";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, cuenta_id);
            pstmt.setString(2, deposito.getTipoDeposito());
            pstmt.setInt(3, deposito.getNroCheque());
            pstmt.setString(4,FormatoFechaHora(tiempo));
            pstmt.executeUpdate();
            
        }catch(SQLException e){
            e.getMessage();
        }
    }
    
    
    //Trae la id de un deposito
    private int obtenerID(Deposito deposito, Date date,int cuenta_id){
        String sql = "SELECT deposito_id FROM deposito WHERE cuenta_id=? AND deposito_tipo=? AND deposito_numeroCheque =? AND deposito_fecha = ?";
        
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, cuenta_id);
            pstmt.setString(2, deposito.getTipoDeposito());
            pstmt.setInt(3, deposito.getNroCheque());
            pstmt.setString(4,FormatoFechaHora(date));
            
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt("deposito_id");
            
        }catch(SQLException e){
            e.getMessage();
        }
        return 0;
    }
    
    //Agregar una transaccion a la Base de Datos
    private void addTransaccion(Transaccion transaccion,int cuenta_id,int deposito_id,Date tiempo){
        String sql = "INSERT INTO transaccion (" +
"                            cuenta_id," +
"                            transaccion_monto," +
"                            transaccion_fech," +
"                            transaccion_desc," +
"                            transaccion_tipo," +
"                            deposito_id" +
"                        )" +
"                        VALUES (?,?,?,?,?,?)";
        
        try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,cuenta_id);
            pstmt.setDouble(2,transaccion.getMonto());
            pstmt.setString(3,FormatoFechaHora(tiempo));
            pstmt.setString(4,transaccion.getDescripcion());
            pstmt.setString(5,transaccion.getTipo());
            pstmt.setInt(6,deposito_id);

            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //Actualizar Saldo de la Cuenta
    private void actualizarSaldoCuenta(Cuenta cuentaDestino) {
        String sql="UPDATE cuenta SET cuenta_saldo = ? WHERE cuenta_id = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, cuentaDestino.getSaldoEnCuenta());
            pstmt.setDouble(2, cuentaDestino.getIdCuenta());
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    /**
     * FUNCIONES DE TRANSACCIONES traerTransacciones
     * @param cuenta_id
     * @return 
     */
    public DefaultTableModel traerTransacciones(int cuenta_id){
        String sql = "SELECT transaccion_id,transaccion_tipo,transaccion_fech,transaccion_desc,transaccion_monto from transaccion WHERE cuenta_id=?";
        DefaultTableModel modeloTabla = new DefaultTableModel();
        
        modeloTabla.addColumn("Nro Transaccion");
        modeloTabla.addColumn("TipoTransaccion");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("monto");
        
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, cuenta_id);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("transaccion_id");
                fila[1] = rs.getString("transaccion_tipo");
                fila[2] = rs.getString("transaccion_fech");
                fila[3] = rs.getString("transaccion_desc");
                fila[4] = rs.getDouble("transaccion_monto");
                
                modeloTabla.addRow(fila);

            }
            
            return modeloTabla;
        }catch(SQLException e){
            e.getMessage();
        }
        return modeloTabla;
    }
    
    /*FUNCIONES PARA TRANSFERENCIAS ENTRE CUENTAS */
    
    public Cuenta getCuentaDestino(String nroCuentaDestino){
        String sql = "SELECT cuenta_id, cuenta_saldo, cuenta_est FROM cuenta WHERE cuenta_nro = ?";
        Cuenta cuentaDest = new Cuenta();
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, nroCuentaDestino);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs == null) {
                return null;
            } else {
                cuentaDest.setNroCuenta(nroCuentaDestino);
                cuentaDest.setIdCuenta(rs.getInt("cuenta_id"));
                cuentaDest.setSaldoEnCuenta(rs.getDouble("cuenta_saldo"));
                cuentaDest.setEstadoCuenta(rs.getInt("cuenta_est"));

                if (cuentaDest.getEstadoCuenta() == 1) {
                    return null;
                } else {
                    return cuentaDest;
                }
            }
        }catch(SQLException e){
            e.getMessage();
        }
        return null;   
    }
    
    public void guardarDatosTransferencia(Transaccion transaccionOrig,Transferencia transferencia,Transaccion transaccionDest){
        final java.util.Date date=new java.util.Date();
        
        addTransferencia(transferencia,date);
        
        int transferencia_id=obtenerIDT(date,transferencia.getCuentaOrigen().getIdCuenta(),transferencia.getCuentaDestino().getIdCuenta());
        addTransaccionT(transaccionOrig,transferencia.getCuentaOrigen().getIdCuenta(),transferencia_id,date );
        addTransaccionT(transaccionDest,transferencia.getCuentaDestino().getIdCuenta(),transferencia_id,date);
        
        actualizarSaldoCuenta(transferencia.getCuentaOrigen());
        actualizarSaldoCuenta(transferencia.getCuentaDestino());
    }
    
        //Trae la id de una transferencia
    private int obtenerIDT(Date date,int cuentaorig_id, int cuentadest_id){
        String sql = "SELECT transferencia_id FROM transferencia WHERE cuenta_id_origen = ? AND cuenta_id_dest = ? AND transferencia_fech = ?";
        
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, cuentaorig_id);
            pstmt.setInt(2, cuentadest_id);
            pstmt.setString(3,FormatoFechaHora(date));
            
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt("transferencia_id");
            
        }catch(SQLException e){
            e.getMessage();
        }
        return 0;
    }
    
    
    private void addTransferencia(Transferencia transferencia, Date tiempo){
        
        String sql = "INSERT INTO transferencia (cuenta_id_origen,cuenta_id_dest,transferencia_fech,pin_transaccion) VALUES ( ?, ?, ?, ?)";
        
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, transferencia.getCuentaOrigen().getIdCuenta());
            pstmt.setInt(2, transferencia.getCuentaDestino().getIdCuenta());
            pstmt.setString(3,FormatoFechaHora(tiempo));
            pstmt.setString(4,transferencia.getPinTransaccion());
            pstmt.executeUpdate();
            
        }catch(SQLException e){
            e.getMessage();
        }
    }
    
    //Agrega Transacciones de Tipo Transferencia
    private void addTransaccionT(Transaccion transaccion,int cuenta_id,int transferencia_id,Date tiempo){
        String sql = "INSERT INTO transaccion (" +
"                            cuenta_id," +
"                            transaccion_monto," +
"                            transaccion_fech," +
"                            transaccion_desc," +
"                            transaccion_tipo," +
"                            transferencia_id" +
"                        )" +
"                        VALUES (?,?,?,?,?,?)";
        
        try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,cuenta_id);
            pstmt.setDouble(2,transaccion.getMonto());
            pstmt.setString(3,FormatoFechaHora(tiempo));
            pstmt.setString(4,transaccion.getDescripcion());
            pstmt.setString(5,transaccion.getTipo());
            pstmt.setInt(6,transferencia_id);

            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
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
}
