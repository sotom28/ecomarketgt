INSERT INTO PERFIL (id_Perfil, descripcion, permiso) VALUES (1, 'Administrador', 'FULL_ACCESS');
INSERT INTO perfil (id_Perfil, descripcion, permiso) VALUES (2, 'USUARIO', 'MEDIO_ACCESS');






INSERT INTO usuario (id, id_perfil, apellidos, direccion, email, nombres, password, rut, telefono) VALUES (1, 1, 'Perez', 'Calle 1', 'juan.perez@example.com', 'Juan', 'password123', '12345678-9', '123456789');
INSERT INTO usuario (id, id_perfil, apellidos, direccion, email, nombres, password, rut, telefono) VALUES(2, 2, 'Lopez', 'Calle 2', 'maria.lopez@example.com', 'Maria', 'password456', '98765432-1', '987654321');
INSERT INTO usuario (id, id_perfil, apellidos, direccion, email, nombres, password, rut, telefono) VALUES(3, 1, 'Gomez', 'Calle 3', 'ana.gomez@example.com', 'Ana', 'password789', '11223344-5', '1122334455');

INSERT INTO usuario (id, id_perfil, apellidos, direccion, email, nombres, password, rut, telefono) VALUES(4, 2, 'Martinez', 'Calle 4', 'carlos.martinez@example.com', 'Carlos', 'password321', '55667788-6', '5566778899');
INSERT INTO usuario (id, id_perfil, apellidos, direccion, email, nombres, password, rut, telefono) VALUES(5, 1, 'Hernandez', 'Calle 5', 'sofia.hernandez@example.com', 'Sofia', 'password555', '33445566-7', '3344556677'),
INSERT INTO usuario (id, id_perfil, apellidos, direccion, email, nombres, password, rut, telefono) VALUES(6, 2, 'Ramirez', 'Calle 6', 'luis.ramirez@example.com', 'Luis', 'password666', '99887766-5', '9988776655');
INSERT INTO usuario (id, id_perfil, apellidos, direccion, email, nombres, password, rut, telefono) VALUES(7, 1, 'Torres', 'Calle 7', 'camila.torres@example.com', 'Camila', 'password777', '44556677-8', '4455667788');
INSERT INTO usuario (id, id_perfil, apellidos, direccion, email, nombres, password, rut, telefono) VALUES(8, 2, 'Vargas', 'Calle 8', 'diego.vargas@example.com', 'Diego', 'password888', '22334455-6', '2233445566');
INSERT INTO usuario (id, id_perfil, apellidos, direccion, email, nombres, password, rut, telefono) VALUES(9, 1, 'Castro', 'Calle 9', 'valeria.castro@example.com', 'Valeria', 'password999', '66778899-0', '6677889900');
INSERT INTO usuario (id, id_perfil, apellidos, direccion, email, nombres, password, rut, telefono) VALUES(10, 2, 'Gonzalez', 'Calle 10', 'fernando.gonzalez@example.com', 'Fernando', 'password1010', '11224433-4', '1122443344');


commit;
