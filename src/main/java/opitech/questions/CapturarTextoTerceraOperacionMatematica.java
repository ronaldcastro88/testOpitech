package opitech.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static opitech.userinterfaces.DashboardPage.SELECCION_TERCERA_OPERACION;

public class CapturarTextoTerceraOperacionMatematica implements Question<String> {

    public static CapturarTextoTerceraOperacionMatematica deLaPagina() {
        return new CapturarTextoTerceraOperacionMatematica();
    }

    @Override
    public String answeredBy(Actor actor) {
        return SELECCION_TERCERA_OPERACION.resolveFor(actor).getText();
    }
}
