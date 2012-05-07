package ru.nsu.ccfit.kanterov.yall.parser;

import ru.nsu.ccfit.kanterov.yall.parser.FunctionNodeBuilder;
import ru.nsu.ccfit.kanterov.yall.parser.FileTools;
import ru.nsu.ccfit.kanterov.yall.parser.IntegerNode;
import ru.nsu.ccfit.kanterov.yall.parser.VariableNode;
import ru.nsu.ccfit.kanterov.yall.parser.Tree;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Token;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Tokenizer;

import java.io.File;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 13.03.2010
 * Time: 19:20:48
 */
public class Parser {
    // Parser exceptions
    public static class ParseException extends Exception {
        public ParseException(Token token) {
            this.token = token;
        }

        public Token getToken() {
            return token;
        }

        private Token token;
    }
    public static class UnexpectedEnd extends ParseException {
        public UnexpectedEnd(Token token) {
            super(token);
        }

        @Override
        public String toString() {
            return "Unexpected end at "+getToken()+".";
        }
    }
    public static class BadSyntax extends ParseException {
        public BadSyntax(Token token) {
            super(token);
        }

        @Override
        public String toString() {
            return "Bad syntax at "+getToken()+".";
        }
    }
    public static class BadArgumentCount extends ParseException {
        public BadArgumentCount(Token token, int minimum, int maximum, int count) {
            super(token);

            this.minimum = minimum;
            this.maximum = maximum;
            this.count = count;
        }

        @Override
        public String toString() {
            String message = new String("Bad argument count at "+getToken()+", expected ");

            if (minimum == maximum)
                message = message + minimum + " arguments, got "+count+".";
            else
                message = message + "from " + minimum + " to " + maximum+ ", got "+count+".";

            return message;
        }

        private int minimum;
        private int maximum;
        private int count;
    }
    public static class FunctionImplementationProblem extends ParseException {
        public FunctionImplementationProblem(Token token) {
            super(token);
        }

        @Override
        public String toString() {
            return "Function implementation problem at "+getToken()+".";
        }
    }
    public static class UnknownFunction extends ParseException {
        public UnknownFunction(Token token) {
            super(token);
        }

        @Override
        public String toString() {
            return "Unknown function at "+getToken()+".";
        }
    }
    public static class UnexpectedTokenType extends ParseException {
        public UnexpectedTokenType(Token token) {
            super(token);
        }

        @Override
        public String toString() {
            return "Unexcepted token type at "+getToken();
        }
    }
    public static class MissingClosingBracket extends ParseException {
        public MissingClosingBracket(Token token) {
            super(token);
        }

        @Override
        public String toString() {
            return "Missing closing bracket at "+getToken()+".";
        }
    }
    public static class DefinableRequired extends ParseException {
        public DefinableRequired(Token token) {
            super(token);
        }

        @Override
        public String toString() {
            return getToken() + " requires definable arguments."; // TODO: Write numbers
        }
    }

    // <code> ::= {<statement>}
    public static List<Tree> parseCode(List<Token> tokenList) throws ParseException {
        List<Tree> forest = new LinkedList<Tree>();
        ListIterator<Token> iterator = tokenList.listIterator();
        Tree tree = parseStatement(iterator);

        while (tree != null) {
            forest.add(tree);
            tree = parseStatement(iterator);
        }

        return forest;
    }

    // <statement> ::= "(", <function_name>, <parameters>, ")" | <number> | <identificator>
    private static Tree parseStatement(ListIterator<Token> iterator) throws ParseException {
        if (!iterator.hasNext())
            return null;

        Token firstToken = iterator.next();

        if (firstToken.getType() == Token.Type.OPENNING_BRACKET) {
            if (!iterator.hasNext())
                throw new UnexpectedEnd(firstToken); 
                

            Token functionName = iterator.next();
            List<Tree> parameters = new LinkedList<Tree>();
            Tree parameter;

            if (functionName.getType() != Token.Type.IDENTIFIER && functionName.getType() != Token.Type.OPERATION)
                throw new BadSyntax(functionName);

            parameter = parseStatement(iterator);

            if (parameter == null)
                throw new BadSyntax(functionName);

            while (parameter != null) {
                parameters.add(parameter);
                parameter = parseStatement(iterator);
            }

            if (!iterator.hasNext())
                throw new UnexpectedEnd(iterator.previous());
            
            Token closingBracket = iterator.next();

            if (closingBracket.getType() != Token.Type.CLOSING_BRACKET)
                throw new MissingClosingBracket(closingBracket);

            return FunctionNodeBuilder.getFunctionNode(functionName, parameters);
        }
        else
        if (firstToken.getType() == Token.Type.NUMBER) {
            return new IntegerNode(firstToken);
        }
        else
        if (firstToken.getType() == Token.Type.IDENTIFIER) {
            //TODO: Check for not being reserved identifier, but it's ok due to language specification, so no check now
            return new VariableNode(firstToken);
        }
        else
        if (firstToken.getType() == Token.Type.UNKNOWN) {
            throw new UnexpectedTokenType(firstToken);
        }

        iterator.previous();

        return null;
    }

    public static void main(String[] args) {

        if (args.length == 0)
            System.out.println("Usage: <file1> <file2> ...");

        for (String arg : args) {
            try {
                String code = FileTools.getContents(new File(arg));
                List<Token> tokenList = Tokenizer.tokenizeString(code);
                List<Tree> forest = parseCode(tokenList);

                for (Token token : tokenList) {
                    System.out.print(token+" ");
                }

                System.out.println();

                for (Tree tree : forest)
                    TreePrinter.printTree(System.out, tree);

                System.out.println();
            } catch (ParseException e) {
                System.out.println(e);
            }
        }
    }
}
