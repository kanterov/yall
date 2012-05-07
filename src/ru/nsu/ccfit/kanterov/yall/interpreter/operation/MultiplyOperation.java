package ru.nsu.ccfit.kanterov.yall.interpreter.operation;

import ru.nsu.ccfit.kanterov.yall.interpreter.*;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 17:09:28
 */
@OperationDefinition(
        regularExpression = "\\*"

)
public class MultiplyOperation implements Operation {
    public MultiplyOperation(String operationCode) {
    }

    public MultiplyOperation() {
    }

    public void execute(ProgramContext context, ProgramContextRunner contextRunner) {
        Variable a = context.pop();
        Variable b = context.pop();

        if (!a.getType().equals(IntegerVariable.type) || !b.getType().equals(IntegerVariable.type)) {
            throw new RuntimeException("Type error");
        }

        Variable result = new IntegerVariable(a.getIntegerValue() * b.getIntegerValue());
        context.push(result);
    }

    public String toString() {
        return getClass().getAnnotation(OperationDefinition.class).regularExpression().replace("\\", "");
    }
}
