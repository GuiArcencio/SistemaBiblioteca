package app.Service.spec;

import java.util.List;

import app.Domain.PacoteObras.CategoriaObra;

public interface ICategoriaObraService {
    List<CategoriaObra> buscaCategoriasObra();
    boolean insereCategoriaObra(CategoriaObra categoria);
    boolean removeCategoriaObra(Long codigo);
    boolean alteraCategoriaObra(Long codigo, CategoriaObra categoria);
    CategoriaObra buscaCategoriaObraPorCodigo(Long codigo);
}
