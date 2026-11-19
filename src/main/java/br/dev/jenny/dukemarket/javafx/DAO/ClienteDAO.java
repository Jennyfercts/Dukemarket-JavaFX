package br.dev.jenny.dukemarket.javafx.DAO;

import br.dev.jenny.dukemarket.javafx.model.Cliente;
import br.dev.jenny.dukemarket.javafx.model.Produto;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Realiza as alterações na tabela
 * @author sesi2dia
 */
public class ClienteDAO {
    
    private static final String SQL_INSERT = "INSERT INTO cliente(nome, "
            + "cpf, endereço, cidade, uf, dataCadastro) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL = "SELECT * FROM cliente";

    private static final String SQL_SELECT_ID = "SELECT * FROM cliente "
            + "WHERE id = ?";

    private static final String SQL_UPDATE = "UPDATE cliente SET nome = ?,"
            + "cpf = ?, endereço = ?, cidade = ?, uf = ?"
            + "WHERE id = ?";

    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id = ?";
    
    
    
    /**
     * Insere um cliente no banco de dados
     * @param c
     */
    public void create(Cliente c) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEndereco());
            stmt.setString(4, c.getCidade());
            stmt.setString(5, c.getUf());
            stmt.setString(6, c.getDataCadastroISO8601());

            // Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.INFO, "Inclusao: {0} {1}", auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            // Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    
    
    /**
     * Retorna todos os registros da tabela cliente
     * @return
     */
    public List<Cliente> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente c = null;
        List<Cliente> listaClientes = null;

        try {
            // Prepara a string de SELECT e executa a query.
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            // Carrega os dados do ResultSet rs, converte em Cliente e
            // adiciona na lista de retorno.
            listaClientes = new ArrayList<>();

            while (rs.next()) {
                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEndereco(rs.getString("endereço"));
                c.setCidade(rs.getString("cidade"));
                c.setUf(rs.getString("uf"));
                
                
                c.setDataCadastro(rs.getString("dataCadastro"));
                listaClientes.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaClientes;
    }
    
    
     /**
     * Retorna um cliente da tabela cliente.
     * @param id IDentificação do Cliente
     * @return
     */
    public Cliente getResultById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente c = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEndereco(rs.getString("endereço"));
                c.setCidade(rs.getString("cidade"));
                c.setUf(rs.getString("uf"));
                c.setDataCadastro(rs.getString("dataCadastro"));
            }

        } catch (SQLException sQLException) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            // Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt, rs);
        }

        return c;
    }
    
    
    /**
     * Atualiza um registro na tabela cliente.
     * @param p cliente a ser atualizado.
     */
    public void update(Cliente c) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEndereco());
            stmt.setString(4, c.getCidade());
            stmt.setString(5, c.getUf());
            
            stmt.setInt(6, c.getId());

            // Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.INFO, "Update: {0}", auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            // Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    
}
