package ru.nsu.ccfit.kanterov.yall.interpreter;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 15.04.2010
 * Time: 13:12:01
 */
public class IntegerVariable implements Variable {
    private int value;
    public static String type = "Integer";

    public IntegerVariable(int value) {
        this.value = value;
    }

    public int getIntegerValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public Variable clone() {
        return new IntegerVariable(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerVariable that = (IntegerVariable) o;

        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "IntegerVariable{" +
                "value=" + value +
                '}';
    }
}