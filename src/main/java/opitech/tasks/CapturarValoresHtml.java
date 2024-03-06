package opitech.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static opitech.userinterfaces.DashboardPage.resultadoRespuestaCheckRadioButton;

public class CapturarValoresHtml implements Task {

    private final Target listaElementos;
    private final long multiplo;

    public CapturarValoresHtml(Target listaElementos, long multiplo) {
        this.listaElementos = listaElementos;
        this.multiplo = multiplo;
    }

    public static Performable deLaLista(Target listaElementos, long multiplo) {
        return instrumented(CapturarValoresHtml.class, listaElementos, multiplo);
    }

    public static boolean esMultiplo(int numeroA, long numeroB) {
        return numeroA % numeroB == 0;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(listaElementos, isVisible()));
        List<WebElementFacade> elementos = listaElementos.resolveAllFor(actor);
        for (WebElementFacade elemento : elementos) {
            String valorHtml = elemento.getAttribute("value");
            int valorEntero = Integer.parseInt(valorHtml);
            if (esMultiplo(valorEntero, multiplo)) {
                actor.attemptsTo(Click.on(resultadoRespuestaCheckRadioButton(String.valueOf(valorEntero))));
            } else {
                System.out.println(valorEntero + " no es múltiplo de " + multiplo);
            }
        }
    }
}
