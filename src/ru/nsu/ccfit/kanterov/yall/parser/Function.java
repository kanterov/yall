package ru.nsu.ccfit.kanterov.yall.parser;

import ru.nsu.ccfit.kanterov.yall.tokenizer.Token;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 21.03.2010
 * Time: 16:40:17
 */
public abstract class Function implements Tree {
    public Function(Token function, List<Tree> children) {
        this.children = Collections.unmodifiableList(children);
        this.function = function;
    }

    public List<Tree> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        Class thisClass = this.getClass();
                                                                                     
        if (thisClass.isAnnotationPresent(FunctionDefinition.class)) {
            FunctionDefinition definition = (FunctionDefinition)thisClass.getAnnotation(FunctionDefinition.class);
            if (definition.keywords().length > 0)
                return definition.keywords()[0];
            else
                return super.toString();
        }
        else
            return super.toString();
    }

    public boolean isDefinable() {
        return false;
    }

    private List<Tree> children;
    private Token function;
}

