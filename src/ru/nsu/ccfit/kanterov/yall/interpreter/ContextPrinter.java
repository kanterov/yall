package ru.nsu.ccfit.kanterov.yall.interpreter;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 15.04.2010
 * Time: 14:56:08
 */
public class ContextPrinter {
    public synchronized static void printContext(ProgramContext context) {
        System.out.println(Thread.currentThread()+" Worker thread finished.");
        System.out.println(context);
    }
}
