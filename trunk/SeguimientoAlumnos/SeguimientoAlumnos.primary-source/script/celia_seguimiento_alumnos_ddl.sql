create database seguimiento_alumnos;

create view vw_alumnos_activos as
select * from mdl_user usr
where 1=1
and deleted <> 1
and suspended <> 1
and exists (select 1 from mdl_role_assignments ra where ra.userid=usr.id and ra.roleid=5)
;

create or replace view vw_indicadores_alumnos as
select CONCAT(usr.id,'1') id,usr.id usr_id,'1' codigo_indicador,'Ingresó al Moodle' desc_indicador,1 valor_indicador from mdl_user usr
union select CONCAT(usr.id,'2'),usr.id,'2','Presentó trabajo práctico',1 from mdl_user usr;

  CREATE TABLE CEL_USUARIO 
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


  CREATE TABLE CEL_GRUPO 
   (	GRP_ID bigint default NULL auto_increment primary key,
    	GRP_NOMBRE VARCHAR(50), 
    	GRP_DESCRIPCION VARCHAR(4000), 
    	AUD_FECHA_INS TIMESTAMP, 
    	AUD_FECHA_UPD TIMESTAMP, 
    	AUD_USR_INS VARCHAR(250), 
    	AUD_USR_UPD VARCHAR(250)
   );


  CREATE TABLE CEL_GRUPO_USUARIO 
   (	GRU_ID bigint default NULL auto_increment primary key,
    	GRP_ID bigint, 
    	USR_ID bigint
   );

ALTER TABLE CEL_GRUPO_USUARIO ADD CONSTRAINT CEL_GRU_GRP_ID FOREIGN KEY (GRP_ID)
    REFERENCES CEL_GRUPO (GRP_ID);


ALTER TABLE CEL_GRUPO_USUARIO ADD CONSTRAINT CEL_GRU_USR_ID FOREIGN KEY (USR_ID)
    REFERENCES CEL_USUARIO (USR_ID);