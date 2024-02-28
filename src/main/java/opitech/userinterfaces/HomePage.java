package banco.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {

    public static final Target CAMPO_USUARIO = Target.the("Campo de correo electrónico para iniciar sesión").located(By.xpath("//input[@name='username']"));
    public static final Target CAMPO_CONTRASENIA = Target.the("Campo de contraseña para iniciar sesión").located(By.xpath("//input[@name='password']"));
    public static final Target BOTON_ENVIAR = Target.the("Botón para iniciar sesión").located(By.xpath("//button[@type='submit']"));
    private HomePage() {
        throw new IllegalStateException("Clase de interfaz de usuario");
    }
}