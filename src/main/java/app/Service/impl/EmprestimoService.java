package app.Service.impl;

import java.math.BigInteger;
import java.util.List;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import app.Domain.PacoteEntradaSaidaObras.Devolucao;
import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.Obra;
import app.Domain.PacoteObras.CategoriaObra;
import app.Domain.SubjectObserver.EmprestimoAtrasado;
import app.Domain.PacoteUsuarios.Funcionario;
import app.Domain.PacoteUsuarios.Leitor;
import app.Service.spec.IEmprestimoService;
import app.dao.EmprestimoDAO;
import app.dao.UsuarioDAO;
import app.dao.ObraDAO;
import app.dao.CopiaDAO;
import app.dao.AutorDAO;
import app.dao.LeitorDAO;

public class EmprestimoService implements IEmprestimoService {

    private EmprestimoDAO edao;
    private UsuarioDAO udao;
    private ObraDAO odao;
    private CopiaDAO cdao;
    private AutorDAO adao;
    private LeitorDAO ldao;

    public EmprestimoService() {
        this.edao = new EmprestimoDAO();
    }

    @Override
    public List<Emprestimo> buscaEmprestimos(Long idUsuario) {
        List<Emprestimo> lista = edao.getByLeitor(idUsuario);
        return lista;
    }

    @Override
    public List<Devolucao> buscaDevolucoes(Long idUsuario) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Copia emprestarObra(Long idUsuario, Long isbn, Funcionario funcionarioResponsavel) {
        Obra obra = odao.getByIsbn(isbn);
        if (obra == null) {
            //não há obras com esse isbn
            return null;
        }
        
        obra.setCopias(cdao.getAllByObraId(obra.getCodigo()));
        obra.setAutores(adao.getAllByObraId(obra.getCodigo()));

        LocalDate dataDevolucao = obra.getCategoria().calculaDataDevolucao();
        java.util.Date dataPrevistaDevolucao = java.util.Date.from(dataDevolucao.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date dataEmprestimo = java.util.Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        Copia copia = null;
        for (Copia copia_i : obra.getCopias()) {
            if (copia_i.getState().getState() == "Disponivel") {
                copia = copia_i;
                break;
            }
        }

        if(copia == null){
            //não há copias disponiveis
            return null;
        }

        copia.setState(copia.getState().emprestar());
        
        cdao.update(copia);
        Leitor leitor = ldao.getById(idUsuario);
        
        //obs o id do emprestimo nao é usado, o bd gera um
        Emprestimo emprestimo = new Emprestimo(1L, dataEmprestimo, dataPrevistaDevolucao, funcionarioResponsavel, copia, leitor, false);

        new EmprestimoAtrasado(emprestimo, leitor);
        
        edao.insert(emprestimo);
        return copia;
    }

    @Override
    public Copia devolverObra(Long idUsuario, Long isbn) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Copia reservarObra(Long idUsuario, Long isbn) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
