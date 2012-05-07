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
 * Time: 20:46:29
 * To change this template use File | Settings | File Templates.
 */
public class VariableNode implements Tree {
    public VariableNode(Token token) {
        if (token.getType() != Token.Type.IDENTIFIER)
            throw new RuntimeException("Logics problem");

        name = token.getValue();
    }

    public List<Tree> getChildren() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isDefinable() {
        return true;
    }

    public void build(ProgramBuilder programBuilder) {
        int id = programBuilder.getVariableId(name);
        programBuilder.addOperation(new DigitOperation(id));
    }

    private String name;
}
