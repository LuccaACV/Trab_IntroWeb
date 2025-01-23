<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Aluno"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Aluno</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Aluno aluno = (Aluno) request.getAttribute("Aluno");
                        String acao = (String) request.getAttribute("acao");
                        
                        // Exibir o título conforme a ação
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Aluno</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Aluno</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Aluno</h1>");
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
                    <form action="/aplicacaoMVC/admin/AlunoController" method="POST">
                        <input type="hidden" name="id" value="<%= aluno != null ? aluno.getId() : "" %>" class="form-control">

                        <!-- Formulário para Incluir -->
                        <%
                            if ("Incluir".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Nome</label>
                                <input type="text" name="nome" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">E-mail</label>
                                <input type="text" name="email" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Celular</label>
                                <input type="text" name="celular" class="form-control" required>
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
                                <label for="email" class="form-label">Endereço</label>
                                <input type="text" name="endereco" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Cidade</label>
                                <input type="text" name="cidade" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Bairro</label>
                                <input type="text" name="bairro" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">CEP</label>
                                <input type="text" name="cep" class="form-control" required>
                            </div>
                        <% 
                            // Formulário para Alterar
                            } else if ("Alterar".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Nome</label>
                                <input type="text" name="nome" value="<%= aluno.getNome() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="nome" class="form-label">E-mail</label>
                                <input type="text" name="email" value="<%= aluno.getEmail() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Celular</label>
                                <input type="text" name="celular" value="<%= aluno.getCelular() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="nome" class="form-label">CPF</label>
                                <input type="text" name="cpf" value="<%= aluno.getCpf() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Senha</label>
                                <input type="text" name="senha" value="<%= aluno.getSenha() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Endereço</label>
                                <input type="text" name="endereco" value="<%= aluno.getEndereco() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Cidade</label>
                                <input type="text" name="cidade" value="<%= aluno.getCidade() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Bairro</label>
                                <input type="text" name="bairro" value="<%= aluno.getBairro() %>" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label for="nome" class="form-label">CEP</label>
                                <input type="text" name="cep" value="<%= aluno.getCep() %>" class="form-control" required>
                            </div>
                            
                        <% 
                            // Formulário para Excluir
                            } else if ("Excluir".equals(acao)) {
                        %>
                            <div class="mb-3">
                                <label for="nome" class="form-label">Nome</label>
                                <input type="text" name="nome" value="<%= aluno.getNome() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">E-mail</label>
                                <input type="text" name="email" value="<%= aluno.getEmail() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Celular</label>
                                <input type="text" name="celular" value="<%= aluno.getCelular() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="cpf" class="form-label">CPF</label>
                                <input type="text" name="cpf" value="<%= aluno.getCpf() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="senha" class="form-label">Senha</label>
                                <input type="text" name="senha" value="<%= aluno.getSenha() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Endereço</label>
                                <input type="text" name="endereco" value="<%= aluno.getEndereco() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Cidade</label>
                                <input type="text" name="cidade" value="<%= aluno.getCidade() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Bairro</label>
                                <input type="text" name="bairro" value="<%= aluno.getBairro() %>" class="form-control" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">CEP</label>
                                <input type="text" name="cep" value="<%= aluno.getCep() %>" class="form-control" readonly>
                            </div>
                        <% } %>

                        <div>
                            <input type="submit" name="btEnviar" value="<%= acao %>" class="btn btn-primary">
                            <a href="/aplicacaoMVC/admin/AlunoController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>