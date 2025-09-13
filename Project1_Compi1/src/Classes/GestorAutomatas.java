package Classes;

import java.util.*;
import java.io.*;

/**
 * Gestor de autómatas que almacena y maneja AFDs y APs
 */
public class GestorAutomatas {
    private Map<String, AFD> afds;
    private Map<String, AP> aps;
    
    public GestorAutomatas() {
        this.afds = new HashMap<>();
        this.aps = new HashMap<>();
    }
    
    // Métodos para AFD
    public void agregarAFD(AFD afd) {
        afds.put(afd.getNombre(), afd);
    }
    
    public AFD obtenerAFD(String nombre) {
        return afds.get(nombre);
    }
    
    public Set<String> getNombresAFD() {
        return afds.keySet();
    }
    
    // Métodos para AP
    public void agregarAP(AP ap) {
        aps.put(ap.getNombre(), ap);
    }
    
    public AP obtenerAP(String nombre) {
        return aps.get(nombre);
    }
    
    public Set<String> getNombresAP() {
        return aps.keySet();
    }
    
    /**
     * Muestra todos los autómatas registrados
     */
    public void verAutomatas() {
        System.out.println("=== AUTÓMATAS REGISTRADOS ===");
        
        if (!afds.isEmpty()) {
            System.out.println("\n--- Autómatas Finitos Deterministas (AFD) ---");
            for (AFD afd : afds.values()) {
                System.out.println(afd);
                System.out.println("---");
            }
        }
        
        if (!aps.isEmpty()) {
            System.out.println("\n--- Autómatas de Pila (AP) ---");
            for (AP ap : aps.values()) {
                System.out.println(ap);
                System.out.println("---");
            }
        }
        
        if (afds.isEmpty() && aps.isEmpty()) {
            System.out.println("No hay autómatas registrados.");
        }
    }
    
    /**
     * Describe un autómata específico
     */
    public void describirAutomata(String nombre) {
        AFD afd = afds.get(nombre);
        AP ap = aps.get(nombre);
        
        if (afd != null) {
            System.out.println("Descripción del AFD:");
            System.out.println(afd);
        } else if (ap != null) {
            System.out.println("Descripción del AP:");
            System.out.println(ap);
        } else {
            System.out.println("Autómata '" + nombre + "' no encontrado.");
        }
    }
    
    /**
     * Exporta todos los autómatas a archivos DOT
     */
    public void exportarTodosDOT(String directorio) {
        File dir = new File(directorio);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        // Exportar AFDs
        for (AFD afd : afds.values()) {
            try {
                FileWriter writer = new FileWriter(new File(dir, afd.getNombre() + "_AFD.dot"));
                writer.write(afd.generarDOT());
                writer.close();
                System.out.println("AFD '" + afd.getNombre() + "' exportado a " + 
                                 afd.getNombre() + "_AFD.dot");
            } catch (IOException e) {
                System.err.println("Error exportando AFD '" + afd.getNombre() + "': " + 
                                 e.getMessage());
            }
        }
        
        // Exportar APs
        for (AP ap : aps.values()) {
            try {
                FileWriter writer = new FileWriter(new File(dir, ap.getNombre() + "_AP.dot"));
                writer.write(ap.generarDOT());
                writer.close();
                System.out.println("AP '" + ap.getNombre() + "' exportado a " + 
                                 ap.getNombre() + "_AP.dot");
            } catch (IOException e) {
                System.err.println("Error exportando AP '" + ap.getNombre() + "': " + 
                                 e.getMessage());
            }
        }
    }
    
    /**
     * Simula un AFD específico con una cadena
     */
    public boolean simularAFD(String nombreAFD, String cadena) {
        AFD afd = afds.get(nombreAFD);
        if (afd == null) {
            System.err.println("AFD '" + nombreAFD + "' no encontrado.");
            return false;
        }
        
        boolean resultado = afd.procesar(cadena);
        System.out.println("Simulación de AFD '" + nombreAFD + 
                          "' con cadena '" + cadena + "': " + 
                          (resultado ? "ACEPTADA" : "RECHAZADA"));
        return resultado;
    }
    
    /**
     * Obtiene estadísticas de los autómatas
     */
    public void mostrarEstadisticas() {
        System.out.println("=== ESTADÍSTICAS ===");
        System.out.println("Total AFDs: " + afds.size());
        System.out.println("Total APs: " + aps.size());
        System.out.println("Total autómatas: " + (afds.size() + aps.size()));
        
        if (!afds.isEmpty()) {
            System.out.println("\nAFDs registrados:");
            for (String nombre : afds.keySet()) {
                AFD afd = afds.get(nombre);
                System.out.println("  - " + nombre + " (" + afd.getEstados().size() + 
                                 " estados, " + afd.getAlfabeto().size() + " símbolos)");
            }
        }
        
        if (!aps.isEmpty()) {
            System.out.println("\nAPs registrados:");
            for (String nombre : aps.keySet()) {
                AP ap = aps.get(nombre);
                System.out.println("  - " + nombre + " (" + ap.getEstados().size() + 
                                 " estados, " + ap.getAlfabeto().size() + " símbolos de entrada, " +
                                 ap.getSimbolosPila().size() + " símbolos de pila)");
            }
        }
    }
}