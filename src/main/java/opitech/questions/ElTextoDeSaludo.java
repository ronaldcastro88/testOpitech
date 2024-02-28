package banco.questions;

import co.com.devco.automation.mobile.actions.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static banco.userinterfaces.DashboardPage.TEXTO_SALUDO_DASHBOARD;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;

public class ElTextoDeSaludo implements Question<String> {

    public static ElTextoDeSaludo enDashboard() {
        return new ElTextoDeSaludo();
    }

    @Override
    @Step("Verifica el texto de saludo en el dashboard principal")
    public String answeredBy(Actor actor) {
        actor.attemptsTo(WaitUntil.the(TEXTO_SALUDO_DASHBOARD ,isPresent()).forNoMoreThan(5).seconds(),
                WaitFor.seconds(3));
        return Text.of(TEXTO_SALUDO_DASHBOARD).viewedBy(actor).asString();
    }
}
