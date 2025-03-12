package uvg.edu;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {

 
    public AstNode eval(AstNode node) {
        switch (node.getType()) {
            case NUMBER:
                return new AstNode(AstNode.Type.NUMBER, node.getValue());

            case SYMBOL:
                throw new RuntimeException("Símbolo no soportado en esta entrega: " + node.getValue());

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
                List<Integer> operands = new ArrayList<>();
                for (int i = 1; i < list.size(); i++) {
                    AstNode operandNode = eval(list.get(i));
                    if (operandNode.getType() != AstNode.Type.NUMBER) {
                        throw new RuntimeException("Se esperaba un número como operando");
                    }
                    operands.add((Integer) operandNode.getValue());
                }
                return applyBuiltInFunction(operator, operands);

            default:
                throw new RuntimeException("Tipo de nodo desconocido");
        }
    }

    /**
     * Aplica una operación aritmética incorporada.
     */
    private AstNode applyBuiltInFunction(String operator, List<Integer> operands) {
        switch (operator) {
            case "+":
                return BuiltInFunctions.add(operands);
            case "-":
                return BuiltInFunctions.subtract(operands);
            case "*":
                return BuiltInFunctions.multiply(operands);
            case "/":
                return BuiltInFunctions.divide(operands);
            default:
                throw new RuntimeException("Operador no soportado: " + operator);
        }
    }

    @SuppressWarnings("unchecked")
    private List<AstNode> castToList(Object value) {
        return (List<AstNode>) value;
    }
}

