package ru.nsu.ccfit.kanterov.yall.interpreter.operation;

import ru.nsu.ccfit.kanterov.yall.interpreter.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 15.04.2010
 * Time: 13:02:01
 */
@OperationDefinition(
        regularExpression = "\\d+"
)
public class DigitOperation implements Operation {
    private int value;

    public DigitOperation(String operationCode) {
        Pattern pattern = Pattern.compile("(\\d+)", Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(operationCode);

        if (matcher.matches())
            value = Integer.valueOf(matcher.group(1));
        else
            throw new RuntimeException("Parse error");
    }

    public DigitOperation(int value) {
        this.value = value;
    }
    
    public void execute(ProgramContext context, ProgramContextRunner contextRunner) {
        context.push(new IntegerVariable(value));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

