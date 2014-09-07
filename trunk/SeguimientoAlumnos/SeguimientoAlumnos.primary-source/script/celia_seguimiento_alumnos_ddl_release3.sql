
-- drop table if exists cel_alerta_enviada;
create table `seguimiento_alumnos`.`cel_alerta_enviada`
   (	ale_ID  bigint default NULL auto_increment primary key,
		ale_obj_tipo varchar(250) not null,
		ale_obj_id bigint not null,
		USR_ID bigint not null,
		ale_alerta varchar(250) not null,
		aud_fecha_ins TIMESTAMP not null
   );
 
create or replace view `seguimiento_alumnos`.`vw_alerta_alumnos_nuevos_examenes` as
select distinct q.id quiz_id,ue.userid userid,u.lastname, u.firstname, u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,q.name quiz_name,DATE_FORMAT(FROM_UNIXTIME(timeopen), '%d/%m/%Y %H:%i:%s') fecha_inicio,DATE_FORMAT(FROM_UNIXTIME(timeclose), '%d/%m/%Y %H:%i:%s') fecha_vencimiento
  from celiacie_moodle2.mdl_quiz q
  join seguimiento_alumnos.vw_cursos_activos c on c.id = q.course
  join celiacie_moodle2.mdl_enrol e on e.courseid = c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id = ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid = u.id
