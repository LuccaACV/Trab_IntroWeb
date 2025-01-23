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
        Aluno Aluno = new Aluno();
        AlunoDAO AlunoDAO = new AlunoDAO();
        RequestDispatcher rd;
        switch (acao){
            case "Listar":
                ArrayList<Aluno> listaAlunos = AlunoDAO.getAll();
                request.setAttribute("listaAlunos", listaAlunos);

                rd = request.getRequestDispatcher("/views/admin/aluno/listaAluno.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual Aluno será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                Aluno = AlunoDAO.get(id);

                request.setAttribute("Aluno", Aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("Aluno", Aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                rd.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String acao = request.getParameter("btEnviar");
        Aluno aluno = new Aluno();
        
        // Pega o ID e os dados do formulário
        aluno.setId(Integer.parseInt(request.getParameter("id")));
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
                response.sendRedirect("/aplicacaoMVC/admin/AlunoController?acao=Listar");
                break;
            case "Alterar":
                alunoDAO.update(aluno);
                response.sendRedirect("/aplicacaoMVC/admin/AlunoController?acao=Listar");
                break;
            case "Excluir":
                alunoDAO.delete(aluno.getId());
                response.sendRedirect("/aplicacaoMVC/admin/AlunoController?acao=Listar");
                break;
        }
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
