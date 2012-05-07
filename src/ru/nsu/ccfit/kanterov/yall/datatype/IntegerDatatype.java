package ru.nsu.ccfit.kanterov.yall.datatype;

import ru.nsu.ccfit.kanterov.yall.datatype.Datatype;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 13.03.2010
 * Time: 20:14:19
 * To change this template use File | Settings | File Templates.
 */
public class IntegerDatatype extends Datatype {
    private IntegerDatatype() {
    }

    private static final Datatype datatype = new IntegerDatatype();
    private static final String value = "integer";

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    static public Datatype getInstance() {
        return datatype;
    }

}
