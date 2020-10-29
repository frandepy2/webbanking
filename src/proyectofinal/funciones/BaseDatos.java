package proyectofinal.funciones;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
               JOptionPane.showMessageDialog(null,"Que idiota cargo mal la base de datos?");
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
    
    private Date convertirFecha(String fecha) throws ParseException{
        return new SimpleDateFormat("yyyy-MM-dd").parse(fecha.substring(0,fecha.indexOf(" ")));
    }
    
}
