package main.java.app.Obra;

import java.util.Date;
import java.util.List;
import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import main.java.app.Estados.Disponivel.*;
import main.java.app.CategoriaObra.*;
import main.java.app.Autor.*;
import main.java.app.Copia.*;
import main.java.app.Estados.*;
import main.java.app.Editora.*;

@Entity
@Table(name="Obra")
@NoArgsConstructor @AllArgsConstructor
public class Obra implements InterfaceObra {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Getter int idObra;
	
	@Column(nullable = false, unique = false)
	@Getter @Setter int isbn;

	@Column(nullable = false, unique = false, length = 256)
	@Getter @Setter String titulo;

	@Transient
	CategoriaObra categoria;
	@Transient
	List<Autor> autores;
	@Transient
	List<String> palavrasChave;

	@Column(nullable = false, unique = false)
	@Getter @Setter Date dataPublicacao;

	@Column(nullable = false, unique = false)
	@Getter @Setter int numeroEdicao;

	@Transient
	Editora editora;

	@Column(nullable = false, unique = false)
	@Getter @Setter int numeroPaginas;

	@Transient
	List<Copia> copias;
	
	public Obra(
		int idObra,
		int isbn,
		String titulo,
		CategoriaObra categoria,
		List<Autor> autores,
		List<String> palavrasChave,
		Date dataPublicacao,
		int numeroEdicao,
		Editora editora,
		int numeroPaginas
	) {
		this.idObra = idObra;
		this.titulo = titulo;
		this.isbn = isbn;
		this.categoria = categoria;
		this.autores = autores;
		this.palavrasChave = palavrasChave;
		this.dataPublicacao = dataPublicacao;
		this.numeroEdicao = numeroEdicao;
		this.editora = editora;
		this.numeroPaginas = numeroPaginas;
	}
	
	public Obra(String titulo, int isbn){
		this.titulo = titulo;
		this.isbn = isbn;
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
