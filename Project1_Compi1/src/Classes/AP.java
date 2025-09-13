package Classes;

import java.util.*;

/**
 * Clase que representa un Autómata de Pila (AP)
 */
public class AP {
    private String nombre;
    private Set<String> estados;
    private Set<String> alfabeto;
    private Set<String> simbolosPila;
    private String estadoInicial;
    private Set<String> estadosAceptacion;
    private List<TransicionAP> transiciones;

    public AP(String nombre) {
        this.nombre = nombre;
        this.estados = new HashSet<>();
        this.alfabeto = new HashSet<>();
        this.simbolosPila = new HashSet<>();
        this.estadosAceptacion = new HashSet<>();
        this.transiciones = new ArrayList<>();
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

    public Set<String> getSimbolosPila() {
        return simbolosPila;
    }

    public void setSimbolosPila(Set<String> simbolosPila) {
        this.simbolosPila = simbolosPila;
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

    public List<TransicionAP> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(List<TransicionAP> transiciones) {
        this.transiciones = transiciones;
    }

    // Métodos para agregar elementos
    public void agregarEstado(String estado) {
        this.estados.add(estado);
    }

    public void agregarSimbolo(String simbolo) {
        this.alfabeto.add(simbolo);
    }

    public void agregarSimboloPila(String simbolo) {
        this.simbolosPila.add(simbolo);
    }

    public void agregarEstadoAceptacion(String estado) {
        this.estadosAceptacion.add(estado);
    }

    public void agregarTransicion(String estadoOrigen, String simboloEntrada, String simboloPila, 
                                String estadoDestino, String accionPila) {
        TransicionAP transicion = new TransicionAP(estadoOrigen, simboloEntrada, simboloPila, 
                                                  estadoDestino, accionPila);
        transiciones.add(transicion);
    }

    /**
     * Genera representación del autómata en formato DOT para Graphviz
     */
    public String generarDOT() {
        StringBuilder dot = new StringBuilder();
        dot.append("digraph AP_").append(nombre).append(" {\n");
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
        for (TransicionAP transicion : transiciones) {
            String label = transicion.getSimboloEntrada() + ", " + 
                          transicion.getSimboloPila() + " / " + 
                          transicion.getAccionPila();
            dot.append("    ").append(transicion.getEstadoOrigen())
               .append(" -> ").append(transicion.getEstadoDestino())
               .append(" [label=\"").append(label).append("\"];\n");
        }
        
        dot.append("}\n");
        return dot.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AP: ").append(nombre).append("\n");
        sb.append("Estados: ").append(estados).append("\n");
        sb.append("Alfabeto: ").append(alfabeto).append("\n");
        sb.append("Símbolos de Pila: ").append(simbolosPila).append("\n");
        sb.append("Estado Inicial: ").append(estadoInicial).append("\n");
        sb.append("Estados de Aceptación: ").append(estadosAceptacion).append("\n");
        sb.append("Transiciones:\n");
        
        for (TransicionAP transicion : transiciones) {
            sb.append("  δ(").append(transicion.getEstadoOrigen())
              .append(", ").append(transicion.getSimboloEntrada())
              .append(", ").append(transicion.getSimboloPila())
              .append(") = (").append(transicion.getEstadoDestino())
              .append(", ").append(transicion.getAccionPila()).append(")\n");
        }
        
        return sb.toString();
    }
}

/**
 * Clase que representa una transición del Autómata de Pila
 */
class TransicionAP {
    private String estadoOrigen;
    private String simboloEntrada;
    private String simboloPila;
    private String estadoDestino;
    private String accionPila;

    public TransicionAP(String estadoOrigen, String simboloEntrada, String simboloPila,
                       String estadoDestino, String accionPila) {
        this.estadoOrigen = estadoOrigen;
        this.simboloEntrada = simboloEntrada;
        this.simboloPila = simboloPila;
        this.estadoDestino = estadoDestino;
        this.accionPila = accionPila;
    }

    // Getters
    public String getEstadoOrigen() {
        return estadoOrigen;
    }

    public String getSimboloEntrada() {
        return simboloEntrada;
    }

    public String getSimboloPila() {
        return simboloPila;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public String getAccionPila() {
        return accionPila;
    }

    @Override
    public String toString() {
        return "(" + estadoOrigen + ", " + simboloEntrada + ", " + simboloPila + 
               ") -> (" + estadoDestino + ", " + accionPila + ")";
    }
}