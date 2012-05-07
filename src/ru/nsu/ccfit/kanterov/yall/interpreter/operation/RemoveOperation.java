package ru.nsu.ccfit.kanterov.yall.interpreter.operation;

import ru.nsu.ccfit.kanterov.yall.interpreter.*;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 10.05.2010
 * Time: 18:08:24
 */
@OperationDefinition(
        regularExpression = "remove"
)
public class RemoveOperation implements Operation {
    public RemoveOperation(String operationCode) {
    }

    public RemoveOperation() {
    }

    public void execute(ProgramContext context, ProgramContextRunner contextRunner) {
        context.pop();
    }

    public String toString() {
        return getClass().getAnnotation(OperationDefinition.class).regularExpression();
    }
}