package banco.exceptions;

public class AutenticacionEvalartError extends AssertionError {

    public static final String INICIO_DE_SESION_FALLIDO = "El usuario no pudo iniciar sesi\u00F3n de forma exitosa";
    public static final String FORMULARIO_MAL_DILIGENCIADO = "El usuario no pudo diligenciar correctamente el formulario";
}
