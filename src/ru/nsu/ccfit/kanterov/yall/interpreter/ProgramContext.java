package ru.nsu.ccfit.kanterov.yall.interpreter;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 16:13:09
 */
public class ProgramContext {
    public ProgramContext(Program program) {
        this.label = 0;
        this.column = 0;
        this.finished = false;
        this.program = program;
    }

    public Variable getVariable(Integer id) {
        if (memoryMap.containsKey(id))
            return memoryMap.get(id);
        else
            throw new RuntimeException("Trying to get undefined variable id="+id);
    }

    public void setVariable(Integer id, Variable value) {
        memoryMap.put(id, value);
    }

    public void push(Variable value) {
        stack.addLast(value);
    }

    public Variable pop() {
        if (stack.isEmpty())
            throw new RuntimeException("Stack pointer exception");

        return stack.pollLast();
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isFinished() {
        return finished;
    }

    public String toString() {
        String string = "";

        for (Integer key : memoryMap.keySet()) {
            string += key+"="+memoryMap.get(key)+System.getProperty("line.separator");
        }

        return string;
    }

    public Program getProgram() {
        return program;
    }

    private boolean finished;
    private Integer label;
    private int column;
    private Program program;

    private Map<Integer, Variable> memoryMap = new HashMap<Integer, Variable>();
    private LinkedList<Variable> stack = new LinkedList<Variable>();

    @Override
    public ProgramContext clone() {
        ProgramContext context = new ProgramContext(program);                                         
        context.memoryMap = new HashMap(memoryMap);
        context.stack = new LinkedList(stack);
        context.label = new Integer(label);
        context.column = column;

        return context;
    }
}
