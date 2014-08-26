update cel_indicador set ind_function = 'f_materias_por_quedar_libres_detalle'
where ind_nombre = 'Quedó libre en una materia' ;


update cel_indicador set ind_moreinfo_renderer = 'ar.com.celia.seguimiento_alumnos.renderers.Materias'
where ind_nombre = 'Quedó libre en una materia' ;


update cel_indicador set ind_moreinfo_class = 'ar.com.celia.seguimiento_alumnos.domain.VwMaterias'
where ind_nombre = 'Quedó libre en una materia' ;


update cel_indicador set ind_function = 'f_tpsvencidos_detalle'
where ind_nombre = 'Trabajo práctico vencido' ;


update cel_indicador set ind_moreinfo_renderer = 'ar.com.celia.seguimiento_alumnos.renderers.MoreInfoTpVencidos'
where ind_nombre = 'Trabajo práctico vencido' ;


update cel_indicador set ind_moreinfo_class = 'ar.com.celia.seguimiento_alumnos.domain.VwTpsVencidos'
where ind_nombre = 'Trabajo práctico vencido' ;


update cel_indicador set ind_function = 'f_examenes_reprobados_detalle'
where ind_nombre = 'Examen reprobado' ;


update cel_indicador set ind_moreinfo_renderer = 'ar.com.celia.seguimiento_alumnos.renderers.MoreInfoExamenesReprobados'
where ind_nombre = 'Examen reprobado' ;


update cel_indicador set ind_moreinfo_class = 'ar.com.celia.seguimiento_alumnos.domain.VwExameneesReprobados'
where ind_nombre = 'Examen reprobado' ;

update cel_indicador set ind_order = 1
where IND_NOMBRE = 'Quedó libre en una materia';

update cel_indicador set ind_order =5
where IND_NOMBRE = 'Trabajo práctico vencido';

update cel_indicador set ind_order = 10
where IND_NOMBRE = 'Examen reprobado';

update cel_indicador set ind_order = 15
where IND_NOMBRE = 'No ingresa al CIE';

INSERT INTO `seguimiento_alumnos`.`cel_propiedad`
(`PRO_CLAVE`,
`PRO_TIPO`,
`PRO_VALOR`,
`PRO_DESCRIPCION`)
VALUES
('IND_2_ROJO',
'INDICADORES_RANGOS',
'20',
'Si el valor del indicador de TP es mayor a 20 va en rojo');