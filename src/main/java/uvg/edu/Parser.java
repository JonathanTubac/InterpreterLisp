package uvg.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    /**
     * Parsea la entrada en una lista de expresiones (AST).
     */
    public List<AstNode> parse(String input) {
        List<String> tokens = tokenize(input);
        List<AstNode> expressions = new ArrayList<>();
        while (!tokens.isEmpty()) {
            expressions.add(readFromTokens(tokens));
        }
        return expressions;
    }

    /**
     * Separa la entrada en tokens.
     */
    private List<String> tokenize(String input) {
        input = input.replace("(", " ( ").replace(")", " ) ");
        String[] split = input.trim().split("\\s+");
        return new ArrayList<>(Arrays.asList(split));
    }

    /**
     * Construye recursivamente un AstNode a partir de los tokens.
     */
    private AstNode readFromTokens(List<String> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Fin de tokens inesperado");
        }
        String token = tokens.remove(0);
        if ("(".equals(token)) {
            List<AstNode> list = new ArrayList<>();
            while (!tokens.isEmpty() && !")".equals(tokens.get(0))) {
                list.add(readFromTokens(tokens));
            }
            if (tokens.isEmpty()) {
                throw new RuntimeException("Falta un paréntesis de cierre ')'");
            }
            tokens.remove(0); // Remover ")"
            return new AstNode(AstNode.Type.LIST, list);
        } else if (")".equals(token)) {
            throw new RuntimeException("Paréntesis de cierre inesperado");
        } else if (token.startsWith("\"") && token.endsWith("\"")) {
            return new AstNode(AstNode.Type.STRING, token.substring(1, token.length() - 1));
        } else {
            try {
                int value = Integer.parseInt(token);
                return new AstNode(AstNode.Type.NUMBER, value);
            } catch (NumberFormatException e) {
                return new AstNode(AstNode.Type.SYMBOL, token);
            }
        }
    }
}
