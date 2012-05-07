package ru.nsu.ccfit.kanterov.yall.interpreter;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 10.05.2010
 * Time: 13:46:53
 */
public class ProgramContextManager implements ProgramContextRunner {
    private LinkedList<ProgramContext> programContextList = new LinkedList<ProgramContext>();

    private Integer workingThreads = 0; 

    public ProgramContext pop() {
        while(true) {
            boolean finished = false;
            boolean needToWait = false;

            synchronized (programContextList) {
                synchronized (workingThreads) {
                    if (programContextList.isEmpty() && workingThreads == 0) {
                        finished = true;
                    }
                }
            }

            if (finished)
                return null;

            synchronized (programContextList) {
                if (programContextList.isEmpty())
                    needToWait = true;
                else {
                    synchronized (workingThreads) {
                        workingThreads++;
                    }

                    return programContextList.pollFirst();
                }

                try {
                    if (needToWait) {
                        programContextList.wait(1);
                        continue;
                    }
                } catch (InterruptedException e) {
                    return null;
                }
            }
        }
    }

    public void run(ProgramContext programContext) {
        synchronized (programContextList) {
            programContextList.addLast(programContext);
            programContextList.notifyAll();
        }

    }

    public void operationEnded() {
        synchronized (workingThreads) {
            workingThreads--;
        }
    }
}
