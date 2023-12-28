package NewPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

        JPanel borderSetupPanel = new JPanel(new BorderLayout());
        borderSetupPanel.setPreferredSize(new Dimension(710, 120));
        Border setupBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.PINK);
        borderSetupPanel.setBorder(BorderFactory.createCompoundBorder(setupBorder,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        borderSetupPanel.add(setupPanel);

        JPanel outerSetupPanel = new JPanel(new FlowLayout());
        outerSetupPanel.add(borderSetupPanel);

        // Add components to the frame
        add(outerSetupPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void setEnabledPanel(JPanel panel, boolean enabled) {
        for (Component component : panel.getComponents()) {
                component.setEnabled(enabled);
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