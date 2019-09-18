package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cidade;
import model.Cliente;
import model.ClientePF;
import model.ClientePJ;

/**
 *
 * @author assparremberger
 */
public class ClienteDAO {
    
    public static void inserir(ClientePF cliente){
        String query = "INSERT INTO clientes "
            + " (nome, email, tipo, cpf_cnpj, receberEmail, codCidade )"
            + " VALUES ( '" + cliente.getNome()           + "' , "
                     + " '" + cliente.getEmail()          + "' , "
                     + "  " + Cliente.PESSOA_FISICA       + "  , "
                     + " '" + cliente.getCpf()            + "' , "
                     + "  " + cliente.isReceberEmail()    + "  , "
                     + "  " + cliente.getCidade().getId() + " ) ";
        Conexao.executar( query );
    }
    
    public static void inserir(ClientePJ cliente){
        String query = "INSERT INTO clientes "
            + " (nome, email, tipo, cpf_cnpj, receberEmail, codCidade )"
            + " VALUES ( '" + cliente.getNome()           + "' , "
                     + " '" + cliente.getEmail()          + "' , "
                     + "  " + Cliente.PESSOA_JURIDICA     + "  , "
                     + " '" + cliente.getCnpj()           + "' , "
                     + "  " + cliente.isReceberEmail()    + "  , "
                     + "  " + cliente.getCidade().getId() + " ) ";
        Conexao.executar( query );
    }
    
    public static void editar(ClientePF cliente){
        String query = "UPDATE clientes SET "
            + " nome =        '" + cliente.getNome()           + "' , "
            + " email =       '" + cliente.getEmail()          + "' , "
            + " tipo =         " + Cliente.PESSOA_FISICA       + "  , "
            + " cpf_cnjp =    '" + cliente.getCpf()            + "' , "
            + " receberEmail = " + cliente.isReceberEmail()    + "  , "
            + " codCidade =    " + cliente.getCidade().getId() + "    "
            + " WHERE id = " + cliente.getId();
        Conexao.executar( query );
    }
    
    public static void editar(ClientePJ cliente){
        String query = "UPDATE clientes SET "
            + " nome =        '" + cliente.getNome()           + "' , "
            + " email =       '" + cliente.getEmail()          + "' , "
            + " tipo =         " + Cliente.PESSOA_JURIDICA     + "  , "
            + " cpf_cnjp =    '" + cliente.getCnpj()           + "' , "
            + " receberEmail = " + cliente.isReceberEmail()    + "  , "
            + " codCidade =    " + cliente.getCidade().getId() + "    "
            + " WHERE id = " + cliente.getId();
        Conexao.executar( query );
    }
    
    public static void excluir(int idCliente){
        String query = "DELETE FROM clientes "
                    + " WHERE id = " + idCliente;
        Conexao.executar( query );
    }
    
    public static List<ClientePF> getClientesPF(){
        List<ClientePF> lista = new ArrayList<>();
        String query = 
            "SELECT c.id, c.nome, c.email, c.cpf_cnpj, "
                + " c.receberEmail, d.id, d.nome "
                + " FROM clientes c "
                + " INNER JOIN cidades d "
                + " ON c.codcidade = d.id "
                + " WHERE tipo = " + Cliente.PESSOA_FISICA;
        
        ResultSet rs = Conexao.consultar( query );
        if( rs != null){
            try {
                while ( rs.next()  ) {                    
                    Cidade cid = new Cidade();
                    cid.setId( rs.getInt( 6 ) );
                    cid.setNome( rs.getString( 7 ) );
                    
                    ClientePF cliente = new ClientePF();
                    cliente.setTipo( Cliente.PESSOA_FISICA );
                    cliente.setId( rs.getInt( 1 ) );
                    cliente.setNome( rs.getString( 2 ) );
                    cliente.setEmail( rs.getString( 3 ) );
                    cliente.setCpf( rs.getString( 4 ) );
                    if( rs.getInt( 5 ) == 1 ){
                        cliente.setReceberEmail(true);
                    }else{
                        cliente.setReceberEmail(false);
                    }
                    cliente.setCidade( cid );
                    lista.add( cliente );
                }
            } catch (Exception e) {
                
            }
        }
        return lista;
    }
    
