package uvg.edu;

import java.util.List;

public class AstNode {

    public enum Type {
        NUMBER,
        SYMBOL,
        LIST
    }

    private Type type;
    private Object value;

    public AstNode(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AstNode{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
