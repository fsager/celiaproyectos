INSERT INTO `seguimiento_alumnos`.`cel_grupo`
(`GRP_NOMBRE`,
`GRP_DESCRIPCION`,
`AUD_FECHA_INS`,
`AUD_FECHA_UPD`,
`AUD_USR_INS`,
`AUD_USR_UPD`)
VALUES
('ADMIN_USER',
'Usuario admistrador celia',
now(),
now(),
'administrador',
'administrador');

-- 

INSERT INTO `seguimiento_alumnos`.`cel_usuario`
(`USR_USUARIO`,
`USR_CLAVE`,
`USR_NOMBRE`,
`USR_APELLIDO`,
`USR_MAIL`,
`USR_ACTIVO`,
`AUD_FECHA_INS`,
`AUD_FECHA_UPD`,
`AUD_USR_INS`,
`AUD_USR_UPD`)
VALUES
('valeria',
'fEqNCco3Yq9h5ZUglD3CZJT4lBs=',
'Valeria',
' ',
'valeria@celiagastronomia.com.ar',
1,
now(),
now(),
'administrador',
'administrador');


INSERT INTO `seguimiento_alumnos`.`cel_grupo_usuario`
(`GRP_ID`,
`USR_ID`)
VALUES
(1,
1);

INSERT INTO `seguimiento_alumnos`.`cel_indicador` (`IND_NOMBRE`, `IND_DESCRIPCION`, `AUD_FECHA_INS`, `AUD_FECHA_UPD`, `AUD_USR_INS`, `AUD_USR_UPD`)
VALUES ('No ingresa al CIE', 'El alumno no ingresa al sistema regularmente, es decir ha pasado un plazo determinado desde su último ingreso.', NOW(), NOW(), 'Administrador', 'Administrador');

INSERT INTO `seguimiento_alumnos`.`cel_indicador` (`IND_NOMBRE`, `IND_DESCRIPCION`, `AUD_FECHA_INS`, `AUD_FECHA_UPD`, `AUD_USR_INS`, `AUD_USR_UPD`)
VALUES ('Trabajo práctico vencido', 'El alumno no ha entregado en la fecha esperada al menos un trabajo práctico.', NOW(), NOW(), 'Administrador', 'Administrador');

INSERT INTO `seguimiento_alumnos`.`cel_indicador` (`IND_NOMBRE`, `IND_DESCRIPCION`, `AUD_FECHA_INS`, `AUD_FECHA_UPD`, `AUD_USR_INS`, `AUD_USR_UPD`)
VALUES ('Examen reprobado', 'El alumno ha reprobado o ha estado ausente en un examen.', NOW(), NOW(), 'Administrador', 'Administrador');

INSERT INTO `seguimiento_alumnos`.`cel_indicador` (`IND_NOMBRE`, `IND_DESCRIPCION`, `AUD_FECHA_INS`, `AUD_FECHA_UPD`, `AUD_USR_INS`, `AUD_USR_UPD`)
VALUES ('Quedó libre en una materia', 'El alumno ha reprobado o estado ausente en 3 ó más trabajos prácticos o cualquiera de los exámenes con su correspondiente recuperatorio.', NOW(), NOW(), 'Administrador', 'Administrador');

INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('INDICADOR_CASO_RESPUESTA', 'NO_TUVO_TIEMPO', '0', 'No tuvo tiempo para hacerlo', '1');

INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('INDICADOR_CASO_RESPUESTA', 'NO_SABE_HACERLO', '0', 'No sabe hacerlo', '2');

INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('INDICADOR_CASO_RESPUESTA', 'OTRA_RAZON', '0', 'Otra razón', '3');




INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('TIPO_INTERACCION_CASO', 'EMAIL', '0', 'Interacción vía email', '1');

INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('TIPO_INTERACCION_CASO', 'TELEFONICA', '0', 'Interacción vía telefónica', '2');

INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('TIPO_INTERACCION_CASO', 'PERSONALMENTE', '0', 'Interacción personal', '3');

