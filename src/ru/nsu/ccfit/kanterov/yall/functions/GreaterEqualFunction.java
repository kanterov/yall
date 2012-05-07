package ru.nsu.ccfit.kanterov.yall.functions;

import ru.nsu.ccfit.kanterov.yall.interpreter.operation.ConditionalOperation;
import ru.nsu.ccfit.kanterov.yall.parser.FunctionDefinition;
import ru.nsu.ccfit.kanterov.yall.parser.Parser;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Token;
import ru.nsu.ccfit.kanterov.yall.parser.Tree;
import ru.nsu.ccfit.kanterov.yall.functions.ArithmeticalFunction;
import ru.nsu.ccfit.kanterov.yall.translator.ProgramBuilder;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 17.03.2010
 * Time: 22:29:51
 * To change this template use File | Settings | File Templates.
 */
@FunctionDefinition(
     keywords = { "geq" },
     maxArguments = 2,
     minArguments = 2
)
public class GreaterEqualFunction extends ArithmeticalFunction {
    public GreaterEqualFunction(Token function, List<Tree> children) throws Parser.ParseException {
        super(function, children);
    }

    public void build(ProgramBuilder programBuilder) {
        super.build(programBuilder);

        programBuilder.addOperation(new ConditionalOperation(ConditionalOperation.Condition.GEQ));
    }
}
