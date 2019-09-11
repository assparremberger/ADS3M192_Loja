package dao;

import model.Cliente;
import model.ClientePF;

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
}
