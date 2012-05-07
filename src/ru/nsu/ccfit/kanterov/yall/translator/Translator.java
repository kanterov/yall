package ru.nsu.ccfit.kanterov.yall.translator;

import ru.nsu.ccfit.kanterov.yall.parser.FileTools;
import ru.nsu.ccfit.kanterov.yall.parser.Parser;
import ru.nsu.ccfit.kanterov.yall.parser.Tree;
import ru.nsu.ccfit.kanterov.yall.parser.TreePrinter;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Token;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Tokenizer;

import java.io.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 10.05.2010
 * Time: 0:02:11
 */
public class Translator {
    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.out.println("Usage: <input> <output>");
            return;
        }

        String input = args[0];
        String output;

        if (args.length == 2) {
            output = args[1];
        }
        else {
            File inputFile = new File(input);
            output = inputFile.getName() + ".vm";
        }

        try {
            String code = FileTools.getContents(new File(input));
            List<Token> tokenList = Tokenizer.tokenizeString(code);
            List<Tree> forest = Parser.parseCode(tokenList);

            for (Token token : tokenList) {
                System.out.print(token+" ");
            }

            System.out.println();

            for (Tree tree : forest) {
                ProgramBuilder programBuilder = new ProgramBuilder();
                TreePrinter.printTree(System.out, tree);

                tree.build(programBuilder);

                Writer writer = new FileWriter(new File(output));
                writer.write(programBuilder.getProgram().toString());
                writer.flush();
            }
        } catch (Parser.ParseException e) {
            System.out.println(e);
        }
    }
}
