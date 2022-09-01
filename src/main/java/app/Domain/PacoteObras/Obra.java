package app.Domain.PacoteObras;


import java.util.Date;
import java.util.List;
import app.Domain.SubjectObserver.Subject;
import app.Exception.AnnotatedDeserializer.JsonRequired;
import app.Domain.PacoteObras.Estados.*;


public class Obra extends Subject {
	Long codigo;

	@JsonRequired
	Long isbn;

	@JsonRequired
	CategoriaObra categoria;

	@JsonRequired
	List<Autor> autores;

	@JsonRequired
	List<String> palavrasChave;

	@JsonRequired
	Date dataPublicacao;
	
	@JsonRequired
	String titulo;

	@JsonRequired
	String Edicao;

	@JsonRequired
	Editora editora;

	@JsonRequired
	int numeroPaginas;

	@JsonRequired
	List<Copia> copias;
	
	public Obra(){

	}

	public Obra(
		Long codigo,
		Long isbn,
		CategoriaObra categoria,
		List<Autor> autores,
		List<String> palavrasChave,
		Date dataPublicacao,
		String Edicao,
		Editora editora,
        String titulo,
		int numeroPaginas
	) {
		this.codigo = codigo;
		this.isbn = isbn;
		this.categoria = categoria;
		this.autores = autores;
		this.palavrasChave = palavrasChave;
		this.dataPublicacao = dataPublicacao;
		this.Edicao = Edicao;
		this.editora = editora;
        this.titulo = titulo;
		this.numeroPaginas = numeroPaginas;
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

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
    
    public List<Autor> getAutores() {
        return this.autores;
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

    public void setEdicao(String Edicao) {
        this.Edicao = Edicao;
    }
    
    public String getEdicao() {
        return this.Edicao;
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

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return this.numeroPaginas;
    }

    public void setCopias(List<Copia> copias) {
        this.copias = copias;
    }

    public List<Copia> getCopias() {
        return this.copias;
    }
	
	
	public void adicionarPalavraChave(String palavra) {
		this.palavrasChave.add(palavra);
	}
	
	public void removerPalavraChave(int indice) {
		this.palavrasChave.remove(indice);
	}

	public void adicionarCopia(Long idCopia){
		State state = Disponivel.getInstancia();
		Copia copia = new Copia(state, idCopia);
		this.copias.add(copia);
        this.notifyAllObservers();
	}

	public void removerCopia(Long idCopia){
		
	}

	public void marcarEmprestadoCopia(Long idCopia){

	}

	public void marcarDevolverCopia(Long idCopia){
        this.notifyAllObservers();
	}
	
	public void marcarDisponivelCopia(Long idCopia){
        this.notifyAllObservers();
	}
}
