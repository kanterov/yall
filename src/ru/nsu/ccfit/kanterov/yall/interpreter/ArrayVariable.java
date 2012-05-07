package ru.nsu.ccfit.kanterov.yall.interpreter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 15.04.2010
 * Time: 16:39:10
 */
public class ArrayVariable implements Variable {
    private Variable key;
    private Variable value;

    private static Integer currentObjectId = new Integer(0);
    private int id;

    public static Map<Integer, ArrayVariable> arrayIndex = Collections.synchronizedMap(
            new HashMap<Integer, ArrayVariable>());

    private Map<Variable, Variable> data = new HashMap<Variable,  Variable>();

    public ArrayVariable(Variable key, Variable value) {
        this.key = key;
        this.value = value;

        synchronized (currentObjectId) {
            id = currentObjectId;
            currentObjectId++;
        }

        arrayIndex.put(id, this);
        data.put(key, value);
    }

    public String getType() {
        return "("+key.getType()+"->"+value.getType()+")";
    }

    public int getIntegerValue() {
        return id;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Variable getValue(Variable value) {
        if (!value.getType().equals(IntegerVariable.type)) //FIXME
            throw new RuntimeException("Not supported");

        if (!value.getType().equals(key.getType())) //FIXME
            throw new RuntimeException("Parse error");

        if (data.containsKey(value))
            return data.get(value);
        else {
            for (Variable var : data.keySet()) {
                System.out.println("Key: "+var);
            }
            throw new RuntimeException("Element not found "+value+" at "+id); //FIXME
        }
    }

    public void setValue(Variable a, Variable b) {
        if (!a.getType().equals(IntegerVariable.type) || !b.getType().equals(IntegerVariable.type)) //FIXME
            throw new RuntimeException("Not supported");

        if (!a.getType().equals(key.getType()) || !b.getType().equals(value.getType())) //FIXME
            throw new RuntimeException("Parse error");

        data.put(a, b);
    }

    public ArrayVariable clone() {
        ArrayVariable arrayVariable = new ArrayVariable(key, value);
        arrayVariable.data = new HashMap(this.data);
        return arrayVariable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayVariable that = (ArrayVariable) o;

        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public String toString() {
        String string = "(id="+id+") ";

        string += "{ "+System.getProperty("line.separator");

        for (Variable key : data.keySet()) {
            string += key+":"+data.get(key)+System.getProperty("line.separator");
        }

        string += "} "+System.getProperty("line.separator");
        return string;
    }
}
