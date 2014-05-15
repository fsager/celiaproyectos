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



INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('Nuevo trabajo práctico o examen', 'NUEVO_TRABAJO_PRACTICO', 'Se enviará esta alerta al alumno cuando se habilita un trabajo o examen en el sistema Moodle.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('Día previo al cierre de trabajo práctico o examen', 'DIA_PREVIO_CIERRE_TP_O_EXAMEN','Se enviará esta alerta al alumno un día antes que cierre un trabajo práctico o examen.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('Vencimiento de trabajo práctico o examen', 'VENCIMIENTO_DE_TP_O_EXAMEN','Se enviará esta alerta al alumno cuando haya vencido un trabajo o examen que no ha realizado.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('Dos trabajos prácticos reprobados en una materia', 'REPROBADOS_2_TP_DE_MATERIA','Se enviará esta alerta al alumno cuando haya reprobado dos trabajos prácticos de una materia; estará a punto de quedar libre.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('No ingresa al CIE regularmente', 'NO_INGRESA_AL_CIE_REGULARMENTE','Se enviará una alerta al docente cuando ha pasado un plazo determinado desde su último ingreso.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('Adeuda carga de notas', 'ADEUDA_CARGA_NOTAS','Cuando el docente adeuda la carga de notas en el sistema de exámenes y/o trabajos prácticos.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('No sube un trabajo práctico o examen en la fecha definida en el sistema', 'NO_SUBIO_TP_O_EXAMEN_EN_FECHA_DEFINIDA','Cuando el docente no sube un trabajo práctico o examen en la fecha definida del mismo.');



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




INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail.smtp.socketFactory.class',
'MAIL.PROP',
'javax.net.ssl.SSLSocketFactory',
'javax.net.ssl.SSLSocketFactory ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail.smtp.socketFactory.port',
'MAIL.PROP',
'465',
'465');


delete from cel_propiedad
where pro_tipo = 'MAIL.PROP';

			
INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail.smtp.starttls.enable',
'MAIL.PROP',
'true',
'mail.smtp.starttls.enable ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail.debug',
'MAIL.PROP',
'false',
' mail.debug ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail.smtp.ssl.enable',
'MAIL.PROP',
'true',
' Enable ');


INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail.smtp.user',
'MAIL.PROP',
'federicosager@gmail.com',
'Usuario para el envio de mails');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail.smtp.auth',
'MAIL.PROP',
'true',
'Requiere autenticación');


INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail.smtp.pass',
'MAIL.PROP',
'#####',
'Clave de mail');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail.host',
'MAIL.PROP',
'smtp.gmail.com',
'Host smtp');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail.smtp.port',
'MAIL.PROP',
'465',
'Puerto smtp');


INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_from',
'MAIL.PROP',
'celiacie_admin@celia.com.ar',
'Mail from');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_reply_to',
'MAIL.PROP',
'celiacie_admin@celia.com.ar',
'Mail reply to');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_cc',
'MAIL.PROP',
'celiacie_admin@celia.com.ar',
'Mail reply to');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_doc_no_ingreso_subject',
'MAIL.PROP',
'Ingreso moodle',
'Subject ');


INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('entorno_pruebas',
'GRAL',
'true',
'Es entorno de pruebas ');--cambier en produccion por false