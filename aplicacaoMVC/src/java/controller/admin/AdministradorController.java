package controller.admin;

import entidade.Administrador;  // Alterado de Professor para Administrador
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AdministradorDAO;  // Alterado de ProfessorDAO para AdministradorDAO

@WebServlet(name = "AdministradorController", urlPatterns = {"/admin/AdministradorController"})
public class AdministradorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = (String) request.getParameter("acao");
        Administrador administrador = new Administrador();  // Alterado para Administrador
        AdministradorDAO administradorDAO = new AdministradorDAO();  // Alterado para AdministradorDAO
        RequestDispatcher rd;
        
        switch (acao){
            case "Listar":
                ArrayList<Administrador> listaAdministradores = administradorDAO.getAll();  // Alterado para getAll
                request.setAttribute("listaAdministradores", listaAdministradores);  // Alterado para listaAdministradores

                rd = request.getRequestDispatcher("/views/admin/administrador/listaAdministrador.jsp");  // Alterado para administrador
                rd.forward(request, response);
                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual Administrador será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                administrador = administradorDAO.get(id);  // Alterado para get

                request.setAttribute("administrador", administrador);  // Alterado para administrador
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/administrador/formAdministrador.jsp");  // Alterado para administrador
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("administrador", administrador);  // Alterado para administrador
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/administrador/formAdministrador.jsp");  // Alterado para administrador
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("btEnviar");
        Administrador administrador = new Administrador();  // Alterado para Administrador
        
        // Pega o ID e os dados do formulário
        administrador.setId(Integer.parseInt(request.getParameter("id")));
        administrador.setNome(request.getParameter("nome"));
        administrador.setCpf(request.getParameter("cpf"));
        administrador.setEndereco(request.getParameter("endereco"));
        administrador.setSenha(request.getParameter("senha"));
        
        AdministradorDAO administradorDAO = new AdministradorDAO();  // Alterado para AdministradorDAO
        
        switch (acao) {
            case "Incluir":
                administradorDAO.insert(administrador);  // Alterado para insert
                response.sendRedirect("/aplicacaoMVC/admin/AdministradorController?acao=Listar");  // Alterado para AdministradorController
                break;
            case "Alterar":
                administradorDAO.update(administrador);  // Alterado para update
                response.sendRedirect("/aplicacaoMVC/admin/AdministradorController?acao=Listar");  // Alterado para AdministradorController
                break;
            case "Excluir":
                administradorDAO.delete(administrador.getId());  // Alterado para delete
                response.sendRedirect("/aplicacaoMVC/admin/AdministradorController?acao=Listar");  // Alterado para AdministradorController
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador de Administradores";
    }
}
