package ru.nsu.ccfit.kanterov.yall.interpreter;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 15.04.2010
 * Time: 13:04:49
 */
public interface Variable {
    String getType();
    int getIntegerValue();
    Variable clone();
}
