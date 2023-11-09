package lox;

import org.junit.Test;
import static org.junit.Assert.*;

public class InterpreterTest {

        @Test
        public void testArithmeticOperations() {
                Interpreter interpreter = new Interpreter();

                // Addition
                assertEquals("4", interpreter.interpret(new Expr.Binary(
                                new Expr.Literal(2.0),
                                new Token(TokenType.PLUS, "+", null, 1),
                                new Expr.Literal(2.0))));

                // Subtraction
                assertEquals("1", interpreter.interpret(new Expr.Binary(
                                new Expr.Literal(3.0),
                                new Token(TokenType.MINUS, "-", null, 1),
                                new Expr.Literal(2.0))));

                // Multiplication
                assertEquals("6", interpreter.interpret(new Expr.Binary(
                                new Expr.Literal(3.0),
                                new Token(TokenType.STAR, "*", null, 1),
                                new Expr.Literal(2.0))));

                // Division
                assertEquals("2", interpreter.interpret(new Expr.Binary(
                                new Expr.Literal(4.0),
                                new Token(TokenType.SLASH, "/", null, 1),
                                new Expr.Literal(2.0))));
        }

        @Test
        public void testComparisonOperations() {
                Interpreter interpreter = new Interpreter();

                // Greater than
                assertTrue(Boolean.valueOf(interpreter.interpret(new Expr.Binary(
                                new Expr.Literal(4.0),
                                new Token(TokenType.GREATER, ">", null, 1),
                                new Expr.Literal(2.0)))));

                // Less than
                assertTrue(Boolean.valueOf(interpreter.interpret(new Expr.Binary(
                                new Expr.Literal(2.0),
                                new Token(TokenType.LESS, "<", null, 1),
                                new Expr.Literal(4.0)))));

                // Greater than or equal
                assertTrue(Boolean.valueOf(interpreter.interpret(new Expr.Binary(
                                new Expr.Literal(4.0),
                                new Token(TokenType.GREATER_EQUAL, ">=", null, 1),
                                new Expr.Literal(4.0)))));

                // Less than or equal
                assertTrue(Boolean.valueOf(interpreter.interpret(new Expr.Binary(
                                new Expr.Literal(2.0),
                                new Token(TokenType.LESS_EQUAL, "<=", null, 1),
                                new Expr.Literal(2.0)))));
        }

        @Test
        public void testEqualityOperations() {
                Interpreter interpreter = new Interpreter();

                // Equal
                assertTrue(Boolean.valueOf(interpreter.interpret(new Expr.Binary(
                                new Expr.Literal(2.0),
                                new Token(TokenType.EQUAL_EQUAL, "==", null, 1),
                                new Expr.Literal(2.0)))));

                // Not equal
                assertTrue(Boolean.valueOf(interpreter.interpret(new Expr.Binary(
                                new Expr.Literal(2.0),
                                new Token(TokenType.BANG_EQUAL, "!=", null, 1),
                                new Expr.Literal(3.0)))));
        }

}
