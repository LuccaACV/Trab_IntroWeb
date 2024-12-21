package controller.admin;

import entidade.Disciplina;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DisciplinaDAO;

@WebServlet(name = "DisciplinaController", urlPatterns = {"/admin/DisciplinaController"})
public class DisciplinaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String acao = (String) request.getParameter("acao");
        Disciplina Disciplina = new Disciplina();
        DisciplinaDAO DisciplinaDAO = new DisciplinaDAO();
        RequestDispatcher rd;
        switch (acao){
            case "Listar":
                ArrayList<Disciplina> listaDisciplinas = DisciplinaDAO.getAll();
                request.setAttribute("listaDisciplinas", listaDisciplinas);

                rd = request.getRequestDispatcher("/views/admin/disciplina/listaDisciplina.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual Disciplina será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                Disciplina = DisciplinaDAO.get(id);

                request.setAttribute("Disciplina", Disciplina);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/disciplina/formDisciplina.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("Disciplina", Disciplina);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/disciplina/formDisciplina.jsp");
                rd.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String acao = request.getParameter("btEnviar");
        Disciplina disciplina = new Disciplina();
        
        // Pega o ID e os dados do formulário
        disciplina.setId(Integer.parseInt(request.getParameter("id")));
        disciplina.setNome(request.getParameter("nome"));
        disciplina.setRequisito(request.getParameter("requisito"));
        disciplina.setEmenta(request.getParameter("ementa"));
        disciplina.setCarga_horaria(Integer.parseInt(request.getParameter("carga_horaria")));
        
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        
        switch (acao) {
            case "Incluir":
                disciplinaDAO.insert(disciplina);
                response.sendRedirect("/aplicacaoMVC/admin/DisciplinaController?acao=Listar");
                break;
            case "Alterar":
                disciplinaDAO.update(disciplina);
                response.sendRedirect("/aplicacaoMVC/admin/DisciplinaController?acao=Listar");
                break;
            case "Excluir":
                disciplinaDAO.delete(disciplina.getId());
                response.sendRedirect("/aplicacaoMVC/admin/DisciplinaController?acao=Listar");
                break;
        }
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
