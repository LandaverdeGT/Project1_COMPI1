package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import Classes.LexicalError;

import Analyze.Scanner;
import Analyze.Parser;
import Classes.SintacticalError;

public class PruebaP {
    public static void main(String[] args) {
        try {
            String input = readInput("src/Inputs/input.txt");
            Scanner scanner = new Scanner(
                    new BufferedReader(new StringReader(input))
            );
            Parser parser = new Parser(scanner);
            parser.parse();
            if (scanner.getLexicalErrors().size() > 0) {
                System.out.println("\nLexical Errors:");
                for (LexicalError error : scanner.getLexicalErrors()) {
                    System.out.println(error);
                }
            } else {
                System.out.println("No lexical errors found.");
            }
            if (parser.erroresSintacticos.size() > 0) {
                System.out.println("Sintactical Errors:");
                for (int i = 0; i < parser.erroresSintacticos.size(); i++) {
                    System.out.println(parser.erroresSintacticos.get(i));
                }
            } else {
                System.out.println("No sintactical errors found.");
            }

            System.out.println("Parsing completed successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String readInput(String path) {
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append("\n");
            }
            br.close();
            fis.close();
            return text.toString();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return "";
        }
    }
}