package app.Domain.PacoteUsuarios;

<<<<<<< Updated upstream
import app.Domain.PacoteUsuarios.Endereco;
import app.Exception.AnnotatedDeserializer.JsonRequired;

=======
//import app.Domain.PacoteUsuarios.Endereco;
>>>>>>> Stashed changes
import java.util.Date;

public abstract class Usuario {
    protected Long id;
<<<<<<< Updated upstream

    @JsonRequired
    private String nome;

    @JsonRequired
    private String telefone;

    @JsonRequired
    private Date dataNascimento;

    @JsonRequired
    private Endereco endereco;

    @JsonRequired
    private String role;
=======
    protected String nome;
    protected String telefone;
    protected Date dataNascimento;
    protected Endereco endereco;
    protected String role;
>>>>>>> Stashed changes

    public Usuario() {

    }

    public Usuario(Long id, String nome, String telefone, Date dataNascimento, Endereco endereco, String role){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.role = role;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
