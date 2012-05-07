package ru.nsu.ccfit.kanterov.yall.parser;

import ru.nsu.ccfit.kanterov.yall.functions.*;
import ru.nsu.ccfit.kanterov.yall.parser.Parser;
import ru.nsu.ccfit.kanterov.yall.parser.Tree;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Token;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 17.03.2010
 * Time: 21:37:17
 */
public class FunctionNodeBuilder {
    private static Class []functionClasses = {
            AnyFunction.class,
            ArithmeticalFunction.class,
            ArrayFunction.class,
            DefinitionOperatorFunction.class,
            DivisionFunction.class,
            DoFunction.class,
            EquationFunction.class,
            GetFunction.class,
            GreaterEqualFunction.class,
            GreaterFunction.class,
            LesserEqualFunction.class,
            LesserFunction.class,
            ListFunction.class,
            MinusFunction.class,
            MultiplyFunction.class,
            PutFunction.class,
            PlusFunction.class,
            TestFunction.class
    };

    private static final Class [] functionConstrutorArguments = new Class [] { Token.class, List.class };

    static {
        try {
            Function.class.getConstructor(functionConstrutorArguments);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("API problem");
        }
    }


    public static Tree getFunctionNode(Token function, List<Tree> children) throws Parser.ParseException {
        String functionName = function.getValue();

        for (Class functionClass : functionClasses) {
            if (functionClass.isAnnotationPresent(FunctionDefinition.class)) {
                FunctionDefinition functionAnnotation =
                        (FunctionDefinition)functionClass.getAnnotation(FunctionDefinition.class);
                boolean foundKeyword = false;

                for (String keyword : functionAnnotation.keywords()) {
                    if (keyword.equals(functionName)) {
                        foundKeyword = true;
                        break;
                    }
                }

                if (foundKeyword) {
                    Constructor functionConstructor = null;

                    if (children.size() < functionAnnotation.minArguments() ||
                            children.size() > functionAnnotation.maxArguments()) {
                        throw new Parser.BadArgumentCount(function, functionAnnotation.minArguments(),
                                functionAnnotation.maxArguments(), children.size());
                    }

                    for (int definableArgument : functionAnnotation.definableArguments()) {
                        if (!children.get(definableArgument).isDefinable())
                            throw new Parser.DefinableRequired(function);
                    }

                    try {
                        functionConstructor = functionClass.getConstructor(functionConstrutorArguments);
                    } catch (NoSuchMethodException e) {
                        throw new Parser.FunctionImplementationProblem(function);
                    }

                    try {
                        Tree tree = (Tree)functionConstructor.newInstance(function, children);
                        return tree;
                    } catch (Exception e) {
                        throw new Parser.FunctionImplementationProblem(function);
                    }
                }
            }
        }

        throw new Parser.UnknownFunction(function);
    }
}
