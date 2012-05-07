package ru.nsu.ccfit.kanterov.yall.parser;

import ru.nsu.ccfit.kanterov.yall.datatype.Datatype;
import ru.nsu.ccfit.kanterov.yall.translator.ProgramBuilder;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 13.03.2010
 * Time: 20:00:28
 */

public interface Tree {
    List<Tree> getChildren();
    boolean isDefinable();
    void build(ProgramBuilder programBuilder);
//    Datatype getDatatype();
}
