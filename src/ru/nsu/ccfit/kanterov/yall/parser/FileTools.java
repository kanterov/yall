package ru.nsu.ccfit.kanterov.yall.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Gleb Kanterov
 * Date: 13.03.2010
 * Time: 21:39:04
 */
public class FileTools {

    public static String getContents(File file) {
        StringBuilder contents = new StringBuilder();

        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            try {
                String line = null; //not declared within while loop

                while ((line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            }

            finally {
                input.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return contents.toString();
    }
}
