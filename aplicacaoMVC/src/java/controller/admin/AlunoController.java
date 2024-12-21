package controller.admin;

import entidade.Aluno;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AlunoDAO;

@WebServlet(name = "AlunoController", urlPatterns = {"/admin/AlunoController"})
public class AlunoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        Aluno aluno = new Aluno();
        AlunoDAO alunoDAO = new AlunoDAO();
        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Aluno> listaAlunos = alunoDAO.getAll();
                request.setAttribute("listaAlunos", listaAlunos);

                rd = request.getRequestDispatcher("/views/admin/aluno/listaAluno.jsp");
                rd.forward(request, response);
                break;

            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                aluno = alunoDAO.get(id);

                request.setAttribute("aluno", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("aluno", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("btEnviar");
        Aluno aluno = new Aluno();

        // Pega o ID e os dados do formulário
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            aluno.setId(Integer.parseInt(idParam));
        }
        aluno.setNome(request.getParameter("nome"));
        aluno.setEmail(request.getParameter("email"));
        aluno.setCelular(request.getParameter("celular"));
        aluno.setCpf(request.getParameter("cpf"));
        aluno.setSenha(request.getParameter("senha"));
        aluno.setEndereco(request.getParameter("endereco"));
        aluno.setCidade(request.getParameter("cidade"));
        aluno.setBairro(request.getParameter("bairro"));
        aluno.setCep(request.getParameter("cep"));

        AlunoDAO alunoDAO = new AlunoDAO();

        switch (acao) {
            case "Incluir":
                alunoDAO.insert(aluno);
                response.sendRedirect("/admin/AlunoController?acao=Listar");
                break;

            case "Alterar":
                alunoDAO.update(aluno);
                response.sendRedirect("/admin/AlunoController?acao=Listar");
                break;

            case "Excluir":
                alunoDAO.delete(aluno.getId());
                response.sendRedirect("/admin/AlunoController?acao=Listar");
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Controller para gerenciamento de alunos";
    }
}
