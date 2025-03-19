package uvg.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluator {

    private Map<String, AstNode> environment;

    public Evaluator() {
        environment = new HashMap<>();
        // Puedes agregar símbolos predefinidos aquí si es necesario
    }

    public AstNode eval(AstNode node) {
        switch (node.getType()) {
            case NUMBER:
                return new AstNode(AstNode.Type.NUMBER, node.getValue());

            case SYMBOL:
                throw new RuntimeException("Símbolo no soportado: " + node.getValue());

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
                if (operator.equals("and") || operator.equals("or")) {
                    List<Boolean> operands = new ArrayList<>();
                    for (int i = 1; i < list.size(); i++) {
                        AstNode operandNode = eval(list.get(i));
                        if (operandNode.getType() != AstNode.Type.NUMBER) {
                            throw new RuntimeException("Se esperaba un número como operando");
                        }
                        operands.add((Boolean) operandNode.getValue());
                    }
                    return applyLogicalFunction(operator, operands);
                } else {
                    List<Integer> operands = new ArrayList<>();
                    for (int i = 1; i < list.size(); i++) {
                        AstNode operandNode = eval(list.get(i));
                        if (operandNode.getType() != AstNode.Type.NUMBER) {
                            throw new RuntimeException("Se esperaba un número como operando");
                        }
                        operands.add((Integer) operandNode.getValue());
                    }
                    return applyArithmeticFunction(operator, operands);
                }

            default:
                throw new RuntimeException("Tipo de nodo desconocido");
        }
    }

    /**
     * Aplica una operación aritmética incorporada.
     */
    private AstNode applyArithmeticFunction(String operator, List<Integer> operands) {
        switch (operator) {
            case "+":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.add(operands));
            case "-":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.subtract(operands));
            case "*":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.multiply(operands));
            case "/":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.divide(operands));
            case "=":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.equals(operands) ? 1 : 0);
            case "<":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.lessThan(operands) ? 1 : 0);
            case ">":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.greaterThan(operands) ? 1 : 0);
            default:
                throw new RuntimeException("Operador no soportado: " + operator);
        }
    }

    /**
     * Aplica una operación lógica incorporada.
     */
    private AstNode applyLogicalFunction(String operator, List<Boolean> operands) {
        switch (operator) {
            case "and":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.and(operands) ? 1 : 0);
            case "or":
                return new AstNode(AstNode.Type.NUMBER, BuiltInFunctions.or(operands) ? 1 : 0);
            default:
                throw new RuntimeException("Operador no soportado: " + operator);
        }
    }

    @SuppressWarnings("unchecked")
    private List<AstNode> castToList(Object value) {
        return (List<AstNode>) value;
    }
}

