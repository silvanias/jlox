package lox;

class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    // TODO: implement column and length for more accurate error report
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}