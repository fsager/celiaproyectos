drop database seguimiento_alumnos;

create database seguimiento_alumnos;
USE seguimiento_alumnos;


CREATE TABLE tmp_indicadores_alumnos
(
ina_id	varchar(21) not null,
usr_id	bigint(20) not null,
id_indicador	varchar(1) not null,
nombre_indicador	varchar(250) not null,
desc_indicador varchar(250) not null,		
valor_indicador	bigint(20) not null
);

CREATE INDEX tmp_ind_alu_index
    ON tmp_indicadores_alumnos (usr_id);



  CREATE TABLE seguimiento_alumnos.cel_usuario
   (	USR_ID  bigint default NULL auto_increment primary key,
    	USR_USUARIO VARCHAR(50), 
    	USR_CLAVE VARCHAR(100), 
    	USR_NOMBRE VARCHAR(250), 
    	USR_APELLIDO VARCHAR(250), 
    	USR_MAIL VARCHAR(250), 
    	USR_ACTIVO int DEFAULT 1, 
    	AUD_FECHA_INS TIMESTAMP, 
    	AUD_FECHA_UPD TIMESTAMP, 
    	AUD_USR_INS VARCHAR(250), 
    	AUD_USR_UPD VARCHAR(250)
   );


CREATE TABLE seguimiento_alumnos.cel_grupo
   (	GRP_ID bigint default NULL auto_increment primary key,
    	GRP_NOMBRE VARCHAR(50), 
    	GRP_DESCRIPCION VARCHAR(4000), 
    	AUD_FECHA_INS TIMESTAMP, 
    	AUD_FECHA_UPD TIMESTAMP, 
    	AUD_USR_INS VARCHAR(250), 
    	AUD_USR_UPD VARCHAR(250)
   );


  CREATE TABLE seguimiento_alumnos.cel_grupo_usuario
   (	GRU_ID bigint default NULL auto_increment primary key,
    	GRP_ID bigint, 
    	USR_ID bigint
   );

ALTER TABLE seguimiento_alumnos.cel_grupo_usuario ADD CONSTRAINT CEL_GRU_GRP_ID FOREIGN KEY (GRP_ID)
    REFERENCES seguimiento_alumnos.cel_grupo (GRP_ID);


ALTER TABLE seguimiento_alumnos.cel_grupo_usuario ADD CONSTRAINT CEL_GRU_USR_ID FOREIGN KEY (USR_ID)
    REFERENCES seguimiento_alumnos.cel_usuario (USR_ID);
    

  CREATE TABLE seguimiento_alumnos.cel_propiedad
   (	PRO_CLAVE varchar(250),
		PRO_TIPO varchar(100),
		PRO_VALOR varchar(4000) not null,
		PRO_DESCRIPCION varchar(4000) not null,
		PRIMARY KEY (PRO_CLAVE, PRO_TIPO)
   );


  CREATE TABLE seguimiento_alumnos.cel_dominio
   (	DOM_ID bigint default NULL auto_increment primary key,
		DOM_DOMINIO varchar(100),
		DOM_CLAVE varchar(250) not null,
		DOM_EDITABLE smallint not null,
		DOM_TEXTO varchar(4000) not null,
		DOM_ORDEN smallint not null
   );


  CREATE TABLE seguimiento_alumnos.cel_indicador
   (	IND_ID bigint default NULL auto_increment primary key, 
		IND_NOMBRE varchar(250) not null,
		IND_DESCRIPCION varchar(250) not null,
    	AUD_FECHA_INS TIMESTAMP not null, 
    	AUD_FECHA_UPD TIMESTAMP not null, 
    	AUD_USR_INS VARCHAR(250) not null, 
    	AUD_USR_UPD VARCHAR(250) not null
   );


  CREATE TABLE seguimiento_alumnos.cel_interaccion_caso
   (	CAS_ID  bigint default NULL auto_increment primary key, 
		CAS_OBSERVACIONES_GRALES varchar(3000),    	
		ALU_ID bigint(10) unsigned not null,
    	AUD_FECHA_INS TIMESTAMP not null, 
    	AUD_FECHA_UPD TIMESTAMP not null, 
    	AUD_USR_INS VARCHAR(250) not null, 
    	AUD_USR_UPD VARCHAR(250) not null
   );


