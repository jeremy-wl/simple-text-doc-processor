package edu.neu.ccs.cs5004.assignment10.problem1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

/**
 * Created by Jeremy on 4/2/17.
 */
abstract class AbstractTextFile extends File<Text> {

    /**
     * Creates a text file given the file name.
     * The file name given must be valid.
     * @param fileName the name of the file
     */
    protected AbstractTextFile(String fileName) throws FileNotFoundException {
        super(fileName);
        this.content = readContent();
    }

    protected AbstractTextFile(String fileName, Text content) {
        super(fileName);
        this.content = content;
    }

    @Override
    protected Text getContent() {
        return content;
    }

    @Override
    protected Text readContent() {
        StringBuilder res = new StringBuilder();
        try (BufferedReader inputFile = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), "UTF-8"))) {
            String line;
            while ((line = inputFile.readLine()) != null) {
                if (res.length() != 0) {
                    res.append("\n");
                }
                res.append(line);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("*** OUPS! A file was not found : " + fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Something went wrong! : " + ioe.getMessage());
        }
        return new Text(res.toString());
    }

    @Override
    protected void writeContent() {
        try (BufferedWriter outputFile = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "UTF-8"))) {
            outputFile.write(content.getText());
        } catch (IOException ioe) {
            System.out.println("Something went wrong! : " + ioe.getMessage());
        }
    }
}
