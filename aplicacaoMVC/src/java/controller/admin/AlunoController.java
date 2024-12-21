/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

        String acao = (String) request.getParameter("acao");
        Aluno aluno = new Aluno();
        AlunoDAO alunoDAO = new AlunoDAO();
        RequestDispatcher rd;
        
        switch (acao) {
            case "Listar":
                // Listar todos os alunos
                ArrayList<Aluno> listaAlunos = alunoDAO.getAll();
                request.setAttribute("listaAlunos", listaAlunos);

                rd = request.getRequestDispatcher("/views/admin/aluno/listaAluno.jsp");
                rd.forward(request, response);
                break;

            case "Alterar":
            case "Excluir":
                // get parâmetro ação indicando qual aluno será alterado ou excluído
                int id = Integer.parseInt(request.getParameter("id"));
                aluno = alunoDAO.get(id);

                request.setAttribute("aluno", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                // Incluir um novo aluno
                request.setAttribute("aluno", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                rd.forward(request, response);
                break;

            default:
                response.sendRedirect("/admin/alunos"); // Redireciona para a lista de alunos
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno;
        RequestDispatcher rd;

        if ("Salvar".equals(acao)) {
            // Obtendo os dados do formulário
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String celular = request.getParameter("celular");
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            String endereco = request.getParameter("endereco");
            String cidade = request.getParameter("cidade");
            String bairro = request.getParameter("bairro");
            String cep = request.getParameter("cep");

            // Criando o objeto Aluno
            aluno = new Aluno(0, nome, email, celular, cpf, senha, endereco, cidade, bairro, cep);

            // Salvar ou atualizar o aluno
            if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
                int id = Integer.parseInt(request.getParameter("id"));
                aluno.setId(id);
                alunoDAO.update(aluno); // Atualiza o aluno
            } else {
                alunoDAO.insert(aluno); // Insere um novo aluno
            }

            // Redirecionar para a lista de alunos
            response.sendRedirect("/admin/alunos");
        }
    }

    @Override
    public String getServletInfo() {
        return "Controle de alunos para administração.";
    }
}

