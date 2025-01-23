<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Administrador, entidade.Aluno"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/aplicacaoMVC/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                    // Testar se está logado
                    HttpSession sessao = request.getSession(false); // false não cria uma nova sessão
                    if (sessao != null) { // Se a sessão existir
                    
                        // Recupera o tipo de usuário e o objeto usuário da sessão
                        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
                        
                        // Verifica o tipo de usuário e exibe o nome correspondente
                        if ("administrador".equals(tipoUsuario)){
                %>  
                    <a class="nav-link" href="/aplicacaoMVC/admin/dashboard">Dashboard</a>
                    <a class="nav-link" href="/aplicacaoMVC/admin/AdministradorController?acao=Listar">Administrador</a>
                    <a class="nav-link" href="/aplicacaoMVC/admin/AlunoController?acao=Listar">Aluno</a>
                    <a class="nav-link" href="/aplicacaoMVC/admin/ProfessorController?acao=Listar">Professor</a>
                    <a class="nav-link" href="/aplicacaoMVC/admin/DisciplinaController?acao=Listar">Disciplina</a>
                    <a class="nav-link" href="/aplicacaoMVC/admin/TurmaController?acao=Listar">Turma</a>
                    <a class="nav-link" href="/aplicacaoMVC/admin/logOut">Logout</a>
                <%      
                    } else if ("aluno".equals(tipoUsuario)) {
                %>
                    <a class="nav-link" href="/aplicacaoMVC/aluno/TurmaController?acao=Listar">Disciplinas/Turmas</a>
                    <a class="nav-link" href="/aplicacaoMVC/aluno/AlunoController?acao=Listar">Historico</a>
                    <a class="nav-link" href="/aplicacaoMVC/aluno/logOut">Logout</a>
                <%
                        } else { // Se não houver administrador logado
                %>
                    <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login</a>
                <%
                        }
                   } else { // Caso a sessão não exista
                %>
                <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login</a>
            <%
                }
            %>
            </div>
        </div>
    </div>
</nav>
