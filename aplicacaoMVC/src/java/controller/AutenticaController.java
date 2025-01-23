package controller;

import entidade.Administrador;
import entidade.Aluno;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AdministradorDAO;
import model.AlunoDAO;

@WebServlet(name = "AutenticaController", urlPatterns = {"/AutenticaController"})
public class AutenticaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // Pegando os parâmetros do request
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");

        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            // Dados não foram preenchidos, retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha incorretos");
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            rd.forward(request, response);
            return;
        }

        // Tentativa de login para Administrador
        Administrador administradorObtido;
        Administrador administrador = new Administrador(cpf_user, senha_user);
        AdministradorDAO administradorDAO = new AdministradorDAO();

        try {
            administradorObtido = administradorDAO.Logar(administrador);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Falha na query do Admin para Logar");
        }

        if (administradorObtido != null && administradorObtido.getId() != 0) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", administradorObtido);
            session.setAttribute("tipoUsuario", "administrador");

            rd = request.getRequestDispatcher("/admin/dashboard");
            rd.forward(request, response);
            return;
        }

        // Tentativa de login para Aluno
        Aluno alunoObtido;
        Aluno aluno = new Aluno(cpf_user, senha_user);
        AlunoDAO alunoDAO = new AlunoDAO();

        try {
            alunoObtido = alunoDAO.Logar(aluno);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Falha na query do ALuno para Logar");
        }

        if (alunoObtido != null && alunoObtido.getId() != 0) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", alunoObtido);
            session.setAttribute("tipoUsuario", "aluno");

            rd = request.getRequestDispatcher("/aluno/dashboard");
            rd.forward(request, response);
            return;
        }
    }
}
