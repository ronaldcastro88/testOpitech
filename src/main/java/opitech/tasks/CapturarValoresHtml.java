package banco.tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;

import static banco.userinterfaces.DashboardPage.resultadoRespuestaCheckRadioButton;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

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
        // Utilizar el operador de módulo para verificar si el resultado es cero
        return numeroA % numeroB == 0;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Esperar hasta que la lista de elementos sea visible
        actor.attemptsTo(WaitUntil.the(listaElementos, isVisible()));

        // Obtener todos los elementos de la lista
        List<WebElementFacade> elementos = listaElementos.resolveAllFor(actor);

        // Recorrer la lista de elementos
        for (WebElementFacade elemento : elementos) {
            // Capturar el valor del HTML de cada elemento
            String valorHtml = elemento.getAttribute("value");
            int valorEntero = Integer.parseInt(valorHtml);
            // Imprimir el valor del HTML
            System.out.println("Valor del HTML: " + valorEntero);
            if (esMultiplo(valorEntero, multiplo)) {
                System.out.println(valorEntero + " es múltiplo de " + multiplo);
                System.out.println("ESTO CONTIENE "+ listaElementos);
                actor.attemptsTo(Click.on(resultadoRespuestaCheckRadioButton(String.valueOf(valorEntero))));
            } else {
                System.out.println(valorEntero + " no es múltiplo de " + multiplo);
            }
        }
    }
}
