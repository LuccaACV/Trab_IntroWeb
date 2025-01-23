package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Turma;

public class TurmaDAO {

    public Turma get(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "SELECT * FROM Turmas WHERE id = ?"
            );
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                return new Turma(
                    resultado.getInt("id"),
                    resultado.getInt("professor_id"),
                    resultado.getInt("disciplina_id"),
                    resultado.getInt("aluno_id"),
                    resultado.getString("codigo_turma"),
                    resultado.getFloat("nota")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar turma pelo ID: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public void insert(Turma t) {
        Conexao conexao = new Conexao();
        try {
            String sqlQuery = "INSERT INTO Turmas (professor_id, disciplina_id, aluno_id, codigo_turma, nota) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);

            sql.setInt(1, t.getProfessorID());
            sql.setInt(2, t.getDisciplinaID());
            sql.setInt(3, t.getAlunoID());
            sql.setString(4, t.getCodigo_turma());
            sql.setFloat(5, t.getNota());

            int rowsAffected = sql.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhuma linha foi inserida.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir turma: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public void update(Turma t) {
        Conexao conexao = new Conexao();
        try {
            String sqlQuery = "UPDATE Turmas SET professor_id = ?, disciplina_id = ?, aluno_id = ?, codigo_turma = ?, nota = ? WHERE id = ?";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);

            sql.setInt(1, t.getProfessorID());
            sql.setInt(2, t.getDisciplinaID());
            sql.setInt(3, t.getAlunoID());
            sql.setString(4, t.getCodigo_turma());
            sql.setFloat(5, t.getNota());
            sql.setInt(6, t.getId());

            int rowsAffected = sql.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhuma linha foi atualizada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar turma: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Turma> getAll() {
        ArrayList<Turma> turmas = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Turmas";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                Turma turma = new Turma(
                    resultado.getInt("id"),
                    resultado.getInt("professor_id"),
                    resultado.getInt("disciplina_id"),
                    resultado.getInt("aluno_id"),
                    resultado.getString("codigo_turma"),
                    resultado.getFloat("nota")
                );
                turmas.add(turma);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as turmas: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return turmas;
    }

    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                "DELETE FROM Turmas WHERE id = ?"
            );
            sql.setInt(1, id);
            int rowsAffected = sql.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhuma linha foi excluída.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir turma: " + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }
}
