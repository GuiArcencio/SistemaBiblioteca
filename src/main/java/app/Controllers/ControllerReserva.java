package app.Controllers;

public class ControllerReserva {
    /*
    @Override
    public Copia devolverObra(Long idUsuario, Long codigoCopia) {
        Emprestimo emprestimo = edao.getByLeitorAndCopia(idUsuario, codigoCopia);

        if(emprestimo == null){
            System.out.println("Emprestimo não encontrado");
            return null;
        }
        Obra obra = odao.getByCodigo(emprestimo.getCopia().getId());
        

        java.util.Date dataDevolucao = java.util.Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        Devolucao devolucao = new Devolucao(1L, dataDevolucao, multaTotal, emprestimoCorrespondente)
    }

    @Override
    public Copia reservarObra(Long idUsuario, Long isbn, java.util.Date dataRetirada, Funcionario funcionarioResponsavel) {
        Obra obra = odao.getByIsbn(isbn);
        if (obra == null) {
            //não há obras com esse isbn
            System.out.println("Obra não encontrada! Retornando NULL.");
            return null;
        }

        obra.setCopias(cdao.getAllByObraId(obra.getCodigo()));
        obra.setAutores(adao.getAllByObraId(obra.getCodigo()));

        java.util.Date dataReserva = java.util.Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        
        long diffInMillies = Math.abs(dataRetirada.getTime() - dataReserva.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)   

        long daysBetween = ChronoUnit.DAYS.between(dataReserva, dataRetirada);
        if (daysBetween < 0 && daysBetween > 7) {
            System.out.println("[ERRO] data de Retirada deve ser em até 1 semana depois de hoje , retornando null");
            return null;
        }
        
        
        
        LocalDate dataDevolucao = obra.getCategoria().calculaDataDevolucao().plusDays(0L);
        java.util.Date dataPrevistaDevolucao = java.util.Date.from(dataDevolucao.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Copia copia = null;
        //percorre todas as copias por uma disponivel
        for (Copia copia_i : obra.getCopias()) {
            Reserva reserva = rdao.getByLeitorAndCopia(idUsuario, copia_i.getId());

            if (reserva != null){
                System.out.println("[Erro] Já há uma reserva feita para esse usuário e ess obra");
                return null;
            }
            if (copia_i.getState().getState() == "Disponivel") {
                copia = copia_i;
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
        Reserva reserva = new Reserva(1L, dataReserva, dataRetirada, dataPrevistaDevolucao, funcionarioResponsavel, leitor, copia);

        new ReservaExpirada(reserva, leitor);

        rdao.insert(reserva);

        return copia;

    }
     */
}
