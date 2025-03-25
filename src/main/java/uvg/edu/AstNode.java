package uvg.edu;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa un nodo en un árbol de sintaxis abstracta (AST).
 */
public class AstNode {

    /**
     * Enum que representa el tipo del nodo AST.
     */
    public enum Type {
        NUMBER,  // Representa un valor numérico
        SYMBOL,  // Representa un valor simbólico
        STRING,  // Representa un valor de cadena
        LIST     // Representa una lista de nodos AST
    }

    private Type type;  // El tipo del nodo AST
    private Object value;  // El valor del nodo AST

    /**
     * Construye un nodo AST con el tipo y valor especificados.
     *
     * @param type  el tipo del nodo AST
     * @param value el valor del nodo AST
     */
    public AstNode(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Devuelve el tipo del nodo AST.
     *
     * @return el tipo del nodo AST
     */
    public Type getType() {
        return type;
    }

    /**
     * Devuelve el valor del nodo AST.
     *
     * @return el valor del nodo AST
     */
    public Object getValue() {
        return value;
    }

    /**
     * Devuelve una representación en cadena del nodo AST.
     * Si el nodo es de tipo LIST, convierte recursivamente cada nodo en la lista a una cadena.
     *
     * @return una representación en cadena del nodo AST
     */
    @Override
    public String toString() {
        if (type == Type.LIST) {
            List<AstNode> list = (List<AstNode>) value;
            return "(" + list.stream().map(AstNode::toString).collect(Collectors.joining(" ")) + ")";
        }
        return value.toString();
    }
}