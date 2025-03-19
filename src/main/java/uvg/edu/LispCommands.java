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
        if (argNodes.size() != 3) {
            throw new RuntimeException("defun espera 3 argumentos: nombre, lista de parámetros y cuerpo");
        }
        // 1) Nombre de la función
        AstNode nameNode = argNodes.get(0);
        if (nameNode.getType() != AstNode.Type.SYMBOL) {
            throw new RuntimeException("El nombre de la función debe ser un símbolo");
        }
        String functionName = (String) nameNode.getValue();

        // 2) Lista de parámetros
        AstNode paramsNode = argNodes.get(1);
        if (paramsNode.getType() != AstNode.Type.LIST) {
            throw new RuntimeException("Los parámetros deben ser una lista");
        }
        List<AstNode> paramAstNodes = castToList(paramsNode.getValue());
        List<String> parameters = new ArrayList<>();
        for (AstNode param : paramAstNodes) {
            if (param.getType() != AstNode.Type.SYMBOL) {
                throw new RuntimeException("Cada parámetro debe ser un símbolo");
            }
            parameters.add((String) param.getValue());
        }

        // 3) Cuerpo de la función
        AstNode bodyNode = argNodes.get(2);

        // Creamos la definición y la almacenamos en el entorno
        FunctionDefinition funcDef = new FunctionDefinition(parameters, bodyNode, new HashMap<>(env));
        env.put(functionName, funcDef);

        return functionName;  // Podemos retornar el nombre de la función o la misma definición
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
        Object value = evaluator.eval(argNodes.get(1), env);

        // Asignamos en el entorno
        env.put(varName, value);
        return value;
    }

    private static Object handlePrint(List<AstNode> argNodes, Evaluator evaluator, Map<String, Object> env) {
        if (argNodes.size() != 1) {
            throw new RuntimeException("print espera 1 argumento");
        }
        Object value = evaluator.eval(argNodes.get(0), env);
        System.out.println(value);
        return value;
    }
    
    @SuppressWarnings("unchecked")
    private static List<AstNode> castToList(Object value) {
        return (List<AstNode>) value;
    }
}
