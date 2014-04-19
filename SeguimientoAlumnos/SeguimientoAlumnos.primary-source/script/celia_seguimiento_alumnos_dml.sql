INSERT INTO `seguimiento_alumnos`.`cel_indicador` (`IND_NOMBRE`, `IND_DESCRIPCION`, `AUD_FECHA_INS`, `AUD_FECHA_UPD`, `AUD_USR_INS`, `AUD_USR_UPD`)
VALUES ('No ingresa al CIE', 'El alumno no ingresa al sistema regularmente, es decir ha pasado un plazo determinado desde su último ingreso.', NOW(), NOW(), 'Administrador', 'Administrador');

INSERT INTO `seguimiento_alumnos`.`cel_indicador` (`IND_NOMBRE`, `IND_DESCRIPCION`, `AUD_FECHA_INS`, `AUD_FECHA_UPD`, `AUD_USR_INS`, `AUD_USR_UPD`)
VALUES ('Trabajo práctico vencido', 'El alumno no ha entregado en la fecha esperada al menos un trabajo práctico.', NOW(), NOW(), 'Administrador', 'Administrador');

INSERT INTO `seguimiento_alumnos`.`cel_indicador` (`IND_NOMBRE`, `IND_DESCRIPCION`, `AUD_FECHA_INS`, `AUD_FECHA_UPD`, `AUD_USR_INS`, `AUD_USR_UPD`)
VALUES ('Examen reprobado', 'El alumno ha reprobado o ha estado ausente en un examen.', NOW(), NOW(), 'Administrador', 'Administrador');

INSERT INTO `seguimiento_alumnos`.`cel_indicador` (`IND_NOMBRE`, `IND_DESCRIPCION`, `AUD_FECHA_INS`, `AUD_FECHA_UPD`, `AUD_USR_INS`, `AUD_USR_UPD`)
VALUES ('Quedó libre en una materia', 'El alumno ha reprobado o estado ausente en 3 ó más trabajos prácticos o cualquiera de los exámenes con su correspondiente recuperatorio.', NOW(), NOW(), 'Administrador', 'Administrador');
