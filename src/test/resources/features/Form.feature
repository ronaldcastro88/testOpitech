# language: es
Característica: Diligenciamiento del formulario

  @AutenticacionExitosa
  Escenario: Diligenciamiento exitosamente del formulario
    Dado el usuario se encuentra autenticado en el sitio de Evalartapp
    Cuando diligencia el formulario durante diez ciclos
    Entonces debe mostrar el hash con el mensaje exitoso <General>c2fe0e21ce445033Mi00LTExMDc2Nw==df63afbf4da3d4b1