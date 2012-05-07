package ru.nsu.ccfit.kanterov.yall.interpreter.operation;

import ru.nsu.ccfit.kanterov.yall.interpreter.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 16:59:16
 */
@OperationDefinition(
    regularExpression = "push"        
)
public class PushOperation implements Operation {
    public PushOperation(String operationCode) {
        Pattern pattern = Pattern.compile("push", Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(operationCode);

        if (matcher.matches()) {
        }
        else
            throw new RuntimeException("Parse error");
    }

    public PushOperation() {
    }

    public void execute(ProgramContext context, ProgramContextRunner contextRunner) {
        Variable target = context.pop();

        if (target.getType().equals(IntegerVariable.type)) {
            Integer variableId = target.getIntegerValue();
            Variable variable = context.getVariable(variableId);
            context.push(variable);
        }
        else
            throw new RuntimeException("Parse error");
    }

    public String toString() {
        return getClass().getAnnotation(OperationDefinition.class).regularExpression();
    }    
}
