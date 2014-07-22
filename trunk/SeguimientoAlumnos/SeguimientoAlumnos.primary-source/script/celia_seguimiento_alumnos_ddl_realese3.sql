CREATE OR REPLACE VIEW `seguimiento_alumnos`.`vw_cursos_activos` AS
select *
FROM celiacie_moodle2.mdl_course c
where c.category=12 -- Materias de Marzo 2014
AND startdate > UNIX_TIMESTAMP(STR_TO_DATE((select pro_valor from `seguimiento_alumnos`.`cel_propiedad` where pro_clave = 'FECHA_DESDE'),'%d/%m/%Y'))
;

-- drop table cel_alerta_enviada;
create table cel_alerta_enviada
   (	ale_ID  bigint default NULL auto_increment primary key,
		ale_obj_tipo varchar(250) not null,
		ale_obj_id bigint not null,
		USR_ID bigint not null,
		ale_alerta varchar(250) not null,
		aud_fecha_ins TIMESTAMP not null
   );
 
create or replace view `seguimiento_alumnos`.`vw_alerta_alumnos_nuevos_examenes` as
select q.id quiz_id,ue.userid userid,u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,q.name quiz_name
  from celiacie_moodle2.mdl_quiz q
  join vw_cursos_activos c on c.id = q.course
  join celiacie_moodle2.mdl_enrol e on e.courseid = c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id = ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid = u.id
