package app.Domain.SubjectObserver;


import app.Domain.PacoteUsuarios.Leitor;
import app.Exception.AnnotatedDeserializer.JsonRequired;

public class EmprestimoAtrasado extends Observer {

    @JsonRequired
    private Leitor leitor;

    public EmprestimoAtrasado(Subject subject, Leitor leitor){
        
        this.subject = subject;
        this.leitor = leitor;
        this.subject.attach(this);
    }

    @Override
    public void update(){
        //Adicionar função para enviar email aqui
        System.out.println("Enviar para " + leitor.getEmail() + " seu emprestimo está atrasado");
    }
}

