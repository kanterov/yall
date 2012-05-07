package ru.nsu.ccfit.kanterov.yall.datatype;

import ru.nsu.ccfit.kanterov.yall.parser.Parser;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Token;


/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 18.03.2010
 * Time: 12:57:33
 */
public class ArrayDatatype extends Datatype {
    public ArrayDatatype(Token token, Datatype key, Datatype value) throws Parser.ParseException {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayDatatype that = (ArrayDatatype) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    private Datatype key;
    private Datatype value;
}
