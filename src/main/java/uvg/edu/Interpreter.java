package uvg.edu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Interpreter {

    /**
     * El método principal que sirve como punto de entrada para el intérprete.
     *
     * @param args argumentos de la línea de comandos
     *
     * Autores:
     *      Javier Alvarado - 24546
     *      Juan Montenegro - 24750
     *      Jonathan Tubac - 24484
     */
    public static void main(String[] args) {
        String filePath = "src/main/java/uvg/edu/sample.lisp";

        try {
            // Leer el contenido del archivo especificado por filePath
            String code = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);

            // Analizar el código en una lista de nodos AST
            Parser parser = new Parser();
            List<AstNode> astList = parser.parse(code);

            // Evaluar cada nodo AST
            Evaluator evaluator = new Evaluator();
            for (AstNode expr : astList) {
                evaluator.eval(expr);
            }

        } catch (IOException e) {
            // Manejar errores de lectura de archivos
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (RuntimeException e) {
            // Manejar errores de evaluación
            System.err.println("Error de evaluación: " + e.getMessage());
        }
    }
}