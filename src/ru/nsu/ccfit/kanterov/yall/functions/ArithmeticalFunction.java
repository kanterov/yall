package ru.nsu.ccfit.kanterov.yall.functions;

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
 * Date: 17.03.2010
 * Time: 21:49:23
 */
@FunctionDefinition(
     keywords = { },
     maxArguments = 2,
     minArguments = 2
)
public abstract class ArithmeticalFunction extends Function {
    public ArithmeticalFunction(Token function, List<Tree> children) throws Parser.ParseException {
        super(function, children);
    }

    public void build(ProgramBuilder programBuilder) {
        for (Tree tree : getChildren()) {
            tree.build(programBuilder);
            if (tree.isDefinable())
                programBuilder.addOperation(new PushOperation());
        }
    }
}
