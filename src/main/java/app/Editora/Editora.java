package main.java.app.Editora;


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
@Table(name="Editora")
@NoArgsConstructor @AllArgsConstructor
public class Editora {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Getter int idEditora;

    @Column(nullable = false, unique = false, length = 256)
	@Getter @Setter String nome;
    
}
