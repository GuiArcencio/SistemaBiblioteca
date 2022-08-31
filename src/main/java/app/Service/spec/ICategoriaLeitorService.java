package app.Service.spec;

import java.util.List;

import app.Domain.PacoteUsuarios.CategoriaLeitor;

public interface ICategoriaLeitorService {
    List<CategoriaLeitor> buscaCategorias();
    boolean insereCategoria(CategoriaLeitor categoria);
    boolean removerCategoria(Long id);
    boolean alterarCategoria(Long id, CategoriaLeitor categoria);
}
