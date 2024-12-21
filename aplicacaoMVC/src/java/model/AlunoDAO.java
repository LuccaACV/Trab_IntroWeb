package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Aluno;

public class AlunoDAO implements Dao<Aluno> {

    @Override
    public Aluno get(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Alunos WHERE ID = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Aluno aluno = new Aluno();

            if (resultado != null) {
                while (resultado.next()) {
                    aluno.setId(resultado.getInt("ID"));
                    aluno.setNome(resultado.getString("NOME"));
                    aluno.setEmail(resultado.getString("EMAIL"));
                    aluno.setCelular(resultado.getString("CELULAR"));
                    aluno.setCpf(resultado.getString("CPF"));
                    aluno.setSenha(resultado.getString("SENHA"));
                    aluno.setEndereco(resultado.getString("ENDERECO"));
                    aluno.setCidade(resultado.getString("CIDADE"));
                    aluno.setBairro(resultado.getString("BAIRRO"));
                    aluno.setCep(resultado.getString("CEP"));
                }
            }
            return aluno;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Aluno t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "INSERT INTO Alunos (nome, email, celular, cpf, senha, endereco, cidade, bairro, cep) VALUES (?,?,?,?,?,?,?,?,?)");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getEmail());
            sql.setString(3, t.getCelular());
            sql.setString(4, t.getCpf());
            sql.setString(5, t.getSenha());  // Incluindo senha
            sql.setString(6, t.getEndereco());
            sql.setString(7, t.getCidade());
            sql.setString(8, t.getBairro());
            sql.setString(9, t.getCep());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de insert (aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Aluno t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "UPDATE Alunos SET nome = ?, email = ?, celular = ?, cpf = ?, senha = ?, endereco = ?, cidade = ?, bairro = ?, cep = ? WHERE ID = ?");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getEmail());
            sql.setString(3, t.getCelular());
            sql.setString(4, t.getCpf());
            sql.setString(5, t.getSenha());  // Incluindo senha
            sql.setString(6, t.getEndereco());
            sql.setString(7, t.getCidade());
            sql.setString(8, t.getBairro());
            sql.setString(9, t.getCep());
            sql.setInt(10, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Aluno> getAll() {
        ArrayList<Aluno> alunos = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT id, nome, email, celular, cpf, senha, endereco, cidade, bairro, cep FROM Alunos";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Aluno aluno = new Aluno(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("email"),
                        resultado.getString("celular"),
                        resultado.getString("cpf"),
                        resultado.getString("senha"),
                        resultado.getString("endereco"),
                        resultado.getString("cidade"),
                        resultado.getString("bairro"),
                        resultado.getString("cep")
                    );
                    alunos.add(aluno);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (getAll alunos) incorreta" + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return alunos;
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Alunos WHERE ID = ?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}
