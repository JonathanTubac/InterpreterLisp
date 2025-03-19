package uvg.edu;

import java.util.List;
import java.util.Map;

/**
 * Representa una definición de función en el contexto de un AST.
 */
public class FunctionDefinition {
    private List<String> parameters;
    private AstNode body;
    private Map<String, Object> closure;

    /**
     * Construye una FunctionDefinition con los parámetros, cuerpo y cierre especificados.
     *
     * @param parameters la lista de nombres de parámetros para la función
     * @param body el cuerpo de la función, representado como un AstNode
     * @param closure el cierre de la función, representado como un mapa de nombres de variables a valores
     */
    public FunctionDefinition(List<String> parameters, AstNode body, Map<String, Object> closure) {
        this.parameters = parameters;
        this.body = body;
        this.closure = closure;
    }

    /**
     * Devuelve la lista de nombres de parámetros para la función.
     *
     * @return la lista de nombres de parámetros
     */
    public List<String> getParameters() {
        return parameters;
    }

    /**
     * Devuelve el cuerpo de la función.
     *
     * @return el cuerpo de la función como un AstNode
     */
    public AstNode getBody() {
        return body;
    }

    /**
     * Devuelve el cierre de la función.
     *
     * @return el cierre de la función como un mapa de nombres de variables a valores
     */
    public Map<String, Object> getClosure() {
        return closure;
    }
}