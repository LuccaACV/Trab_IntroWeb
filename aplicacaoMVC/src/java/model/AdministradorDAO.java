package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Administrador;

public class AdministradorDAO implements Dao<Administrador> {

    @Override
    public Administrador get(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM administrador WHERE id = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Administrador administrador = new Administrador();
            if (resultado != null && resultado.next()) {
                administrador.setId(resultado.getInt("id"));
                administrador.setNome(resultado.getString("nome"));
                administrador.setCpf(resultado.getString("cpf"));
                administrador.setEndereco(resultado.getString("endereco"));
                administrador.setSenha(resultado.getString("senha"));
                administrador.setAprovado(resultado.getString("aprovado"));
            }
            return administrador;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar administrador", e);
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Administrador t) {
        Conexao conexao = new Conexao();
        try {
            String sqlQuery = "INSERT INTO administrador (nome, cpf, endereco, senha, aprovado) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);

            sql.setString(1, t.getNome());
            sql.setString(2, t.getCpf());
            sql.setString(3, t.getEndereco());
            sql.setString(4, t.getSenha());
            sql.setString(5, t.getAprovado());

            // Exibindo o SQL antes de executar para depuração
            System.out.println("Executando SQL: " + sqlQuery);

            int rowsAffected = sql.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhuma linha foi inserida.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir administrador", e);
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Administrador t) {
        Conexao conexao = new Conexao();
        try {
            String sqlQuery = "UPDATE administrador SET nome = ?, cpf = ?, endereco = ?, senha = ?, aprovado = ? WHERE id = ?";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);

            sql.setString(1, t.getNome());
            sql.setString(2, t.getCpf());
            sql.setString(3, t.getEndereco());
            sql.setString(4, t.getSenha());
            sql.setString(5, t.getAprovado());
            sql.setInt(6, t.getId());

            int rowsAffected = sql.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Erro ao atualizar administrador.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar administrador", e);
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM administrador WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir administrador", e);
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Administrador> getAll() {
        ArrayList<Administrador> administradores = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM administrador ORDER BY nome";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            while (resultado.next()) {
                Administrador administrador = new Administrador();
                administrador.setId(resultado.getInt("id"));
                administrador.setNome(resultado.getString("nome"));
                administrador.setCpf(resultado.getString("cpf"));
                administrador.setEndereco(resultado.getString("endereco"));
                administrador.setSenha(resultado.getString("senha"));
                administrador.setAprovado(resultado.getString("aprovado"));
                administradores.add(administrador);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar administradores", e);
        } finally {
            conexao.closeConexao();
        }
        return administradores;
    }

    public Administrador Logar(Administrador administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM administrador WHERE cpf = ? AND senha = ? LIMIT 1");
            sql.setString(1, administrador.getCpf());
            sql.setString(2, administrador.getSenha());
            ResultSet resultado = sql.executeQuery();
            if (resultado != null && resultado.next()) {
                administrador.setId(resultado.getInt("id"));
                administrador.setNome(resultado.getString("nome"));
                administrador.setCpf(resultado.getString("cpf"));
                administrador.setEndereco(resultado.getString("endereco"));
                administrador.setSenha(resultado.getString("senha"));
                administrador.setAprovado(resultado.getString("aprovado"));
            }
            return administrador;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao realizar login", e);
        } finally {
            conexao.closeConexao();
        }
    }
}
