<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Disciplina"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Disciplina</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Disciplina disciplina = (Disciplina) request.getAttribute("Disciplina");
                        String acao = (String) request.getAttribute("acao");
                        
                        // Exibir o título conforme a ação
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Disciplina</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Disciplina</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Disciplina</h1>");
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
                    <form action="/aplicacaoMVC/admin/DisciplinaController" method="POST">
                        <input type="hidden" name="id" value="<%= disciplina != null ? disciplina.getId() : "" %>" class="form-control">

                        <!-- Formulário para Incluir -->
                        <%
                            if ("Incluir".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Nome</label>
                                <input type="text" name="nome" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="requisito" class="form-label">Requisito</label>
                                <input type="text" name="requisito" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="ementa" class="form-label">Ementa</label>
                                <input type="text" name="ementa" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="carga_horaria" class="form-label">Carga Horaria</label>
                                <input type="text" name="carga_horaria" class="form-control" required>
                            </div>
                        <% 
                            // Formulário para Alterar
                            } else if ("Alterar".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Nome</label>
                                <input type="text" name="nome" value="<%= disciplina.getNome() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="requisito" class="form-label">Requisito</label>
                                <input type="text" name="requisito" value="<%= disciplina.getRequisito() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="ementa" class="form-label">Ementa</label>
                                <input type="text" name="ementa" value="<%= disciplina.getEmenta() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="carga_horaria" class="form-label">Carga Horaria</label>
                                <input type="text" name="carga_horaria" value="<%= disciplina.getCarga_horaria() %>" class="form-control" required>
                            </div>
                        <% 
                            // Formulário para Excluir
                            } else if ("Excluir".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Nome</label>
                                <input type="text" name="nome" value="<%= disciplina.getNome() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="requisito" class="form-label">Requisito</label>
                                <input type="text" name="requisito" value="<%= disciplina.getRequisito() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="ementa" class="form-label">Ementa</label>
                                <input type="text" name="ementa" value="<%= disciplina.getEmenta() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="carga_horaria" class="form-label">Carga Horaria</label>
                                <input type="text" name="carga_horaria" value="<%= disciplina.getCarga_horaria() %>" class="form-control" readonly>
                            </div>
                        <% } %>

                        <div>
                            <input type="submit" name="btEnviar" value="<%= acao %>" class="btn btn-primary">
                            <a href="/aplicacaoMVC/admin/DisciplinaController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>
