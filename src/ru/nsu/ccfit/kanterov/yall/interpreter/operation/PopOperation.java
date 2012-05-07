package ru.nsu.ccfit.kanterov.yall.interpreter.operation;

import ru.nsu.ccfit.kanterov.yall.interpreter.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 17:02:11
 */
@OperationDefinition(
        regularExpression = "pop"

)
public class PopOperation implements Operation {
    public PopOperation(String operationCode) {
        Pattern pattern = Pattern.compile("pop", Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(operationCode);

        if (matcher.matches()) {
        }
        else
            throw new RuntimeException("Parse error");
    }

    public PopOperation() {
    }

    public void execute(ProgramContext context, ProgramContextRunner contextRunner) {
        Variable target = context.pop();
        Variable variable = context.pop();

        if (target.getType().equals(IntegerVariable.type)) {
            Integer variableId = target.getIntegerValue();
            
            context.setVariable(variableId, variable);
            context.push(target);
        }
        else
            throw new RuntimeException("Parse error");
    }

    public String toString() {
        return getClass().getAnnotation(OperationDefinition.class).regularExpression();
    }
}
