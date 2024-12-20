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
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
