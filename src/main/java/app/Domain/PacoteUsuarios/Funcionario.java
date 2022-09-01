package app.Domain.PacoteUsuarios;

public class Funcionario extends Usuario {

    public boolean devolverObraLeitor(Leitor leitor, Long obraId){
        return true;
    }

    public boolean reservarObraLeitor(Leitor leitor, Long obraId){
        return true;
    }

    public boolean emprestarObraLeitor(Leitor leitor, Long obraId){
        return true;
    }

    public boolean alterarEmprestimo(Leitor leitor, Long obraId){
        return true;
    }

    public boolean alterarDevolucao(Leitor leitor, Long obraId){
        return true;
    }

    public boolean alterarReserva(Leitor leitor, Long obraId){
        return true;
    }
}
