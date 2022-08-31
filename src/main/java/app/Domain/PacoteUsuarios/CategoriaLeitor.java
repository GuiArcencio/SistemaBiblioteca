package app.Domain.PacoteUsuarios;

public class CategoriaLeitor {
    private Long id;
    private int maximoDiasEmprestimo;
    private String descricao;

    public CategoriaLeitor(Long id, int maximoDiasEmprestimo, String descricao){
        this.id = id;
        this.maximoDiasEmprestimo = maximoDiasEmprestimo;
        this.descricao = descricao;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setMaximoDiasEmprestimo(int maximoDiasEmprestimo) {
        this.maximoDiasEmprestimo = maximoDiasEmprestimo;
    }

    public int getMaximoDiasEmprestimo() {
        return this.maximoDiasEmprestimo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
