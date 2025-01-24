<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.ArrayList, entidade.Administrador" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Relatório de Disciplinas e Alunos</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">
                <%
                    Administrador administradorLogado = (Administrador) session.getAttribute("usuario");
                    out.println("<h3>Administrador logado com sucesso</h3>");
                    out.println("<h2>Nome: " + administradorLogado.getNome() + "</h2>");
                %>
                
                <h1>Relatório de Disciplinas, Turmas e Alunos</h1>

                <%
                    // Recupera o relatório passado como atributo de requisição
                    ArrayList<String> relatorio = (ArrayList<String>) request.getAttribute("relatorio");

                    // Exibe os dados de forma organizada
                    if (relatorio != null && !relatorio.isEmpty()) {
                        String currentDisciplina = "";
                        String currentTurma = "";
                        String currentProfessor = "";

                        for (String linha : relatorio) {
                            // Verifica se a linha pertence a uma nova disciplina/turma/professor
                            String[] partes = linha.split(", ");
                            String disciplina = partes[0].replace("Disciplina: ", "");
                            String turma = partes[1].replace("Turma: ", "");
                            String professor = partes[2].replace("Professor: ", "");

                            // Organiza a exibição de forma hierárquica
                            if (!disciplina.equals(currentDisciplina)) {
                                if (!currentDisciplina.isEmpty()) {
                                    out.println("</ul>");
                                }
                                out.println("<h2>Disciplina: " + disciplina + "</h2>");
                                currentDisciplina = disciplina;
                            }
                            if (!turma.equals(currentTurma)) {
                                out.println("<h4>Turma: " + turma + "</h4>");
                                currentTurma = turma;
                            }
                            if (!professor.equals(currentProfessor)) {
                                out.println("<h5>Professor: " + professor + "</h5>");
                                currentProfessor = professor;
                            }

                            // Exibe o aluno e a nota
                            String aluno = partes[3].replace("Aluno: ", "");
                            String nota = partes[4].replace("Nota: ", "");
                            out.println("<ul class=\"list-group mt-3\"><li class=\"list-group-item d-flex justify-content-between align-items-center\">");
                            out.println("<span>Aluno: " + aluno + "</span>");
                            out.println("<span>Nota: " + nota + "</span></li></ul>");
                        }
                    } else {
                %>
                        <div class="alert alert-warning mt-3" role="alert">
                            Nenhuma disciplina ou aluno foi encontrado para exibição.
                        </div>
                <%
                    }
                %>

            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
