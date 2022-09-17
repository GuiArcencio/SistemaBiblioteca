package app.Service.spec;

import java.util.List;

import app.Domain.PacoteUsuarios.Endereco;
public interface IEnderecoService {
    
    List<Endereco> buscaEnderecos();
    boolean insereEndereco(Endereco e);
    boolean removeEndereco (Long id);
    boolean alteraEndereco(Long id, Endereco e);
    Endereco buscaEnderecoPorId(Long id);
}
