package ru.nsu.ccfit.kanterov.yall.interpreter.operation;

import ru.nsu.ccfit.kanterov.yall.interpreter.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 17:10:54
 */
@OperationDefinition (
        regularExpression = "cjmp \\s+ \\{ \\s* [\\d\\s+]* \\} \\s+ \\{ \\s* [\\d\\s+]* \\}"
)
public class IfJumpOperation implements Operation {
    private List<Integer> trueLabels;
    private List<Integer> falseLabels;

    private Operation trueJump;
    private Operation falseJump;

    private List<Integer> getLabels(String string) {
        List<Integer> labels = new LinkedList<Integer>();

        Pattern pattern = Pattern.compile("\\G (\\d+) \\s*", Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            labels.add(Integer.valueOf(matcher.group(1)));
        }

        return labels;
    }

    public IfJumpOperation(String operationCode) {
        Pattern pattern = Pattern.compile("cjmp \\s+ \\{ \\s* ([\\d\\s+]*) \\} \\s+ \\{ \\s* ([\\d\\s+]*) \\}",
                Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(operationCode);

        if (matcher.matches()) {
            String trueLabelsString = matcher.group(1);
            String falseLabelsString = matcher.group(2);

            if (trueLabelsString != null && falseLabelsString != null) {
                trueLabels = getLabels(trueLabelsString);
                falseLabels = getLabels(falseLabelsString);

                trueJump = new JumpOperation(trueLabels);
                falseJump = new JumpOperation(falseLabels);
            }
            else {
                throw new RuntimeException("Parse error");
            }
        }
        else
            throw new RuntimeException("Parse error");
    }

    public IfJumpOperation(List<Integer> trueLabels, List<Integer> falseLabels) {
        this.trueLabels = trueLabels;
        this.falseLabels = falseLabels;

        trueJump = new JumpOperation(trueLabels);
        falseJump = new JumpOperation(falseLabels);
    }

    public IfJumpOperation(Integer trueLabels[], Integer falseLabels[]) {
        this.trueLabels = Arrays.asList(trueLabels);
        this.falseLabels = Arrays.asList(falseLabels);

        trueJump = new JumpOperation(trueLabels);
        falseJump = new JumpOperation(falseLabels);

    }

    public void execute(ProgramContext context, ProgramContextRunner contextRunner) {
        Variable variable = context.pop();

        if (!variable.getType().equals(IntegerVariable.type))
            throw new RuntimeException("Type error");

        if (variable.getIntegerValue() == 0) {
            falseJump.execute(context, contextRunner);
        }
        else {
            trueJump.execute(context, contextRunner);
        }
    }

    public static void main(String []args) {
        IfJumpOperation jmp = new IfJumpOperation("cjmp {1 2} {3 4}");

        System.out.println(jmp.trueLabels.size());
        System.out.println(jmp.falseLabels.size());
    }

    public String toString() {
        String trueString = "{ ";
        String falseString = "{ ";

        for (Integer label : trueLabels) {
            trueString += label.toString() + " ";
        }
        trueString += "}";

        for (Integer label : falseLabels) {
            falseString += label.toString() + " ";
        }
        falseString += "}";

        return "cjmp "+trueString+" "+falseString;
    }    
}
