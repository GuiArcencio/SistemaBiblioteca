package app.Domain.PacoteObras;


import java.util.Date;
import java.util.List;
import app.Exception.AnnotatedDeserializer.JsonRequired;
//import app.Domain.PacoteObras.Estados.*;


public class Obra {
	Long codigo;

	@JsonRequired
	Long isbn;

    @JsonRequired
	String titulo;

	@JsonRequired
	CategoriaObra categoria;

	@JsonRequired
	List<String> palavrasChave;

	@JsonRequired
	Date dataPublicacao;

	@JsonRequired
	String edicao;

	@JsonRequired
	Editora editora;

	@JsonRequired
	int numPaginas;
	
	public Obra(){

	}

	public Obra(
		Long codigo,
		Long isbn,
		CategoriaObra categoria,
		List<String> palavrasChave,
		Date dataPublicacao,
		String edicao,
		Editora editora,
        String titulo,
		int numeroPaginas
	) {
		this.codigo = codigo;
		this.isbn = isbn;
		this.categoria = categoria;
		this.palavrasChave = palavrasChave;
		this.dataPublicacao = dataPublicacao;
		this.edicao = edicao;
		this.editora = editora;
        this.titulo = titulo;
		this.numPaginas = numeroPaginas;
	}

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return this.codigo;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Long getIsbn() {
        return this.isbn;
    }

    public void setCategoria(CategoriaObra categoria) {
        this.categoria = categoria;
    }
    
    public CategoriaObra getCategoria() {
        return this.categoria;
    }

    public void setPalavrasChave(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }
    
    public List<String> getPalavrasChave() {
        return this.palavrasChave;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    
    public Date getDataPublicacao() {
        return this.dataPublicacao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }
    
    public String getEdicao() {
        return this.edicao;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    
    public Editora getEditora() {
        return this.editora;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getTitulo() {
        return this.titulo;
    }

    public void setNumPaginas(int numeroPaginas) {
        this.numPaginas = numeroPaginas;
    }

    public int getNumPaginas() {
        return this.numPaginas;
    }

	
}
