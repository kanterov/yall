package ru.nsu.ccfit.kanterov.yall.functions;

import ru.nsu.ccfit.kanterov.yall.interpreter.operation.ConditionalOperation;
import ru.nsu.ccfit.kanterov.yall.interpreter.operation.IfJumpOperation;
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
     keywords = { "?" },
     maxArguments = 1,
     minArguments = 1
)
public class TestFunction extends ArithmeticalFunction {
    public TestFunction(Token function, List<Tree> children) throws Parser.ParseException {
        super(function, children);
    }

    public void build(ProgramBuilder programBuilder) {
        super.build(programBuilder);

        int oldLabel = programBuilder.getCurrentLabel();
        int newLabel = programBuilder.makeNextLabel();

        programBuilder.addOperation(oldLabel, new IfJumpOperation(new Integer [] { newLabel }, new Integer [] {}));
        programBuilder.setCurrentLabel(newLabel);
    }
}
