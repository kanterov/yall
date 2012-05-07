package ru.nsu.ccfit.kanterov.yall.interpreter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 14.04.2010
 * Time: 16:08:48
 */
public class Program {
    public Program(Map<Integer, List<Operation>> code) {
        this.code = code;
    }

    boolean hasLabel(Integer label) {
        return code.containsKey(label);
    }

    List<Operation> getOperations(Integer label) {
        return Collections.unmodifiableList(code.get(label));
    }

    @Override
    public String toString() {
        String string = "";

        for (Integer key : code.keySet()) {
            List<Operation> operations = code.get(key);

            for (Operation operation : operations) {
                string += key+": ";
                string += operation;
                string += System.getProperty("line.separator");
            }
        }

        return string;
    }

    private Map<Integer, List<Operation>> code;
}
