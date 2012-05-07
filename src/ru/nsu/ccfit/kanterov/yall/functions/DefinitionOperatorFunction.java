package ru.nsu.ccfit.kanterov.yall.functions;

import ru.nsu.ccfit.kanterov.yall.interpreter.operation.PopOperation;
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
 * Time: 23:20:01
 */
@FunctionDefinition(
     keywords = { "def" },
     maxArguments = 2,
     minArguments = 2,
     definableArguments = { 1 }
)
public class DefinitionOperatorFunction extends Function {
     public DefinitionOperatorFunction(Token function, List<Tree> children) throws Parser.ParseException {
        super(function, children);
    }

    @Override
    public boolean isDefinable() {
        return true;
    }

    public void build(ProgramBuilder programBuilder) {
        getChildren().get(0).build(programBuilder);        
        if (getChildren().get(0).isDefinable())
            programBuilder.addOperation(new PushOperation());

        getChildren().get(1).build(programBuilder);
        programBuilder.addOperation(new PopOperation());
    }
}
