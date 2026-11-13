# ACTIVIDAD POST-CONTENIDO — UNIDAD 5
## ARQUITECTURA EN CAPAS Y MVC + REST: RESERVAS DE LABORATORIOS
**Estudiante:** Jorge Armando Martínez Peinado (Cód. 1152162)  
**Curso:** Patrones de Diseño de Software (1156701-A)  
**Institución:** Universidad Francisco de Paula Santander (UFPS)  

---

## 1. DECISIONES DE DISEÑO Y UBICACIÓN DE LA LÓGICA DE NEGOCIO

### DÓNDE VIVE LA REGLA DE NO SOLAPAMIENTO:
- **Problema:** No permitir reservar un laboratorio ya ocupado en un rango temporal que se solape con una reserva preexistente.
- **Ubicación Arquitectónica:** La regla vive estrictamente dentro de la clase **`ReservaService`** y se apoya en el método de dominio de la entidad **`Reserva.seSolapaCon(inicio, fin)`**.
- **Justificación:**
  1. *No en el Controller:* Los controladores (REST o MVC Thymeleaf) solo validan estructura HTTP. Poner lógica de negocio en el controller violaría SRP y generaría duplicación masiva entre endpoints REST y vistas HTML.
  2. *No en el Repository:* El repositorio solo se encarga de consultar registros (`findByLaboratorioId`). Poner la validación en el repositorio confundiría la persistencia con las políticas transaccionales del negocio.
  3. *En el Service:* El Service actúa como la frontera transaccional atómica, recuperando las reservas del laboratorio y aplicando el algoritmo de colisión temporal antes de autorizar la persistencia.

---

## 2. INSTRUCCIONES DE EJECUCIÓN
```bash
mvn clean compile
mvn exec:java
```
