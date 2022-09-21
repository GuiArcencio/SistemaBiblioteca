package app.Domain.PacoteObras;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import app.Exception.AnnotatedDeserializer.JsonRequired;
import java.util.Observable;
import java.util.Observer;
//import app.Domain.PacoteObras.Estados.*;


public class Obra extends Observable{
    private List<Observer> canaisComunicacao = new ArrayList<>();
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
        //String status
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
        //this.status = status;
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

    public void notifyObservers(String status) {
        for (Observer observer : this.canaisComunicacao) {
            observer.update(this, status);
        }
    }

    public void registrar(Observer observer) {
        canaisComunicacao.add(observer);
    }
    /*
    public void atualizaStatus(){
        if(this.status == "DISPONIVEL"){
            this.status = "INDISPONIVEL";
            notifyObservers(this.status);
        }
        else{
            this.status = "DISPONIVEL";
            notifyObservers(this.status);
        }
    }
    */

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
        notifyObservers(status);
    }

    public String getStatus(){
        return this.status;
    }

}
