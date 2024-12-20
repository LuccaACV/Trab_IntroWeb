package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Professor;

public class ProfessorDAO implements Dao<Professor> {

    @Override
    public Professor get(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Professores WHERE ID = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Professor professor = new Professor();

            if (resultado != null) {
                while (resultado.next()) {
                    professor.setId(Integer.parseInt(resultado.getString("ID")));
                    professor.setNome(resultado.getString("NOME"));
                    professor.setEmail(resultado.getString("EMAIL"));
                    professor.setCpf(resultado.getString("CPF"));
                    professor.setSenha(resultado.getString("SENHA"));
                }
            }
            return professor;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get professor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Professor t) {
        Conexao conexao = new Conexao();
        try {
            System.out.println("Query: UPDATE Professores SET nome = " + t.getNome() + 
                   ", email = " + t.getEmail() + 
                   ", cpf = " + t.getCpf() + 
                   ", senha = " + t.getSenha() + 
                   " WHERE ID = " + t.getId());
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Professores (nome, email, cpf, senha) VALUES (?,?,?,?)");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getEmail());
            sql.setString(3, t.getCpf());
            sql.setString(4, t.getSenha());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de insert (professor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Professor t) {
        Conexao conexao = new Conexao();
        try {
            System.out.println("Query: UPDATE Professores SET nome = " + t.getNome() + 
                   ", email = " + t.getEmail() + 
                   ", cpf = " + t.getCpf() + 
                   ", senha = " + t.getSenha() + 
                   " WHERE ID = " + t.getId());

            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Professores SET nome = ?, email = ?, cpf = ?, senha = ? WHERE ID = ?");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getEmail());
            sql.setString(3, t.getCpf());
            sql.setString(4, t.getSenha());
            sql.setInt(5, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar professor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Professor> getAll() {
        ArrayList<Professor> professores = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT id, nome, email, cpf, senha FROM Professores";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Professor professor = new Professor(
                            resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("email"),
                            resultado.getString("cpf"),
                            resultado.getString("senha")
                    );
                    professores.add(professor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (getAll) incorreta" + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return professores;
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Professores WHERE ID = ?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir professor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}
