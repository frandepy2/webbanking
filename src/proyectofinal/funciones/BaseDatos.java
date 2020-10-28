package proyectofinal.funciones;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class BaseDatos {
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
    
    public void selectUsuarios(){
        String sql = "SELECT cuenta_id ,cuenta_saldo FROM cuenta";
        
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()){
                System.out.println(rs.getInt("cuenta_id")+"\t"+
                                   rs.getDouble("cuenta_saldo"));
                
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }   
    }
    
    public static void main(String[]args){
        BaseDatos bd = new BaseDatos();
        bd.selectUsuarios();
    }
    
}
