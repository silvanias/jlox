package lox;

import org.junit.jupiter.api.Test;

import static lox.TokenType.EOF;
import static lox.TokenType.EQUAL_EQUAL;
import static lox.TokenType.NUMBER;
import static lox.TokenType.PLUS;
import static lox.TokenType.STAR;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    @Test
    public void testParsingEqualityExpression() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(NUMBER, "5", 5.0, 1));
        tokens.add(new Token(EQUAL_EQUAL, "==", null, 1));
        tokens.add(new Token(NUMBER, "5", 5.0, 1));
        tokens.add(new Token(EOF, "", null, 2));

        Parser parser = new Parser(tokens);

        Expr result = parser.parse();
        assertTrue(result instanceof Expr.Binary);
        Expr.Binary binary = (Expr.Binary) result;

        assertTrue(binary.left instanceof Expr.Literal);

        assertTrue(binary.right instanceof Expr.Literal);
        Expr.Literal literal = (Expr.Literal) binary.right;
        assertEquals(5.0, literal.value);

        assertEquals(EQUAL_EQUAL, binary.operator.type);

        assertTrue(binary.right instanceof Expr.Literal);
    }

    @Test
    public void testParsingArithmeticExpression() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(NUMBER, "5", 5.0, 1));
        tokens.add(new Token(PLUS, "+", null, 1));
        tokens.add(new Token(NUMBER, "3", 3.0, 1));
        tokens.add(new Token(STAR, "*", null, 1));
        tokens.add(new Token(NUMBER, "2", 2.0, 1));
        tokens.add(new Token(EOF, "", null, 2));

        Parser parser = new Parser(tokens);

        Expr result = parser.parse();
        assertTrue(result instanceof Expr.Binary);
        Expr.Binary binary = (Expr.Binary) result;

        assertTrue(binary.left instanceof Expr.Literal);
        Expr.Literal leftLiteral = (Expr.Literal) binary.left;
        assertEquals(5.0, leftLiteral.value);

        assertEquals(PLUS, binary.operator.type);

        assertTrue(binary.right instanceof Expr.Binary);
        Expr.Binary rightBinary = (Expr.Binary) binary.right;

        assertTrue(rightBinary.left instanceof Expr.Literal);
        Expr.Literal lliteral = (Expr.Literal) rightBinary.left;
        assertEquals(3.0, lliteral.value);

        assertEquals(STAR, rightBinary.operator.type);

        assertTrue(rightBinary.right instanceof Expr.Literal);
        Expr.Literal rliteral = (Expr.Literal) rightBinary.right;
        assertEquals(2.0, rliteral.value);
    }

}
