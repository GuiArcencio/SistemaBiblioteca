package app.Service.impl;

import java.util.List;
import app.Domain.PacoteEntradaSaidaObras.Reserva;
import app.Service.spec.IReservaService;
import app.dao.ReservaDAO;

public class ReservaService implements IReservaService{
    
    private ReservaDAO dao;

    public ReservaService(){
        this.dao = new ReservaDAO();
    }
    
    @Override
    public List<Reserva> buscarReservaPorUsuario(Long idUsuario){
        try{
            return dao.getAllByLeitor(idUsuario);
        } catch (Exception e){
            System.out.println("Erro na busca, retornando lista vazia");
            return null;
        }
    }

    @Override
    public Reserva buscarPorLeitorECopia(Long idLeitor, Long idCopia){
        try{
            return dao.getByLeitorAndCopia(idLeitor, idCopia);
        } catch(Exception e){
            return null;
        }
    }

    @Override
    public Reserva buscarPorCopia(Long idCopia){
        try{
            return dao.getByCopia(idCopia);
        } catch(Exception e){
            return null;
        }
    }

    @Override
    public boolean realizarReserva(Reserva Reserva){
        try{
            dao.insert(Reserva);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removerReserva(Long id){
        try{
            Reserva e = dao.getById(id);
            dao.delete(e);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alterarReserva(Long id, Reserva e){

        try{
            if(dao.getById(id) == null){
                System.out.println("Reserva de id:" + id + " não foi encontrada.");
                return false;
            }
            e.setId(id);
            dao.update(e);
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Reserva buscaReserva(Long id){
        try{
            return dao.getById(id);
        }catch(Exception e){
            System.out.println("[ERRO] Reserva de id:" + id + " não foi encontrada. Retornando objeto vazio.");
            return null;
        }
    }
}
