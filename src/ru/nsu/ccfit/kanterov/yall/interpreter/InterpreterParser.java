package ru.nsu.ccfit.kanterov.yall.interpreter;

import ru.nsu.ccfit.kanterov.yall.interpreter.operation.*;
import ru.nsu.ccfit.kanterov.yall.parser.FileTools;
import ru.nsu.ccfit.kanterov.yall.parser.Parser;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Lexem;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Token;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 16:06:47
 */
public class InterpreterParser {
    private static String regexp = "";
    private static final Class []operations = {
            IfJumpOperation.class,
            MinusOperation.class,
            MultiplyOperation.class,
            PlusOperation.class,
            PopOperation.class,
            GetOperation.class,
            PutOperation.class,
            ArrayOperation.class,
            JumpOperation.class,
            PushOperation.class,
            DigitOperation.class,
            ConditionalOperation.class,
            DivisionOperation.class,
            RemoveOperation.class,
            
    };

    private static Map<Integer, Class> groupToClass = new HashMap<Integer, Class>();

    static {
        int index = 2;

        for (Class operationClass : operations) {
            OperationDefinition operationDefinition;                

            if (operationClass.isAnnotationPresent(OperationDefinition.class))
                operationDefinition = (OperationDefinition)operationClass.getAnnotation(OperationDefinition.class);
            else
                throw new RuntimeException("Bad operation class annotation");

            regexp += "(\\d+:)? \\s*" + "("+operationDefinition.regularExpression()+")|";
        }
    }

    public static Program parse(String programCode) {
        Map<Integer, List<Operation>> code = new HashMap<Integer,  List<Operation>>();
        String lines[] = programCode.split("[\\r\\n]+");

        Integer currentLabel = 0;

        for (String line : lines) {
            Pattern pattern = Pattern.compile(regexp, Pattern.COMMENTS);
            Matcher matcher = pattern.matcher(line);

            if (matcher.matches()) {

                for (int i = 2; i <= matcher.groupCount(); i+=2) {
                    String value = matcher.group(i);

                    if (value != null) {
                        String label = matcher.group(i-1);

                        if (label != null) {
                            label = label.replace(":", "");
                            currentLabel = Integer.valueOf(label);
                        }

                        List<Operation> operationList;
                        Class [] constuctorArguments = { String.class };

                        if (!code.containsKey(currentLabel)) {
                            operationList = new ArrayList<Operation>();
                            code.put(currentLabel, operationList);
                        }
                        else
                            operationList = code.get(currentLabel);

                        Class operationClass = operations[(i-2)/2];
                        Operation operation;

                        try {
                            operation = (Operation) operationClass.getConstructor(constuctorArguments).newInstance(value);
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException("Bad operation constructor for "+operationClass);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException("Bad operation constructor for "+operationClass);
                        } catch (InstantiationException e) {
                            throw new RuntimeException("Bad operation constructor for "+operationClass);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("Bad operation constructor for "+operationClass);
                        }

                        operationList.add(operation);
                    }
                }
            }
            else {
                throw new RuntimeException("Parse error at "+line);
            }
        }

        return new Program(code);
    }
    
    public static void main(String []args) {
        String programCode =
                "0: push 1\n" +
                "0: push 2\n" +
                "1: +\n";

        Program program = InterpreterParser.parse(programCode);
        System.out.println(program);
    }
}
