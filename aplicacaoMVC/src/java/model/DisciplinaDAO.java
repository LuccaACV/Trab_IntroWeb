package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Disciplina;

public class DisciplinaDAO implements Dao<Disciplina> {

    @Override
    public Disciplina get(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Disciplina WHERE ID = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Disciplina disciplina = new Disciplina();

            if (resultado != null) {
                while (resultado.next()) {
                    disciplina.setId(Integer.parseInt(resultado.getString("ID")));
                    disciplina.setNome(resultado.getString("NOME"));
                    disciplina.setRequisito(resultado.getString("REQUISITO"));
                    disciplina.setEmenta(resultado.getString("EMENTA"));
                    disciplina.setCarga_horaria(Short.parseShort(resultado.getString("CARGA_HORARIA")));

                }
            }
            return disciplina;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Disciplina t) {
        Conexao conexao = new Conexao();
        try {
            
            String sqlQuery = "INSERT INTO disciplina (nome, requisito, ementa, carga_horaria) VALUES (?, ?, ?, ?)";
            PreparedStatement sql = conexao.getConexao().prepareStatement(sqlQuery);

            sql.setString(1, t.getNome());
            sql.setString(2, t.getRequisito());
            sql.setString(3, t.getEmenta());
            sql.setInt(4, t.getCarga_horaria());

            // Exibindo o SQL antes de executar para depuração
            System.out.println("Executando SQL: " + sqlQuery);

            int rowsAffected = sql.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Nenhuma linha foi inserida.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de insert (disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Disciplina t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Disciplina SET nome = ?, requisito = ?, ementa = ?, carga_horaria = ? WHERE ID = ?");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getRequisito());
            sql.setString(3, t.getEmenta());
            sql.setInt(4, t.getCarga_horaria());
            sql.setInt(5, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Disciplina> getAll() {
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT id, nome, requisito, ementa, carga_horaria FROM Disciplina";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Disciplina disciplina = new Disciplina(
                            resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("requisito"),
                            resultado.getString("ementa"),
                            resultado.getInt("carga_horaria")
                    );
                    disciplinas.add(disciplina);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (getAll) incorreta" + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return disciplinas;
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Disciplina WHERE ID = ?");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}
