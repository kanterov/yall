package ru.nsu.ccfit.kanterov.yall.interpreter;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 15.04.2010
 * Time: 14:43:53
 */
public class InterpreterWorker implements Runnable {

    private ProgramContextManager programContextManager;

    public InterpreterWorker(ProgramContextManager programContextManager) {
        this.programContextManager = programContextManager;
    }

    private void finishProgram(ProgramContext programContext) {
        ContextPrinter.printContext(programContext);
    }

    public void run() {
        boolean finished = false;

        while(!finished) {
            ProgramContext programContext = programContextManager.pop();

            if (programContext == null) {
                finished = true;
                break;
            }

            Program program = programContext.getProgram();

            Integer label = programContext.getLabel();
            Integer column = programContext.getColumn();

            if (!program.hasLabel(label)) {
                finishProgram(programContext);
                programContextManager.operationEnded();
                continue;
            }

            List<Operation> operations = program.getOperations(label);

            programContext.setColumn(column+1);

            if (operations.size() <= column) {
                finishProgram(programContext);
                programContextManager.operationEnded();
                continue;
            }
            else {
                try {
                    operations.get(column).execute(programContext, programContextManager);
                    programContextManager.run(programContext);
                }

                catch (RuntimeException e) {
                    if (e == null || !e.getMessage().startsWith("No jump point")) {


                    System.out.println("Exception at "+programContext.getLabel()+":"+programContext.getColumn()+"("+
                            operations.get(column)+")");
                    e.printStackTrace();
                    }
                    // ProgramContext is dead, no message
                }

                finally {
                    programContextManager.operationEnded();
                }
            }
        }
    }
}
