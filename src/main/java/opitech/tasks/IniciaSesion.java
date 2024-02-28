package banco.tasks;


import banco.models.factories.Usuario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.type.Type;
import net.thucydides.core.annotations.Step;

import static banco.userinterfaces.HomePage.*;


public class IniciaSesion implements Task {

    private final String usuario;
    private final String clave;

    public IniciaSesion(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public static Usuario enEvalart() {
        return new Usuario();
    }

    @Override
    @Step("{0} inicia sesi\u00F3n con usuario #usuario y clave #clave")
    public <T extends Actor> void performAs(T actor) {
        //actor.remember(CORREO.getValor(),usuario);
        actor.attemptsTo(
                Type.theValue(usuario).into(CAMPO_USUARIO),
                Type.theValue(clave).into(CAMPO_CONTRASENIA),
                Click.on(BOTON_ENVIAR));
    }
}