ALTER TABLE seguimiento_alumnos.cel_interaccion_caso ADD CONSTRAINT CEL_INT_CASO_ALU_ID FOREIGN KEY (ALU_ID)
    REFERENCES celiacie_moodle2.mdl_user (id);



  CREATE TABLE seguimiento_alumnos.cel_interaccion_caso_detalle
   (	ICD_ID bigint default NULL auto_increment primary key, 
		ICD_RTA_TIPO varchar(250) not null,
		ICD_OBSERVACIONES varchar(2000) null,
		CAS_ID bigint not null,
		IND_ID bigint not null,
    	AUD_FECHA_INS TIMESTAMP not null, 
    	AUD_FECHA_UPD TIMESTAMP not null, 
    	AUD_USR_INS VARCHAR(250) not null, 
    	AUD_USR_UPD VARCHAR(250) not null
   );

 ALTER TABLE seguimiento_alumnos.cel_interaccion_caso_detalle ADD CONSTRAINT CEL_INT_CASO_IND_ID FOREIGN KEY (IND_ID)
    REFERENCES seguimiento_alumnos.cel_indicador (IND_ID);


 CREATE TABLE seguimiento_alumnos.cel_indicador_usuario_estado
   (	IUE_ID bigint default NULL auto_increment primary key, 
		IND_ID bigint not null,
		IUE_IGNORAR_HASTA TIMESTAMP not null,
		IUE_OBSERVACIONES varchar(250) not null,
    	AUD_FECHA_INS TIMESTAMP not null, 
    	AUD_FECHA_UPD TIMESTAMP not null, 
    	AUD_USR_INS VARCHAR(250) not null, 
    	AUD_USR_UPD VARCHAR(250) not null
   );

 ALTER TABLE seguimiento_alumnos.cel_indicador_usuario_estado ADD CONSTRAINT CEL_IND_USR_EST_IND_ID FOREIGN KEY (IND_ID)
    REFERENCES seguimiento_alumnos.cel_indicador (IND_ID);

    
  CREATE TABLE seguimiento_alumnos.cel_alerta
   (	ALE_ID bigint default NULL auto_increment primary key,
		ALE_CODIGO VARCHAR(100) not null UNIQUE,
    	ALE_NOMBRE VARCHAR(200) not null, 
    	ALE_DESCRIPCION VARCHAR(400)
   );

  CREATE TABLE seguimiento_alumnos.cel_alerta_notificacion
   (	ALN_ID bigint default NULL auto_increment primary key,
    	ALE_ID bigint not null, 
    	USR_ID bigint(10) unsigned not null,
		ALE_FECHA_ENVIO_MAIL timestamp not null
   );

ALTER TABLE seguimiento_alumnos.cel_alerta_notificacion ADD CONSTRAINT CEL_ALN_ALE_ID FOREIGN KEY (ALE_ID)
    REFERENCES seguimiento_alumnos.cel_alerta (ALE_ID);

ALTER TABLE seguimiento_alumnos.cel_alerta_notificacion ADD CONSTRAINT CEL_ALN_USR_ID FOREIGN KEY (USR_ID)
    REFERENCES celiacie_moodle2.mdl_user (ID);

    

    