    public static List<ClientePJ> getClientesPJ(){
        List<ClientePJ> lista = new ArrayList<>();
        String query = 
            "SELECT c.id, c.nome, c.email, c.cpf_cnpj, "
                + " c.receberEmail, d.id, d.nome "
                + " FROM clientes c "
                + " INNER JOIN cidades d "
                + " ON c.codcidade = d.id "
                + " WHERE tipo = " + Cliente.PESSOA_JURIDICA;
        
        ResultSet rs = Conexao.consultar( query );
        if( rs != null){
            try {
                while ( rs.next()  ) {                    
                    Cidade cid = new Cidade();
                    cid.setId( rs.getInt( 6 ) );
                    cid.setNome( rs.getString( 7 ) );
                    
                    ClientePJ cliente = new ClientePJ();
                    cliente.setTipo( Cliente.PESSOA_JURIDICA );
                    cliente.setId( rs.getInt( 1 ) );
                    cliente.setNome( rs.getString( 2 ) );
                    cliente.setEmail( rs.getString( 3 ) );
                    cliente.setCnpj( rs.getString( 4 ) );
                    if( rs.getInt( 5 ) == 1 ){
                        cliente.setReceberEmail(true);
                    }else{
                        cliente.setReceberEmail(false);
                    }
                    cliente.setCidade( cid );
                    lista.add( cliente );
                }
            } catch (Exception e) {
                
            }
        }
        return lista;
    }
    
    
    public static List<Cliente> getClientes(){
        List<Cliente> lista = new ArrayList<>();
        String query = 
            "SELECT c.id, c.nome, c.email, c.cpf_cnpj, "
                + " c.receberEmail, d.id, d.nome, c.tipo "
                + " FROM clientes c "
                + " INNER JOIN cidades d "
                + " ON c.codcidade = d.id ";
        
        ResultSet rs = Conexao.consultar( query );
        if( rs != null){
            try {
                while ( rs.next()  ) {                    
                    Cidade cid = new Cidade();
                    cid.setId( rs.getInt( 6 ) );
                    cid.setNome( rs.getString( 7 ) );
                    
                    if( rs.getInt( 8 ) == Cliente.PESSOA_FISICA ){
                    
                        ClientePF cliente = new ClientePF();
                        cliente.setTipo( Cliente.PESSOA_FISICA );
                        cliente.setId( rs.getInt( 1 ) );
                        cliente.setNome( rs.getString( 2 ) );
                        cliente.setEmail( rs.getString( 3 ) );
                        cliente.setCpf( rs.getString( 4 ) );
                        if( rs.getInt( 5 ) == 1 ){
                            cliente.setReceberEmail(true);
                        }else{
                            cliente.setReceberEmail(false);
                        }
                        cliente.setCidade( cid );
                        lista.add( cliente );
                    }else{
                        ClientePJ cliente = new ClientePJ();
                        cliente.setTipo( Cliente.PESSOA_JURIDICA );
                        cliente.setId( rs.getInt( 1 ) );
                        cliente.setNome( rs.getString( 2 ) );
                        cliente.setEmail( rs.getString( 3 ) );
                        cliente.setCnpj(rs.getString( 4 ) );
                        if( rs.getInt( 5 ) == 1 ){
                            cliente.setReceberEmail(true);
                        }else{
                            cliente.setReceberEmail(false);
                        }
                        cliente.setCidade( cid );
                        lista.add( cliente );
                    }
                }
            } catch (Exception e) {
                
            }
        }
        return lista;
    }
    
    
}
