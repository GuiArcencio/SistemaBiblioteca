package app.Service.spec;

import java.util.List;

import app.Domain.PacoteEntradaSaidaObras.Reserva;

public interface IReservaService {
    Reserva buscaReserva(Long id);
    boolean realizarReserva(Reserva Reserva);
    boolean alterarReserva(Long id, Reserva Reserva);
    boolean removerReserva(Long id);
    Reserva buscarPorLeitorECopia(Long idLeitor, Long idCopia);
    List<Reserva> buscarReservaPorUsuario(Long idUsuario);
    Reserva buscarPorCopia(Long idCopia);
}