where timeopen > UNIX_TIMESTAMP(STR_TO_DATE((select pro_valor from `seguimiento_alumnos`.`cel_propiedad` where pro_clave = 'FECHA_DESDE'),'%d/%m/%Y'))
and not exists (select 1 from cel_alerta_enviada where ale_obj_tipo='mdl_quiz' and ale_obj_id=q.id and ale_alerta='ALU_NUEVO_QUIZ' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_alumnos_examenes_por_vencer` as
select q.id quiz_id,ue.userid userid,u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,q.name quiz_name
  from celiacie_moodle2.mdl_quiz q
  join vw_cursos_activos c on c.id = q.course
  join celiacie_moodle2.mdl_enrol e on e.courseid = c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id = ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid = u.id
where timeopen > UNIX_TIMESTAMP(STR_TO_DATE((select pro_valor from `seguimiento_alumnos`.`cel_propiedad` where pro_clave = 'FECHA_DESDE'),'%d/%m/%Y'))
and DATEDIFF(FROM_UNIXTIME(timeclose),now())=2
and not exists (select 1 from cel_alerta_enviada where ale_obj_tipo='mdl_quiz' and ale_obj_id=q.id and ale_alerta='ALU_QUIZ_POR_VENCER' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_alumnos_examenes_vencidos` as
select q.id quiz_id,ue.userid userid,u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,q.name quiz_name
  from celiacie_moodle2.mdl_quiz q
  join vw_cursos_activos c on c.id = q.course
  join celiacie_moodle2.mdl_enrol e on e.courseid = c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id = ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid = u.id
  LEFT  JOIN  celiacie_moodle2.mdl_quiz_grades gr on gr.quiz=q.id and gr.userid=ue.userid
where timeopen > UNIX_TIMESTAMP(STR_TO_DATE((select pro_valor from `seguimiento_alumnos`.`cel_propiedad` where pro_clave = 'FECHA_DESDE'),'%d/%m/%Y'))
and FROM_UNIXTIME(timeclose) < now()
and gr.grade is null
and not exists (select 1 from cel_alerta_enviada where ale_obj_tipo='mdl_quiz' and ale_obj_id=q.id and ale_alerta='ALU_QUIZ_VENCIDO' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_alumnos_nuevos_tp` as
select a.id assignment_id,ue.userid userid,u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,a.name assignment_name
  from celiacie_moodle2.mdl_assignment a
  join vw_cursos_activos c on c.id = a.course
  join celiacie_moodle2.mdl_enrol e on e.courseid=c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id=ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid=u.id
where timeavailable > UNIX_TIMESTAMP(STR_TO_DATE((select pro_valor from `seguimiento_alumnos`.`cel_propiedad` where pro_clave = 'FECHA_DESDE'),'%d/%m/%Y'))
and not exists (select 1 from cel_alerta_enviada where ale_obj_tipo='mdl_assignment' and ale_obj_id=a.id and ale_alerta='ALU_NUEVO_TP' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_alumnos_tp_por_vencer` as
select a.id assignment_id,ue.userid userid,u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,a.name assignment_name
  from celiacie_moodle2.mdl_assignment a
  join vw_cursos_activos c on c.id = a.course
  join celiacie_moodle2.mdl_enrol e on e.courseid=c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id=ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid=u.id
where timeavailable > UNIX_TIMESTAMP(STR_TO_DATE((select pro_valor from `seguimiento_alumnos`.`cel_propiedad` where pro_clave = 'FECHA_DESDE'),'%d/%m/%Y'))
and DATEDIFF(FROM_UNIXTIME(timedue),now())=2
and not exists (select 1 from cel_alerta_enviada where ale_obj_tipo='mdl_assignment' and ale_obj_id=a.id and ale_alerta='ALU_TP_POR_VENCER' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_tpsvencidos` as
select a.id assignment_id,ue.userid userid,u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,a.name assignment_name
  from celiacie_moodle2.mdl_assignment a
  join vw_cursos_activos c on c.id = a.course
  join celiacie_moodle2.mdl_enrol e on e.courseid=c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id=ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid=u.id
  LEFT JOIN celiacie_moodle2.mdl_assignment_submissions asu on asu.userid=ue.userid and asu.assignment=a.id
  where FROM_UNIXTIME(a.timedue) < now()
  and asu.timecreated is null
  and not exists (select 1 from cel_alerta_enviada where ale_obj_tipo='mdl_assignment' and ale_obj_id=a.id and ale_alerta='ALU_TP_VENCIDO' and usr_id=ue.userid)
;



create or replace view `seguimiento_alumnos`.`vw_alerta_docentes_examenes_nota_pendiente` as
select q.id quiz_id,ue.userid userid,(select u.email from celiacie_moodle2.mdl_role_assignments ra, celiacie_moodle2.mdl_user u
where ra.userid=u.id
and contextid = (select id from celiacie_moodle2.mdl_context where contextlevel = 50 and instanceid =c.id)
and ra.roleid = 3) mail,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,q.name quiz_name, gr.grade
  from celiacie_moodle2.mdl_quiz q
  join vw_cursos_activos c on c.id = q.course
  join celiacie_moodle2.mdl_enrol e on e.courseid = c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id = ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid = u.id
  join celiacie_moodle2.mdl_quiz_grades gr on gr.quiz=q.id and gr.userid=ue.userid
where timeopen > UNIX_TIMESTAMP(STR_TO_DATE((select pro_valor from `seguimiento_alumnos`.`cel_propiedad` where pro_clave = 'FECHA_DESDE'),'%d/%m/%Y'))
 and DATE_ADD(FROM_UNIXTIME(timeclose), INTERVAL 4 DAY) < now()
 and gr.grade = 0.00000
 and not exists (select 1 from cel_alerta_enviada where ale_obj_tipo='mdl_quiz' and ale_obj_id=q.id and ale_alerta='DOC_EXAMEN_NOTA_PENDIENTE' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_docentes_tp_nota_pendiente` as
select a.id assignment_id,ue.userid userid,u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,a.name assignment_name,FROM_UNIXTIME(timedue), asu.grade
  from celiacie_moodle2.mdl_assignment a
  join vw_cursos_activos c on c.id = a.course
  join celiacie_moodle2.mdl_enrol e on e.courseid=c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id=ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid=u.id
  join celiacie_moodle2.mdl_assignment_submissions asu on asu.userid=ue.userid and asu.assignment=a.id
where timeavailable > UNIX_TIMESTAMP(STR_TO_DATE((select pro_valor from `seguimiento_alumnos`.`cel_propiedad` where pro_clave = 'FECHA_DESDE'),'%d/%m/%Y'))
 and DATE_ADD(FROM_UNIXTIME(timedue), INTERVAL 4 DAY) < now()
 and asu.grade = 0
 and not exists (select 1 from cel_alerta_enviada where ale_obj_tipo='mdl_assignment' and ale_obj_id=a.id and ale_alerta='DOC_TP_NOTA_PENDIENTE' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_docentes_tps_pendientes_carga` as
select a.id assignment_id,ue.userid userid,u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,a.name assignment_name
  from celiacie_moodle2.mdl_assignment a
  join vw_cursos_activos c on c.id = a.course
  join celiacie_moodle2.mdl_enrol e on e.courseid=c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id=ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid=u.id
where timeavailable > UNIX_TIMESTAMP(STR_TO_DATE((select pro_valor from `seguimiento_alumnos`.`cel_propiedad` where pro_clave = 'FECHA_DESDE'),'%d/%m/%Y'))
and DATEDIFF(FROM_UNIXTIME(timeavailable),now())<=10  -- falten x dias para estar disponible
and DATEDIFF(FROM_UNIXTIME(timeavailable),now())>=0  -- falten x dias para estar disponible
and LENGTH(intro) < 95 -- Si tiene menos de esa longitud debe ser porque no esta caargado
and not exists (select 1 from cel_alerta_enviada where ale_obj_tipo='mdl_assignment' and ale_obj_id=a.id and ale_alerta='DOC_TPS_PENDIENTE_CARGA' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_docentes_examenes_pendientes_carga` as
select q.id quiz_id,ue.userid userid,u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,q.name quiz_name
  from celiacie_moodle2.mdl_quiz q
  join vw_cursos_activos c on c.id = q.course
  join celiacie_moodle2.mdl_enrol e on e.courseid = c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id = ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid = u.id
where timeopen > UNIX_TIMESTAMP(STR_TO_DATE((select pro_valor from `seguimiento_alumnos`.`cel_propiedad` where pro_clave = 'FECHA_DESDE'),'%d/%m/%Y'))
and DATEDIFF(FROM_UNIXTIME(timeopen),now())<=10  -- falten x dias para estar disponible
and DATEDIFF(FROM_UNIXTIME(timeopen),now())>=0  -- falten x dias para estar disponible
and LENGTH(intro) < 95 -- Si tiene menos de esa longitud debe ser porque no esta caargado
and not exists (select 1 from cel_alerta_enviada where ale_obj_tipo='mdl_quiz' and ale_obj_id=q.id and ale_alerta='DOC_EXAMEN_PENDIENTE_CARGA' and usr_id=ue.userid)
;





