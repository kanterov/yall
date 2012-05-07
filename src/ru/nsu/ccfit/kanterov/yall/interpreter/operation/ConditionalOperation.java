package ru.nsu.ccfit.kanterov.yall.interpreter.operation;

import ru.nsu.ccfit.kanterov.yall.interpreter.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 15.04.2010
 * Time: 15:39:13
 */
@OperationDefinition(
        regularExpression = "check \\s+ \\w+"
)
public class ConditionalOperation implements Operation {
    // FIXME: write in good way

    public enum Condition {
        EQ("eq"), GEQ("geq"), LEQ("leq"), GR("gr"), LE("le");

        private String string;

        private Condition(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    ;
    private Condition condition = null;

    public ConditionalOperation(String operationCode) {
        Pattern pattern = Pattern.compile("check \\s+ (\\w+)", Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(operationCode);

        if (matcher.matches()) {
            String conditionString = matcher.group(1);

            for (Condition conditionItem : Condition.values()) {
                if (conditionItem.toString().equals(conditionString)) {
                    condition = conditionItem;
                    break;
                }
            }

            if (condition == null)
                throw new RuntimeException("Parse error");
        }
    }

    public ConditionalOperation(Condition condition) {
        this.condition = condition;
    }

    public void execute(ProgramContext context, ProgramContextRunner contextRunner) {
        Variable bVariable = context.pop();
        Variable aVariable = context.pop();

        if (!aVariable.getType().equals(IntegerVariable.type) || !bVariable.getType().equals(IntegerVariable.type)) {
            throw new RuntimeException("Type error");
        }

        int a = aVariable.getIntegerValue();
        int b = bVariable.getIntegerValue();

        boolean result = false;

        switch (condition) {
            case EQ:
                result = a == b;
                break;

            case LEQ:
                result = a <= b;
                break;


            case GEQ:
                result = a >= b;
                break;


            case GR:
                result = a > b;
                break;

            case LE:
                result = a < b;
                break;
        }

        if (result)
            context.push(new IntegerVariable(1));
        else
            context.push(new IntegerVariable(0));
    }

    public String toString() {
        return "check "+condition;
    }    
}
