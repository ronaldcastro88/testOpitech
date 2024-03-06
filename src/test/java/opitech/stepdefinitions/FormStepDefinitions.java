package opitech.stepdefinitions;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import opitech.exceptions.AutenticacionEvalartError;
import opitech.interactions.IrA;
import opitech.questions.ElTextoExitoso;
import opitech.tasks.DiligenciarDatos;
import opitech.tasks.IniciaSesion;
import opitech.utils.PaginaWeb;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class FormStepDefinitions {
    @Dado("^(.*) se encuentra autenticado en el sitio de Evalartapp$")
    public void elUsuarioSeEncuentraAutenticadoEnElSitioDeEvalartapp(String actor) {
        theActorCalled(actor).wasAbleTo(IrA
                .laPagina(PaginaWeb.EVALART.getUrl()),
                (IniciaSesion.enEvalart().conDatosCorrectos()));
    }

    @Cuando("^diligencia el formulario durante diez ciclos$")
    public void diligenciaElFormularioDuranteDiezCiclos() {
        theActorInTheSpotlight().attemptsTo(DiligenciarDatos.delFormulario());

    }

    @Entonces("^debe mostrar el hash con el mensaje exitoso (.*)$")
    public void debeMostrarElHashConElMensajeExitoso(String mensajeExitoso) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(ElTextoExitoso.alFinal(), equalTo(mensajeExitoso))
                .orComplainWith(AutenticacionEvalartError.class, AutenticacionEvalartError.FORMULARIO_MAL_DILIGENCIADO));
    }
}
