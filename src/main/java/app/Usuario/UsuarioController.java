package main.java.app.Usuario;

import org.mindrot.jbcrypt.*;
import static main.java.app.Application.usuarioDao;

public class UsuarioController {

    // Authenticate the Usuario by hashing the inputted password using the stored salt,
    // then comparing the generated hashed password to the stored hashed password
    public static boolean authenticate(String Usuarioname, String password) {
        if (Usuarioname.isEmpty() || password.isEmpty()) {
            return false;
        }
        Usuario usuario = usuarioDao.getUsuarioByUsuarioname(Usuarioname);
        if (usuario == null) {
            return false;
        }
        String hashedPassword = BCrypt.hashpw(password, Usuario.getSalt());
        return hashedPassword.equals(Usuario.getHashedPassword());
    }

    
}
