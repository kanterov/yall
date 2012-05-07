package ru.nsu.ccfit.kanterov.yall.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Lexem;
import ru.nsu.ccfit.kanterov.yall.tokenizer.Token;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 13.03.2010
 * Time: 18:02:05
 */
public class Tokenizer {
    public static List<Token> tokenizeString(String string) {
        StringTokenizer tokenizer = new StringTokenizer (string, " \t\n()", true);
        List<Token> tokenList = new ArrayList<Token>(tokenizer.countTokens());

        int currentColumn = 0;
        int currentLine = 0;
        boolean comment = false;

        while(tokenizer.hasMoreTokens()) {
            String value = tokenizer.nextToken();

            if (currentColumn == 0 && value.startsWith("#")) {
                comment = true;
            }

            if (!value.matches("\\s+")) {
                if (!comment) {
                    Lexem lexem = new Lexem(value, currentColumn, currentLine);
                    Token token = new Token(lexem);
                    tokenList.add(token);
                    }
            }
            else {
                if (value.equals("\n")) {
                    currentColumn = 0;
                    currentLine++;
                    comment = false;
                    continue;
                }
            }

            currentColumn += value.length();
        }

        return tokenList;
    }

    public static void main(String []args) {
        if (args.length == 0)
            System.out.println("Usage: <code1> <code2> ...");

        for (String arg : args) {
            List<Token> tokenList = tokenizeString(arg);

            for (Token token : tokenList) {
                System.out.print(token+" ");
            }

            System.out.println();
        }
    }
}
