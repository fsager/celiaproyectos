create or replace view vw_listado_notas_tp_alumno as
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
            and `tpa`.`userid` = `u`.`id`;

create or replace view vw_listado_notas_examen_alumno as
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
            and `exama`.`userid` = `u`.`id`