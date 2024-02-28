package banco.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IrA implements Interaction {
    private final String pagina;

    public IrA(String pagina) {
        this.pagina = pagina;
    }

    public static IrA laPagina(String url) {
        return instrumented(IrA.class, url);
    }
    @Override
    @Step("{0} Abre la p\u00E1gina web #pagina")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(pagina));
    }
}
