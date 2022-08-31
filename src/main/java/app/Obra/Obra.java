package app.Obra;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import app.Subject.Subject;

import app.CategoriaObra.*;
import app.Autor.*;
import app.Copia.*;
import app.Estados.*;

public class Obra extends Subject {
	int codigo;
	BigInteger isbn;
	CategoriaObra categoria;
	List<Autor> autores;
	List<String> palavrasChave;
	Date dataPublicacao;
	String Edicao;
	String editora;
	int numeroPaginas;
	List<Copia> copias;
	
	public Obra(
		int codigo,
		BigInteger isbn,
		CategoriaObra categoria,
		List<Autor> autores,
		List<String> palavrasChave,
		Date dataPublicacao,
		String Edicao,
		String editora,
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
		this.numeroPaginas = numeroPaginas;
	}
	
	public void adicionarAutor(String autor) {
		String iniciais = "";
		String[] nomesAutor = autor.split(" ");
		for (int i = 0; i < nomesAutor.length; i++) {
			iniciais = iniciais.concat(nomesAutor[i].substring(0, 1));
		}
		
		this.autores.add(new Autor(autor, iniciais));
	}
	
	public void removerAutor(int indice) {
		this.autores.remove(indice);
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
