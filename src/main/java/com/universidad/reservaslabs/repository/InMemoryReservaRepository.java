package com.universidad.reservaslabs.repository;

import com.universidad.reservaslabs.model.Reserva;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryReservaRepository implements ReservaRepository {
    private final ConcurrentHashMap<Long, Reserva> db = new ConcurrentHashMap<>();
    private final AtomicLong idSeq = new AtomicLong(1);

    @Override
    public Reserva save(Reserva reserva) {
        Long id = reserva.getId() != null ? reserva.getId() : idSeq.getAndIncrement();
        Reserva guardada = new Reserva(id, reserva.getLaboratorio(), reserva.getSolicitante(), reserva.getHoraInicio(), reserva.getHoraFin(), reserva.getMotivo());
        db.put(id, guardada);
        return guardada;
    }

    @Override
    public List<Reserva> findByLaboratorioId(Long laboratorioId) {
        return db.values().stream()
                .filter(r -> r.getLaboratorio().getId().equals(laboratorioId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reserva> findAll() {
        return new ArrayList<>(db.values());
    }
}
