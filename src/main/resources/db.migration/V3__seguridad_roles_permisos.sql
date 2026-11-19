INSERT IGNORE INTO roles (role_nombre) VALUES ('ADMIN');

INSERT IGNORE INTO permisos (permiso_nombre) VALUES
('LOCATARIO_LISTAR'),
('LOCATARIO_CREAR'),
('LOCATARIO_ACTUALIZAR'),
('LOCATARIO_ELIMINAR'),
('LOCAL_LISTAR'),
('LOCAL_CREAR'),
('LOCAL_ACTUALIZAR'),
('LOCAL_DESACTIVAR');

INSERT IGNORE INTO rol_permisos (id_role, id_permiso)
SELECT r.id_role, p.id_permiso
FROM roles r
JOIN permisos p
WHERE r.role_nombre = 'ADMIN';

INSERT IGNORE INTO usuarios (id_role, nombre_usuario, contrasenia, activo)
SELECT r.id_role,
       'admin',
       '$2a$10$vr7R7x7s0GQiWvW/N2xhduwIV9pCsGFZrRXHCVDf7Cms88v7C8JYO',
       TRUE
FROM roles r
WHERE r.role_nombre = 'ADMIN';
