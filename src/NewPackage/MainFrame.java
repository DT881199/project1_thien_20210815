package NewPackage;

import java.awt.BorderLayout;

import javax.swing.*;

public class MainFrame extends JFrame {

    private SetupPanel setupPanel; 

    public MainFrame() {
        setTitle("Visualizing Sorting Algorithms");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Create an instance of MyPanel
        MainPanel mainPanel = new MainPanel();
        setupPanel = new SetupPanel(mainPanel);

        // Add components to the frame
        add(setupPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}