-- VIEWS
CREATE OR REPLACE VIEW `seguimiento_alumnos`.`vw_alumnos_activos` AS
select distinct
        `usr`.`id` AS `id`,
        `usr`.`auth` AS `auth`,
        `usr`.`confirmed` AS `confirmed`,
        `usr`.`policyagreed` AS `policyagreed`,
        `usr`.`deleted` AS `deleted`,
        `usr`.`suspended` AS `suspended`,
        `usr`.`mnethostid` AS `mnethostid`,
        `usr`.`username` AS `username`,
        `usr`.`password` AS `password`,
        `usr`.`idnumber` AS `idnumber`,
        `usr`.`firstname` AS `firstname`,
        `usr`.`lastname` AS `lastname`,
        `usr`.`email` AS `email`,
        `usr`.`emailstop` AS `emailstop`,
        `usr`.`icq` AS `icq`,
        `usr`.`skype` AS `skype`,
        `usr`.`yahoo` AS `yahoo`,
        `usr`.`aim` AS `aim`,
        `usr`.`msn` AS `msn`,
        `usr`.`phone1` AS `phone1`,
        `usr`.`phone2` AS `phone2`,
        `usr`.`institution` AS `institution`,
        `usr`.`department` AS `department`,
        `usr`.`address` AS `address`,
        `usr`.`city` AS `city`,
        `usr`.`country` AS `country`,
        `usr`.`lang` AS `lang`,
        `usr`.`theme` AS `theme`,
        `usr`.`timezone` AS `timezone`,
        `usr`.`firstaccess` AS `firstaccess`,
        `usr`.`lastaccess` AS `lastaccess`,
        `usr`.`lastlogin` AS `lastlogin`,
        `usr`.`currentlogin` AS `currentlogin`,
        `usr`.`lastip` AS `lastip`,
        `usr`.`secret` AS `secret`,
        `usr`.`picture` AS `picture`,
        `usr`.`url` AS `url`,
        `usr`.`description` AS `description`,
        `usr`.`descriptionformat` AS `descriptionformat`,
        `usr`.`mailformat` AS `mailformat`,
        `usr`.`maildigest` AS `maildigest`,
        `usr`.`maildisplay` AS `maildisplay`,
        `usr`.`htmleditor` AS `htmleditor`,
        `usr`.`ajax` AS `ajax`,
        `usr`.`autosubscribe` AS `autosubscribe`,
        `usr`.`trackforums` AS `trackforums`,
        `usr`.`timecreated` AS `timecreated`,
        `usr`.`timemodified` AS `timemodified`,
        `usr`.`trustbitmask` AS `trustbitmask`,
        `usr`.`imagealt` AS `imagealt`,
        `usr`.`screenreader` AS `screenreader`,
        `usrinfo`.`data` AS `matricula`
FROM celiacie_moodle2.mdl_user usr
JOIN celiacie_moodle2.mdl_user_enrolments ue ON ue.userid = usr.id
JOIN celiacie_moodle2.mdl_user_info_data usrinfo ON usrinfo.userid=usr.id
JOIN celiacie_moodle2.mdl_enrol e ON e.id = ue.enrolid
JOIN celiacie_moodle2.mdl_role_assignments ra ON ra.userid = usr.id
JOIN celiacie_moodle2.mdl_context ct ON ct.id = ra.contextid AND ct.contextlevel = 50
JOIN vw_cursos_activos c ON c.id = ct.instanceid AND e.courseid = c.id
JOIN celiacie_moodle2.mdl_role r ON r.id = ra.roleid AND r.shortname = 'student'
WHERE e.status = 0 AND usr.suspended = 0 AND usr.deleted = 0 -- Usuarios que estén activos
AND (ue.timeend = 0 OR ue.timeend > NOW()) AND ue.status = 0 -- una asignación puede tener fecha de fin, por lo que comprobamos que esté vigente.
AND usrinfo.fieldid=1 -- Para la matrícula del usuario
AND auth != 'nologin' -- Usuarios que no fueron excluidos de poder loguearse.
;


create or replace view `seguimiento_alumnos`.`vw_docentes_no_ingresan_a_moodle` as
select * from celiacie_moodle2.mdl_user usr
where 1=1
and deleted <> 1
and suspended <> 1
and exists (select 1 from celiacie_moodle2.mdl_role_assignments ra where ra.userid=usr.id and (ra.roleid = 3 or ra.roleid = 4))
and DATE_ADD(FROM_UNIXTIME(usr.lastaccess), INTERVAL 2 DAY) < now()
;

CREATE OR REPLACE VIEW `seguimiento_alumnos`.`vw_cursos_activos` AS
select *
FROM celiacie_moodle2.mdl_course c
where c.category=12 -- Materias de Marzo 2014
AND startdate > UNIX_TIMESTAMP(STR_TO_DATE('2014/01/01','%Y/%m/%d'))
;



