package Test;
import java.io.*;

import Analyze.Scanner;
import Classes.LexicalError;
import java_cup.runtime.Symbol;

public class PruebaS {
    public static void main(String [] args) throws Exception {
        String[] tokensNames = {
                "EOF",
                "error",
                "TK_Plus",
                "TK_Number",
                "TK_Point",
                "TK_LessT",
                "TK_GreatherT",
                "TK_Equal",
                "TK_KW_AFD",
                "TK_KW_Nombre",
                "TK_String",
                "TK_KW_NumberStates",
                "TK_KW_Alphabet",
                "TK_KW_InitialState",
                "TK_KW_AcceptStates",
                "TK_ID",
                "TK_Left_Brace",
                "TK_Right_Brace",
                "TK_Comma",
                "TK_SemiColon",
                "TK_OR",
                "TK_KW_Tranciciones",
                "TK_Colon",
                "TK_Slash",
                "TK_Arrow",
                "TK_KW_AP"
        };
        try {
            String input = readInput("src/Inputs/input.txt");
            Scanner scanner = new Scanner(
                    new BufferedReader(new StringReader(input))
            );
            Symbol token = null;
            System.out.println("TOKEN"+ " ".repeat(35 - "TOKEN".length()) + "LINE" + " ".repeat(
                    6 - "LINE".length()) + "COL" + " ".repeat(6 - "COL".length()) + "TYPE" + " ".repeat(6 - "TYPE".length()));
            do {
                token = scanner.next_token();
                System.out.println(token.value + " ".repeat(35 - String.valueOf(token.value).length()) + token.left + " ".repeat(20 - String.valueOf(token.value).length()) + token.right + " ".repeat(35 - String.valueOf(token.value).length()) + tokensNames[token.sym] );
            } while (token.value != null);
            if (scanner.getLexicalErrors().size() > 0) {
                System.out.println("\nLexical Errors:");
                for (LexicalError error : scanner.getLexicalErrors()) {
                    System.out.println(error);
                }
            }
            else {
                System.out.println("\nNo lexical errors found.");
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String readInput(String path) {
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String text = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                text += line + "\n";
            }
            br.close();
            fis.close();
            return text;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}