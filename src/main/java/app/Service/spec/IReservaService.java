package app.Service.spec;



import app.Domain.PacoteEntradaSaidaObras.Reserva;

public interface IReservaService {
    Reserva buscaReserva(Long id);
    boolean realizarReserva(Reserva Reserva);
    boolean alterarReserva(Long id, Reserva Reserva);
    boolean removerReserva(Long id);
    public Reserva buscarPorLeitorECopia(Long idLeitor, Long idCopia);
}
