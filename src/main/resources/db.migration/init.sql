
-- 1. Tabla de Configuración de Boletos
CREATE TABLE configuracion_boletos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    plantilla_nombre VARCHAR(100) NOT NULL,
    es_active BOOLEAN DEFAULT TRUE,
    imagen_url VARCHAR(255),
    subido_el DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- 2. Tabla de Locatarios
CREATE TABLE locatarios (
    id_locatario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    telefono VARCHAR(20)
) ENGINE=InnoDB;

-- 3. Tabla de Locales (Soporta jerarquía con local_padre_id y relación con locatarios)
CREATE TABLE locales (
    id_de_local INT AUTO_INCREMENT PRIMARY KEY,
    propietario_id INT NOT NULL,
    local_padre_id INT DEFAULT NULL,
    numero VARCHAR(50) NOT NULL,
    pasillo VARCHAR(50),
    nombre VARCHAR(100),
    giro_de_negocio VARCHAR(100),
    es_activo BOOLEAN DEFAULT TRUE,
    creado_el DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_local_propietario FOREIGN KEY (propietario_id) REFERENCES locatarios(id_locatario) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_local_padre FOREIGN KEY (local_padre_id) REFERENCES locales(id_de_local) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

-- 4. Tabla de Roles
CREATE TABLE roles (
    id_role INT AUTO_INCREMENT PRIMARY KEY,
    role_nombre VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- 5. Tabla de Permisos
CREATE TABLE permisos (
    id_permiso INT AUTO_INCREMENT PRIMARY KEY,
    permiso_nombre VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- 6. Tabla intermedia Roles - Permisos (Opcional pero recomendada para sistemas robustos)
CREATE TABLE rol_permisos (
    id_role INT,
    id_permiso INT,
    PRIMARY KEY (id_role, id_permiso),
    CONSTRAINT fk_rolpermiso_role FOREIGN KEY (id_role) REFERENCES roles(id_role) ON DELETE CASCADE,
    CONSTRAINT fk_rolpermiso_permiso FOREIGN KEY (id_permiso) REFERENCES permisos(id_permiso) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 7. Tabla de Usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    id_role INT NOT NULL,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasenia VARCHAR(255) NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_role) REFERENCES roles(id_role) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- 8. Tabla de Conceptos de Pago (Define los precios base de los servicios o rentas)
CREATE TABLE conceptos_pago (
    id_concepto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
) ENGINE=InnoDB;

-- 9. Tabla de Cargos (Adeudos semanales generados por local)
CREATE TABLE cargos (
    id_cargo INT AUTO_INCREMENT PRIMARY KEY,
    id_de_local INT NOT NULL,
    id_concepto_pago INT NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    identificador_semana VARCHAR(20) NOT NULL,
    estado_de_pago ENUM('PENDIENTE', 'PAGADO', 'VENCIDO')
        NOT NULL DEFAULT 'PENDIENTE',
    creado_el DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_cargo_monto
        CHECK (monto > 0),

    CONSTRAINT uq_cargo_local_concepto_semana
        UNIQUE (
            id_de_local,
            id_concepto_pago,
            identificador_semana
        ),

    CONSTRAINT fk_cargo_local
        FOREIGN KEY (id_de_local)
        REFERENCES locales(id_de_local)
        ON DELETE RESTRICT ON UPDATE CASCADE,

    CONSTRAINT fk_cargo_concepto
        FOREIGN KEY (id_concepto_pago)
        REFERENCES conceptos_pago(id_concepto)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;


-- 10. Tabla de Pagos (Historial de transacciones de cada pago semanal)
CREATE TABLE pagos (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_cargo INT NOT NULL,
    monto_pagado DECIMAL(10, 2) NOT NULL,
    dia_de_pago DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_pago_monto
        CHECK (monto_pagado > 0),

    CONSTRAINT uq_pago_por_cargo
        UNIQUE (id_cargo),

    CONSTRAINT fk_pago_cargo
        FOREIGN KEY (id_cargo)
        REFERENCES cargos(id_cargo)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

ALTER TABLE pagos
    ADD COLUMN estado ENUM('APLICADO', 'CANCELADO')
        NOT NULL DEFAULT 'APLICADO';
        
        
CREATE TABLE cancelaciones_pago (
    id_cancelacion INT AUTO_INCREMENT PRIMARY KEY,
    id_pago INT NOT NULL,
    fecha_cancelacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    motivo VARCHAR(500) NOT NULL,

    CONSTRAINT fk_cancelacion_pago
        FOREIGN KEY (id_pago)
        REFERENCES pagos(id_pago)
        ON DELETE RESTRICT,

    CONSTRAINT fk_cancelacion_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id_usuario),

    INDEX idx_cancelacion_fecha (fecha_cancelacion)
) ENGINE=InnoDB;
