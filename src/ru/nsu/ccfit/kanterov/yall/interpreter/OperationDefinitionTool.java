package ru.nsu.ccfit.kanterov.yall.interpreter;

import ru.nsu.ccfit.kanterov.yall.parser.FunctionDefinition;
import ru.nsu.ccfit.kanterov.yall.parser.Parser;
import ru.nsu.ccfit.kanterov.yall.parser.Tree;

import java.lang.reflect.Constructor;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 17:22:09
 */
public class OperationDefinitionTool {
    static public OperationDefinition getOperationDefinition(Class operationClass) {
        if (operationClass.isAnnotationPresent(OperationDefinition.class))
            return (OperationDefinition)operationClass.getAnnotation(OperationDefinition.class);
        else
            throw new RuntimeException("Bad operation class annotation");
    }
}
