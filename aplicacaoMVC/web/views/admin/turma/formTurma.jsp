<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Turma"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Turma</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Turma turma = (Turma) request.getAttribute("Turma");
                        String acao = (String) request.getAttribute("acao");
                        
                        // Exibir o título conforme a ação
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Turma</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Turma</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Turma</h1>");
                                break;
                        }

                        // Exibir a mensagem de erro, se houver
                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) { %>
                            <div class="alert alert-danger" role="alert">
                                <%= msgError %>
                            </div>
                    <% } %>

                    <!-- Formulário para cada ação -->
                    <form action="/aplicacaoMVC/admin/TurmaController" method="POST">
                        <input type="hidden" name="id" value="<%= turma != null ? turma.getId() : "" %>" class="form-control">

                        <!-- Formulário para Incluir -->
                        <%
                            if ("Incluir".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="professorID" class="form-label">ID do Professor</label>
                                <input type="text" name="professorID" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="disciplinaID" class="form-label">ID da Disciplina</label>
                                <input type="text" name="disciplinaID" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="alunoID" class="form-label">ID do Aluno</label>
                                <input type="text" name="alunoID" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="codigo_turma" class="form-label">Código da Turma</label>
                                <input type="text" name="codigo_turma" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="nota" class="form-label">Nota</label>
                                <input type="text" name="nota" class="form-control" required>
                            </div>
                        <% 
                            // Formulário para Alterar
                            } else if ("Alterar".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="professorID" class="form-label">ID do Professor</label>
                                <input type="text" name="professorID" value="<%= turma.getProfessorID() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="disciplinaID" class="form-label">ID da Disciplina</label>
                                <input type="text" name="disciplinaID" value="<%= turma.getDisciplinaID() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="alunoID" class="form-label">ID do Aluno</label>
                                <input type="text" name="alunoID" value="<%= turma.getAlunoID() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="codigo_turma" class="form-label">Código da Turma</label>
                                <input type="text" name="codigo_turma" value="<%= turma.getCodigo_turma() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="nota" class="form-label">Nota</label>
                                <input type="text" name="nota" value="<%= turma.getNota() %>" class="form-control" required>
                            </div>
                        <% 
                            // Formulário para Excluir
                            } else if ("Excluir".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="professorID" class="form-label">ID do Professor</label>
                                <input type="text" name="professorID" value="<%= turma.getProfessorID() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="disciplinaID" class="form-label">ID da Disciplina</label>
                                <input type="text" name="disciplinaID" value="<%= turma.getDisciplinaID() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="alunoID" class="form-label">ID do Aluno</label>
                                <input type="text" name="alunoID" value="<%= turma.getAlunoID() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="codigo_turma" class="form-label">Código da Turma</label>
                                <input type="text" name="codigo_turma" value="<%= turma.getCodigo_turma() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="nota" class="form-label">Nota</label>
                                <input type="text" name="nota" value="<%= turma.getNota() %>" class="form-control" readonly>
                            </div>
                        <% } %>

                        <div>
                            <input type="submit" name="btEnviar" value="<%= acao %>" class="btn btn-primary">
                            <a href="/aplicacaoMVC/admin/TurmaController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>
