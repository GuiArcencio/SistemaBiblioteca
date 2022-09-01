package app.Service.impl;

import java.math.BigInteger;
import java.util.List;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import app.Domain.PacoteEntradaSaidaObras.Devolucao;
import app.Domain.PacoteEntradaSaidaObras.Emprestimo;
import app.Domain.PacoteEntradaSaidaObras.Reserva;
import app.Domain.PacoteObras.Copia;
import app.Domain.PacoteObras.Obra;
import app.Domain.PacoteObras.CategoriaObra;
import app.Domain.SubjectObserver.EmprestimoAtrasado;
import app.Domain.SubjectObserver.ReservaExpirada;
import app.Domain.PacoteUsuarios.Funcionario;
import app.Domain.PacoteUsuarios.Leitor;
import app.Service.spec.IEmprestimoService;
import app.dao.EmprestimoDAO;
import app.dao.UsuarioDAO;
import app.dao.ObraDAO;
import app.dao.CopiaDAO;
import app.dao.AutorDAO;
import app.dao.LeitorDAO;
import app.dao.DevolucaoDAO;
import app.dao.ReservaDAO;

public class EmprestimoService implements IEmprestimoService {

    private EmprestimoDAO edao;
    private UsuarioDAO udao;
    private ObraDAO odao;
    private CopiaDAO cdao;
    private AutorDAO adao;
    private LeitorDAO ldao;
    private DevolucaoDAO ddao;
    private ReservaDAO rdao;

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
        List<Devolucao> lista = ddao.getAllByLeitorId(idUsuario);
        return lista;
    }

    @Override
    public Copia emprestarObra(Long idUsuario, Long isbn, Funcionario funcionarioResponsavel) {
        Obra obra = odao.getByIsbn(isbn);
        if (obra == null) {
            //não há obras com esse isbn
            System.out.println("Obra não encontrada! Retornando NULL.");

            return null;
        }
        
        obra.setCopias(cdao.getAllByObraId(obra.getCodigo()));
        obra.setAutores(adao.getAllByObraId(obra.getCodigo()));

        LocalDate dataDevolucao = obra.getCategoria().calculaDataDevolucao();
        java.util.Date dataPrevistaDevolucao = java.util.Date.from(dataDevolucao.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date dataEmprestimo = java.util.Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        Copia copia = null;
        Reserva reserva = null;
        //percorre todas as copias por uma reserva, se nao achar, o emprestimo é feito com a ultima copia achada
        for (Copia copia_i : obra.getCopias()) {
            reserva = rdao.getByLeitorCopia(idUsuario, copia.getId());
            if (reserva != null) {
                copia = copia_i;
                break;
            }
            if (copia_i.getState().getState() == "Disponivel") {
                copia = copia_i;
            }
        }

        if(reserva != null){
            rdao.delete(reserva);
        }

        if(copia == null){
            //não há copias disponiveis
            System.out.println("Não há cópias disponíveis para empréstimo! Retornando NULL.");
            return null;
        }
    

        copia.setState(copia.getState().emprestar());
        
        cdao.update(copia);
        Leitor leitor = ldao.getById(idUsuario);
        if(leitor == null){
            System.out.println("[ERRO] leitor não encontrado! Retornando NULL");
            return null;
        }
        
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
    public Copia reservarObra(Long idUsuario, Long isbn, Date dataRetirada) {
        Obra obra = odao.getByIsbn(isbn);
        if (obra == null) {
            //não há obras com esse isbn
            System.out.println("Obra não encontrada! Retornando NULL.");
            return null;
        }

        obra.setCopias(cdao.getAllByObraId(obra.getCodigo()));
        obra.setAutores(adao.getAllByObraId(obra.getCodigo()));

        java.util.Date dataReserva = java.util.Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        long daysBetween = DAYS.between(dataReserva, dateRetirada);
        if (daysBetween < 0 && daysBetween > 7) {
            System.out.println("[ERRO] data de Retirada deve ser em até 1 semana depois de hoje , retornando null");
            return null;
        }

        LocalDate dataDevolucao = obra.getCategoria().calculaDataDevolucao().plusDays(daysBetween);
        java.util.Date dataPrevistaDevolucao = java.util.Date.from(dataDevolucao.atStartOfDay(ZoneId.systemDefault()).toInstant());

        
        //percorre todas as copias por uma disponivel
        for (Copia copia_i : obra.getCopias()) {
            if (copia_i.getState().getState() == "Disponivel") {
                copia = copia_i;
                break;
            }
        }

        if(copia == null){
            //não há copias disponiveis
            System.out.println("Não há cópias disponíveis para reserva! Retornando NULL.");
            return null;
        }


        copia.setState(copia.getState().reservar());

        cdao.update(copia);
        Leitor leitor = ldao.getById(idUsuario);
        if(leitor == null){
            System.out.println("[ERRO] leitor não encontrado! Retornando NULL");
            return null;
        }

        //obs o id da reserva nao é usado, o bd gera um
        Reserva reserva = new Reserva(1L, dataReserva, dataPrevistaRetirada, dataPrevistaDevolucao, funcionarioResponsavel, leitor, copia);

        new ReservaExpirada(reserva, leitor);

        rdao.insert(reserva);

        return copia;

    }
    
}
