package ru.nsu.ccfit.kanterov.yall.tokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 13.03.2010
 * Time: 18:07:05
 */
public class Lexem {

    public Lexem(String value, int column, int line) {
        this.value = value;
        this.column = column;
        this.line = line;
    }

    public String getValue() {
        return value;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "("+line+","+column+",'"+value+"')";
    }

    private String value;
    private int column;
    private int line; 
}
