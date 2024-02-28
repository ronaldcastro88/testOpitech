package banco.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static banco.userinterfaces.DashboardPage.SELECCION_LABEL_TEXT_AREA;

public class CapturarTextoRepetitivo implements Question<String> {

    public static CapturarTextoRepetitivo deLaPagina() {
        return new CapturarTextoRepetitivo();
    }

    @Override
    public String answeredBy(Actor actor) {
        return SELECCION_LABEL_TEXT_AREA.resolveFor(actor).getText();
    }
}
