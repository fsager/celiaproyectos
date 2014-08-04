-- delete from `seguimiento_alumnos`.`cel_propiedad` where PRO_CLAVE = 'FECHA_DESDE';

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_TIPO`,
`PRO_CLAVE`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('GRAL',
'FECHA_DESDE',
'01/08/2014',
'Fecha a partír de la cual se filtran los registros en las consultas');

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('mail_alu_nuevos_tp_subject',
'MAIL.PROP',
'Nuevo trabajo práctico',
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
'Trabajo práctico por vencer',
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
'Trabajo práctico vencido',
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
'Trabajo práctico pendiente de carga',
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


