package uvg.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluator {

    private Map<String, Object> environment;

    public Evaluator() {
        environment = new HashMap<>();
    }

    public AstNode eval(AstNode node) {
        return eval(node, environment);
    }

    public AstNode eval(AstNode node, Map<String, Object> env) {
        switch (node.getType()) {
            case NUMBER:
                return new AstNode(AstNode.Type.NUMBER, node.getValue());

            case SYMBOL:
                String symbol = (String) node.getValue();
                if (env.containsKey(symbol)) {
                    Object value = env.get(symbol);
                    if (value instanceof FunctionDefinition) {
                        return new AstNode(AstNode.Type.SYMBOL, symbol);
                    } else if (value instanceof Integer) {
                        return new AstNode(AstNode.Type.NUMBER, value);
                    } else if (value instanceof String) {
                        return new AstNode(AstNode.Type.STRING, value);
                    } else {
                        throw new RuntimeException("Tipo de valor no soportado: " + value);
                    }
                } else {
                    throw new RuntimeException("Símbolo no definido: " + symbol);
                }

            case STRING:
                return new AstNode(AstNode.Type.STRING, node.getValue());

            case LIST:
                List<AstNode> list = castToList(node.getValue());
                if (list.isEmpty()) {
                    throw new RuntimeException("No se puede evaluar una lista vacía");
                }
                
                // El primer elemento debe ser un operador (símbolo).
                AstNode first = list.get(0);
                if (first.getType() != AstNode.Type.SYMBOL) {
                    throw new RuntimeException("El primer elemento de la lista debe ser un operador (símbolo)");
                }
                String operator = (String) first.getValue();
                
                // Manejo de comandos especiales como setq, print, cond, defun
                if (operator.equals("setq") || operator.equals("print") || operator.equals("cond") || operator.equals("defun")) {
                    return LispCommands.evaluateCommand(operator, list.subList(1, list.size()), this, env);
                }

                // Manejo de funciones definidas por el usuario
                if (env.containsKey(operator) && env.get(operator) instanceof FunctionDefinition) {
                    FunctionDefinition funcDef = (FunctionDefinition) env.get(operator);
                    List<String> parameters = funcDef.getParameters();
                    AstNode body = funcDef.getBody();
                    
                    // Crear un nuevo entorno combinando el cierre con el entorno actual
                    Map<String, Object> newEnv = new HashMap<>(env);
                    newEnv.putAll(funcDef.getClosure());
                
                    // Si la función es recursiva, asegurarse de que se puede llamar a sí misma
                    newEnv.put(operator, funcDef);
                
                    if (list.size() - 1 != parameters.size()) {
                        throw new RuntimeException("Número incorrecto de argumentos para la función: " + operator);
                    }
                
                    for (int i = 0; i < parameters.size(); i++) {
                        newEnv.put(parameters.get(i), eval(list.get(i + 1), env).getValue());
                    }
                
                    return eval(body, newEnv);
                }
                

                // Se evalúan los argumentos
                List<Object> operands = new ArrayList<>();
                for (int i = 1; i < list.size(); i++) {
                    operands.add(eval(list.get(i), env).getValue());
                }
                return applyBuiltInFunction(operator, operands);

            default:
                throw new RuntimeException("Tipo de nodo desconocido");
        }
    }

    /**
     * Aplica una operación incorporada (aritmética o lógica).
     */
    private AstNode applyBuiltInFunction(String operator, List<Object> operands) {
        switch (operator) {
            case "+":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.add(castToIntegerList(operands)));
            case "-":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.subtract(castToIntegerList(operands)));
            case "*":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.multiply(castToIntegerList(operands)));
            case "/":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.divide(castToIntegerList(operands)));
            case "=":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.equals(castToIntegerList(operands)) ? 1 : 0);
            case "<":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.lessThan(castToIntegerList(operands)) ? 1 : 0);
            case ">":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.greaterThan(castToIntegerList(operands)) ? 1 : 0);
            case "<=":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.lessThanOrEqual(castToIntegerList(operands)) ? 1 : 0);
            case ">=":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.greaterThanOrEqual(castToIntegerList(operands)) ? 1 : 0);
            case "and":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.and(castToBooleanList(operands)) ? 1 : 0);
            case "or":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.or(castToBooleanList(operands)) ? 1 : 0);
            default:
                throw new RuntimeException("Operador no soportado: " + operator);
        }
    }

    @SuppressWarnings("unchecked")
    private List<AstNode> castToList(Object value) {
        return (List<AstNode>) value;
    }

    private List<Integer> castToIntegerList(List<Object> operands) {
        List<Integer> intList = new ArrayList<>();
        for (Object operand : operands) {
            if (operand instanceof Integer) {
                intList.add((Integer) operand);
            } else {
                throw new RuntimeException("Operando no es un número: " + operand);
            }
        }
        return intList;
    }

    private List<Boolean> castToBooleanList(List<Object> operands) {
        List<Boolean> boolList = new ArrayList<>();
        for (Object operand : operands) {
            if (operand instanceof Boolean) {
                boolList.add((Boolean) operand);
            } else {
                throw new RuntimeException("Operando no es un booleano: " + operand);
            }
        }
        return boolList;
    }
}

