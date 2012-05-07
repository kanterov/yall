package ru.nsu.ccfit.kanterov.yall.parser;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 25.03.2010
 * Time: 10:31:53
 */
public class TreePrinter {
    public static void printTree(PrintStream writer, Tree tree) {
        printTree(writer, tree, "");
    }

    public static void printTree(PrintStream writer, Tree tree, String prefix) {
        writer.println(prefix+tree);
        
        for (Tree child : tree.getChildren()) {
            printTree(writer, child, prefix+"\t");
        }
    }
}
