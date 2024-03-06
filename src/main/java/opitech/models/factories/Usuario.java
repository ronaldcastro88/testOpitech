package opitech.models.factories;

import net.serenitybdd.screenplay.Performable;
import opitech.tasks.IniciaSesion;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Usuario {
    private String usuario;
    private String clave;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Performable conDatosCorrectos() {
        setUsuario("793445");
        setClave("10df2f32286b7120Mi00LTU0NDM5Nw==30e0c83e6c29f1c3");
        return instrumented(IniciaSesion.class, this.usuario, this.clave);
    }
}
