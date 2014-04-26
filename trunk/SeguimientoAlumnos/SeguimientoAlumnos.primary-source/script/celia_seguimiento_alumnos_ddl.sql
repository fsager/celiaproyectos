drop database seguimiento_alumnos;

create database seguimiento_alumnos;
USE seguimiento_alumnos;

DELIMITER $$

DROP PROCEDURE IF EXISTS p_alumnos_activos_con_indicadores $$
CREATE PROCEDURE p_alumnos_activos_con_indicadores ()
BEGIN
	TRUNCATE TABLE tmp_indicadores_alumnos;

	INSERT INTO tmp_indicadores_alumnos
		select ia.* 
		  from vw_alumnos_activos ac,vw_indicadores_alumnos ia
		 where ac.id=ia.usr_id;

	SET @s = CONCAT('select alu.*,ind.* 
	  from vw_alumnos_activos alu,tmp_indicadores_alumnos ind ', ' where alu.id=ind.usr_id');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

	
	


END $$

CREATE TABLE tmp_indicadores_alumnos
(
ina_id	varchar(21) not null,
usr_id	bigint(20) not null,
codigo_indicador	varchar(1) not null,
desc_indicador	varchar(25) not null,
valor_indicador	bigint(20) not null
);

create view vw_alumnos_activos as
select * from celiacie_moodle2.mdl_user usr
where 1=1
and deleted <> 1
and suspended <> 1
and exists (select 1 from celiacie_moodle2.mdl_role_assignments ra where ra.userid=usr.id and ra.roleid=5)
;

create or replace view vw_indicadores_alumnos as
select CONCAT(usr.id,'1') ina_id,usr.id usr_id,'1' codigo_indicador,'Ingresó al Moodle' desc_indicador,1 valor_indicador from celiacie_moodle2.mdl_user usr
union select CONCAT(usr.id,'2') ina_id,usr.id,'2','Presentó trabajo práctico',1 from celiacie_moodle2.mdl_user usr;

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
		CAS_OBSERVACIONES_GRALES varchar(250),    	
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
		ICD_IGNORAR_HASTA TIMESTAMP not null,
		ICD_OBSERVACIONES varchar(250) not null,
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

ALTER TABLE CEL_GRUPO_USUARIO ADD CONSTRAINT CEL_GRU_USR_ID FOREIGN KEY (USR_ID)
    REFERENCES CEL_USUARIO (USR_ID);