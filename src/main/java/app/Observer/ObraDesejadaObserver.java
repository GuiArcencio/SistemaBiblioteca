package app.Observer;


import app.Leitor.Leitor;
import app.Subject.Subject;

public class ObraDesejadaObserver extends Observer {
    private Leitor leitorInteressado;

    public ObraDesejadaObserver(Subject subject, Leitor leitor){
        this.subject = subject;
        this.leitorInteressado = leitor;
        this.subject.attach(this);
    }

    @Override
    public void update(){
        //Adicionar função para enviar email aqui
        System.out.println("Leitor " + leitorInteressado.getNome() + " deseja uma obra");
    }
}
