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
VALUES ('No ingresa al CIE', 'El alumno no ingresa al sistema regularmente, es decir ha pasado un plazo determinado desde su �ltimo ingreso.', NOW(), NOW(), 'Administrador', 'Administrador');

INSERT INTO `seguimiento_alumnos`.`cel_indicador` (`IND_NOMBRE`, `IND_DESCRIPCION`, `AUD_FECHA_INS`, `AUD_FECHA_UPD`, `AUD_USR_INS`, `AUD_USR_UPD`)
VALUES ('Trabajo pr�ctico vencido', 'El alumno no ha entregado en la fecha esperada al menos un trabajo pr�ctico.', NOW(), NOW(), 'Administrador', 'Administrador');

INSERT INTO `seguimiento_alumnos`.`cel_indicador` (`IND_NOMBRE`, `IND_DESCRIPCION`, `AUD_FECHA_INS`, `AUD_FECHA_UPD`, `AUD_USR_INS`, `AUD_USR_UPD`)
VALUES ('Examen reprobado', 'El alumno ha reprobado o ha estado ausente en un examen.', NOW(), NOW(), 'Administrador', 'Administrador');

INSERT INTO `seguimiento_alumnos`.`cel_indicador` (`IND_NOMBRE`, `IND_DESCRIPCION`, `AUD_FECHA_INS`, `AUD_FECHA_UPD`, `AUD_USR_INS`, `AUD_USR_UPD`)
VALUES ('Qued� libre en una materia', 'El alumno ha reprobado o estado ausente en 3 � m�s trabajos pr�cticos o cualquiera de los ex�menes con su correspondiente recuperatorio.', NOW(), NOW(), 'Administrador', 'Administrador');



INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('Nuevo trabajo pr�ctico o examen', 'NUEVO_TRABAJO_PRACTICO', 'Se enviar� esta alerta al alumno cuando se habilita un trabajo o examen en el sistema Moodle.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('D�a previo al cierre de trabajo pr�ctico o examen', 'DIA_PREVIO_CIERRE_TP_O_EXAMEN','Se enviar� esta alerta al alumno un d�a antes que cierre un trabajo pr�ctico o examen.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('Vencimiento de trabajo pr�ctico o examen', 'VENCIMIENTO_DE_TP_O_EXAMEN','Se enviar� esta alerta al alumno cuando haya vencido un trabajo o examen que no ha realizado.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('Dos trabajos pr�cticos reprobados en una materia', 'REPROBADOS_2_TP_DE_MATERIA','Se enviar� esta alerta al alumno cuando haya reprobado dos trabajos pr�cticos de una materia; estar� a punto de quedar libre.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('No ingresa al CIE regularmente', 'NO_INGRESA_AL_CIE_REGULARMENTE','Se enviar� una alerta al docente cuando ha pasado un plazo determinado desde su �ltimo ingreso.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('Adeuda carga de notas', 'ADEUDA_CARGA_NOTAS','Cuando el docente adeuda la carga de notas en el sistema de ex�menes y/o trabajos pr�cticos.');

INSERT INTO `seguimiento_alumnos`.`cel_alerta` (`ALE_NOMBRE`, `ALE_CODIGO`, `ALE_DESCRIPCION`)
VALUES ('No sube un trabajo pr�ctico o examen en la fecha definida en el sistema', 'NO_SUBIO_TP_O_EXAMEN_EN_FECHA_DEFINIDA','Cuando el docente no sube un trabajo pr�ctico o examen en la fecha definida del mismo.');



INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('INDICADOR_CASO_RESPUESTA', 'NO_TUVO_TIEMPO', '0', 'No tuvo tiempo para hacerlo', '1');

INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('INDICADOR_CASO_RESPUESTA', 'NO_SABE_HACERLO', '0', 'No sabe hacerlo', '2');

INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('INDICADOR_CASO_RESPUESTA', 'OTRA_RAZON', '0', 'Otra raz�n', '3');




INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('TIPO_INTERACCION_CASO', 'EMAIL', '0', 'Interacci�n v�a email', '1');

INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('TIPO_INTERACCION_CASO', 'TELEFONICA', '0', 'Interacci�n v�a telef�nica', '2');

INSERT INTO `seguimiento_alumnos`.`cel_dominio` (`DOM_DOMINIO`, `DOM_CLAVE`, `DOM_EDITABLE`, `DOM_TEXTO`, `DOM_ORDEN`)
VALUES ('TIPO_INTERACCION_CASO', 'PERSONALMENTE', '0', 'Interacci�n personal', '3');


INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_TIPO`,
`PRO_CLAVE`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('GRAL',
'FECHA_DESDE',
'01/08/2014',
'Fecha a partir de la cual se filtran los registros en las consultas');


delete from cel_propiedad
where pro_tipo = 'MAIL.PROP';


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
'notificaciones.celia.eig@gmail.com',
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
'Requiere autenticaci�n');


INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail.smtp.pass',
'MAIL.PROP',
'4rp153np7g3Z',
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
'notificaciones.celia.eig@gmail.com',
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
'false',
'Es entorno de pruebas');


INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_alu_nuevos_tp_subject',
'MAIL.PROP',
'Nuevo trabajo pr�ctico',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_alu_nuevos_examenes_subject',
'MAIL.PROP',
'Nuevo examen',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_alu_tp_por_vencer_subject',
'MAIL.PROP',
'Trabajo pr�ctico por vencer',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_alu_examenes_por_vecer_subject',
'MAIL.PROP',
'Examen por vencer',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_alu_tp_vencidos_subject',
'MAIL.PROP',
'Trabajo pr�ctico vencido',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_alu_examenes_vencidos_subject',
'MAIL.PROP',
'Examen vencido',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_alu_libres_subject',
'MAIL.PROP',
'Materia libre',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_doc_carga_nota_subject',
'MAIL.PROP',
'Nota pendiente de carga',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_doc_tp_carga_nota_subject',
'MAIL.PROP',
'Nota pendiente de carga',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_doc_tp_pendiente_subida',
'MAIL.PROP',
'Trabajo pr�ctico pendiente de carga',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_doc_examen_pendiente_subida',
'MAIL.PROP',
'Examen pendiente de carga',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_alu_por_quedar_libres_subject',
'MAIL.PROP',
'Materia en riesgo de condici�n de libre',
'Subject ');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('periodo_activo',
'PERIODOS',
'13',
'Id de la categor�a que representa el periodo activo');