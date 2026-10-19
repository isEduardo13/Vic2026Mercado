# 🛒 Mercado Victoria - Sistema de Gestión y Cobranza 

> Sistema backend diseñado para la administración de mercados municipales, control de locatarios, asignación de locales, generación de cargos semanales y trazabilidad de pagos.

---

## 🚀 Estado Actual del Proyecto
Actualmente el proyecto se encuentra en su **Fase 1 y 2 de desarrollo**, implementando los fundamentos de arquitectura backend, control de versiones con Git, versionado de base de datos con Flyway y diseño relacional optimizado para la integridad financiera.

---

## 🛠️ Tecnologías y Stack Tecnológico
* **Backend:** Java, Spring Boot
* **Gestión de Base de Datos:** PostgreSQL / MySQL, Flyway (Migraciones DDL)
* **Persistencia:** Spring Data JPA / Hibernate
* **Control de Versiones:** Git & GitHub
* **Arquitectura:** Principios SOLID, Capas (Controller, Service, Repository, DTOs)

---

## 📊 Modelo de Negocio y Reglas Clave
El sistema implementa restricciones estrictas orientadas a la administración comercial:
1. **Cobranza Semanal:** Cada local genera adeudos o cargos de manera periódica según los conceptos de pago establecidos.
2. **Historial de Pagos Inalterable:** Un pago está vinculado de forma directa a un cargo único. Cada transacción registra el monto exacto cobrado en el momento exacto, protegiendo el historial ante futuros cambios de tarifas.
3. **Gestión de Adeudos:** Permite rastrear el estado de cuenta por local (pendientes, pagados, parciales).

---

## 🗄️ Esquema de Base de Datos (Derivado)
El sistema gestiona entidades clave interrelacionadas mediante integridad referencial estricta:
* `locatarios`: Propietarios o arrendatarios de los espacios.
* `locales`: Espacios físicos dentro del mercado (soporta jerarquías/locales padres y giros comerciales).
* `conceptos_pago`: Catálogo de tarifas base.
* `cargos`: Obligaciones o adeudos semanales generados por local.
* `pagos`: Historial transaccional con montos y fechas inalterables.
* `usuarios`, `roles`, `permisos`: Módulo de seguridad y control de acceso.

---

## ⚙️ Configuración y Puesta en Marcha (Local)

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/tu-usuario/mercado-victoria.git](https://github.com/tu-usuario/mercado-victoria.git)
   cd mercado-victoria