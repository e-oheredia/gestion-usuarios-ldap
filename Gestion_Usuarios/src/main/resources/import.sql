insert into permisos (nombre, descripcion) values ('ROLE_CREADOR_DE_PERFILES','CREAR PERFILES DE USUARIO')
insert into permisos (nombre, descripcion) values ('ROLE_MODIFICADOR_DE_PERFILES','MODIFICAR PERFILES DE USUARIO')
insert into permisos (nombre, descripcion) values ('ROLE_MODIFICADOR_DE_ESTADO_DE_PERFILES','MODIFICA EL ESTADO DE LOS PERFILES')
insert into permisos (nombre, descripcion) values ('ROLE_ASIGNADOR_DE_PERFILES','ASIGNAR PERFILES A UN USUARIO')
insert into permisos (nombre, descripcion) values ('ROLE_CREADOR_DE_PERMISOS','CREAR PERMISOS DE UN USUARIO')
insert into permisos (nombre, descripcion) values ('ROLE_MODIFICADOR_DE_PERMISOS','MODIFICAR PERMISOS DE USUARIO')
insert into permisos (nombre, descripcion) values ('ROLE_ASIGNADOR_DE_PERMISOS','ASIGNAR PERMISOS A UN PERFIL')
insert into permisos (nombre, descripcion) values ('ROLE_QUITAR_PERMISOS','QUITAR PERMISOS A UN PERFIL')
insert into permisos (nombre, descripcion) values ('ROLE_CREAR_USUARIOS','CREA USUARIOS CON UN PERFIL')
insert into permisos (nombre, descripcion) values ('ROLE_MODIFICADOR_DE_ESTADO_DE_USUARIOS','MODIFICA EL ESTADO')
insert into permisos (nombre, descripcion) values ('ROLE_MODIFICADOR_USUARIOS','MODIFICA LOS DATOS DEL USUARIO')
insert into permisos (nombre, descripcion) values ('ROLE_USUARIO_REGULAR','USUARIO REGULAR')
insert into permisos (nombre, descripcion) values ('ROLE_UTD','GESTIONA DOCUMENTO')
insert into permisos (nombre, descripcion) values ('ROLE_SUPERVISOR','SUPERVISA LAS OPERACIONES DE UTD')
insert into permisos (nombre, descripcion) values ('ROLE_AUTORIZADOR','AUTORIZA ENVIO DE DOCUMENTOS')
insert into permisos (nombre, descripcion) values ('ROLE_USUARIO_BLOQUE','USUARIO BLOQUE')
insert into permisos (nombre, descripcion) values ('ROLE_PROVEEDOR','PROVEEDOR')


--debe estar por defecto activo a '1'11
insert into perfiles values (1,'ESTA A CARGO DE UN AREA ASIGNADA',GETDATE(),'JEFE')
insert into perfiles values (1,'ES EL RESPONSABLE DEL CONTROL DE ACTIVIDADES DIARIAS Y MANEJO DE LAS OPERACIONES',GETDATE(),'SUPERVISOR')
insert into perfiles values (1,'ES EL ENCARGADO DE REALIZAR LAS ACTIVIDADES DIARIAS DE LA EMPRESA',GETDATE(),'OPERATIVO')
insert into perfiles values (1,'ES EL ENCARGADO DE GUARDAR TARJETAS DE CREDITO',GETDATE(),'ASISTENTE')
insert into perfiles values (1,'CREA DOCUMENTOS DE ENVIO REGULAR',GETDATE(),'USUARIO_REGULAR')
insert into perfiles values (1,'CONTROLA DOCUMENTOS',GETDATE(),'GESTION DOCUMENTAL')
insert into perfiles values (1,'CREA DOCUMENTOS DE ENVIO BLOQUE',GETDATE(),'USUARIO_BLOQUE')
insert into perfiles values (1,'PROCESAR LAS GUIAS ENVIADAS',GETDATE(),'USUARIO_PROVEEDOR')

