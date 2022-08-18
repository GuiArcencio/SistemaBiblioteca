package main.java.pooa.app.Obra;

import java.util.Date;
import java.util.List;

import main.java.pooa.app.Obra.InterfaceObra;

public class Obra implements InterfaceObra {
	int codigo;
	int isbn;
	CategoriaObra categoria;
	List<Autor> autores;
	List<String> palavrasChave;
	Date dataPublicacao;
	int numeroEdicao;
	String editora;
	int numeroPaginas;
	List<Copia> copias;
	
	public Obra(
		int codigo,
		int isbn,
		CategoriaObra categoria,
		List<Autor> autores,
		List<String> palavrasChave,
		Date dataPublicacao,
		int numeroEdicao,
		String editora,
		int numeroPaginas
	) {
		this.codigo = codigo;
		this.isbn = isbn;
		this.categoria = categoria;
		this.autores = autores;
		this.palavrasChave = palavrasChave;
		this.dataPublicacao = dataPublicacao;
		this.numeroEdicao = numeroEdicao;
		this.editora = editora;
		this.numeroPaginas = numeroPaginas;
	}
	
	@Override
	public void adicionarAutor(String autor) {
		String iniciais = "";
		String[] nomesAutor = autor.split(" ");
		for (int i = 0; i < nomesAutor.length; i++) {
			iniciais = iniciais.concat(nomesAutor[i].substring(0, 1));
		}
		
		this.autores.add(new Autor(autor, iniciais));
	}
	
	@Override
	public void removerAutor(int indice) {
		this.autores.remove(indice);
	}
	
	@Override
	public void adicionarPalavraChave(String palavra) {
		this.palavrasChave.add(palavra);
	}
	
	@Override
	public void removerPalavraChave(int indice) {
		this.palavrasChave.remove(indice);
	}

	@Override
	public void adicionarCopia(int idCopia){
		State state = new Disponivel();
		Copia copia = new Copia(idCopia, state);
		this.copias.add(copia);
	}

	@Override
	public void removerCopia(int idCopia){
		
	}

	@Override
	public void marcarEmprestadoCopia(int idCopia){

	}

	@Override
	public void marcarDevolverCopia(int idCopia){

	}
	
	@Override
	public void marcarDisponivelCopia(int idCopia){

	}
}
