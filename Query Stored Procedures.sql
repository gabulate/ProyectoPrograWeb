--SP para crear un usuario con contraseña encriptada
CREATE PROCEDURE CrearUsuario
@Usuario nvarchar(50),
@IdRol int,
@Contrasenna nvarchar(50)
AS
--Verifica si ya existe un usuario con ese nombre. Si ya existe retorna 0 y si no se crea y retorna 1
IF (EXISTS(SELECT * FROM Usuario WHERE Nombre = @Usuario))
BEGIN
SELECT 1 as 'Aprobado'
END
ELSE
BEGIN
INSERT INTO Usuario VALUES(@Usuario, @IdRol, ENCRYPTBYPASSPHRASE('JAWY', @Contrasenna))
SELECT 0 as 'Aprobado'
END


EXECUTE CrearUsuario @Usuario = 'Gabo', @IdRol = 1, @Contrasenna = 'bizcocho'
--###############################################################################################################################################

--SP para verificar si el usuario y la contraseña con correctos
--Devuelve 1 si las credenciales son correctas y 0 si no lo son
CREATE PROCEDURE VerificarLogin
@Usuario nvarchar(50),
@Contrasenna nvarchar(50)
AS
--Selecciona el usuario ingresado, desencripta la contraseña guardada y la compara con la dada por el usuario
IF @Contrasenna = (SELECT CAST(DECRYPTBYPASSPHRASE('JAWY', Contrasenna) as nvarchar(100)) FROM Usuario WHERE Nombre = 'Gabo')
SELECT 1 as 'Aprobado'
ELSE
SELECT 0 as 'Aprobado'

EXECUTE VerificarLogin @Usuario = 'Gabo', @Contrasenna = 'bizcocho'

SELECT CAST(DECRYPTBYPASSPHRASE('JAWY', Contrasenna) as nvarchar(100)) FROM Usuario
--###############################################################################################################################################

CREATE PROCEDURE CambiarContrasenna
@Usuario nvarchar(50),
@ContrasennaVieja nvarchar(50),
@ContrasennaNueva nvarchar(50)
AS
--Verifica que la contraseña sea correcta
IF @ContrasennaVieja = (SELECT CAST(DECRYPTBYPASSPHRASE('JAWY', Contrasenna) as nvarchar(100)) FROM Usuario WHERE Nombre = @Usuario)
BEGIN
--Actualiza la contraseña y la encripta
UPDATE Usuario SET Contrasenna = ENCRYPTBYPASSPHRASE('JAWY', @ContrasennaNueva) WHERE Nombre = @Usuario
SELECT 1 as 'Aprobado'
END
ELSE
BEGIN
SELECT 0 as 'Aprobado'
END

EXECUTE CambiarContrasenna @Usuario = 'Gabo', @ContrasennaVieja = 'pain', @ContrasennaNueva = 'bizcocho'
--###############################################################################################################################################


insert into Rol values (1, 'Administrador')
insert into Rol values (2, 'Planillero')
insert into Rol values (3, 'Recursos Humanos')

