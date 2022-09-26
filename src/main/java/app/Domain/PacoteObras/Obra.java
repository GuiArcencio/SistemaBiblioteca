package app.Domain.PacoteObras;


import java.util.Date;
import java.util.List;
import app.Exception.AnnotatedDeserializer.JsonRequired;
import app.Domain.SubjectObserver.Subject;


public class Obra extends Subject{
	private Long codigo;

	@JsonRequired
	private Long isbn;

    @JsonRequired
	private String titulo;

	@JsonRequired
	private CategoriaObra categoria;

	@JsonRequired
	private List<String> palavrasChave;

	@JsonRequired
	private Date dataPublicacao;

	@JsonRequired
	private String edicao;

	@JsonRequired
	private Editora editora;

	@JsonRequired
	private int numPaginas;

    @JsonRequired
    private String status;
	
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

    public Obra(
		Long codigo,
		Long isbn,
		CategoriaObra categoria,
		List<String> palavrasChave,
		Date dataPublicacao,
		String edicao,
		Editora editora,
        String titulo,
		int numeroPaginas,
        String status
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
        this.status = status;
	}


    //Setters e Getters//
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

    public void setStatus(String status){
        this.status = status;
        this.notifyAllObservers();
    }

    public String getStatus(){
        return this.status;
    }
    
}
