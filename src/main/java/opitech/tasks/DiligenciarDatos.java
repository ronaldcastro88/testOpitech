package opitech.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.type.Type;
import net.thucydides.core.annotations.Step;
import opitech.questions.CapturarTextoOperacionMatematica;
import opitech.questions.CapturarTextoRepetitivo;
import opitech.questions.CapturarTextoSegundaOperacionMatematica;
import opitech.questions.CapturarTextoTerceraOperacionMatematica;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static opitech.userinterfaces.DashboardPage.*;


public class DiligenciarDatos implements Task {
    public static DiligenciarDatos delFormulario() {
        return Tasks.instrumented(DiligenciarDatos.class);
    }

    public static String eliminarCaracteres(String input, String... caracteresAEliminar) {
        for (String caracter : caracteresAEliminar) {
            input = input.replace(caracter, "");
        }
        return input;
    }

    public static double evaluarExpresion(String textoOperacionMatematica) throws ScriptException {
        String expresionModificada = eliminarCaracteres(textoOperacionMatematica, "=", "?");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        return ((Number) engine.eval(expresionModificada)).doubleValue();
    }

    public static String extraerNumeros(String input) {
        String regex = "(?<!\\d|\\/)\\d{1,2}\\/\\d{1,2}\\/\\d{4}(?!\\d)";
        String stringWithoutDates = input.replaceAll(regex, "");
        return stringWithoutDates.replaceAll("[^0-9]", "");
    }

    public static LocalDate recorrerDias(LocalDate fechaInicial, int dias) {
        return fechaInicial.plusDays(dias);
    }

    public static String extraerLetraEspecial(String input) {
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    public static boolean contienePalabra(String mensaje) {
        return mensaje.toLowerCase().contains("antes");
    }

    public static int convertirAEntero(String input) {
        return Integer.parseInt(input);
    }

    @Override
    @Step("Diligencia los datos del formulario ")
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
                        resultado = evaluarExpresion(textoOperacionMatematica);
                        entero = Math.round(resultado);
                        actor.attemptsTo(
                                Click.on(SELECCION_RESPUESTA_OPERACION_MATEMATICA),
                                Click.on(resultadoRespuestaOperacionMatematica(String.valueOf(entero))));
                    } else if (f == 1) {
                        textoOperacionMatematica = actor.asksFor(CapturarTextoSegundaOperacionMatematica.deLaPagina());
                        ZonedDateTime fechaActualGmt = ZonedDateTime.from(ZonedDateTime.now(ZoneOffset.UTC));
                        //LocalDate fechaActual = LocalDate.now();
                        //ZonedDateTime fechaActualGmt = ZonedDateTime.of(fechaActual.atStartOfDay(), ZoneId.of("GMT"));
                        System.out.println("LA FECHA ACTUAL GMT " + fechaActualGmt);
                        if (contienePalabra(textoOperacionMatematica)) {
                            textoOperacionMatematica = extraerNumeros(textoOperacionMatematica);
                            resultado = evaluarExpresion(textoOperacionMatematica);
                            entero = Math.round(resultado);
                            entero = -entero;
                        } else {
                            textoOperacionMatematica = extraerNumeros(textoOperacionMatematica);
                            resultado = evaluarExpresion(textoOperacionMatematica);
                            entero = Math.round(resultado);
                        }
                        LocalDate nuevaFecha = recorrerDias(LocalDate.from(fechaActualGmt), (int) entero);
                        DateTimeFormatter nuevoFormato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        String fechaFormateada = nuevaFecha.format(nuevoFormato);
                        fechaFormateada =  extraerNumeros(fechaFormateada);
                        actor.attemptsTo(
                                Type.theValue(fechaFormateada).into(SELECCION_FECHA));
                    }
                }
                catch (ScriptException e) {
                    System.out.println("Error al evaluar la expresión: " + e.getMessage());
                }
            }
            String numerosExtraidos = actor.asksFor(CapturarTextoTerceraOperacionMatematica.deLaPagina());
            numerosExtraidos = extraerNumeros(numerosExtraidos);
            entero = convertirAEntero(numerosExtraidos);
            actor.attemptsTo(
                 CapturarValoresHtml.deLaLista(SELECCION_LISTADO_CHECKBOX, entero));
            numerosExtraidos = actor.asksFor(CapturarTextoRepetitivo.deLaPagina());
            String extraerCaracterRepetitivo = extraerLetraEspecial(numerosExtraidos);
            numerosExtraidos = extraerNumeros(numerosExtraidos);
            entero = convertirAEntero(numerosExtraidos);
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

