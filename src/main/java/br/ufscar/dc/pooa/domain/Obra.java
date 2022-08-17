package main.java.br.ufscar.dc.pooa.domain;

import java.util.Date;
import java.util.List;

public class Obra {
	int codigo;
	int isbn;
	CategoriaObra categoria;
	List<Autor> autores;
	List<String> palavrasChave;
	Date dataPublicacao;
	int numeroEdicao;
	String editora;
	int numeroPaginas;
	Copia[] copias;
	
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
}
