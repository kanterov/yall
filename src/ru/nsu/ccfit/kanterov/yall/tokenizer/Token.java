package ru.nsu.ccfit.kanterov.yall.tokenizer;

import ru.nsu.ccfit.kanterov.yall.tokenizer.Lexem;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 13.03.2010
 * Time: 18:02:22
 */
public class Token {

    public enum Type {
        NUMBER, OPENNING_BRACKET, CLOSING_BRACKET, IDENTIFIER, OPERATION, UNKNOWN;
    }

    public Token(Lexem lexem) {
        value = lexem.getValue();

        if (value.matches("^-?[0-9]+$"))
            type = Type.NUMBER;
        else
        if (value.matches("^[a-zA-Z_][a-zA-Z_]*$"))
            type = Type.IDENTIFIER;
        else
        if (value.matches("^[\\=\\+\\-\\*\\?]$"))
            type = Type.OPERATION;
        else
        if (value.matches("^\\($"))
            type = Type.OPENNING_BRACKET;
        else
        if (value.matches("^\\)$"))
            type = Type.CLOSING_BRACKET;
        else
            type = Type.UNKNOWN;

        line = lexem.getLine();
        column = lexem.getColumn();
    }

    public String getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "("+line+","+column+","+type+",'"+value+"')";
    }

    private String value;
    private Type type;
    private int line;
    private int column;
}
