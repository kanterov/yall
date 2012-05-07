package ru.nsu.ccfit.kanterov.yall.interpreter.operation;

import ru.nsu.ccfit.kanterov.yall.interpreter.*;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 17:30:44
 */
@OperationDefinition(
        regularExpression = "array"
)
public class ArrayOperation implements Operation {
    public ArrayOperation(String operationCode) {
    }

    public ArrayOperation() {
        
    }

    public void execute(ProgramContext context, ProgramContextRunner contextRunner) {
        Variable b = context.pop();
        Variable a = context.pop();

        ArrayVariable array = new ArrayVariable(a,b);
        context.push(array);
    }

    public String toString() {
        return getClass().getAnnotation(OperationDefinition.class).regularExpression();
    }
}
