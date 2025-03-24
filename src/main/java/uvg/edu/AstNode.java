package uvg.edu;

import java.util.List;
import java.util.stream.Collectors;

public class AstNode {

    public enum Type {
        NUMBER,
        SYMBOL,
        STRING,
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
        if (type == Type.LIST) {
            List<AstNode> list = (List<AstNode>) value;
            return "(" + list.stream().map(AstNode::toString).collect(Collectors.joining(" ")) + ")";
        }
        return value.toString();
    }
}
