package ru.nsu.ccfit.kanterov.yall.functions;

import ru.nsu.ccfit.kanterov.yall.interpreter.Operation;
import ru.nsu.ccfit.kanterov.yall.interpreter.operation.JumpOperation;
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

// Chooses random argument
@FunctionDefinition(
     keywords = { "any" },
     maxArguments = 2,
     minArguments = 2
)
public class AnyFunction extends Function {
    public AnyFunction(Token function, List<Tree> children) {
        super(function, children);
    }

    public void build(ProgramBuilder programBuilder) {
        int beginLabel = programBuilder.getCurrentLabel(); 
        Integer anyLabel[] = new Integer[] { programBuilder.makeNextLabel(), programBuilder.makeNextLabel() };  
        int endLabel = programBuilder.makeNextLabel();
        int i = 0;

        boolean definable = isDefinable();

        Operation jump = new JumpOperation(anyLabel);

        programBuilder.addOperation(beginLabel, jump);

        for (Tree tree : getChildren()) {
            programBuilder.setCurrentLabel(anyLabel[i]);
            tree.build(programBuilder);
            if (!definable && tree.isDefinable())
                programBuilder.addOperation(new PushOperation());
            programBuilder.addOperation(new JumpOperation(new Integer[] { endLabel }));

            i++;
        }
        programBuilder.setCurrentLabel(endLabel);
    }

    @Override
    public boolean isDefinable() {
        boolean definable = true;

        for (Tree tree : getChildren()) {
            definable = definable && tree.isDefinable();
        }

        return definable;
    }
}
