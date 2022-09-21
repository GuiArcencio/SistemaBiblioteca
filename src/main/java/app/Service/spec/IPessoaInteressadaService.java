package app.Service.spec;

import java.util.List;

import app.Domain.PacoteUsuarios.Leitor;
import app.Domain.PacoteUsuarios.PessoaInteressada;

public interface IPessoaInteressadaService {

    
    List<Leitor> buscaPessoasPorObra(Long obraCodigo);
    boolean insere(PessoaInteressada pi);
    boolean remove(Long id);
    boolean atualiza(Long id, PessoaInteressada pi);
    List<PessoaInteressada> buscaTodos();
    
}