create or replace view `seguimiento_alumnos`.`vw_examenes_reprobados` as
select q.id quiz_id,ue.userid userid,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,q.name quiz_name,gr.grade quiz_grade
  from celiacie_moodle2.mdl_quiz q
  join vw_cursos_activos c on c.id = q.course
  join celiacie_moodle2.mdl_enrol e on e.courseid = c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id = ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid = u.id
  LEFT  JOIN  celiacie_moodle2.mdl_quiz_grades gr on gr.quiz=q.id and gr.userid=ue.userid
  where not exists (select 1 from celiacie_moodle2.mdl_quiz_grades gr where gr.quiz=q.id and gr.userid=ue.userid and gr.grade > 60)-- El alumno ha rendido el examen y obtuvo más de 60 puntos.
  and FROM_UNIXTIME(q.timeclose) < now()-- La fecha de cierre del examen tiene que ser anterior a la fecha actual para que se considere.
  and true=coalesce(FROM_UNIXTIME(q.timeclose) > (select max(cdet.aud_fecha_ins) from seguimiento_alumnos.cel_interaccion_caso_detalle cdet join seguimiento_alumnos.cel_interaccion_caso caso on cdet.CAS_ID=caso.cas_id where caso.alu_id=ue.userid and cdet.ind_id=3), true)-- Si hay al menos una interacción con el alumno sobre examenes reprobados, consideramos sólo los examenes posteriores a esa fecha.;

;

create or replace view `seguimiento_alumnos`.`vw_tpsvencidos` as
select a.id assignment_id,ue.userid userid,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,a.name assignment_name
  from celiacie_moodle2.mdl_assignment a
  join vw_cursos_activos c on c.id = a.course
  join celiacie_moodle2.mdl_enrol e on e.courseid=c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id=ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid=u.id
  where FROM_UNIXTIME(a.timedue) < now()
  and not exists (select 1 from celiacie_moodle2.mdl_assignment_submissions asu where asu.userid=ue.userid and asu.assignment=a.id) -- Comprobamos si fue enviado
  and not exists (select 1 from seguimiento_alumnos.cel_interaccion_caso_detalle cdet join seguimiento_alumnos.cel_interaccion_caso caso on cdet.CAS_ID=caso.cas_id where caso.alu_id=ue.userid and cdet.ind_id=2 and DATE_ADD(cdet.AUD_FECHA_INS, INTERVAL 7 DAY) > now())

;

CREATE OR REPLACE VIEW `seguimiento_alumnos`.`vw_alumno_materias` AS
select u.id userid,c.*
  from vw_cursos_activos c
  join celiacie_moodle2.mdl_enrol e on e.courseid = c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id = ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid = u.id
;


CREATE OR REPLACE VIEW `seguimiento_alumnos`.`vw_materias_por_quedar_libres` AS
select u.id userid,c.*
  from celiacie_moodle2.mdl_quiz q
  join  seguimiento_alumnos.vw_cursos_activos c on c.id = q.course
  join celiacie_moodle2.mdl_enrol e on e.courseid = c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id = ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid = u.id
  LEFT  JOIN  celiacie_moodle2.mdl_quiz_grades gr on gr.quiz=q.id and gr.userid=ue.userid
where 1=1
  and FROM_UNIXTIME(q.timeclose) < now()-- La fecha de cierre del examen tiene que ser anterior a la fecha actual para que se considere.
  and true=coalesce(FROM_UNIXTIME(q.timeclose) > (select max(cdet.aud_fecha_ins) from seguimiento_alumnos.cel_interaccion_caso_detalle cdet join seguimiento_alumnos.cel_interaccion_caso caso on cdet.CAS_ID=caso.cas_id where caso.alu_id=ue.userid and cdet.ind_id=4), true)-- Si hay al menos una interacción con el alumno sobre examenes reprobados, consideramos sólo los examenes posteriores a esa fecha.;
