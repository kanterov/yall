package ru.nsu.ccfit.kanterov.yall.datatype;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 20.03.2010
 * Time: 19:23:37
 * To change this template use File | Settings | File Templates.
 */
public class UndefiniteDatatype extends Datatype {
    public UndefiniteDatatype(Datatype []possibleDatatypes) {
        this.possibleDatatypes = possibleDatatypes;
    }


    @Override
    public boolean equals(Object o) {
        for (Datatype datatype : possibleDatatypes) {
            if (datatype.equals(o))
                return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    private Datatype possibleDatatypes[];
}
