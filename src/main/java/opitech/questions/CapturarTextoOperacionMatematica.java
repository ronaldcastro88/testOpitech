package banco.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static banco.userinterfaces.DashboardPage.SELECCION_PRIMERA_OPERACION_MATEMATICA;

public class CapturarTextoOperacionMatematica implements Question<String> {

    public static CapturarTextoOperacionMatematica deLaPagina() {
        return new CapturarTextoOperacionMatematica();
    }

    @Override
    public String answeredBy(Actor actor) {
        return SELECCION_PRIMERA_OPERACION_MATEMATICA.resolveFor(actor).getText();
    }
}
