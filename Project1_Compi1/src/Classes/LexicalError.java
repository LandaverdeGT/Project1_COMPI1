package Classes;

public class LexicalError {
    private int line;
    private int column;
    private String message;

    public LexicalError(int line, int column, String message) {
        this.line = line;
        this.column = column;
        this.message = message;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LexicalError at line " + line + ", column " + column + ": " + message;
    }
}
