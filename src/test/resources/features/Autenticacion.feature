# language: es
Característica: Autenticacion en el sitio de Evalartapp

  @AutenticacionExitosa
  Escenario: Autenticación exitosa
    Dado Ronald no se encuentra autenticado
    Cuando el usuario ingresa sus credenciales
    Entonces debe ver el mensaje Prueba de automatización