package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    // Main panels
    private JPanel mainPanel;
    private JPanel entradaPanel;
    private JPanel reportePanel;
    private JPanel salidaPanel;
    
    // Text areas
    private JTextArea entradaTextArea;
    private JTextArea reporteTextArea;
    private JTextArea salidaTextArea;
    
    // Menu components
    private JMenuBar menuBar;
    private JMenu archivoMenu;
    private JMenu reportesMenu;
    private JMenu ejecutarMenu;
    
    // Top labels
    private JLabel ldmLabel;
    private JLabel dpLabel;
    
    public Menu() {
        this(true);
    }
    
    public Menu(boolean showGUI) {
        if (showGUI) {
            initializeComponents();
            setupLayout();
            setupMenuBar();
            setupStyling();
            setupEventHandlers();
            
            setTitle("Project1 COMPI1 - Compiler Interface");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1000, 700);
            setLocationRelativeTo(null);
        } else {
            // For testing purposes - initialize minimal components
            initializeComponentsHeadless();
        }
    }
    
    
    private void initializeComponentsHeadless() {
        // Initialize text areas only for testing
        entradaTextArea = new JTextArea(15, 30);
        reporteTextArea = new JTextArea(15, 30);
        salidaTextArea = new JTextArea(15, 30);
        
        // Initialize menu bar for testing
        menuBar = new JMenuBar();
        archivoMenu = new JMenu("Archivo");
        reportesMenu = new JMenu("Reportes");
        ejecutarMenu = new JMenu("Ejecutar");
        
        // Add basic menu items
        archivoMenu.add(new JMenuItem("Nuevo"));
        archivoMenu.add(new JMenuItem("Abrir"));
        archivoMenu.add(new JMenuItem("Guardar Archivo"));
        
        reportesMenu.add(new JMenuItem("Reporte de Tokens"));
        reportesMenu.add(new JMenuItem("Reporte de Errores"));
        
        ejecutarMenu.add(new JMenuItem("Analizar"));
        ejecutarMenu.add(new JMenuItem("Compilar"));
        
        menuBar.add(archivoMenu);
        menuBar.add(reportesMenu);
        menuBar.add(ejecutarMenu);
    }
    
    private void initializeComponents() {
        // Initialize main panel
        mainPanel = new JPanel(new BorderLayout());
        
        // Initialize text areas with scroll panes
        entradaTextArea = new JTextArea(15, 30);
        reporteTextArea = new JTextArea(15, 30);
        salidaTextArea = new JTextArea(15, 30);
        
        entradaTextArea.setEditable(true);
        reporteTextArea.setEditable(false);
        salidaTextArea.setEditable(false);
        
        // Initialize panels
        entradaPanel = createTitledPanel("Entrada", entradaTextArea);
        reportePanel = createTitledPanel("Reporte", reporteTextArea);
        salidaPanel = createTitledPanel("Salida", salidaTextArea);
        
        // Initialize top labels
        ldmLabel = new JLabel("LDM", SwingConstants.CENTER);
        dpLabel = new JLabel("DP", SwingConstants.CENTER);
    }
    
    private JPanel createTitledPanel(String title, JTextArea textArea) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1), 
            title, 
            0, 
            0, 
            new Font("Arial", Font.BOLD, 12), 
            Color.WHITE
        ));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Create top panel for LDM and DP labels
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(ldmLabel);
        topPanel.add(dpLabel);
        
        // Create center panel for the three main areas
        JPanel centerPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(entradaPanel);
        centerPanel.add(reportePanel);
        centerPanel.add(salidaPanel);
        
        // Add components to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void setupMenuBar() {
        menuBar = new JMenuBar();
        
        // Create Archivo menu
        archivoMenu = new JMenu("Archivo");
        JMenuItem nuevoItem = new JMenuItem("Nuevo");
        JMenuItem abrirItem = new JMenuItem("Abrir");
        JMenuItem guardarItem = new JMenuItem("Guardar Archivo");
        
        archivoMenu.add(nuevoItem);
        archivoMenu.add(abrirItem);
        archivoMenu.addSeparator();
        archivoMenu.add(guardarItem);
        
        // Create Reportes menu
        reportesMenu = new JMenu("Reportes");
        JMenuItem reporteTokensItem = new JMenuItem("Reporte de Tokens");
        JMenuItem reporteErroresItem = new JMenuItem("Reporte de Errores");
        
        reportesMenu.add(reporteTokensItem);
        reportesMenu.add(reporteErroresItem);
        
        // Create Ejecutar menu
        ejecutarMenu = new JMenu("Ejecutar");
        JMenuItem analizarItem = new JMenuItem("Analizar");
        JMenuItem compilarItem = new JMenuItem("Compilar");
        
        ejecutarMenu.add(analizarItem);
        ejecutarMenu.add(compilarItem);
        
        // Add menus to menu bar
        menuBar.add(archivoMenu);
        menuBar.add(reportesMenu);
        menuBar.add(ejecutarMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void setupStyling() {
        // Set dark theme colors
        Color darkBackground = new Color(60, 60, 60);
        Color lightBackground = new Color(80, 80, 80);
        Color textColor = Color.WHITE;
        Color borderColor = Color.GRAY;
        
        // Style main panel
        mainPanel.setBackground(darkBackground);
        
        // Style text areas
        styleTextArea(entradaTextArea, lightBackground, textColor);
        styleTextArea(reporteTextArea, lightBackground, textColor);
        styleTextArea(salidaTextArea, lightBackground, textColor);
        
        // Style panels
        stylePanel(entradaPanel, darkBackground);
        stylePanel(reportePanel, darkBackground);
        stylePanel(salidaPanel, darkBackground);
        
        // Style labels
        ldmLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ldmLabel.setForeground(textColor);
        dpLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dpLabel.setForeground(textColor);
        
        // Style menu bar
        menuBar.setBackground(lightBackground);
        menuBar.setBorderPainted(false);
        
        styleMenu(archivoMenu, lightBackground, textColor);
        styleMenu(reportesMenu, lightBackground, textColor);
        styleMenu(ejecutarMenu, lightBackground, textColor);
    }
    
    private void styleTextArea(JTextArea textArea, Color background, Color foreground) {
        textArea.setBackground(background);
        textArea.setForeground(foreground);
        textArea.setCaretColor(foreground);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
    
    private void stylePanel(JPanel panel, Color background) {
        panel.setBackground(background);
    }
    
    private void styleMenu(JMenu menu, Color background, Color foreground) {
        menu.setBackground(background);
        menu.setForeground(foreground);
        menu.setOpaque(true);
        
        for (int i = 0; i < menu.getItemCount(); i++) {
            JMenuItem item = menu.getItem(i);
            if (item != null) {
                item.setBackground(background);
                item.setForeground(foreground);
                item.setOpaque(true);
            }
        }
    }
    
    private void setupEventHandlers() {
        // Add action listeners for menu items
        // These can be expanded with actual functionality later
        
        // Archivo menu actions
        for (int i = 0; i < archivoMenu.getItemCount(); i++) {
            JMenuItem item = archivoMenu.getItem(i);
            if (item != null) {
                item.addActionListener(new MenuActionListener(item.getText()));
            }
        }
        
        // Reportes menu actions
        for (int i = 0; i < reportesMenu.getItemCount(); i++) {
            JMenuItem item = reportesMenu.getItem(i);
            if (item != null) {
                item.addActionListener(new MenuActionListener(item.getText()));
            }
        }
        
        // Ejecutar menu actions
        for (int i = 0; i < ejecutarMenu.getItemCount(); i++) {
            JMenuItem item = ejecutarMenu.getItem(i);
            if (item != null) {
                item.addActionListener(new MenuActionListener(item.getText()));
            }
        }
    }
    
    // Inner class for handling menu actions
    private class MenuActionListener implements ActionListener {
        private String actionCommand;
        
        public MenuActionListener(String actionCommand) {
            this.actionCommand = actionCommand;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // Placeholder for menu actions
            System.out.println("Menu action: " + actionCommand);
            
            // Example actions - these can be expanded with actual functionality
            switch (actionCommand) {
                case "Nuevo":
                    entradaTextArea.setText("");
                    reporteTextArea.setText("");
                    salidaTextArea.setText("");
                    break;
                case "Abrir":
                    // Add file opening logic here
                    break;
                case "Guardar Archivo":
                    // Add file saving logic here
                    break;
                case "Analizar":
                    reporteTextArea.setText("Analizando...\n");
                    // Add analysis logic here
                    break;
                case "Compilar":
                    salidaTextArea.setText("Compilando...\n");
                    // Add compilation logic here
                    break;
            }
        }
    }
    
    // Getter methods for accessing components from other classes
    public JTextArea getEntradaTextArea() {
        return entradaTextArea;
    }
    
    public JTextArea getReporteTextArea() {
        return reporteTextArea;
    }
    
    public JTextArea getSalidaTextArea() {
        return salidaTextArea;
    }
    
    // Main method for testing the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
}
