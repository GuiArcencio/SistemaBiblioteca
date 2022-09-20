package app.Domain.PacoteUsuarios;

import java.util.Date;

public class Funcionario extends Usuario {

    public Funcionario(Long id){
        this.id = id;
    }

    public Funcionario(Long id, String nome, String telefone, Date dataNascimento, Endereco endereco){
        this.id = id;
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setDataNascimento(dataNascimento);
        this.setEndereco(endereco);
        this.setRole("FUNC");
    }


}
