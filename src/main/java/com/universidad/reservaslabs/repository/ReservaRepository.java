package com.universidad.reservaslabs.repository;

import com.universidad.reservaslabs.model.Reserva;
import java.util.List;

public interface ReservaRepository {
    Reserva save(Reserva reserva);
    List<Reserva> findByLaboratorioId(Long laboratorioId);
    List<Reserva> findAll();
}
