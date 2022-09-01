package app.Domain.PacoteUsuarios;

import java.util.Date;

public class Funcionario extends Usuario {

    public Funcionario(Long id){
        this.id = id;
    }

    public Funcionario(Long id, String nome, String telefone, Date dataNascimento, Endereco endereco, String role){
        this.id = id;
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setDataNascimento(dataNascimento);
        this.setEndereco(endereco);
        this.setRole(role);
    }

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
