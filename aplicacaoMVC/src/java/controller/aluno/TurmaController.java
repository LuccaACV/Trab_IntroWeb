package controller.aluno;

import entidade.Turma;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TurmaDAO;

@WebServlet(name = "TurmaController", urlPatterns = {"/aluno/TurmaController"})
public class TurmaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String acao = (String) request.getParameter("acao");
        Turma Turma = new Turma();
        TurmaDAO TurmaDAO = new TurmaDAO();
        RequestDispatcher rd;
        switch (acao){
            case "Listar":
                ArrayList<Turma> listaTurmas = TurmaDAO.getAll();
                request.setAttribute("listaTurmas", listaTurmas);

                rd = request.getRequestDispatcher("/views/aluno/turma/listaTurma.jsp");
                rd.forward(request, response);
                break;
                
            case "Sair":
                int id = Integer.parseInt(request.getParameter("id"));
                Turma = TurmaDAO.get(id);

                request.setAttribute("Turma", Turma);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/aluno/turma/listaTurma.jsp");
                rd.forward(request, response);
                break;
                
            case "Entrar":
                request.setAttribute("Turma", Turma);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/aluno/turma/listaTurma.jsp");
                rd.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String acao = request.getParameter("btEnviar");
        Turma turma = new Turma();
        
        // Pega o ID e os dados do formulário
        turma.setId(Integer.parseInt(request.getParameter("id")));
        turma.setProfessorID(Integer.parseInt(request.getParameter("professorID")));
        turma.setDisciplinaID(Integer.parseInt(request.getParameter("disciplinaID")));
        turma.setAlunoID(Integer.parseInt(request.getParameter("alunoID")));
        turma.setCodigo_turma(request.getParameter("codigo_turma"));
        turma.setNota(Float.parseFloat(request.getParameter("nota")));
        
        TurmaDAO turmaDAO = new TurmaDAO();
        
        switch (acao) {
            case "Incluir":
                turmaDAO.insert(turma);
                response.sendRedirect("/aplicacaoMVC/aluno/TurmaController?acao=Listar");
                break;
            case "Alterar":
                turmaDAO.update(turma);
                response.sendRedirect("/aplicacaoMVC/aluno/TurmaController?acao=Listar");
                break;
            case "Excluir":
                turmaDAO.delete(turma.getId());
                response.sendRedirect("/aplicacaoMVC/aluno/TurmaController?acao=Listar");
                break;
        }
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

