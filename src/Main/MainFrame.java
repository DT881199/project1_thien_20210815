package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.Border;

public class MainFrame extends JFrame {

    private SetupPanel setupPanel; 

    public MainFrame() {
        setTitle("Visualizing Sorting Algorithms");
        setSize(1540, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        MainPanel mainPanel = new MainPanel(this);
        setupPanel = new SetupPanel(mainPanel);
        setupPanel.setBackground(Color.WHITE);

        JPanel borderSetupPanel = new JPanel(new BorderLayout());
        borderSetupPanel.setPreferredSize(new Dimension(750, 130));
        Border outerSetupBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE);
        Border innerSetupBorder = BorderFactory.createMatteBorder(10, 10,10, 10, Color.CYAN);
        borderSetupPanel.setBorder(BorderFactory.createCompoundBorder(outerSetupBorder,innerSetupBorder));
        borderSetupPanel.add(setupPanel);

        JPanel outerSetupPanel = new JPanel(new FlowLayout());
        outerSetupPanel.add(borderSetupPanel);

        // Add components to the frame
        add(outerSetupPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void setEnabledPanel(Container container, boolean enabled) {
        container.setEnabled(enabled);

        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof Container) {
                setEnabledPanel((Container) component, enabled);
            } else {
                component.setEnabled(enabled);
            }
        }
    }

    public SetupPanel getSetupPanel() {
        return setupPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}