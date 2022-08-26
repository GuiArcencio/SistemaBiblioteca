package main.java.app.Usuario;

import com.google.common.collect.*;
import java.util.*;
import java.util.stream.*;

public class UsuarioDAO {

    private final List<Usuario> Usuarios = ImmutableList.of(
            //        Usuarioname    Salt for hash                    Hashed password (the password is "password" for all Usuarios)
            new Usuario("perwendel", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO"),
            new Usuario("davidase",  "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy"),
            new Usuario("federico",  "$2a$10$E3DgchtVry3qlYlzJCsyxe", "$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2")
    );

    public Usuario getUsuarioByUsuarioname(String Usuarioname) {
        return Usuarios.stream().filter(b -> b.getUsername().equals(Usuarioname)).findFirst().orElse(null);
    }

    public Iterable<String> getAllUserNames() {
        return Usuarios.stream().map(Usuario::getUsername).collect(Collectors.toList());
    }

}
