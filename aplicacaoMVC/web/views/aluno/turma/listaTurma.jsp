<%@page import="entidade.Turma"%> 
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Turmas</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">
                <h1>Lista de Turmas</h1>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Professor ID</th>
                                <th scope="col">Disciplina ID</th>
                                <th scope="col">Aluno ID</th>
                                <th scope="col">Código da Turma</th>
                                <th scope="col">Nota</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Turma> listaTurmas = (ArrayList<Turma>) request.getAttribute("listaTurmas");

                                for (Turma turma : listaTurmas) {
                            %>
                            <tr>
                                <td><%= turma.getId() %></td>
                                <td><%= turma.getProfessorID() %></td>
                                <td><%= turma.getDisciplinaID() %></td>
                                <td><%= turma.getAlunoID() %></td>
                                <td><%= turma.getCodigo_turma() %></td>
                                <td><%= turma.getNota() %></td>
                                <td>
                                    <a href="/aplicacaoMVC/aluno/TurmaController?acao=Alterar&id=<%= turma.getId() %>" class="btn btn-warning">Entrar</a>
                                    <a href="/aplicacaoMVC/aluno/TurmaController?acao=Excluir&id=<%= turma.getId() %>" class="btn btn-danger">Sair</a>
                                </td>
                            </tr>
                            <% 
                                } 
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
