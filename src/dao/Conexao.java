package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author assparremberger
 */
public class Conexao {
    
    private static final String URL = 
            "jdbc:mysql://localhost:3306/loja?zeroDateTimeBehavior=convertToNull";
    private static final String USER = "root";
    private static final String SENHA = "";
    private static final String DRIVER = 
            "com.mysql.jdbc.Driver";
    
    
    public static void executar(String query){
        try {
            Class.forName(DRIVER);
            Connection conn = 
                DriverManager.getConnection(URL, USER, SENHA);
            Statement st = conn.createStatement();
            st.execute(query);
        //    conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    public static ResultSet consultar(String query){
        try {
            Class.forName(DRIVER);
            Connection conn = 
                DriverManager.getConnection(URL, USER, SENHA);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( query );
           // conn.close();
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }
    
    
}
