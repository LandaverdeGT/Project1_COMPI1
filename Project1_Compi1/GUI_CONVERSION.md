# GUI Conversion: NetBeans to IntelliJ IDEA

## Overview
This document describes the conversion of the NetBeans JFrame GUI to an IntelliJ IDEA compatible pure Java Swing implementation.

## Changes Made

### 1. Removed NetBeans Dependencies
- **Deleted**: `Menu.form` - NetBeans GUI form file
- **Converted**: GUI layout from `.form` XML to pure Java Swing code

### 2. Implemented Required GUI Components

#### Menu Bar Structure
- **Archivo** menu with submenu items:
  - Nuevo
  - Abrir  
  - Guardar Archivo
- **Reportes** menu with submenu items:
  - Reporte de Tokens
  - Reporte de Errores
- **Ejecutar** menu with submenu items:
  - Analizar
  - Compilar

#### Main Layout Panels
- **Entrada**: Text input area (editable)
- **Reporte**: Report display area (read-only)
- **Salida**: Output display area (read-only)

#### Top Labels
- **LDM**: Positioned at top center-left
- **DP**: Positioned at top center-right

### 3. Design Features

#### Layout Management
- `BorderLayout` for main window structure
- `GridLayout` for organizing panels and labels
- Proper spacing and padding using borders

#### Dark Theme Styling
- Dark background colors (RGB: 60, 60, 60)
- Light panel backgrounds (RGB: 80, 80, 80)  
- White text for better contrast
- Consistent color scheme throughout

#### Responsive Design
- Scroll panes for text areas
- Proper resizing behavior
- Minimum size constraints

### 4. Code Structure

#### Class Architecture
```java
public class Menu extends JFrame {
    // Component fields
    // Constructor with optional headless mode
    // Initialization methods
    // Layout setup methods
    // Styling methods
    // Event handlers
    // Getter methods for external access
}
```

#### Key Methods
- `initializeComponents()`: Creates all Swing components
- `setupLayout()`: Configures layout managers and positioning
- `setupMenuBar()`: Creates menu structure
- `setupStyling()`: Applies dark theme and styling
- `setupEventHandlers()`: Configures menu action listeners

### 5. Testing

#### Structure Validation
- Created `MenuStructureTest.java` for verifying class structure
- Tests method existence and inheritance
- Validates IntelliJ compatibility

#### Usage
```bash
# Compile the GUI
javac -cp . Views/Menu.java

# Run the application  
java -cp . Views.Menu

# Run structure tests
javac -cp . Views/MenuStructureTest.java
java -cp . Views.MenuStructureTest
```

### 6. Integration

#### Accessing Components
The `Menu` class provides getter methods for integration with other classes:
- `getEntradaTextArea()`: Access input text area
- `getReporteTextArea()`: Access report text area  
- `getSalidaTextArea()`: Access output text area

#### Event Handling
Menu actions are handled by the `MenuActionListener` inner class. Basic functionality is implemented with placeholder actions that can be extended.

## Benefits of the Conversion

1. **IntelliJ IDEA Compatibility**: Pure Java Swing code works seamlessly in IntelliJ
2. **No External Dependencies**: Removed NetBeans-specific form dependencies
3. **Better Maintainability**: Source code is now completely visible and editable
4. **Cross-Platform**: Standard Swing components work on all Java-supported platforms
5. **Customizable**: Easy to modify layout, styling, and behavior programmatically

## Future Enhancements

- Integration with existing parser and scanner classes
- File I/O operations for menu actions
- Enhanced error reporting and syntax highlighting
- Additional customization options for themes and layouts