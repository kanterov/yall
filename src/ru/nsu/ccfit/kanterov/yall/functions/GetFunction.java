package ru.nsu.ccfit.kanterov.yall.functions;

import ru.nsu.ccfit.kanterov.yall.interpreter.operation.GetOperation;
import ru.nsu.ccfit.kanterov.yall.interpreter.operation.PushOperation;
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
 * Time: 15:19:50
 * To change this template use File | Settings | File Templates.
 */
@FunctionDefinition(
     keywords = { "get" },
     maxArguments = 2,
     minArguments = 2
)
public class GetFunction extends Function {
    public GetFunction(Token function, List<Tree> children) throws Parser.ParseException {
        super(function, children);
    }

    public void build(ProgramBuilder programBuilder) {
        getChildren().get(0).build(programBuilder);
        if (getChildren().get(0).isDefinable())
            programBuilder.addOperation(new PushOperation());
        getChildren().get(1).build(programBuilder);

        programBuilder.addOperation(new GetOperation());
    }
}