--  and ue.userid=210
  and (gr.grade is null or gr.grade < 60 ) -- examen reprobado o ausente
  and upper(q.name) not like '%RECUPERATORIO%'-- el examen reprobado no es un recuperatorio
  and exists (select 1
  from celiacie_moodle2.mdl_quiz q2, celiacie_moodle2.mdl_quiz_grades qg2
where q2.id=qg2.quiz
  and q2.course=c.id
  and qg2.userid=ue.userid
  and upper(q2.name) like '%RECUPERATORIO%'
  and (qg2.grade is null or qg2.grade < 60 )
)-- tiene un recuperatorio que tambiene esta reprobado
;
-- VIEWS   


-- FUNCTIONS
DROP FUNCTION IF EXISTS seguimiento_alumnos.f_materias_por_quedar_libres;
DELIMITER |
CREATE FUNCTION seguimiento_alumnos.f_materias_por_quedar_libres (p_alu_id bigint(10) unsigned)
 RETURNS bigint
 NOT DETERMINISTIC
 BEGIN
  DECLARE select_var smallint default 0;
  SET select_var = (

				select count(*) from vw_materias_por_quedar_libres where userid=p_alu_id  
  );

  IF select_var = 0 THEN RETURN 1;
  ELSEIF (select_var > 0) THEN RETURN select_var*15;
  ELSE RETURN 0;
  END IF;
  RETURN select_var;
END;

|

DELIMITER ;

DROP FUNCTION IF EXISTS seguimiento_alumnos.f_tpsvencidos;
DELIMITER |
CREATE FUNCTION seguimiento_alumnos.f_tpsvencidos (p_alu_id bigint(10) unsigned)
 RETURNS bigint
 NOT DETERMINISTIC
 BEGIN
  DECLARE select_var smallint default 0;
  SET select_var = (select count(*) from vw_tpsvencidos where userid=p_alu_id
  
  );

  IF select_var = 0 THEN RETURN 1;-- El indicador es correcto, no hay TPs vencidos.
  ELSEIF (select_var > 0) THEN RETURN select_var*10;-- El indicador devuelve al menos un TP vencido.
  ELSE RETURN 0;
  END IF;
  RETURN select_var;
END;
|

DELIMITER ;



DROP FUNCTION IF EXISTS seguimiento_alumnos.f_ingreso_moodle_cie;
DELIMITER |
CREATE FUNCTION seguimiento_alumnos.f_ingreso_moodle_cie (p_alu_id bigint(10) unsigned)
 RETURNS bigint
 NOT DETERMINISTIC
 BEGIN
  DECLARE select_var smallint default 0;
  SET select_var = (
	select count(*) from celiacie_moodle2.mdl_user u
	where DATE_ADD(FROM_UNIXTIME(u.lastaccess), INTERVAL 7 DAY) < now()
	and u.id=p_alu_id 
	and not exists (select 1 from seguimiento_alumnos.cel_interaccion_caso_detalle cdet join seguimiento_alumnos.cel_interaccion_caso caso on cdet.CAS_ID=caso.cas_id where caso.alu_id=p_alu_id and cdet.ind_id=1 and DATE_ADD(cdet.AUD_FECHA_INS, INTERVAL 7 DAY) > now())-- Si pasaron 7 días o más desde que se habló con el alumno, el indicador debe comprobarse nuevamente.
	);
	IF select_var = 0 THEN RETURN 1;-- El indicador es correcto, el usuario ingresó al CIE en los últimos 7 días.
	ELSEIF (select_var > 0) THEN RETURN 2;-- El usuario no ingresó al CIE en los últimos 7 días.
	ELSE RETURN 0;
	END IF;
  RETURN select_var;
END;
|

DELIMITER ;


DROP FUNCTION IF EXISTS seguimiento_alumnos.f_examenes_reprobados;
DELIMITER |
CREATE FUNCTION seguimiento_alumnos.f_examenes_reprobados (p_alu_id bigint(10) unsigned)
 RETURNS bigint
 NOT DETERMINISTIC
 BEGIN
  DECLARE select_var smallint default 0;
  SET select_var = (select count(*) from vw_examenes_reprobados where userid=p_alu_id
  );
  IF select_var = 0 THEN RETURN 1;-- El indicador es correcto, no hay examenes reprobados o no rendidos.
  ELSEIF (select_var > 0) THEN RETURN select_var * 5;-- El indicador devuelve al menos un examen reprobado o no rendido.
  ELSE RETURN 0;
  END IF;
  RETURN select_var;