where now() between DATE_SUB(FROM_UNIXTIME(timeopen), INTERVAL 5 DAY) and FROM_UNIXTIME(timeopen)  
and not exists (select 1 from seguimiento_alumnos.cel_alerta_enviada where ale_obj_tipo='mdl_quiz' and ale_obj_id=q.id and ale_alerta='ALU_NUEVO_QUIZ' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_alumnos_examenes_por_vencer` as
select distinct q.id quiz_id,ue.userid userid, u.lastname, u.firstname, u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,q.name quiz_name,DATE_FORMAT(FROM_UNIXTIME(timeopen), '%d/%m/%Y %H:%i:%s') fecha_inicio,DATE_FORMAT(FROM_UNIXTIME(timeclose), '%d/%m/%Y %H:%i:%s') fecha_vencimiento
  from celiacie_moodle2.mdl_quiz q
  join vw_cursos_activos c on c.id = q.course
  join celiacie_moodle2.mdl_enrol e on e.courseid = c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id = ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid = u.id
where now() between DATE_SUB(FROM_UNIXTIME(timeclose), INTERVAL 3 DAY) and FROM_UNIXTIME(timeclose)  
and not exists (select 1 from seguimiento_alumnos.cel_alerta_enviada where ale_obj_tipo='mdl_quiz' and ale_obj_id=q.id and ale_alerta='ALU_QUIZ_POR_VENCER' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_alumnos_examenes_vencidos` as
select distinct q.id quiz_id,ue.userid userid, u.lastname, u.firstname, u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,q.name quiz_name,DATE_FORMAT(FROM_UNIXTIME(timeopen), '%d/%m/%Y %H:%i:%s') fecha_inicio,DATE_FORMAT(FROM_UNIXTIME(timeclose), '%d/%m/%Y %H:%i:%s') fecha_vencimiento
  from celiacie_moodle2.mdl_quiz q
  join seguimiento_alumnos.vw_cursos_activos c on c.id = q.course
  join celiacie_moodle2.mdl_enrol e on e.courseid = c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id = ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid = u.id
  LEFT  JOIN  celiacie_moodle2.mdl_quiz_grades gr on gr.quiz=q.id and gr.userid=ue.userid
where FROM_UNIXTIME(timeclose) < now()
and gr.grade is null
and not exists (select 1 from seguimiento_alumnos.cel_alerta_enviada where ale_obj_tipo='mdl_quiz' and ale_obj_id=q.id and ale_alerta='ALU_QUIZ_VENCIDO' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_alumnos_nuevos_tp` as
select distinct a.id assignment_id,ue.userid userid, u.lastname, u.firstname, u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,a.name assignment_name,DATE_FORMAT(FROM_UNIXTIME(timeavailable), '%d/%m/%Y %H:%i:%s') fecha_inicio,DATE_FORMAT(FROM_UNIXTIME(timedue), '%d/%m/%Y %H:%i:%s') fecha_vencimiento
  from celiacie_moodle2.mdl_assignment a
  join seguimiento_alumnos.vw_cursos_activos c on c.id = a.course
  join celiacie_moodle2.mdl_enrol e on e.courseid=c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id=ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid=u.id
where now() between DATE_SUB(FROM_UNIXTIME(timeavailable), INTERVAL 4 DAY) and FROM_UNIXTIME(timeavailable)
and not exists (select 1 from seguimiento_alumnos.cel_alerta_enviada where ale_obj_tipo='mdl_assignment' and ale_obj_id=a.id and ale_alerta='ALU_NUEVO_TP' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_alumnos_tp_por_vencer` as
select distinct a.id assignment_id,ue.userid userid, u.lastname, u.firstname, u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,a.name assignment_name,DATE_FORMAT(FROM_UNIXTIME(timeavailable), '%d/%m/%Y %H:%i:%s') fecha_inicio,DATE_FORMAT(FROM_UNIXTIME(timedue), '%d/%m/%Y %H:%i:%s') fecha_vencimiento
  from celiacie_moodle2.mdl_assignment a
  join seguimiento_alumnos.vw_cursos_activos c on c.id = a.course
  join celiacie_moodle2.mdl_enrol e on e.courseid=c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id=ue.enrolid
  join celiacie_moodle2.mdl_user u on u.id = ue.userid
  left join celiacie_moodle2.mdl_assignment_submissions sub on sub.assignment = a.id and sub.userid = u.id  
where now() between DATE_SUB(FROM_UNIXTIME(timedue), INTERVAL 2 DAY) and FROM_UNIXTIME(timedue)
and sub.grade is null
and not exists (select 1 from seguimiento_alumnos.cel_alerta_enviada where ale_obj_tipo='mdl_assignment' and ale_obj_id=a.id and ale_alerta='ALU_TP_POR_VENCER' and usr_id=ue.userid)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_tpsvencidos` as
select distinct a.id assignment_id,ue.userid userid, u.lastname, u.firstname, u.email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,a.name assignment_name,DATE_FORMAT(FROM_UNIXTIME(timeavailable), '%d/%m/%Y %H:%i:%s') fecha_inicio,DATE_FORMAT(FROM_UNIXTIME(timedue), '%d/%m/%Y %H:%i:%s') fecha_vencimiento
  from celiacie_moodle2.mdl_assignment a
  join seguimiento_alumnos.vw_cursos_activos c on c.id = a.course
  join celiacie_moodle2.mdl_enrol e on e.courseid=c.id
  join celiacie_moodle2.mdl_user_enrolments ue on e.id=ue.enrolid
  join celiacie_moodle2.mdl_user u on ue.userid=u.id
  LEFT JOIN celiacie_moodle2.mdl_assignment_submissions asu on asu.userid=ue.userid and asu.assignment=a.id
  where FROM_UNIXTIME(a.timedue) < now()
  and asu.grade is null
  and not exists (select 1 from seguimiento_alumnos.cel_alerta_enviada 
	  where ale_obj_tipo='mdl_assignment' 
	  	and ale_obj_id=a.id 
	  	and ale_alerta='ALU_TP_VENCIDO' 
	  	and usr_id=ue.userid)
;



create or replace view `seguimiento_alumnos`.`vw_alerta_docentes_examenes_nota_pendiente` as
select q.id quiz_id,null userid, (select u.email from celiacie_moodle2.mdl_role_assignments ra, celiacie_moodle2.mdl_user u
where ra.userid=u.id
and contextid = (select id from celiacie_moodle2.mdl_context where contextlevel = 50 and instanceid =c.id)
and ra.roleid = 3) email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,q.name quiz_name, DATE_FORMAT(FROM_UNIXTIME(timeopen),'%d/%m/%Y %H:%i:%s') fecha_inicio,DATE_FORMAT(FROM_UNIXTIME(timeclose),'%d/%m/%Y %H:%i:%s') fecha_vencimiento
  from celiacie_moodle2.mdl_quiz q
  join seguimiento_alumnos.vw_cursos_activos c on c.id = q.course
where DATE_ADD(FROM_UNIXTIME(timeclose), INTERVAL 4 DAY) < now()
 and exists (select 1 from celiacie_moodle2.mdl_quiz_grades gr where gr.quiz=q.id and gr.grade = 0.00000)
;


create or replace view `seguimiento_alumnos`.`vw_alerta_docentes_tp_nota_pendiente` as
select a.id assignment_id,null userid,(select u.email from celiacie_moodle2.mdl_role_assignments ra, celiacie_moodle2.mdl_user u
where ra.userid=u.id
and contextid = (select id from celiacie_moodle2.mdl_context where contextlevel = 50 and instanceid =c.id)
and ra.roleid = 3) email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,a.name assignment_name,DATE_FORMAT(FROM_UNIXTIME(timeavailable), '%d/%m/%Y %H:%i:%s') fecha_inicio,DATE_FORMAT(FROM_UNIXTIME(timedue), '%d/%m/%Y %H:%i:%s') fecha_vencimiento
  from celiacie_moodle2.mdl_assignment a
  join seguimiento_alumnos.vw_cursos_activos c on c.id = a.course
where DATE_ADD(FROM_UNIXTIME(timedue), INTERVAL 4 DAY) < now()
 and exists (select 1 from celiacie_moodle2.mdl_assignment_submissions asu where asu.assignment=a.id and asu.grade = 0)
;

create or replace view `seguimiento_alumnos`.`vw_alerta_docentes_tps_pendientes_carga` as
select a.id assignment_id,null userid,(select u.email from celiacie_moodle2.mdl_role_assignments ra, celiacie_moodle2.mdl_user u
where ra.userid=u.id
and contextid = (select id from celiacie_moodle2.mdl_context where contextlevel = 50 and instanceid =c.id)
and ra.roleid = 3) email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,a.name assignment_name,DATE_FORMAT(FROM_UNIXTIME(timeavailable), '%d/%m/%Y %H:%i:%s') fecha_inicio,DATE_FORMAT(FROM_UNIXTIME(timedue), '%d/%m/%Y %H:%i:%s') fecha_vencimiento
  from celiacie_moodle2.mdl_assignment a
  join seguimiento_alumnos.vw_cursos_activos c on c.id = a.course
where now() between DATE_SUB(FROM_UNIXTIME(timeavailable), INTERVAL 10 DAY) and FROM_UNIXTIME(timeavailable)
and LENGTH(intro) < 95 -- Si tiene menos de esa longitud debe ser porque no está cargado
;


create or replace view `seguimiento_alumnos`.`vw_alerta_docentes_examenes_pendientes_carga` as
select q.id quiz_id,null userid,(select u.email from celiacie_moodle2.mdl_role_assignments ra, celiacie_moodle2.mdl_user u
where ra.userid=u.id
and contextid = (select id from celiacie_moodle2.mdl_context where contextlevel = 50 and instanceid =c.id)
and ra.roleid = 3) email,c.fullname course_fullname,c.shortname course_shortname,c.category course_category
	,q.name quiz_name,DATE_FORMAT(FROM_UNIXTIME(timeopen), '%d/%m/%Y %H:%i:%s') fecha_inicio,DATE_FORMAT(FROM_UNIXTIME(timeclose), '%d/%m/%Y %H:%i:%s') fecha_vencimiento
  from celiacie_moodle2.mdl_quiz q
  join seguimiento_alumnos.vw_cursos_activos c on c.id = q.course
where now() between DATE_SUB(FROM_UNIXTIME(timeopen), INTERVAL 10 DAY) and FROM_UNIXTIME(timeopen)   
and LENGTH(intro) < 95 -- Si tiene menos de esa longitud debe ser porque no está cargado
;


create or replace VIEW `seguimiento_alumnos`.`vw_alumno_materias` AS
    select distinct
        `u`.`id` AS `userid`,
        `c`.`id` AS `id`,
        `c`.`category` AS `category`,
        `c`.`sortorder` AS `sortorder`,
        `c`.`fullname` AS `fullname`,
        `c`.`shortname` AS `shortname`,
        `c`.`idnumber` AS `idnumber`,
        `c`.`summary` AS `summary`,
        `c`.`summaryformat` AS `summaryformat`,
        `c`.`format` AS `format`,
        `c`.`showgrades` AS `showgrades`,
        `c`.`modinfo` AS `modinfo`,
        `c`.`newsitems` AS `newsitems`,
        `c`.`startdate` AS `startdate`,
        `c`.`numsections` AS `numsections`,
        `c`.`marker` AS `marker`,
        `c`.`maxbytes` AS `maxbytes`,
        `c`.`legacyfiles` AS `legacyfiles`,
        `c`.`showreports` AS `showreports`,
        `c`.`visible` AS `visible`,
        `c`.`visibleold` AS `visibleold`,
        `c`.`hiddensections` AS `hiddensections`,
        `c`.`groupmode` AS `groupmode`,
        `c`.`groupmodeforce` AS `groupmodeforce`,
        `c`.`defaultgroupingid` AS `defaultgroupingid`,
        `c`.`lang` AS `lang`,
        `c`.`theme` AS `theme`,
        `c`.`timecreated` AS `timecreated`,
        `c`.`timemodified` AS `timemodified`,
        `c`.`requested` AS `requested`,
        `c`.`restrictmodules` AS `restrictmodules`,
        `c`.`enablecompletion` AS `enablecompletion`,
        `c`.`completionstartonenrol` AS `completionstartonenrol`,
        `c`.`completionnotify` AS `completionnotify`
    from
        (((`seguimiento_alumnos`.`vw_cursos_activos` `c`
        join `celiacie_moodle2`.`mdl_enrol` `e` ON ((`e`.`courseid` = `c`.`id`)))
        join `celiacie_moodle2`.`mdl_user_enrolments` `ue` ON ((`e`.`id` = `ue`.`enrolid`)))
        join `celiacie_moodle2`.`mdl_user` `u` ON ((`ue`.`userid` = `u`.`id`)));


create or replace VIEW `seguimiento_alumnos`.`vw_alumnos_activos` AS
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
    from
        (((((((`celiacie_moodle2`.`mdl_user` `usr`
        join `celiacie_moodle2`.`mdl_user_enrolments` `ue` ON ((`ue`.`userid` = `usr`.`id`)))
        join `celiacie_moodle2`.`mdl_user_info_data` `usrinfo` ON ((`usrinfo`.`userid` = `usr`.`id`)))
        join `celiacie_moodle2`.`mdl_enrol` `e` ON ((`e`.`id` = `ue`.`enrolid`)))
        join `celiacie_moodle2`.`mdl_role_assignments` `ra` ON ((`ra`.`userid` = `usr`.`id`)))
        join `celiacie_moodle2`.`mdl_context` `ct` ON (((`ct`.`id` = `ra`.`contextid`)
            and (`ct`.`contextlevel` = 50))))
        join `seguimiento_alumnos`.`vw_cursos_activos` `c` ON (((`c`.`id` = `ct`.`instanceid`)
            and (`e`.`courseid` = `c`.`id`))))
        join `celiacie_moodle2`.`mdl_role` `r` ON (((`r`.`id` = `ra`.`roleid`)
            and (`r`.`shortname` = 'student'))))
        
    where
        ((`e`.`status` = 0)
            and (`usr`.`suspended` = 0)
            and (`usr`.`deleted` = 0)
            and ((`ue`.`timeend` = 0)
            or (`ue`.`timeend` > now()))
            and (`ue`.`status` = 0)
            and (`usrinfo`.`fieldid` = 1)
            and (`usr`.`auth` <> 'nologin'));
            
     

create or replace VIEW `seguimiento_alumnos`.`vw_periodo_etapa_materias` AS
    select 
        `catp`.`id` AS `per_cat_id`,
        `catp`.`name` AS `periodo`,
        `catp`.`visible` AS `periodo_visible`,
        `catp`.`sortorder` AS `per_order`,
        `cat`.`id` AS `etp_cat_id`,
        `cat`.`name` AS `etapa`,
        `cat`.`description` AS `etapa_descripcion`,
        `cat`.`visible` AS `etapa_visible`,
        `cat`.`sortorder` AS `etp_order`,
        `mat`.`id` AS `mat_id`,
        `mat`.`fullname` AS `mat_nombre_completo`,
        `mat`.`shortname` AS `mat_nombre_corto`,
        `mat`.`sortorder` AS `orden`,
        `mat`.`visible` AS `visible`
    from
        ((`celiacie_moodle2`.`mdl_course_categories` `catp`
        join `celiacie_moodle2`.`mdl_course_categories` `cat` ON (((`cat`.`parent` = `catp`.`id`)
            and (`catp`.`parent` = 0))))
        join `celiacie_moodle2`.`mdl_course` `mat` ON (((`mat`.`category` = `cat`.`id`)
            and (`mat`.`category` <> 0))))
    order by `catp`.`name` , `mat`.`sortorder` , `cat`.`name`;        

    
create or replace VIEW `seguimiento_alumnos`.`vw_materias_activas` AS
    select distinct
        `vw_periodo_etapa_materias`.`mat_id` AS `mat_id`,
        `vw_periodo_etapa_materias`.`mat_nombre_completo` AS `mat_nombre_completo`,
        `vw_periodo_etapa_materias`.`mat_nombre_corto` AS `mat_nombre_corto`,
        `vw_periodo_etapa_materias`.`orden` AS `orden`,
        `vw_periodo_etapa_materias`.`visible` AS `visible`,
        `vw_periodo_etapa_materias`.`etp_cat_id` AS `etp_cat_id`,
        `vw_periodo_etapa_materias`.`per_cat_id` AS `per_cat_id`
    from
        `seguimiento_alumnos`.`vw_periodo_etapa_materias`;


create or replace view `seguimiento_alumnos`.`vw_alerta_alumno_libre_por_tp` AS
    select 
        `am`.`userid` AS `userid`,
        `u`.`firstname` AS `firstname`,
        `u`.`lastname` AS `lastname`,
        `u`.`email` AS `email`,
        `m`.`per_cat_id` AS `per_cat_id`,
        `m`.`mat_id` AS `mat_id`,
        count(`tp`.`id`) AS `cant_tp`,
        sum((case
            when isnull(`na`.`id`) then 1
            else 0
        end)) AS `cant_ausente`,
        sum((case
            when
                ((`na`.`grade` < 60)
                    and (`na`.`grade` <> -(1)))
            then
                1
            else 0
        end)) AS `cant_reprobado`
    from
        ((((`seguimiento_alumnos`.`vw_alumno_materias` `am`
        join `celiacie_moodle2`.`mdl_user` `u` ON ((`am`.`userid` = `u`.`id`)))
        join `seguimiento_alumnos`.`vw_materias_activas` `m` ON ((`m`.`mat_id` = `am`.`id` and `m`.`per_cat_id` = (select p.pro_valor from cel_propiedad p where p.pro_clave = 'periodo_activo'))))
        join `celiacie_moodle2`.`mdl_assignment` `tp` ON (((`tp`.`course` = `am`.`id`)
            and (not ((ucase(`tp`.`name`) like '%RECUPERATORIO%')))
            and (from_unixtime(`tp`.`timedue`) < now()))))
        left join `celiacie_moodle2`.`mdl_assignment_submissions` `na` ON (((`na`.`assignment` = `tp`.`id`)
            and (`na`.`userid` = `am`.`userid`))))
    where
        (not (exists( select 
                1
            from
                `seguimiento_alumnos`.`cel_alerta_enviada` `ae`
            where
                ((`ae`.`ale_obj_tipo` = 'mdl_course')
                    and (`ae`.`ale_obj_id` = `m`.`mat_id`)
                    and (`ae`.`USR_ID` = `u`.`id`)
                    and (`ae`.`ale_alerta` = 'ALU_POR_QUEDAR_LIBRE')))))
    group by `am`.`userid` , `u`.`email` , `m`.`per_cat_id` , `m`.`mat_id`
    having ((`cant_reprobado` + `cant_ausente`) >= 2);

UPDATE seguimiento_alumnos.cel_alerta_enviada SET ale_obj_tipo = 'mdl_course' where ale_alerta = 'ALU_POR_QUEDAR_LIBRE';

            
create or replace VIEW `seguimiento_alumnos`.`vw_cursos_activos` AS
    select 
        `c`.`id` AS `id`,
        `c`.`category` AS `category`,
        `c`.`sortorder` AS `sortorder`,
        `c`.`fullname` AS `fullname`,
        `c`.`shortname` AS `shortname`,
        `c`.`idnumber` AS `idnumber`,
        `c`.`summary` AS `summary`,
        `c`.`summaryformat` AS `summaryformat`,
        `c`.`format` AS `format`,
        `c`.`showgrades` AS `showgrades`,
        `c`.`modinfo` AS `modinfo`,
        `c`.`newsitems` AS `newsitems`,
        `c`.`startdate` AS `startdate`,
        `c`.`numsections` AS `numsections`,
        `c`.`marker` AS `marker`,
        `c`.`maxbytes` AS `maxbytes`,
        `c`.`legacyfiles` AS `legacyfiles`,
        `c`.`showreports` AS `showreports`,
        `c`.`visible` AS `visible`,
        `c`.`visibleold` AS `visibleold`,
        `c`.`hiddensections` AS `hiddensections`,
        `c`.`groupmode` AS `groupmode`,
        `c`.`groupmodeforce` AS `groupmodeforce`,
        `c`.`defaultgroupingid` AS `defaultgroupingid`,
        `c`.`lang` AS `lang`,
        `c`.`theme` AS `theme`,
        `c`.`timecreated` AS `timecreated`,
        `c`.`timemodified` AS `timemodified`,
        `c`.`requested` AS `requested`,
        `c`.`restrictmodules` AS `restrictmodules`,
        `c`.`enablecompletion` AS `enablecompletion`,
        `c`.`completionstartonenrol` AS `completionstartonenrol`,
        `c`.`completionnotify` AS `completionnotify`
    from
        ((`celiacie_moodle2`.`mdl_course_categories` `catp`
        join `celiacie_moodle2`.`mdl_course_categories` `cat` ON (((`cat`.`parent` = `catp`.`id`)
            and (`catp`.`parent` = 0))))
        join `celiacie_moodle2`.`mdl_course` `c` ON (((`c`.`category` = `cat`.`id`)
            and (`c`.`category` <> 0))))
        where catp.id = (select p.pro_valor from cel_propiedad p where p.pro_clave = 'periodo_activo');

            
create or replace VIEW `seguimiento_alumnos`.`vw_etapas_activas` AS
    select distinct
        `vw_periodo_etapa_materias`.`etp_cat_id` AS `etp_cat_id`,
        `vw_periodo_etapa_materias`.`etapa` AS `etapa`,
        `vw_periodo_etapa_materias`.`etapa_descripcion` AS `etapa_descripcion`,
        `vw_periodo_etapa_materias`.`etapa_visible` AS `etp_visible`,
        `vw_periodo_etapa_materias`.`etp_order` AS `etp_orden`,
        `vw_periodo_etapa_materias`.`per_cat_id` AS `per_cat_id`
    from
        `seguimiento_alumnos`.`vw_periodo_etapa_materias`;

        
create or replace VIEW `seguimiento_alumnos`.`vw_evaluaciones` AS
    select distinct
        `tp`.`id` AS `id`,
        'TRABAJO_PRACTICO' AS `tipo`,
        `tp`.`name` AS `titulo`,
        `tp`.`intro` AS `descripcion`,
        `tp`.`course` AS `mat_id`
    from
        `celiacie_moodle2`.`mdl_assignment` `tp` 
    union select distinct
        `exam`.`id` AS `id`,
        'EXAMEN' AS `tipo`,
        `exam`.`name` AS `titulo`,
        `exam`.`intro` AS `descripcion`,
        `exam`.`course` AS `mat_id`
    from
        `celiacie_moodle2`.`mdl_quiz` `exam`;

        
create or replace VIEW `seguimiento_alumnos`.`vw_examenes` AS
    select distinct
        `exam`.`id` AS `id`,
        'EXAMEN' AS `tipo_evaluacion`,
        `exam`.`name` AS `titulo`,
        `exam`.`intro` AS `descripcion`,
        from_unixtime(`exam`.`timeopen`) AS `fecha_hora_inicio`,
        from_unixtime(`exam`.`timeclose`) AS `fecha_hora_fin`,
        `exam`.`course` AS `mat_id`,
        `exam`.`timecreated` AS `timecreated`
    from
        `celiacie_moodle2`.`mdl_quiz` `exam`;

        
create or replace VIEW `seguimiento_alumnos`.`vw_examenes_alumno` AS
    select distinct
        `exam`.`id` AS `examid`,
        `alu_activo`.`id` AS `userid`,
        `notas`.`grade` AS `nota`
    from
        ((`celiacie_moodle2`.`mdl_quiz` `exam`
        left join `celiacie_moodle2`.`mdl_quiz_grades` `notas` ON ((`notas`.`quiz` = `exam`.`id`)))
        left join `seguimiento_alumnos`.`vw_alumnos_activos` `alu_activo` ON ((`alu_activo`.`id` = `notas`.`userid`)))
    where
        ((`alu_activo`.`id` is not null)
            and (`notas`.`id` is not null));
            
create or replace VIEW `seguimiento_alumnos`.`vw_trabajos_practicos` AS
    select distinct
        `tp`.`id` AS `id`,
        'TRABAJO_PRACTICO' AS `tipo_evaluacion`,
        `tp`.`name` AS `titulo`,
        `tp`.`intro` AS `descripcion`,
        from_unixtime(`tp`.`timeavailable`) AS `fecha_hora_inicio`,
        from_unixtime(`tp`.`timedue`) AS `fecha_hora_fin`,
        `tp`.`course` AS `mat_id`
    from
        `celiacie_moodle2`.`mdl_assignment` `tp`;

        
create or replace VIEW `seguimiento_alumnos`.`vw_trabajos_practicos_alumno` AS
    select distinct
        `tp`.`id` AS `tpid`,
        `alu_activo`.`id` AS `userid`,
        `tpe`.`grade` AS `nota`
    from
        ((`celiacie_moodle2`.`mdl_assignment` `tp`
        left join `celiacie_moodle2`.`mdl_assignment_submissions` `tpe` ON ((`tpe`.`assignment` = `tp`.`id`)))
        left join `seguimiento_alumnos`.`vw_alumnos_activos` `alu_activo` ON ((`tpe`.`userid` = `alu_activo`.`id`)))
    where
        ((`alu_activo`.`id` is not null)
            and (`tpe`.`id` is not null));
            

create or replace VIEW `seguimiento_alumnos`.`vw_notas_alumno` AS
    select 
        `u`.`id` AS `userid`,
        `catp`.`id` AS `per_cat_id`,
        `cat`.`id` AS `etp_cat_id`,
        `mat`.`id` AS `mat_id`,
        'TRABAJO_PRACTICO' AS `tipo_evaluacion`,
        `tp`.`id` AS `id`,
        `tpa`.`nota` AS `nota`
    from
        `celiacie_moodle2`.`mdl_user` `u`
        join `celiacie_moodle2`.`mdl_user_enrolments` `ue` 
        	ON `ue`.`userid` = `u`.`id`
        join `celiacie_moodle2`.`mdl_enrol` `e` 
        	ON `e`.`id` = `ue`.`enrolid`
        join `celiacie_moodle2`.`mdl_course_categories` `catp`
        	ON catp.id = (select p.pro_valor from seguimiento_alumnos.cel_propiedad p where p.pro_clave = 'periodo_activo')
        join `celiacie_moodle2`.`mdl_course_categories` `cat` 
        	ON `cat`.`parent` = `catp`.`id`
            and `catp`.`parent` = 0
        join `celiacie_moodle2`.`mdl_course` `mat` ON `mat`.`category` = `cat`.`id`
            and `mat`.`category` <> 0
            and mat.id= `e`.`courseid` 
        left join `seguimiento_alumnos`.`vw_trabajos_practicos` `tp` ON `tp`.`mat_id` = `mat`.`id`
        left join `seguimiento_alumnos`.`vw_trabajos_practicos_alumno` `tpa` ON `tpa`.`tpid` = `tp`.`id`
            and `tpa`.`userid` = `u`.`id`

            union all

         select 
        `u`.`id` AS `userid`,
        `catp`.`id` AS `per_cat_id`,
        `cat`.`id` AS `etp_cat_id`,
        `mat`.`id` AS `mat_id`,
        'EXAMEN' AS `tipo_evaluacion`,
        `exam`.`id` AS `id`,
        `exama`.`nota` AS `nota`
    from
        `celiacie_moodle2`.`mdl_user` `u`
        join `celiacie_moodle2`.`mdl_user_enrolments` `ue` ON `ue`.`userid` = `u`.`id`
        join `celiacie_moodle2`.`mdl_enrol` `e` ON `e`.`id` = `ue`.`enrolid`
        join `celiacie_moodle2`.`mdl_course_categories` `catp`
        	ON catp.id = (select p.pro_valor from seguimiento_alumnos.cel_propiedad p where p.pro_clave = 'periodo_activo')
        join `celiacie_moodle2`.`mdl_course_categories` `cat` 
        	ON `cat`.`parent` = `catp`.`id`
            and `catp`.`parent` = 0
        join `celiacie_moodle2`.`mdl_course` `mat` ON `mat`.`category` = `cat`.`id`
            and `mat`.`category` <> 0
            and mat.id= `e`.`courseid` 
        left join `seguimiento_alumnos`.`vw_examenes` `exam` ON `exam`.`mat_id` = `mat`.`id`
        left join `seguimiento_alumnos`.`vw_examenes_alumno` `exama` ON `exama`.`examid` = `exam`.`id`
            and `exama`.`userid` = `u`.`id`;
  
               
create or replace VIEW `seguimiento_alumnos`.`vw_listado_notas_alumno` AS
    select 
        `a`.`lastname` AS `lastname`,
        `a`.`firstname` AS `firstname`,
        `n`.`per_cat_id` AS `per_cat_id`,
        `n`.`etp_cat_id` AS `etp_cat_id`,
        `n`.`mat_id` AS `mat_id`,
        `n`.`tipo_evaluacion` AS `tipo_evaluacion`,
        `n`.`id` AS `evalid`,
        `n`.`userid` AS `userid`,
        `n`.`nota` AS `nota`
    from
        (`seguimiento_alumnos`.`vw_notas_alumno` `n`
        join `seguimiento_alumnos`.`vw_alumnos_activos` `a` ON ((`a`.`id` = `n`.`userid`)))
    order by `n`.`userid`;

    
               
create or replace VIEW `seguimiento_alumnos`.`vw_periodos_activos` AS
    select distinct
        `vw_periodo_etapa_materias`.`per_cat_id` AS `per_cat_id`,
        `vw_periodo_etapa_materias`.`periodo` AS `periodo`,
        `vw_periodo_etapa_materias`.`periodo_visible` AS `per_visible`,
        `vw_periodo_etapa_materias`.`per_order` AS `per_orden`
    from
        `seguimiento_alumnos`.`vw_periodo_etapa_materias`;
        
truncate `seguimiento_alumnos`.`cel_alerta_enviada` ;
ALTER TABLE `seguimiento_alumnos`.`cel_alerta_enviada` 
ADD UNIQUE  `cel_alerta_enviada_unique` (`ale_obj_tipo`,`ale_obj_id` ,`USR_ID` ,`ale_alerta` );