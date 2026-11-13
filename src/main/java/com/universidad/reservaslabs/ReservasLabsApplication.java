package com.universidad.reservaslabs;

import com.universidad.reservaslabs.controller.ReservaRestController;
import com.universidad.reservaslabs.model.Laboratorio;
import com.universidad.reservaslabs.model.Reserva;
import com.universidad.reservaslabs.repository.InMemoryReservaRepository;
import com.universidad.reservaslabs.service.ReservaService;

import java.time.LocalDateTime;

public class ReservasLabsApplication {
    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println(" SISTEMA DE RESERVAS DE LABORATORIOS - UNIDAD 5 (UFPS)");
        System.out.println(" Estudiante: Jorge Martínez");
        System.out.println("==================================================================");

        var repo = new InMemoryReservaRepository();
        var service = new ReservaService(repo);
        var restController = new ReservaRestController(service);

        var labSistemas = new Laboratorio(1L, "Laboratorio de Redes y Software (L-204)", 35, "Edificio Fundadores");

        // 1. Crear reserva válida
        LocalDateTime t1 = LocalDateTime.of(2026, 9, 15, 8, 0);
        LocalDateTime t2 = LocalDateTime.of(2026, 9, 15, 10, 0);
        Reserva r1 = restController.postReserva(labSistemas, "Prof. Carlos Gómez", t1, t2, "Clase Práctica Redes IP");
        System.out.printf("  [EXITO] Reserva #%d confirmada para %s de %s a %s%n", r1.getId(), r1.getSolicitante(), t1, t2);

        // 2. Intentar reserva con solapamiento
        LocalDateTime tConflictoInicio = LocalDateTime.of(2026, 9, 15, 9, 30);
        LocalDateTime tConflictoFin = LocalDateTime.of(2026, 9, 15, 11, 30);
        System.out.println("\nIntentando registrar reserva en horario solapado (09:30 - 11:30)...");
        try {
            restController.postReserva(labSistemas, "Prof. Jorge Martínez", tConflictoInicio, tConflictoFin, "Seminario Patrones");
        } catch (IllegalStateException ex) {
            System.out.println("  [BLOQUEO EXITOSO POR REGLA DE NEGOCIO EN SERVICE]: " + ex.getMessage());
        }

        // 3. Crear reserva consecutiva sin solapamiento
        LocalDateTime t3 = LocalDateTime.of(2026, 9, 15, 10, 0);
        LocalDateTime t4 = LocalDateTime.of(2026, 9, 15, 12, 0);
        Reserva r2 = restController.postReserva(labSistemas, "Prof. Jorge Martínez", t3, t4, "Seminario Patrones");
        System.out.printf("\n  [EXITO] Reserva consecutiva #%d confirmada para %s de %s a %s%n", r2.getId(), r2.getSolicitante(), t3, t4);

        System.out.printf("\nTotal de reservas activas en almacén: %d%n", restController.getReservas().size());
        System.out.println("Demostración de Arquitectura en Capas y MVC finalizada con éxito.");
    }
}
