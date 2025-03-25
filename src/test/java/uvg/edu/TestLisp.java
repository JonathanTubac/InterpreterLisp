package uvg.edu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLisp {
    @Test
    void testAdd() {
        assertEquals(10, BuiltInFunctions.add(Arrays.asList(1, 2, 3, 4)));
        assertEquals(0, BuiltInFunctions.add(Collections.emptyList()));
    }

    @Test
    void testSubtract() {
        assertEquals(2, BuiltInFunctions.subtract(Arrays.asList(10, 5, 3)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.subtract(Collections.emptyList()));
    }

    @Test
    void testMultiply() {
        assertEquals(24, BuiltInFunctions.multiply(Arrays.asList(1, 2, 3, 4)));
        assertEquals(1, BuiltInFunctions.multiply(Collections.emptyList()));
    }

    @Test
    void testDivide() {
        assertEquals(2, BuiltInFunctions.divide(Arrays.asList(20, 5, 2)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.divide(Collections.emptyList()));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.divide(Arrays.asList(10, 0)));
    }

    @Test
    void testEquals() {
        assertTrue(BuiltInFunctions.equals(Arrays.asList(5, 5, 5)));
        assertFalse(BuiltInFunctions.equals(Arrays.asList(5, 5, 6)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.equals(Collections.emptyList()));
    }

    @Test
    void testLessThan() {
        assertTrue(BuiltInFunctions.lessThan(Arrays.asList(3, 5)));
        assertFalse(BuiltInFunctions.lessThan(Arrays.asList(5, 3)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.lessThan(Arrays.asList(1)));
    }

    @Test
    void testLessThanOrEqual() {
        assertTrue(BuiltInFunctions.lessThanOrEqual(Arrays.asList(3, 5)));
        assertTrue(BuiltInFunctions.lessThanOrEqual(Arrays.asList(5, 5)));
        assertFalse(BuiltInFunctions.lessThanOrEqual(Arrays.asList(6, 5)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.lessThanOrEqual(Arrays.asList(1)));
    }

    @Test
    void testGreaterThan() {
        assertTrue(BuiltInFunctions.greaterThan(Arrays.asList(5, 3)));
        assertFalse(BuiltInFunctions.greaterThan(Arrays.asList(3, 5)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.greaterThan(Arrays.asList(1)));
    }

    @Test
    void testGreaterThanOrEqual() {
        assertTrue(BuiltInFunctions.greaterThanOrEqual(Arrays.asList(5, 3)));
        assertTrue(BuiltInFunctions.greaterThanOrEqual(Arrays.asList(5, 5)));
        assertFalse(BuiltInFunctions.greaterThanOrEqual(Arrays.asList(3, 5)));
        assertThrows(RuntimeException.class, () -> BuiltInFunctions.greaterThanOrEqual(Arrays.asList(1)));
    }

    @Test
    void testAnd() {
        assertTrue(BuiltInFunctions.and(Arrays.asList(true, true, true)));
        assertFalse(BuiltInFunctions.and(Arrays.asList(true, false, true)));
    }

    @Test
    void testOr() {
        assertTrue(BuiltInFunctions.or(Arrays.asList(false, true, false)));
        assertFalse(BuiltInFunctions.or(Arrays.asList(false, false, false)));
    }

    @Test
    void testAtom() {
        assertTrue(BuiltInFunctions.atom(5));
        assertFalse(BuiltInFunctions.atom(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testList() {
        List<Object> input = Arrays.asList(1, 2, 3);
        assertEquals(input, BuiltInFunctions.list(input));
    }

    //tests para la clase LispCommands

    private Evaluator evaluator;
    private Map<String, Object> env;

    @BeforeEach
    void setUp() {
        evaluator = mock(Evaluator.class);
        env = new HashMap<>();
    }

    @Test
    void testDefun() {
        AstNode nameNode = new AstNode(AstNode.Type.SYMBOL, "myFunc");
        AstNode paramsNode = new AstNode(AstNode.Type.LIST, Arrays.asList(
                new AstNode(AstNode.Type.SYMBOL, "x"),
                new AstNode(AstNode.Type.SYMBOL, "y")
        ));
        AstNode bodyNode = new AstNode(AstNode.Type.NUMBER, 42);
        List<AstNode> argNodes = Arrays.asList(nameNode, paramsNode, bodyNode);

        AstNode result = LispCommands.evaluateCommand("defun", argNodes, evaluator, env);

        assertEquals("myFunc", result.getValue());
        assertTrue(env.containsKey("myFunc"));
        assertTrue(env.get("myFunc") instanceof FunctionDefinition);
    }

    @Test
    void testSetq() {
        AstNode varNode = new AstNode(AstNode.Type.SYMBOL, "x");
        AstNode valueNode = new AstNode(AstNode.Type.NUMBER, 10);
        List<AstNode> argNodes = Arrays.asList(varNode, valueNode);

        when(evaluator.eval(valueNode, env)).thenReturn(new AstNode(AstNode.Type.NUMBER, 10));

        AstNode result = LispCommands.evaluateCommand("setq", argNodes, evaluator, env);

        assertEquals("x", result.getValue());
        assertEquals(10, env.get("x"));
    }

    @Test
    void testPrint() {
        AstNode valueNode = new AstNode(AstNode.Type.STRING, "Hello, World!");
        List<AstNode> argNodes = Collections.singletonList(valueNode);

        when(evaluator.eval(valueNode, env)).thenReturn(new AstNode(AstNode.Type.STRING, "Hello, World!"));

        AstNode result = LispCommands.evaluateCommand("print", argNodes, evaluator, env);

        assertEquals("Hello, World!", result.getValue());
    }

    @Test
    void testCond() {
        AstNode condition1 = new AstNode(AstNode.Type.NUMBER, 0);
        AstNode expr1 = new AstNode(AstNode.Type.STRING, "False branch");
        AstNode clause1 = new AstNode(AstNode.Type.LIST, Arrays.asList(condition1, expr1));

        AstNode condition2 = new AstNode(AstNode.Type.NUMBER, 1);
        AstNode expr2 = new AstNode(AstNode.Type.STRING, "True branch");
        AstNode clause2 = new AstNode(AstNode.Type.LIST, Arrays.asList(condition2, expr2));

        List<AstNode> argNodes = Arrays.asList(clause1, clause2);

        when(evaluator.eval(condition1, env)).thenReturn(new AstNode(AstNode.Type.NUMBER, 0));
        when(evaluator.eval(condition2, env)).thenReturn(new AstNode(AstNode.Type.NUMBER, 1));
        when(evaluator.eval(expr2, env)).thenReturn(new AstNode(AstNode.Type.STRING, "True branch"));

        AstNode result = LispCommands.evaluateCommand("cond", argNodes, evaluator, env);

        assertEquals("True branch", result.getValue());
    }

    @Test
    void testUnknownCommand() {
        List<AstNode> argNodes = Collections.emptyList();
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                LispCommands.evaluateCommand("unknown", argNodes, evaluator, env));
        assertEquals("Comando desconocido: unknown", exception.getMessage());
    }
}
