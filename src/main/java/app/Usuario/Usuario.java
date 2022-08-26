package main.java.app.Usuario;

import java.util.Date;
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
@Table(name="Usuario")
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Getter int idUsuario;

    @Column(nullable = false, unique = false, length = 256)
	@Getter @Setter String nome;

    @Column(nullable = false, unique = false, length = 256)
	@Getter @Setter String telefone;

    @Column(nullable = false, unique = false)
	@Getter @Setter Date dataNascimento;

	@Column(nullable = false, unique = true, length = 256)
	@Getter @Setter String username;

    @Getter static String salt;
    @Getter static String hashedPassword;


    public Usuario(String username, String salt, String hashedPassword){
        this.username = username;
        Usuario.salt = salt;
        Usuario.hashedPassword = hashedPassword;
    }
}
