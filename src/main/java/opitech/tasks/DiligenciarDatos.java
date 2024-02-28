package banco.tasks;

import banco.questions.CapturarTextoOperacionMatematica;
import banco.questions.CapturarTextoRepetitivo;
import banco.questions.CapturarTextoSegundaOperacionMatematica;
import banco.questions.CapturarTextoTerceraOperacionMatematica;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.type.Type;
import net.thucydides.core.annotations.Step;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static banco.userinterfaces.DashboardPage.*;

public class DiligenciarDatos implements Task {
    public static DiligenciarDatos delFormulario() {
        return Tasks.instrumented(DiligenciarDatos.class);
    }

    public static String eliminarCaracteres(String input, String... caracteresAEliminar) {
        // Iterar sobre los caracteres a eliminar y reemplazarlos con una cadena vacía
        for (String caracter : caracteresAEliminar) {
            input = input.replace(caracter, "");
        }
        return input;
    }

    public static double evaluarExpresion(String textoOperacionMatematica) throws ScriptException {
        String expresionModificada = eliminarCaracteres(textoOperacionMatematica, "=", "?");
        System.out.println("Expresión original: " + textoOperacionMatematica);
        System.out.println("Expresión modificada: " + expresionModificada);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        // Evaluar la expresión y devolver el resultado como un Double
        return ((Number) engine.eval(expresionModificada)).doubleValue();
    }

    public static String extraerNumeros(String input) {
        // Utilizar una expresión regular para encontrar solo los caracteres numéricos
        return input.replaceAll("[^0-9]", "");
    }

    public static String extraerLetraEspecial(String input) {
        // Utilizar una expresión regular para encontrar solo los caracteres numéricos
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return ""; // Retornar una cadena vacía si no se encuentra ningún texto entre comillas
        }
    }

    public static int convertirAEntero(String input) {
        // Utilizar Integer.parseInt() para convertir la cadena a un valor entero
        return Integer.parseInt(input);
    }

    @Override
    @Step("{1} Diligencia los datos del formulario ") //inicia sesión con usuario #usuario y clave #clave
    public <T extends Actor> void performAs(T actor) {
        String textoOperacionMatematica;
        long entero;
        for (int i=0;i<10;i++){
            int f;
            for (f=0;f<2;f++) {
                double resultado;
                try {
                    if (f == 0) {
                        textoOperacionMatematica = actor.asksFor(CapturarTextoOperacionMatematica.deLaPagina());
                        System.out.println("Texto de la operación matemática: " + textoOperacionMatematica);
                        resultado = evaluarExpresion(textoOperacionMatematica);
                        entero = Math.round(resultado);
                        System.out.println("Resultado 1: " + entero);
                        actor.attemptsTo(
                                Click.on(SELECCION_RESPUESTA_OPERACION_MATEMATICA),
                                Click.on(resultadoRespuestaOperacionMatematica(String.valueOf(entero))));
                    } else if (f == 1) {
                        textoOperacionMatematica = actor.asksFor(CapturarTextoSegundaOperacionMatematica.deLaPagina());
                        System.out.println("Texto de la operación matemática 2: " + textoOperacionMatematica);
                        resultado = evaluarExpresion(textoOperacionMatematica);
                        entero = Math.round(resultado);
                        System.out.println("Resultado 2: " + entero);
                        actor.attemptsTo(
                                Click.on(resultadoRespuestaCheckRadioButton(String.valueOf(entero))));
                    }
                }
                catch (ScriptException e) {
                    System.out.println("Error al evaluar la expresión: " + e.getMessage());
                }
            }
            String numerosExtraidos = actor.asksFor(CapturarTextoTerceraOperacionMatematica.deLaPagina());
            System.out.println("Expresión original: " + numerosExtraidos);
            numerosExtraidos = extraerNumeros(numerosExtraidos);
            System.out.println("Numeros extraidos: " + numerosExtraidos);
            entero = convertirAEntero(numerosExtraidos);
            System.out.println("Valor entero: " + entero);
            actor.attemptsTo(
                 CapturarValoresHtml.deLaLista(SELECCION_LISTADO_CHECKBOX, entero));
            numerosExtraidos = actor.asksFor(CapturarTextoRepetitivo.deLaPagina());
            String extraerCaracterRepetitivo = extraerLetraEspecial(numerosExtraidos);
            System.out.println("EL CARACTER REPETITIVO ES: " + extraerCaracterRepetitivo);
            numerosExtraidos = extraerNumeros(numerosExtraidos);
            System.out.println("Numeros extraidos DEL TEXTO REPETITIVO ES : " + numerosExtraidos);
            entero = convertirAEntero(numerosExtraidos);
            System.out.println("EL VALOR ENTERO CONVERTIDO ES: " + entero);
                    actor.attemptsTo(Click.on(SELECCION_TEXT_AREA));
            StringBuilder cadenaRepetida = new StringBuilder();
            for (int g=0;g<entero;g++){
                cadenaRepetida.append(extraerCaracterRepetitivo);
            }
            actor.attemptsTo(Type.theValue(cadenaRepetida.toString()).into(SELECCION_TEXT_AREA),
                    Click.on(BOTON_ENVIAR));
                }
            }
}