END;
|

DELIMITER ;

DROP function IF EXISTS `fn_getValorIndicador`;
DELIMITER $$
USE `seguimiento_alumnos`$$
CREATE FUNCTION `fn_getValorIndicador` (p_usr_id bigint,p_ind_id bigint)
RETURNS INTEGER
BEGIN
	DECLARE result bigint;
  IF p_ind_id = 1 -- No ingresa al CIE
	THEN 
		set result=f_ingreso_moodle_cie(p_usr_id);
  ELSEIF p_ind_id = 2 -- Trabajo práctico vencido
	THEN
		set result=f_tpsvencidos(p_usr_id);
  ELSEIF p_ind_id = 3 -- Examen reprobado
	THEN
		set result=f_examenes_reprobados(p_usr_id);
  ELSEIF p_ind_id = 4 -- Quedó libre en una materia
	THEN
		set result=f_materias_por_quedar_libres(p_usr_id);
   END IF;
	
RETURN result; 
END$$

DELIMITER ;

-- PROCEDURES
    
DELIMITER $$

DROP PROCEDURE IF EXISTS p_alumnos_activos_con_indicadores $$
CREATE PROCEDURE p_alumnos_activos_con_indicadores(IN p_list_indicadores varchar(300),
												   IN p_matricula varchar(300),
												   IN p_apellido varchar(300),
												   IN p_nombre varchar(300))
BEGIN
	TRUNCATE TABLE tmp_indicadores_alumnos;

	INSERT INTO tmp_indicadores_alumnos
	select	CONCAT(ac.id,ind.IND_ID) ina_id,
				ac.id usr_id,
				ind.IND_ID id_indicador,
				ind.IND_NOMBRE nombre_indicador,
				ind.IND_DESCRIPCION desc_indicador,
				fn_getValorIndicador(ac.id,ind.IND_ID) valor_indicador
		  from vw_alumnos_activos ac,cel_indicador ind;


	SET @primeraParte =  'select alu.*,ind.*, (select sum(valor_indicador) from tmp_indicadores_alumnos indsuma where indsuma.usr_id=alu.id) criticidad
				 from vw_alumnos_activos alu,tmp_indicadores_alumnos ind
				 where alu.id=ind.usr_id
				  and exists (select 1 
								from tmp_indicadores_alumnos sind 
							   where sind.usr_id=alu.id 
								 and sind.valor_indicador>=2';

       IF p_list_indicadores is not NULL and p_list_indicadores<>''
      THEN
         SET @filtroIndicadores = CONCAT(' and sind.id_indicador in(', p_list_indicadores,')) ');
		 SET @filtroIndicadores2 = CONCAT(' and ind.id_indicador in(', p_list_indicadores,') ');
	  ELSE
		SET @filtroIndicadores = ')';
		SET @filtroIndicadores2 = '';
      END IF; 

       IF p_matricula is not NULL and p_matricula<>''
      THEN
         SET @filtroMatricula = CONCAT(' and LOWER(matricula) like''',p_matricula,'%''');
	  ELSE
		SET @filtroMatricula = '';
      END IF; 

       IF p_apellido is not NULL and p_apellido<>''
      THEN
         SET @filtroApellido = CONCAT(' and LOWER(lastname) like''',p_apellido,'%''');
	  ELSE
		SET @filtroApellido = '';
      END IF; 

       IF p_nombre is not NULL and p_nombre<>''
      THEN
         SET @filtroNombre = CONCAT(' and LOWER(firstname) like''',p_nombre,'%''');
	  ELSE
		SET @filtroNombre = '';
      END IF;

	SET @orderBy =  ' order by criticidad desc,alu.id,ind.id_indicador,lastname,firstname ';
	SET @Query = CONCAT(@primeraParte, @filtroIndicadores,@filtroIndicadores2,@filtroMatricula,@filtroApellido,@filtroNombre,@orderBy,' LIMIT 0,200');

    PREPARE stmt FROM @Query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
	


END $$

