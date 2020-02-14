USE [DB_GESTION_USUARIOS_PANEL]
GO
/****** Object:  Table [dbo].[tipo_autenticacion]    Script Date: 02/14/2020 09:58:55 ******/
SET IDENTITY_INSERT [dbo].[tipo_autenticacion] ON
INSERT [dbo].[tipo_autenticacion] ([id_tipo_autenticacion], [descripcion], [nombre]) VALUES (1, N'INGRESO CON USUARIO Y CONTRASEÃ‘A', N'AUTENTICACION BASICA')
SET IDENTITY_INSERT [dbo].[tipo_autenticacion] OFF
/****** Object:  Table [dbo].[usuarios]    Script Date: 02/14/2020 09:58:55 ******/
SET IDENTITY_INSERT [dbo].[usuarios] ON
INSERT [dbo].[usuarios] ([id_usuario], [activo], [apellido], [correo], [dni], [matricula], [nombre], [password], [username]) VALUES (1, 1, N'REYNOSO', N'preynoso@exact.com.pe', N'36252018', N'PREYNOSO', N'PAMELA', N'$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO', N'preynoso')
INSERT [dbo].[usuarios] ([id_usuario], [activo], [apellido], [correo], [dni], [matricula], [nombre], [password], [username]) VALUES (2, 1, N'ADMINISTRADOR', N'administrador@exact.com.pe', N'36252017', N'ADMINSTRADOR', N'ADMINISTRADOR', N'$2a$04$95zHAMbf4jybXzbVSg0Xu.T1uEX.pgNpTNw1l539ntc4laRLvynYO', N'administrador')
SET IDENTITY_INSERT [dbo].[usuarios] OFF
/****** Object:  Table [dbo].[dominio]    Script Date: 02/14/2020 09:58:55 ******/
/****** Object:  Table [dbo].[permisos]    Script Date: 02/14/2020 09:58:55 ******/
SET IDENTITY_INSERT [dbo].[permisos] ON
INSERT [dbo].[permisos] ([id_permiso], [descripcion], [nombre]) VALUES (1, N'USUARIO DEMO', N'ROLE_DEMO')
INSERT [dbo].[permisos] ([id_permiso], [descripcion], [nombre]) VALUES (2, N'USUARIO MANTENIMIENTO', N'USER_MANTENIMIENTO')
SET IDENTITY_INSERT [dbo].[permisos] OFF
/****** Object:  Table [dbo].[perfiles]    Script Date: 02/14/2020 09:58:55 ******/
SET IDENTITY_INSERT [dbo].[perfiles] ON
INSERT [dbo].[perfiles] ([id_perfil], [activo], [descripcion], [f_creacion], [nombre]) VALUES (1, N'1', N'ES EL USUARIO QUE REALIZA PRESENTACIONES', CAST(0xBC400B00 AS Date), N'USUARIO_DEMO')
INSERT [dbo].[perfiles] ([id_perfil], [activo], [descripcion], [f_creacion], [nombre]) VALUES (2, N'1', N'ES EL USUARIO QUE REALIZA PRESENTACIONES', CAST(0xBC400B00 AS Date), N'USER_MANTENIMIENTO')
SET IDENTITY_INSERT [dbo].[perfiles] OFF
/****** Object:  Table [dbo].[perfiles_permisos]    Script Date: 02/14/2020 09:58:56 ******/
INSERT [dbo].[perfiles_permisos] ([perfil_id_perfil], [permisos_id_permiso]) VALUES (1, 1)
INSERT [dbo].[perfiles_permisos] ([perfil_id_perfil], [permisos_id_permiso]) VALUES (2, 2)
/****** Object:  Table [dbo].[grupo_red]    Script Date: 02/14/2020 09:58:56 ******/
/****** Object:  Table [dbo].[usuario_perfil]    Script Date: 02/14/2020 09:58:56 ******/
SET IDENTITY_INSERT [dbo].[usuario_perfil] ON
INSERT [dbo].[usuario_perfil] ([id_usuario_perfil], [activo], [f_asignada], [f_baja], [perfil_id_perfil], [usuario_id_usuario]) VALUES (1, N'1', CAST(0xBC400B00 AS Date), CAST(0x5B950A00 AS Date), 1, 1)
INSERT [dbo].[usuario_perfil] ([id_usuario_perfil], [activo], [f_asignada], [f_baja], [perfil_id_perfil], [usuario_id_usuario]) VALUES (2, N'1', CAST(0xBC400B00 AS Date), CAST(0x5B950A00 AS Date), 2, 2)
SET IDENTITY_INSERT [dbo].[usuario_perfil] OFF
/****** Object:  Table [dbo].[sesion]    Script Date: 02/14/2020 09:58:56 ******/
