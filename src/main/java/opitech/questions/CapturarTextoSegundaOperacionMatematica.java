package banco.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static banco.userinterfaces.DashboardPage.SELECCION_SEGUNDA_OPERACION_MATEMATICA;

public class CapturarTextoSegundaOperacionMatematica implements Question<String> {

    public static CapturarTextoSegundaOperacionMatematica deLaPagina() {
        return new CapturarTextoSegundaOperacionMatematica();
    }

    @Override
    public String answeredBy(Actor actor) {
        return SELECCION_SEGUNDA_OPERACION_MATEMATICA.resolveFor(actor).getText();
    }
}
