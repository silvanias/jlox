package lox;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ScannerTest {

    @Test
    void testSimpleArithmeticExpression() {
        assertTokens("3 + 4 * 2;",
                TokenType.NUMBER, TokenType.PLUS, TokenType.NUMBER, TokenType.STAR, TokenType.NUMBER,
                TokenType.SEMICOLON, TokenType.EOF);
    }

    @Test
    void testStringLiterals() {
        assertTokens("\"Hello, World!\" + \"Java\";",
                TokenType.STRING, TokenType.PLUS, TokenType.STRING, TokenType.SEMICOLON, TokenType.EOF);
    }

    @Test
    void testKeywordsAndIdentifiers() {
        assertTokens("if (true) { print(\"Hello\"); } else { print(\"World\"); }",
                TokenType.IF, TokenType.LEFT_PAREN, TokenType.TRUE, TokenType.RIGHT_PAREN,
                TokenType.LEFT_BRACE, TokenType.PRINT, TokenType.LEFT_PAREN, TokenType.STRING,
                TokenType.RIGHT_PAREN, TokenType.SEMICOLON, TokenType.RIGHT_BRACE, TokenType.ELSE,
                TokenType.LEFT_BRACE, TokenType.PRINT, TokenType.LEFT_PAREN, TokenType.STRING,
                TokenType.RIGHT_PAREN, TokenType.SEMICOLON, TokenType.RIGHT_BRACE,
                TokenType.EOF);
    }

    @Test
    void testComments() {
        assertTokens("var x = 10; // This is a comment",
                TokenType.VAR, TokenType.IDENTIFIER, TokenType.EQUAL, TokenType.NUMBER, TokenType.SEMICOLON,
                TokenType.EOF);
    }

    private void assertTokens(String input, TokenType... expectedTokens) {
        Scanner scanner = new Scanner(input);
        List<Token> tokens = scanner.scanTokens();

        assert tokens.size() == expectedTokens.length : "Incorrect number of tokens";

        for (int i = 0; i < tokens.size(); i++) {
            TokenType expectedType = expectedTokens[i];
            TokenType actualType = tokens.get(i).type;

            assertEquals(expectedType, actualType, "Token mismatch at position " + i);
        }
    }
}
