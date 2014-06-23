alter table cel_indicador add ind_order int not null;

alter table cel_indicador add ind_function varchar(300);


alter table cel_indicador add ind_moreinfo_renderer varchar(300);

alter table cel_indicador add ind_moreinfo_class varchar(300);

DELIMITER $$
DROP PROCEDURE IF EXISTS seguimiento_alumnos.f_examenes_reprobados_detalle $$
CREATE PROCEDURE seguimiento_alumnos.f_examenes_reprobados_detalle(IN p_alu_id bigint(10) unsigned)
BEGIN
	select * from vw_examenes_reprobados where userid=p_alu_id order by course_fullname,quiz_name asc;
END $$

DROP PROCEDURE IF EXISTS seguimiento_alumnos.f_tpsvencidos_detalle $$
CREATE PROCEDURE seguimiento_alumnos.f_tpsvencidos_detalle(IN p_alu_id bigint(10) unsigned)
BEGIN
	select * from vw_tpsvencidos where userid=p_alu_id order by course_fullname,assignment_name asc;
END $$


DROP PROCEDURE IF EXISTS seguimiento_alumnos.f_materias_por_quedar_libres_detalle $$
CREATE PROCEDURE seguimiento_alumnos.f_materias_por_quedar_libres_detalle(IN p_alu_id bigint(10) unsigned)
BEGIN
select * from vw_materias_por_quedar_libres where 
				userid=p_alu_id order by fullname asc;
END $$
