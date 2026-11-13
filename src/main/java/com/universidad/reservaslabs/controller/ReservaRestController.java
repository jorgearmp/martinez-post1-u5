package com.universidad.reservaslabs.controller;

import com.universidad.reservaslabs.model.Laboratorio;
import com.universidad.reservaslabs.model.Reserva;
import com.universidad.reservaslabs.service.ReservaService;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaRestController {
    private final ReservaService reservaService;

    public ReservaRestController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    public List<Reserva> getReservas() {
        return reservaService.listarTodas();
    }

    public Reserva postReserva(Laboratorio lab, String solicitante, LocalDateTime inicio, LocalDateTime fin, String motivo) {
        return reservaService.crearReserva(lab, solicitante, inicio, fin, motivo);
    }
}
