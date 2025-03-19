package uvg.edu;

import java.util.List;

public class BuiltInFunctions {

    /**
     * Suma todos los operandos.
     */
    public static int add(List<Integer> operands) {
        int result = 0;
        for (int op : operands) {
            result += op;
        }
        return result;
    }

    /**
     * Resta secuencialmente los operandos.
     */
    public static int subtract(List<Integer> operands) {
        if (operands.isEmpty()) {
            throw new RuntimeException("La operación '-' espera al menos un operando");
        }
        int result = operands.get(0);
        for (int i = 1; i < operands.size(); i++) {
            result -= operands.get(i);
        }
        return result;
    }

    /**
     * Multiplica todos los operandos.
     */
    public static int multiply(List<Integer> operands) {
        int result = 1;
        for (int op : operands) {
            result *= op;
        }
        return result;
    }

    /**
     * Divide secuencialmente los operandos (división entera).
     */
    public static int divide(List<Integer> operands) {
        if (operands.isEmpty()) {
            throw new RuntimeException("La operación '/' espera al menos un operando");
        }
        int result = operands.get(0);
        for (int i = 1; i < operands.size(); i++) {
            int divisor = operands.get(i);
            if (divisor == 0) {
                throw new RuntimeException("División por cero");
            }
            result /= divisor;
        }
        return result;
    }

    /**
     * Compara si todos los operandos son iguales.
     */
    public static boolean equals(List<Integer> operands) {
        if (operands.isEmpty()) {
            throw new RuntimeException("La operación '=' espera al menos un operando");
        }
        int first = operands.get(0);
        for (int op : operands) {
            if (op != first) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara si el primer operando es menor que el segundo.
     */
    public static boolean lessThan(List<Integer> operands) {
        if (operands.size() != 2) {
            throw new RuntimeException("La operación '<' espera exactamente dos operandos");
        }
        return operands.get(0) < operands.get(1);
    }

    /**
     * Compara si el primer operando es mayor que el segundo.
     */
    public static boolean greaterThan(List<Integer> operands) {
        if (operands.size() != 2) {
            throw new RuntimeException("La operación '>' espera exactamente dos operandos");
        }
        return operands.get(0) > operands.get(1);
    }

    /**
     * Realiza una operación lógica AND sobre todos los operandos.
     */
    public static boolean and(List<Boolean> operands) {
        for (boolean op : operands) {
            if (!op) {
                return false;
            }
        }
        return true;
    }

    /**
     * Realiza una operación lógica OR sobre todos los operandos.
     */
    public static boolean or(List<Boolean> operands) {
        for (boolean op : operands) {
            if (op) {
                return true;
            }
        }
        return false;
    }
}
