package ru.nsu.ccfit.kanterov.yall.interpreter;

import ru.nsu.ccfit.kanterov.yall.parser.FileTools;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 13.04.2010
 * Time: 1:00:20
 */
public class Interpreter {
    public static void execute(String programCode) {
        Program program = InterpreterParser.parse(programCode);
        ProgramContext context = new ProgramContext(program);

        ProgramContextManager manager = new ProgramContextManager();
        manager.run(context);

        new InterpreterWorker(manager).run();
    }

    public static void main(String []args) {
        for (String filename : args) {
            execute(FileTools.getContents(new File(filename)));
        }
    }
}
