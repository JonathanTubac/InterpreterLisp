package uvg.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LispCommands {

    /**
     * Maneja las formas especiales (defun, setq, etc.).
     * Retorna el resultado de ejecutar el comando.
     *
     * @param command Nombre del comando (por ej. "defun", "setq").
     * @param argNodes Lista de nodos AST que representan los argumentos de la forma especial.
     * @param evaluator Instancia del Evaluator para evaluar subexpresiones.
     * @param env Entorno actual donde se definen variables y funciones.
     */
    public static Object evaluateCommand(String command,
                                         List<AstNode> argNodes,
                                         Evaluator evaluator,
                                         Map<String, Object> env) {
        switch (command) {
            case "defun":
                return handleDefun(argNodes, env);
            case "setq":
                return handleSetq(argNodes, evaluator, env);
            default:
                throw new RuntimeException("Comando desconocido: " + command);
        }
    }

    /**
     * (defun nombre (param1 param2 ...) cuerpo)
     */
    private static Object handleDefun(List<AstNode> argNodes, Map<String, Object> env) {
        // Implementación de defun (no se muestra aquí)
        return null;
    }

    /**
     * (setq var valor)
     */
    private static Object handleSetq(List<AstNode> argNodes, Evaluator evaluator, Map<String, Object> env) {
        if (argNodes.size() != 2) {
            throw new RuntimeException("setq espera 2 argumentos: variable y valor");
        }
        AstNode varNode = argNodes.get(0);
        if (varNode.getType() != AstNode.Type.SYMBOL) {
            throw new RuntimeException("El primer argumento de setq debe ser un símbolo");
        }
        String varName = (String) varNode.getValue();
        // Evaluamos el valor en el entorno actual
        Object value = evaluator.eval(argNodes.get(1), env).getValue();

        // Asignamos en el entorno
        env.put(varName, value);
        return value;
    }

    @SuppressWarnings("unchecked")
    private static List<AstNode> castToList(Object value) {
        return (List<AstNode>) value;
    }
}
