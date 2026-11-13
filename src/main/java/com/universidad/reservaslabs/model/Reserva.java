package com.universidad.reservaslabs.model;

import java.time.LocalDateTime;

public class Reserva {
    private Long id;
    private Laboratorio laboratorio;
    private String solicitante;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private String motivo;

    public Reserva(Long id, Laboratorio laboratorio, String solicitante, LocalDateTime horaInicio, LocalDateTime horaFin, String motivo) {
        this.id = id;
        this.laboratorio = laboratorio;
        this.solicitante = solicitante;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.motivo = motivo;
    }

    public Long getId() { return id; }
    public Laboratorio getLaboratorio() { return laboratorio; }
    public String getSolicitante() { return solicitante; }
    public LocalDateTime getHoraInicio() { return horaInicio; }
    public LocalDateTime getHoraFin() { return horaFin; }
    public String getMotivo() { return motivo; }

    public boolean seSolapaCon(LocalDateTime inicio, LocalDateTime fin) {
        // Regla matemática de solapamiento de intervalos temporales
        return this.horaInicio.isBefore(fin) && inicio.isBefore(this.horaFin);
    }
}
