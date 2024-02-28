package banco.questions;

import co.com.devco.automation.mobile.actions.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static banco.userinterfaces.DashboardPage.TEXTO_MENSAJE_FINAL;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;

public class ElTextoExitoso implements Question<String> {

    public static ElTextoExitoso alFinal() {
        return new ElTextoExitoso();
    }

    @Override
    @Step("Verifica el texto exitoso al final")
    public String answeredBy(Actor actor) {
        actor.attemptsTo(WaitUntil.the(TEXTO_MENSAJE_FINAL ,isPresent()).forNoMoreThan(5).seconds(),
                WaitFor.seconds(3));
        return Text.of(TEXTO_MENSAJE_FINAL).viewedBy(actor).asString();
    }
}
