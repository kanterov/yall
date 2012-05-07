package ru.nsu.ccfit.kanterov.yall.functions;

import ru.nsu.ccfit.kanterov.yall.interpreter.operation.ConditionalOperation;
import ru.nsu.ccfit.kanterov.yall.interpreter.operation.PushOperation;
import ru.nsu.ccfit.kanterov.yall.interpreter.operation.PutOperation;
import ru.nsu.ccfit.kanterov.yall.parser.Function;
import ru.nsu.ccfit.kanterov.yall.parser.FunctionDefinition;
import ru.nsu.ccfit.kanterov.yall.parser.Parser;
import ru.nsu.ccfit.kanterov.yall.parser.Tree;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Token;
import ru.nsu.ccfit.kanterov.yall.translator.ProgramBuilder;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 21.03.2010
 * Time: 15:17:08
 * To change this template use File | Settings | File Templates.
 */
@FunctionDefinition(
    keywords = { "put" },
    maxArguments = 3,
    minArguments = 3,
    definableArguments = { 2 }
)
public class PutFunction extends Function {
    public PutFunction(Token function, List<Tree> children) throws Parser.ParseException {
        super(function, children);
    }

    @Override
    public boolean isDefinable() {
        return false;
    }

    public void build(ProgramBuilder programBuilder) {
        getChildren().get(0).build(programBuilder);

        if (getChildren().get(0).isDefinable())
            programBuilder.addOperation(new PushOperation());

        getChildren().get(1).build(programBuilder);

        if (getChildren().get(1).isDefinable())
            programBuilder.addOperation(new PushOperation());

        getChildren().get(2).build(programBuilder);

        programBuilder.addOperation(new PutOperation());
    }
}
