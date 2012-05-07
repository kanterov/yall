package ru.nsu.ccfit.kanterov.yall.parser;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 21.03.2010
 * Time: 15:32:34                                                               d
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface FunctionDefinition {
    int []definableArguments() default {}; // enumerated from 0 
    String []keywords();
    int minArguments() default 1;
    int maxArguments() default 1;
}
