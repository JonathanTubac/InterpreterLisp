package uvg.edu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Interpreter {

    public static void main(String[] args) {
        String filePath = "src/main/java/uvg/edu/sample.lisp";

        try {
            String code = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            Parser parser = new Parser();
            List<AstNode> astList = parser.parse(code);

            Evaluator evaluator = new Evaluator();
            for (AstNode expr : astList) {
                evaluator.eval(expr);
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Evaluation error: " + e.getMessage());
        }
    }
}