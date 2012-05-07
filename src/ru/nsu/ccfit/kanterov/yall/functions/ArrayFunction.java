package ru.nsu.ccfit.kanterov.yall.functions;

import ru.nsu.ccfit.kanterov.yall.interpreter.operation.ArrayOperation;
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
 * Date: 18.03.2010
 * Time: 13:17:37
 */
@FunctionDefinition(
     keywords = { "array" },
     maxArguments = 2,
     minArguments = 2
)
public class ArrayFunction extends Function {
    public ArrayFunction(Token function, List<Tree> children) throws Parser.ParseException {
        super(function, children);
    }

    public void build(ProgramBuilder programBuilder) {
        for (Tree tree : getChildren()) {
            tree.build(programBuilder);
            if (tree.isDefinable())
                programBuilder.addOperation(new PushOperation());
        }

        programBuilder.addOperation(new ArrayOperation());
    }
}
