package controller.admin;

import entidade.Professor;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProfessorDAO;

@WebServlet(name = "ProfessorController", urlPatterns = {"/admin/ProfessorController"})
public class ProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String acao = (String) request.getParameter("acao");
        Professor Professor = new Professor();
        ProfessorDAO ProfessorDAO = new ProfessorDAO();
        RequestDispatcher rd;
        switch (acao){
            case "Listar":
                ArrayList<Professor> listaProfessores = ProfessorDAO.getAll();
                request.setAttribute("listaProfessores", listaProfessores);

                rd = request.getRequestDispatcher("/views/admin/professor/listaProfessor.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual Professor será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                Professor = ProfessorDAO.get(id);

                request.setAttribute("Professor", Professor);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("Professor", Professor);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp");
                rd.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String acao = request.getParameter("btEnviar");
        Professor professor = new Professor();
        
        // Pega o ID e os dados do formulário
        professor.setId(Integer.parseInt(request.getParameter("id")));
        professor.setNome(request.getParameter("nome"));
        professor.setEmail(request.getParameter("email"));
        professor.setCpf(request.getParameter("cpf"));
        professor.setSenha(request.getParameter("senha"));
        
        ProfessorDAO professorDAO = new ProfessorDAO();
        
        switch (acao) {
            case "Incluir":
                professorDAO.insert(professor);
                response.sendRedirect("/aplicacaoMVC/admin/ProfessorController?acao=Listar");
                break;
            case "Alterar":
                professorDAO.update(professor);
                response.sendRedirect("/aplicacaoMVC/admin/ProfessorController?acao=Listar");
                break;
            case "Excluir":
                professorDAO.delete(professor.getId());
                response.sendRedirect("/aplicacaoMVC/admin/ProfessorController?acao=Listar");
                break;
        }
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
