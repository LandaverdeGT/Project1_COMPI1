public class Main {
    public static void main(String[] args) {
        System.out.println("=== PROYECTO DE AUTÓMATAS CON CUP ===");
        System.out.println("Proyecto que demuestra cómo crear autómatas desde archivo CUP\n");
        
        System.out.println("Para ejecutar la demostración:");
        System.out.println("  java -cp \"lib/*:.\" Test.DemoAutomatas\n");
        
        System.out.println("Para ejecutar las pruebas:");
        System.out.println("  java -cp \"lib/*:.\" Test.PruebaP\n");
        
        System.out.println("Archivos importantes:");
        System.out.println("  - Parser.cup: Gramática CUP con acciones semánticas");
        System.out.println("  - AFD.java: Clase para Autómatas Finitos");
        System.out.println("  - AP.java: Clase para Autómatas de Pila");
        System.out.println("  - GestorAutomatas.java: Administrador de autómatas");
        System.out.println("  - README_AUTOMATAS.md: Documentación completa");
        
        // Ejecutar la demo automáticamente
        System.out.println("\n" + "=".repeat(50));
        System.out.println("EJECUTANDO DEMO AUTOMÁTICAMENTE...");
        System.out.println("=".repeat(50));
        
        try {
            Test.DemoAutomatas.main(new String[]{});
        } catch (Exception e) {
            System.err.println("Error ejecutando demo: " + e.getMessage());
        }
    }
}