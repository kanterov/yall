package ru.nsu.ccfit.kanterov.yall.interpreter;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 16:09:41
 */
public interface Operation {
    void execute(ProgramContext context, ProgramContextRunner contextRunner);
}
