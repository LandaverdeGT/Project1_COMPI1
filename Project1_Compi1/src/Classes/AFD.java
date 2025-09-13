package Classes;

import java.util.*;

/**
 * Clase que representa un Autómata Finito Determinista (AFD)
 */
public class AFD {
    private String nombre;
    private Set<String> estados;
    private Set<String> alfabeto;
    private String estadoInicial;
    private Set<String> estadosAceptacion;
    private Map<String, Map<String, String>> transiciones;

    public AFD(String nombre) {
        this.nombre = nombre;
        this.estados = new HashSet<>();
        this.alfabeto = new HashSet<>();
        this.estadosAceptacion = new HashSet<>();
        this.transiciones = new HashMap<>();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<String> getEstados() {
        return estados;
    }

    public void setEstados(Set<String> estados) {
        this.estados = estados;
    }

    public Set<String> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(Set<String> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Set<String> getEstadosAceptacion() {
        return estadosAceptacion;
    }

    public void setEstadosAceptacion(Set<String> estadosAceptacion) {
        this.estadosAceptacion = estadosAceptacion;
    }

    public Map<String, Map<String, String>> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(Map<String, Map<String, String>> transiciones) {
        this.transiciones = transiciones;
    }

    // Métodos para agregar elementos
    public void agregarEstado(String estado) {
        this.estados.add(estado);
    }

    public void agregarSimbolo(String simbolo) {
        this.alfabeto.add(simbolo);
    }

    public void agregarEstadoAceptacion(String estado) {
        this.estadosAceptacion.add(estado);
    }

    public void agregarTransicion(String estadoOrigen, String simbolo, String estadoDestino) {
        transiciones.computeIfAbsent(estadoOrigen, k -> new HashMap<>()).put(simbolo, estadoDestino);
    }

    /**
     * Simula el autómata con una cadena de entrada
     */
    public boolean procesar(String cadena) {
        String estadoActual = estadoInicial;
        
        for (char c : cadena.toCharArray()) {
            String simbolo = String.valueOf(c);
            
            if (!alfabeto.contains(simbolo)) {
                return false; // Símbolo no está en el alfabeto
            }
            
            Map<String, String> transicionesEstado = transiciones.get(estadoActual);
            if (transicionesEstado == null || !transicionesEstado.containsKey(simbolo)) {
                return false; // No hay transición definida
            }
            
            estadoActual = transicionesEstado.get(simbolo);
        }
        
        return estadosAceptacion.contains(estadoActual);
    }

    /**
     * Genera representación del autómata en formato DOT para Graphviz
     */
    public String generarDOT() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph AFD_").append(nombre).append(" {\n");
        dot.append("    rankdir=LR;\n");
        dot.append("    node [shape = doublecircle]; ");
        
        // Estados de aceptación
        for (String estado : estadosAceptacion) {
            dot.append(estado).append(" ");
        }
        dot.append(";\n");
        
        dot.append("    node [shape = circle];\n");
        
        // Estado inicial
        dot.append("    \"\" [shape=none];\n");
        dot.append("    \"\" -> ").append(estadoInicial).append(";\n");
        
        // Transiciones
        for (Map.Entry<String, Map<String, String>> entrada : transiciones.entrySet()) {
            String estadoOrigen = entrada.getKey();
            for (Map.Entry<String, String> transicion : entrada.getValue().entrySet()) {
                String simbolo = transicion.getKey();
                String estadoDestino = transicion.getValue();
                dot.append("    ").append(estadoOrigen)
                   .append(" -> ").append(estadoDestino)
                   .append(" [label=\"").append(simbolo).append("\"];\n");
            }
        }
        
        dot.append("}\n");
        return dot.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AFD: ").append(nombre).append("\n");
        sb.append("Estados: ").append(estados).append("\n");
        sb.append("Alfabeto: ").append(alfabeto).append("\n");
        sb.append("Estado Inicial: ").append(estadoInicial).append("\n");
        sb.append("Estados de Aceptación: ").append(estadosAceptacion).append("\n");
        sb.append("Transiciones:\n");
        
        for (Map.Entry<String, Map<String, String>> entrada : transiciones.entrySet()) {
            String estado = entrada.getKey();
            for (Map.Entry<String, String> transicion : entrada.getValue().entrySet()) {
                String simbolo = transicion.getKey();
                String destino = transicion.getValue();
                sb.append("  δ(").append(estado).append(", ").append(simbolo)
                  .append(") = ").append(destino).append("\n");
            }
        }
        
        return sb.toString();
    }
}