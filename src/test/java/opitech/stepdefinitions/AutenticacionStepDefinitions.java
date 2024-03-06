package opitech.stepdefinitions;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import opitech.exceptions.AutenticacionEvalartError;
import opitech.interactions.IrA;
import opitech.questions.ElTextoDeSaludo;
import opitech.tasks.IniciaSesion;
import opitech.utils.PaginaWeb;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class AutenticacionStepDefinitions {

    @Dado("^(.*) no se encuentra autenticado$")
    public void ronaldNoSeEncuentraAutenticado(String actor) {
        theActorCalled(actor).wasAbleTo(IrA.laPagina(PaginaWeb.EVALART.getUrl()));
    }
    @Cuando("^el usuario ingresa sus credenciales$")
    public void elUsuarioIngresaSusCredenciales() {
        theActorInTheSpotlight().attemptsTo(IniciaSesion.enEvalart().conDatosCorrectos());
    }

    @Entonces("^debe ver el mensaje (.*)$")
    public void debeVerElMensaje(String mensajeExitoso) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(ElTextoDeSaludo.enDashboard(), equalTo(mensajeExitoso))
                .orComplainWith(AutenticacionEvalartError.class, AutenticacionEvalartError.INICIO_DE_SESION_FALLIDO));
    }
}