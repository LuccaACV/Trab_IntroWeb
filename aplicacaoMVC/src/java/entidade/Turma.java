/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

public class Turma {
    private int id;
    private int professorID;
    private int disciplinaID;
    private int alunoID;
    private String codigo_turma;
    private float nota;
    
    public Turma(){}
    
    public Turma(int id, int professorID, int disciplinaID, int alunoID, String codigo_turma, float nota) {
       this.id = id;
       this.professorID = professorID;
       this.disciplinaID = disciplinaID;
       this.alunoID = alunoID;
       this.codigo_turma = codigo_turma;
       this.nota = nota;
   }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getProfessorID() {
        return professorID;
    }

    public void setProfessorID(int professorID) {
        this.professorID = professorID;
    }
   
    public int getDisciplinaID() {
        return disciplinaID;
    }

    public void setDisciplinaID(int disciplinaID) {
        this.disciplinaID = disciplinaID;
    }

    public int getAlunoID() {
        return alunoID;
    }

    public void setAlunoID(int alunoID) {
        this.alunoID = alunoID;
    }

    public String getCodigo_turma() {
        return codigo_turma;
    }

    public void setCodigo_turma(String codigo_turma) {
        this.codigo_turma = codigo_turma;
    }
    
    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
}
