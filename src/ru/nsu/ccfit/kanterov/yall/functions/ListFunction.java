package ru.nsu.ccfit.kanterov.yall.functions;

import ru.nsu.ccfit.kanterov.yall.interpreter.operation.ConditionalOperation;
import ru.nsu.ccfit.kanterov.yall.interpreter.operation.RemoveOperation;
import ru.nsu.ccfit.kanterov.yall.parser.Function;
import ru.nsu.ccfit.kanterov.yall.parser.FunctionDefinition;
import ru.nsu.ccfit.kanterov.yall.parser.Parser;
import ru.nsu.ccfit.kanterov.yall.parser.Tree;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Token;
import ru.nsu.ccfit.kanterov.yall.translator.ProgramBuilder;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 17.03.2010
 * Time: 21:39:13
 */
@FunctionDefinition(
     keywords = { "list" },
     maxArguments = Integer.MAX_VALUE,
     minArguments = 1 
)
public class ListFunction extends Function {
    public ListFunction(Token function, List<Tree> children) throws Parser.ParseException {
        super(function, children);
    }

    public void build(ProgramBuilder programBuilder) {
        int i = 0;

        for (Tree tree : getChildren()) {
            i++;
            
            tree.build(programBuilder);

            //if (i != getChildren().size())
            //    programBuilder.addOperation(new RemoveOperation());
        }
    }    
}


