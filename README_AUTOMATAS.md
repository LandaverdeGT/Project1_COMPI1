# Cómo Crear Autómatas desde el Archivo CUP

Este proyecto demuestra cómo crear autómatas (AFD y AP) a partir de un archivo de gramática CUP (Constructor of Useful Parsers).

## ¿Qué es esto?

El archivo `Parser.cup` define una gramática que puede parsear definiciones de autómatas escritas en un formato específico. Cuando se parsea un archivo de entrada, **automáticamente se crean objetos de autómatas** que puedes usar programáticamente.

## Archivos Importantes

### 1. Parser.cup - La Gramática
```cup
// Define cómo parsear autómatas AFD y AP
AFD ::= AFDI:nombre ATRIBUTES_LIST:attrs TRANSITION:trans AFDF
      {:
        AFD afd = new AFD(nombre);  // ← CREA EL AUTÓMATA AQUÍ
        // ... código para procesar atributos ...
        RESULT = afd;
      :};
```

### 2. Clases de Autómatas
- **`AFD.java`** - Autómata Finito Determinista
- **`AP.java`** - Autómata de Pila  
- **`GestorAutomatas.java`** - Administra colección de autómatas

### 3. Demo
- **`DemoAutomatas.java`** - Muestra cómo usar todo el sistema

## Cómo Funciona

### Paso 1: Definir Autómatas en el Archivo de Entrada

```xml
<AFD Nombre="AFD_1">
N = {S, H, B, C};        // Estados
T = {0, 1};              // Alfabeto  
I = {S};                 // Estado Inicial
A = {B, C};              // Estados de Aceptación
Transiciones:
S -> 1, H | 0, B ;
H -> 1, H | 0, B ;
B -> 0, C | 1, H ;
C -> 1, H;
</AFD>
```

### Paso 2: Ejecutar el Parser

```java
// Leer archivo
String input = readInput("src/Inputs/input.txt");

// Crear scanner y parser
Scanner scanner = new Scanner(new BufferedReader(new StringReader(input)));
Parser parser = new Parser(scanner);

// ¡Parsear crea los autómatas automáticamente!
parser.parse();

// Obtener autómatas creados
GestorAutomatas gestor = parser.gestorAutomatas;
```

### Paso 3: Usar los Autómatas Creados

```java
// Ver todos los autómatas
gestor.verAutomatas();

// Simular un AFD
gestor.simularAFD("AFD_1", "101");

// Exportar a formato DOT
gestor.exportarTodosDOT("outputs");
```

## Ejecutar la Demo

```bash
# Compilar
javac -cp "lib/*:src" -d . src/Test/DemoAutomatas.java

# Ejecutar
java -cp "lib/*:." Test.DemoAutomatas
```

## Resultado

La demo muestra:

1. **Parsing exitoso** del archivo de entrada
2. **Creación automática** de objetos AFD/AP
3. **Estadísticas** de autómatas creados
4. **Simulación** de cadenas en AFDs
5. **Exportación** a formato DOT para visualización

## Ventajas de este Enfoque

✅ **Automático**: Los autómatas se crean durante el parsing  
✅ **Programático**: Puedes usar los autómatas en tu código  
✅ **Extensible**: Fácil agregar nuevas funcionalidades  
✅ **Visualizable**: Exporta a DOT para Graphviz  
✅ **Reutilizable**: Los autómatas se almacenan en memoria  

## Archivos Generados

- **`outputs/*.dot`** - Archivos para visualizar con Graphviz
- **`Parser.java`** - Parser generado por CUP
- **`Terminal.java`** - Símbolos terminales generados

## Próximos Pasos

1. **Mejorar el procesamiento de transiciones** - Actualmente básico
2. **Agregar más validaciones** - Verificar consistencia de autómatas
3. **Implementar más algoritmos** - Minimización, equivalencia, etc.
4. **Interfaz gráfica** - Para visualizar autómatas directamente

---

**Este sistema demuestra el poder de CUP para crear DSLs (Domain Specific Languages) que no solo validan sintaxis, sino que construyen estructuras de datos útiles durante el parsing.**