package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cidade;

/**
 *
 * @author assparremberger
 */
public class CidadeDAO {
    
    public static void inserir(Cidade cidade){
        String query = "INSERT INTO cidades ( nome ) "
                + " VALUES ('" + cidade.getNome() + "'); ";
        Conexao.executar( query );
    }
    
    public static void editar(Cidade cidade){
        String query = "UPDATE cidades SET "
                     + " nome =  '" + cidade.getNome() + "' "
                     + " WHERE id = " + cidade.getId();
        Conexao.executar( query );
    }
    
    public static void excluir(int idCidade){
        String query = "DELETE FROM cidades  "
                     + " WHERE id = " + idCidade;
        Conexao.executar( query );
    }
    
    public static List<Cidade> getCidades(){
        List<Cidade> lista = new ArrayList<>();
        String query = "SELECT id, nome FROM cidades ORDER BY nome";
        ResultSet rs = Conexao.consultar( query );
        
        if( rs != null ){
           
            try {
                while ( rs.next() ) {                    
                    Cidade cid = new Cidade();
                    cid.setId( rs.getInt( 1 ) );
                    cid.setNome( rs.getString( 2 ) );
                    lista.add(cid);
                }
            } catch (Exception e) {
                
            }
        }
        return lista;
    }
    
    public static Cidade getCidadeById( int idCidade ){
 
        String query = "SELECT id, nome FROM cidades "
                     + " WHERE id = " + idCidade;
        ResultSet rs = Conexao.consultar( query );
        if( rs != null ){
            try {
                rs.next();                   
                Cidade cid = new Cidade();
                cid.setId( rs.getInt( 1 ) );
                cid.setNome( rs.getString( 2 ) );
                return cid;
            } catch (Exception e) {
                return null;
            }
        }else{
            return null;
        }
    }
    
}
