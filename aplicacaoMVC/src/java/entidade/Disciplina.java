package entidade;

public class Disciplina {
    private int id;
    private String nome;
    private String requisito;
    private String ementa;
    private int carga_horaria;


   public Disciplina(){
   }
   
   public Disciplina(int id, String nome, String requisito, String ementa, int carga_horaria) {
       this.id = id;
       this.nome = nome;
       this.requisito = requisito;
       this.ementa = ementa;
       this.carga_horaria = carga_horaria;
   }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   
    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
}
