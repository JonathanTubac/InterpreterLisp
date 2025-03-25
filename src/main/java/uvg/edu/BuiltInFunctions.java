package uvg.edu;

    import java.util.List;

    public class BuiltInFunctions {

        /**
         * Suma todos los operandos.
         *
         * @param operands una lista de enteros a sumar
         * @return la suma de todos los operandos
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
         *
         * @param operands una lista de enteros a restar
         * @return el resultado de restar secuencialmente los operandos
         * @throws RuntimeException si la lista de operandos está vacía
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
         *
         * @param operands una lista de enteros a multiplicar
         * @return el producto de todos los operandos
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
         *
         * @param operands una lista de enteros a dividir
         * @return el resultado de dividir secuencialmente los operandos
         * @throws RuntimeException si la lista de operandos está vacía o si se intenta dividir por cero
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
         *
         * @param operands una lista de enteros a comparar
         * @return true si todos los operandos son iguales, false en caso contrario
         * @throws RuntimeException si la lista de operandos está vacía
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
         *
         * @param operands una lista de dos enteros a comparar
         * @return true si el primer operando es menor que el segundo, false en caso contrario
         * @throws RuntimeException si la lista de operandos no contiene exactamente dos elementos
         */
        public static boolean lessThan(List<Integer> operands) {
            if (operands.size() != 2) {
                throw new RuntimeException("La operación '<' espera exactamente dos operandos");
            }
            return operands.get(0) < operands.get(1);
        }

        /**
         * Compara si el primer operando es menor o igual que el segundo.
         *
         * @param operands una lista de dos enteros a comparar
         * @return true si el primer operando es menor o igual que el segundo, false en caso contrario
         * @throws RuntimeException si la lista de operandos no contiene exactamente dos elementos
         */
        public static boolean lessThanOrEqual(List<Integer> operands) {
            if (operands.size() != 2) {
                throw new RuntimeException("La operación '<=' espera exactamente dos operandos");
            }
            return operands.get(0) <= operands.get(1);
        }

        /**
         * Compara si el primer operando es mayor que el segundo.
         *
         * @param operands una lista de dos enteros a comparar
         * @return true si el primer operando es mayor que el segundo, false en caso contrario
         * @throws RuntimeException si la lista de operandos no contiene exactamente dos elementos
         */
        public static boolean greaterThan(List<Integer> operands) {
            if (operands.size() != 2) {
                throw new RuntimeException("La operación '>' espera exactamente dos operandos");
            }
            return operands.get(0) > operands.get(1);
        }

        /**
         * Compara si el primer operando es mayor o igual que el segundo.
         *
         * @param operands una lista de dos enteros a comparar
         * @return true si el primer operando es mayor o igual que el segundo, false en caso contrario
         * @throws RuntimeException si la lista de operandos no contiene exactamente dos elementos
         */
        public static boolean greaterThanOrEqual(List<Integer> operands) {
            if (operands.size() != 2) {
                throw new RuntimeException("La operación '>=' espera exactamente dos operandos");
            }
            return operands.get(0) >= operands.get(1);
        }

        /**
         * Realiza una operación lógica AND sobre todos los operandos.
         *
         * @param operands una lista de valores booleanos
         * @return true si todos los operandos son true, false en caso contrario
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
         *
         * @param operands una lista de valores booleanos
         * @return true si al menos uno de los operandos es true, false en caso contrario
         */
        public static boolean or(List<Boolean> operands) {
            for (boolean op : operands) {
                if (op) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Verifica si el operando es un átomo.
         *
         * @param operand el operando a verificar
         * @return true si el operando no es una lista, false en caso contrario
         */
        public static boolean atom(Object operand) {
            return !(operand instanceof List);
        }

        /**
         * Verifica si el operando es una lista.
         *
         * @param operands una lista de objetos
         * @return la lista de objetos recibida
         */
        public static List<Object> list(List<Object> operands) {
            return operands;
        }

    }