--debe estar por defecto activo a '1'
--
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'ORLANDO','HEREDIA',12345698,'OHEREDIA','oheredia@exact.com.pe','oheredia','$2a$04$GKk2hQxmmUAyVqGQM3I7R.6Ua9av7/7XNz4kDE8ODzmVOFDnTxuEK')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'CHRISTIAN','CAMPOS',75435710,'CCAMPOS','ccampos@exact.com.pe','ccampos','$2a$04$NvvJE0k5kV2g3gIMxzLa0ungVBERTwH3psJ57SbFK51qDBbbAfM7q')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'RONALD','SANTOS',46059112,'RSANTOS','rsantos@exact.com.pe','rsantos','$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'CESAR','BALTAZAR',58963258,'CBALTAZAR','cbaltazar@exact.com.pe','cbaltazar','$2a$04$NvvJE0k5kV2g3gIMxzLa0ungVBERTwH3psJ57SbFK51qDBbbAfM7q')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'KATHERINE','VEGA',79461382,'KVEGA','kvega@exact.com.pe','kvega','$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'KATHELEEN','MACEDO',23434322,'KMACEDO','kmacedo@exact.com.pe','kmacedo','$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'KRYSTEL','ARRUE',53684524,'KARRUE','karrue@exact.com.pe','karrue','$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'MEDALIT','CEVALLOS',95785632,'MCEVALLOS','mcevallos@exact.com.pe','mcevallos','$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'ERNST','ROJAS',79461389,'EROJAS','ccampos@exact.com.pe','erojas','$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'GIOVANNA','VEGA',12354677,'GVEGA','rsantos@exact.com.pe','gvega','$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'AMANDA','GOMEZ',12954627,'AGOMEZ','agomez@exact.com.pe','agomez','$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'KELLY','SALAZAR',82954621,'KSALAZAR','ksalazar@exact.com.pe','ksalazar','$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO')
insert into usuarios (activo, nombre, apellido, dni, matricula, correo, username, password) values (1,'PROVEEDOR','PROVEEDOR1',82984621,'PROVEEDOR','proveedor@exact.com.pe','proveedor','$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO')




insert into perfiles_permisos values (1,1)
insert into perfiles_permisos values (1,9)
insert into perfiles_permisos values (1,10)
--insert into perfiles_permisos values (2,2)
--insert into perfiles_permisos values (2,3)
--insert into perfiles_permisos values (2,4)
--insert into perfiles_permisos values (2,5)
--insert into perfiles_permisos values (2,6)
--insert into perfiles_permisos values (2,7)
--insert into perfiles_permisos values (2,8)
--insert into perfiles_permisos values (3,5)
--insert into perfiles_permisos values (3,6)
--insert into perfiles_permisos values (3,7)
insert into perfiles_permisos values (4,6)
insert into perfiles_permisos values (3,13)
insert into perfiles_permisos values (5,12)
insert into perfiles_permisos values (2,14)
insert into perfiles_permisos values (6,15)
insert into perfiles_permisos values (7,16)
insert into perfiles_permisos values (8,17)

insert into usuario_perfil values (1,GETDATE(),'',3,1)
insert into usuario_perfil values (1,GETDATE(),'',3,2)
insert into usuario_perfil values (1,GETDATE(),'',3,3)
insert into usuario_perfil values (1,GETDATE(),'',3,4)
insert into usuario_perfil values (1,GETDATE(),'',5,5)
insert into usuario_perfil values (1,GETDATE(),'',5,6)
insert into usuario_perfil values (1,GETDATE(),'',5,7)
insert into usuario_perfil values (1,GETDATE(),'',5,8)
insert into usuario_perfil values (1,GETDATE(),'',2,9)
insert into usuario_perfil values (1,GETDATE(),'',6,10)
insert into usuario_perfil values (1,GETDATE(),'',7,11)
insert into usuario_perfil values (1,GETDATE(),'',7,12)
insert into usuario_perfil values (1,GETDATE(),'',8,13)




INSERT [dbo].[dominio] ([nombre], [url], [base_dn],[manager_username],[manager_password], [filter_pattern], [activo]) VALUES ('EXACT','ldap://localhost:8389','dc=springframework,dc=org','','','uid=%s',1)

INSERT [dbo].[tipo_autenticacion] ([nombre], [descripcion]) VALUES ('AUTENTICACION BASICA','INGRESO CON USUARIO Y CONTRASEÑA')
INSERT [dbo].[tipo_autenticacion] ([nombre], [descripcion]) VALUES ('ACTIVE DIRECTORY', 'INGRESO CON DOMINIO')

INSERT [dbo].[grupo_red] ([grupo_red_id],[nombre],[perfil_id]) VALUES (1,'OPERATIVO UTD',3)
INSERT [dbo].[grupo_red] ([grupo_red_id],[nombre],[perfil_id]) VALUES (2,'USUARIO REGULAR',5)
INSERT [dbo].[grupo_red] ([grupo_red_id],[nombre],[perfil_id]) VALUES (3,'USUARIO BLOQUE',7)
INSERT [dbo].[grupo_red] ([grupo_red_id],[nombre],[perfil_id]) VALUES (4,'GESTION DOCUMENTAL',6)
INSERT [dbo].[grupo_red] ([grupo_red_id],[nombre],[perfil_id]) VALUES (5,'SUPERVISOR UTD',2)



