package Classes;

public class SintacticalError {
    private int line;
    private int column;
    private Object token;
    private String type;

    String error;
    public SintacticalError(int line, int column, Object token, String type) {
        this.line = line;
        this.column = column;
        this.token = token;
        this.type = type;
    }

    public SintacticalError(String error) {
        this.error = error;
    }

    public String toString() {
        if (error != null) {
            return error;
        }
        return "SintacticalError at line " + line + ", column " + column + ": unexpected " + type + " '" + token + "'";
    }
}
