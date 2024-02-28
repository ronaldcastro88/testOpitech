package banco.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DashboardPage {

    public static final Target TEXTO_SALUDO_DASHBOARD = Target.the("Mensaje de saludo en el dashboard").located(By.xpath("//h1[@class='text-center text-3xl p-3 m-3']"));
    public static final Target SELECCION_PRIMERA_OPERACION_MATEMATICA = Target.the("Selección de primera operación matemática").located(By.xpath("//div[@class=' flex flex-col items-center bg-white rounded-md shadow-md p-5 space-y-4 my-5 w-2/5 ']//child::p[2]"));

    public static final Target SELECCION_RESPUESTA_OPERACION_MATEMATICA = Target.the("Selección de opción de respuesta de la operación matemática").located(By.xpath("//select[@name='select']"));
    //public static final Target RESULTADO_RESPUESTA_OPERACION_MATEMATICA = Target.the("Resultado de opción de respuesta de la operación matemática").located(By.xpath("//option[@value='%s']"));
    public static Target resultadoRespuestaOperacionMatematica(String valor) {
        String xpath = String.format("//option[@value='%s']", valor);
        return Target.the("Resultado de opción de respuesta de la operación matemática").located(By.xpath(xpath));
    }
    public static Target resultadoRespuestaCheckRadioButton(String valor) {
        String xpath = String.format("//input[@value='%s']", valor);
        return Target.the("Resultado de opción de respuesta con radio button").located(By.xpath(xpath));
    }
    //public static final Target SELECCION_RESPUESTA_RADIO_BUTTON = Target.the("Selección de opción de respuesta tipo radiobutton").located(By.xpath("//input[@value='-15966']"));

    public static final Target SELECCION_SEGUNDA_OPERACION_MATEMATICA = Target.the("Selección de segunda operación matemática").located(By.xpath("//div[@class='bg-white rounded-md shadow-md p-5  flex flex-col items-center my-5 w-2/5 ']//child::p[2]"));
    public static final Target SELECCION_TERCERA_OPERACION = Target.the("Selección de tercera operación").located(By.xpath("//div[@class='bg-white rounded-md shadow-md p-5 flex flex-col items-center my-5 w-2/5 ']//child::p[1]"));

    public static final Target SELECCION_LISTADO_CHECKBOX = Target.the("Selección de los checkbox para comparar").located(By.xpath("//input[@name='checkbox']"));

    public static final Target SELECCION_LABEL_TEXT_AREA = Target.the("Selección de label del text_area text area").located(By.xpath("//div[@class='flex flex-col p-4 w-2/5 bg-white rounded-md shadow-md my-5 justify-around']//child::p[1]"));
    public static final Target SELECCION_TEXT_AREA = Target.the("Selección de text area para ingresar valores").located(By.xpath("//textarea[@name='text']"));
    public static final Target BOTON_ENVIAR = Target.the("Botón para enviar la data ingresada").located(By.xpath("//button[@type='submit']"));

    public static final Target TEXTO_MENSAJE_FINAL = Target.the("Mensaje exitoso al final").located(By.xpath("//h1[@class='text-center text-3xl p-3 m-3']"));

    private DashboardPage() {
        throw new IllegalStateException("Clase de interfaz de usuario");
    }
}
