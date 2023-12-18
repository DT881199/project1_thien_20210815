import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.Border;

public class MyMainFrame extends JFrame {

    public MyMainFrame() {
        setTitle("Visualizing Sorting Algorithms");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        

        // Create an instance of JPanel and add it to the frame
        add(new MovableBoxArray(),BorderLayout.CENTER);

        // Other frame customization or component addition can be done here

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MyMainFrame();
        });
    }
}