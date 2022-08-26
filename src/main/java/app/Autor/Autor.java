package main.java.app.Autor;


import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="Autor")
@NoArgsConstructor @AllArgsConstructor
public class Autor {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Getter int idAutor;

	@Column(nullable = false, unique = false, length = 256)
	@Getter @Setter String nome;

	@Column(nullable = false, unique = false, length = 256)
	@Getter @Setter String iniciais;
	
	//@Override
	public Autor(String nome, String iniciais){
		this.nome = nome;
		this.iniciais = iniciais;
	}
	
	
}
