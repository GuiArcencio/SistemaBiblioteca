package app.Service.spec;

import java.util.List;

import app.Domain.PacoteObras.Autor;

public interface IAutorService {
    List<Autor> listaAutores();
    boolean insereAutor(Autor Autor);
    boolean removerAutor(Long id);
    boolean alterarAutor(Long id, Autor Autor);
}
