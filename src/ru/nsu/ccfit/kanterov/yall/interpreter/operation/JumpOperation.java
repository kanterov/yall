package ru.nsu.ccfit.kanterov.yall.interpreter.operation;

import ru.nsu.ccfit.kanterov.yall.interpreter.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 15.04.2010
 * Time: 10:25:27
 */

@OperationDefinition(
        regularExpression = "jmp \\s+ \\{ \\s* [\\d\\s+]* \\}"
)
public class JumpOperation implements Operation {
    private List<Integer> labels;


    private void jump(ProgramContext context, List<Integer> labels, ProgramContextRunner contextRunner) {
        if (labels.size() == 0)
            throw new RuntimeException("No jump point");

        for (int i = 1; i < labels.size(); i++) {
            ProgramContext newContext = context.clone();
            newContext.setLabel(labels.get(i));
            newContext.setColumn(0);
            contextRunner.run(newContext);
        }

        context.setLabel(labels.get(0));
        context.setColumn(0);
    }
    
    public JumpOperation(String operationCode) {
        Pattern pattern = Pattern.compile("jmp \\s+ \\{ \\s* ([\\d\\s+]*) \\}", Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(operationCode);

        if (matcher.matches()) {
            String labelsString = matcher.group(1);

            if (labelsString != null) {
                labels = getLabels(labelsString);
            }
            else {
                throw new RuntimeException("Parse error");
            }
        }
        else
            throw new RuntimeException("Parse error");
    }

    public JumpOperation(List<Integer> labels) {
        this.labels = labels;
    }

    public JumpOperation(Integer labels[]) {
        this.labels = Arrays.asList(labels);
    }

    private List<Integer> getLabels(String string) {
        List<Integer> labels = new LinkedList<Integer>();

        Pattern pattern = Pattern.compile("\\G \\s* (\\d+) \\s*", Pattern.COMMENTS);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            labels.add(Integer.valueOf(matcher.group(1)));
        }

        return labels;
    }

    public void execute(ProgramContext context, ProgramContextRunner contextRunner) {
        jump(context, labels, contextRunner);
    }

    @Override
    public String toString() {
        String jumpString = "{ ";
        for (Integer label : labels) {
            jumpString += label.toString() + " ";
        }
        jumpString += "}";

        return "jmp "+jumpString;
    }
}
