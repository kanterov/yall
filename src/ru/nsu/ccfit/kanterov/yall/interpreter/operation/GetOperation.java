package ru.nsu.ccfit.kanterov.yall.interpreter.operation;

import ru.nsu.ccfit.kanterov.yall.interpreter.*;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 17:28:44
 */
@OperationDefinition(
        regularExpression = "get"
)
public class GetOperation implements Operation {
    public GetOperation(String operationCode) {
    }

    public GetOperation() {
    }

    public void execute(ProgramContext context, ProgramContextRunner contextRunner) {
        Variable array = context.pop();
        Variable key = context.pop();

        //TODO checkcheckcheck


        Variable arrayId = context.getVariable(array.getIntegerValue());

        ArrayVariable arrayVariable = ArrayVariable.arrayIndex.get(arrayId.getIntegerValue());
        Variable value = arrayVariable.getValue(key);
        context.push(value);
    }

    public String toString() {
        return getClass().getAnnotation(OperationDefinition.class).regularExpression();
    }    
}
