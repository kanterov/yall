package ru.nsu.ccfit.kanterov.yall.functions;

import ru.nsu.ccfit.kanterov.yall.interpreter.Operation;
import ru.nsu.ccfit.kanterov.yall.interpreter.operation.JumpOperation;
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
 * Time: 21:39:13
 */
@FunctionDefinition(
     keywords = { "do" },
     maxArguments = 2,
     minArguments = 2
)
public class DoFunction extends Function {
    public DoFunction(Token function, List<Tree> children) throws Parser.ParseException {
        super(function, children);
    }

    public void build(ProgramBuilder programBuilder) {
        int oldLabel = programBuilder.getCurrentLabel();
        int startLabel = programBuilder.makeNextLabel();
        int middleLabel = programBuilder.makeNextLabel();
        int endLabel = programBuilder.makeNextLabel();

        programBuilder.addOperation(oldLabel, new JumpOperation(new Integer[] { middleLabel, startLabel }));

        programBuilder.setCurrentLabel(startLabel);

        getChildren().get(0).build(programBuilder);
        programBuilder.addOperation(new JumpOperation(new Integer[] {startLabel, endLabel}));


        programBuilder.setCurrentLabel(middleLabel);
        getChildren().get(1).build(programBuilder);
        programBuilder.addOperation(new JumpOperation(new Integer[] { endLabel }));

        programBuilder.setCurrentLabel(endLabel);
    }
}


