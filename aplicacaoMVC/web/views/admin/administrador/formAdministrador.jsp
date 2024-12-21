<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Administrador"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Administrador</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Administrador administrador = (Administrador) request.getAttribute("Administrador");
                        String acao = (String) request.getAttribute("acao");
                        
                        // Exibir o título conforme a ação
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Administrador</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Administrador</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Administrador</h1>");
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
                    <form action="/aplicacaoMVC/admin/AdministradorController" method="POST">
                        <input type="hidden" name="id" value="<%= administrador != null ? administrador.getId() : "" %>" class="form-control">

                        <!-- Formulário para Incluir -->
                        <%
                            if ("Incluir".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Nome</label>
                                <input type="text" name="nome" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="cpf" class="form-label">CPF</label>
                                <input type="text" name="cpf" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="senha" class="form-label">Senha</label>
                                <input type="text" name="senha" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="aprovado" class="form-label">Aprovado</label>
                                <input type="text" name="aprovado" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="endereço" class="form-label">Endereço</label>
                                <input type="text" name="endereço" class="form-control" required>
                            </div>
                        <% 
                            // Formulário para Alterar
                            } else if ("Alterar".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Nome</label>
                                <input type="text" name="nome" value="<%= administrador.getNome() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="cpf" class="form-label">CPF</label>
                                <input type="text" name="cpf" value="<%= administrador.getCpf() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="senha" class="form-label">Senha</label>
                                <input type="text" name="senha" value="<%= administrador.getSenha() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="aprovado" class="form-label">Aprovado</label>
                                <input type="text" name="aprovado" value="<%= administrador.getAprovado() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="endereço" class="form-label">Endereço</label>
                                <input type="text" name="endereço" class="form-control" value="<%= administrador.getEndereco() %>"required>
                            </div>
                        <% 
                            // Formulário para Excluir
                            } else if ("Excluir".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Nome</label>
                                <input type="text" name="nome" value="<%= administrador.getNome() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="cpf" class="form-label">CPF</label>
                                <input type="text" name="cpf" value="<%= administrador.getCpf() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="senha" class="form-label">Senha</label>
                                <input type="text" name="senha" value="<%= administrador.getCpf() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="aprovado" class="form-label">Aprovado</label>
                                <input type="text" name="aprovado" value="<%= administrador.getAprovado() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="endereço" class="form-label">Endereço</label>
                                <input type="text" name="endereço" value="<%= administrador.getEndereco() %>" class="form-control" readonly>
                            </div>
                        <% } %>

                        <div>
                            <input type="submit" name="btEnviar" value="<%= acao %>" class="btn btn-primary">
                            <a href="/aplicacaoMVC/admin/AdministradorController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>
