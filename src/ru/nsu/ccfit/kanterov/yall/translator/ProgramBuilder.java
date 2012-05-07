package ru.nsu.ccfit.kanterov.yall.translator;

import ru.nsu.ccfit.kanterov.yall.interpreter.Operation;
import ru.nsu.ccfit.kanterov.yall.interpreter.Program;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 08.05.2010
 * Time: 21:43:19
 */
public class ProgramBuilder {
    private Map<Integer, List<Operation>> code = new HashMap<Integer, List<Operation>>();
    private Program program = new Program(code);

    private static int labelCounter = 0;

    private Integer label = 0;
    private int currentVariableId = 0;

    public Program getProgram() {
        return program;
    }

    public Integer getCurrentLabel() {
        return label;
    }

    public void setCurrentLabel(int label) {
        this.label = label;
    }

    public Integer makeNextLabel() {
        return label = ++labelCounter;
    }

    public void addOperation(Operation operation) {
        addOperation(label, operation);                        
    }

    public int getVariableId(String name) {
        if (variableIds.containsKey(name))
            return variableIds.get(name);
        else {
            int id = currentVariableId++;
            variableIds.put(name, id);
            return id;
        }
    }

    private Map<String, Integer> variableIds = new HashMap<String, Integer>();

    public void addOperation(Integer addLabel, Operation operation) {
        List<Operation> operationList;

        if (code.containsKey(addLabel))
            operationList = code.get(addLabel);
        else {
            operationList = new LinkedList();
            code.put(addLabel, operationList);
        }

        operationList.add(operation);
    }
}
