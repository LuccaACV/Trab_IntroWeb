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
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Alunos WHERE id = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Aluno aluno = new Aluno();

            if (resultado != null) {
                while (resultado.next()) {
                    aluno.setId(Integer.parseInt(resultado.getString("id")));
                    aluno.setNome(resultado.getString("nome"));
                    aluno.setEmail(resultado.getString("email"));
                    aluno.setCelular(resultado.getString("celular"));
                    aluno.setCpf(resultado.getString("cpf"));
                    aluno.setSenha(resultado.getString("senha"));
                    aluno.setEndereco(resultado.getString("endereco"));
                    aluno.setCidade(resultado.getString("cidade"));
                    aluno.setBairro(resultado.getString("bairro"));
                    aluno.setCep(resultado.getString("cep"));
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
            
            String sqlQuery = "INSERT INTO alunos (nome, email, celular, cpf, senha, endereco, cidade, bairro, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);

            sql.setString(1, t.getNome());
            sql.setString(2, t.getEmail());
            sql.setString(3, t.getCelular());
            sql.setString(4, t.getCpf());
            sql.setString(5, t.getSenha());
            sql.setString(6, t.getEndereco());
            sql.setString(7, t.getCidade());
            sql.setString(8, t.getBairro());
            sql.setString(9, t.getCep());

            // Exibindo o SQL antes de executar para depuração
            System.out.println("Executando SQL: " + sqlQuery);

            int rowsAffected = sql.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhuma linha foi inserida.");
            }
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Alunos SET nome = ?, email = ?, celular = ?, cpf = ?, senha = ?, endereco = ?, cidade = ?, bairro = ?, cep = ? WHERE ID = ?");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getEmail());
            sql.setString(3, t.getCelular());
            sql.setString(4, t.getCpf());
            sql.setString(5, t.getSenha());
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
            throw new RuntimeException("Query de select (getAll) incorreta" + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return alunos;
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Alunos WHERE id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public Aluno Logar(Aluno aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM alunos WHERE cpf = ? AND senha = ? LIMIT 1");
            sql.setString(1, aluno.getCpf());
            sql.setString(2, aluno.getSenha());
            ResultSet resultado = sql.executeQuery();
            if (resultado != null && resultado.next()) {
                aluno.setId(resultado.getInt("id"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setEmail(resultado.getString("email"));
                aluno.setCelular(resultado.getString("celular"));
                aluno.setCpf(resultado.getString("cpf"));
                aluno.setSenha(resultado.getString("senha"));
                aluno.setEndereco(resultado.getString("endereco"));
                aluno.setCidade(resultado.getString("cidade"));
                aluno.setBairro(resultado.getString("bairro"));
                aluno.setCep(resultado.getString("cep"));
            }
            return aluno;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao realizar login", e);
        } finally {
            conexao.closeConexao();
        }
    }
}
