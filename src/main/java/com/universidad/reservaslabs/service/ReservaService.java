package com.universidad.reservaslabs.service;

import com.universidad.reservaslabs.model.Laboratorio;
import com.universidad.reservaslabs.model.Reserva;
import com.universidad.reservaslabs.repository.ReservaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva crearReserva(Laboratorio laboratorio, String solicitante, LocalDateTime inicio, LocalDateTime fin, String motivo) {
        // 1. Validar precondiciones temporales
        if (inicio == null || fin == null || !inicio.isBefore(fin)) {
            throw new IllegalArgumentException("La hora de inicio debe ser anterior a la hora de fin.");
        }

        // 2. Regla de Negocio Crítica: Verificación estricta de no solapamiento
        List<Reserva> reservasExistentes = reservaRepository.findByLaboratorioId(laboratorio.getId());
        boolean haySolapamiento = reservasExistentes.stream()
                .anyMatch(r -> r.seSolapaCon(inicio, fin));

        if (haySolapamiento) {
            throw new IllegalStateException("Conflicto de reserva: El laboratorio " + laboratorio.getNombre() + 
                    " ya cuenta con una reserva activa en ese rango de horario.");
        }

        // 3. Crear y guardar
        Reserva nueva = new Reserva(null, laboratorio, solicitante, inicio, fin, motivo);
        return reservaRepository.save(nueva);
    }

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }
}
