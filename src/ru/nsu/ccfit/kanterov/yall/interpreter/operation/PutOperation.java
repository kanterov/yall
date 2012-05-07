package ru.nsu.ccfit.kanterov.yall.interpreter.operation;

import ru.nsu.ccfit.kanterov.yall.interpreter.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 17:29:57
 */
@OperationDefinition(
        regularExpression = "put"
)
public class PutOperation implements Operation {
    public PutOperation(String operationCode) {
        Pattern pattern = Pattern.compile("put", Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(operationCode);

        if (matcher.matches()) {
        }
        else
            throw new RuntimeException("Parse error");
    }

    public PutOperation() {
    }

    public void execute(ProgramContext context, ProgramContextRunner contextRunner) {
        Variable target = context.pop();

        if (target.getType().equals(IntegerVariable.type)) {
            Integer variableId = target.getIntegerValue();

            Variable array = context.getVariable(variableId);
            Variable value = context.pop();
            Variable key = context.pop();
            ArrayVariable newArray = (ArrayVariable)array.clone();

            newArray.setValue(key, value);

            context.push(newArray);
        }
        else 
            throw new RuntimeException("Parse error");
    }

    public String toString() {
        return getClass().getAnnotation(OperationDefinition.class).regularExpression();
    }    
}
