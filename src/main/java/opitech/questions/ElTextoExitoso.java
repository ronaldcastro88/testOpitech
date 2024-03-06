package opitech.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static opitech.userinterfaces.DashboardPage.TEXTO_MENSAJE_FINAL;

public class ElTextoExitoso implements Question<String> {

    public static ElTextoExitoso alFinal() {
        return new ElTextoExitoso();
    }

    @Override
    @Step("Verifica el texto exitoso al final")
    public String answeredBy(Actor actor) {
        actor.attemptsTo(WaitUntil.the(TEXTO_MENSAJE_FINAL ,isPresent()).forNoMoreThan(5).seconds());
        return Text.of(TEXTO_MENSAJE_FINAL).viewedBy(actor).asString();
    }
}
