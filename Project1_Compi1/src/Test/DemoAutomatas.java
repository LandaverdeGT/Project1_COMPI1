package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import Classes.LexicalError;
import Classes.SintacticalError;
import Classes.GestorAutomatas;
import Classes.AFD;
import Classes.AP;

import Analyze.Scanner;
import Analyze.Parser;

/**
 * Demostración de cómo crear autómatas a partir del archivo CUP
 */
public class DemoAutomatas {
    public static void main(String[] args) {
        System.out.println("=== DEMO: CREACIÓN DE AUTÓMATAS DESDE ARCHIVO CUP ===\n");
        
        try {
            // Leer el archivo de entrada que contiene las definiciones de autómatas
            String input = readInput("src/Inputs/input.txt");
            
            System.out.println("Contenido del archivo de entrada:");
            System.out.println("-----------------------------------");
            System.out.println(input);
            System.out.println("-----------------------------------\n");
            
            // Crear el scanner y parser
            Scanner scanner = new Scanner(new BufferedReader(new StringReader(input)));
            Parser parser = new Parser(scanner);
            
            // Parsear el archivo - esto creará automáticamente los autómatas
            System.out.println("Parseando archivo...\n");
            parser.parse();
            
            // Verificar errores
            if (scanner.getLexicalErrors().size() > 0) {
                System.out.println("ERRORES LÉXICOS:");
                for (LexicalError error : scanner.getLexicalErrors()) {
                    System.out.println("  " + error);
                }
                System.out.println();
            }
            
            if (parser.erroresSintacticos.size() > 0) {
                System.out.println("ERRORES SINTÁCTICOS:");
                for (SintacticalError error : parser.erroresSintacticos) {
                    System.out.println("  " + error);
                }
                System.out.println();
            }
            
            // Obtener el gestor de autómatas desde el parser
            GestorAutomatas gestor = parser.gestorAutomatas;
            
            System.out.println("=== RESULTADO: AUTÓMATAS CREADOS ===");
            gestor.mostrarEstadisticas();
            System.out.println();
            
            // Mostrar todos los autómatas creados
            gestor.verAutomatas();
            
            // Ejemplo de uso de los autómatas creados
            System.out.println("\n=== EJEMPLOS DE USO ===");
            
            // Si hay AFDs, mostrar ejemplo de simulación
            if (!gestor.getNombresAFD().isEmpty()) {
                String nombreAFD = gestor.getNombresAFD().iterator().next();
                System.out.println("\nEjemplo de simulación de AFD '" + nombreAFD + "':");
                
                // Probar algunas cadenas
                String[] cadenasTest = {"01", "10", "11", "00", "101"};
                for (String cadena : cadenasTest) {
                    gestor.simularAFD(nombreAFD, cadena);
                }
            }
            
            // Exportar autómatas a archivos DOT para visualización
            System.out.println("\nExportando autómatas a formato DOT...");
            gestor.exportarTodosDOT("outputs");
            
            System.out.println("\n=== TUTORIAL: CÓMO FUNCIONA ===");
            System.out.println("1. El archivo Parser.cup define la gramática para parsear autómatas");
            System.out.println("2. Las acciones semánticas en CUP crean objetos AFD y AP durante el parsing");
            System.out.println("3. Los autómatas creados se almacenan en el GestorAutomatas");
            System.out.println("4. Puedes usar los autómatas para simulación, exportación, etc.");
            
            System.out.println("\n=== ARCHIVOS IMPORTANTES ===");
            System.out.println("- Parser.cup: Gramática CUP con acciones semánticas");
            System.out.println("- AFD.java: Clase para Autómatas Finitos Deterministas");
            System.out.println("- AP.java: Clase para Autómatas de Pila");
            System.out.println("- GestorAutomatas.java: Gestor de colección de autómatas");
            System.out.println("- outputs/*.dot: Archivos DOT para visualizar con Graphviz");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
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
            System.err.println("Error reading file: " + e.getMessage());
            return "";
        }
    }
}