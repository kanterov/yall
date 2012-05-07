package ru.nsu.ccfit.kanterov.yall.parser;

import ru.nsu.ccfit.kanterov.yall.datatype.Datatype;
import ru.nsu.ccfit.kanterov.yall.datatype.IntegerDatatype;
import ru.nsu.ccfit.kanterov.yall.interpreter.operation.DigitOperation;
import ru.nsu.ccfit.kanterov.yall.parser.Tree;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Token;
import ru.nsu.ccfit.kanterov.yall.translator.ProgramBuilder;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 13.03.2010
 * Time: 20:10:46
 */
public class IntegerNode implements Tree {
    public IntegerNode(Token token) {
        if (token.getType() != Token.Type.NUMBER)
            throw new RuntimeException("Logics problem");

        value = Integer.valueOf(token.getValue());     
    }

    public List<Tree> getChildren() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public boolean isDefinable() {
        return false;
    }

    public void build(ProgramBuilder programBuilder) {
        programBuilder.addOperation(new DigitOperation(value));
    }

    private int value;
}
