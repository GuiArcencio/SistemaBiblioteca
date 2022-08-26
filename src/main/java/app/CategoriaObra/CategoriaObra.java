package main.java.app.CategoriaObra;


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
@Table(name="CategoriaObra")
@NoArgsConstructor @AllArgsConstructor
public class CategoriaObra {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Getter int idCategoriaCopia;

	@Column(nullable = false, unique = false, length=100)
	@Getter @Setter String descricao;

	@Column(nullable = false, unique = false)
	@Getter @Setter int maximoDiasEmprestimo;

	@Column(nullable = false, unique = false)
	@Getter @Setter double taxaMulta;

	
	
	
}
