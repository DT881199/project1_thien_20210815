package NewPackage;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.NumberFormatter;

import OptionListener.OptionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupPanel extends JPanel{
    
    private JComboBox<String> optionsBox;
    private JFormattedTextField firstField;
    private JFormattedTextField lastField;
    private JFormattedTextField numberField;
    private JButton submitButton;

    public SetupPanel(MainPanel mainPanel){

        NumberFormatter formatter = new NumberFormatter();
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(true);
        formatter.setOverwriteMode(true);
        firstField  = new JFormattedTextField(formatter);
        lastField   = new JFormattedTextField(formatter);
        numberField = new JFormattedTextField(formatter); 

        //Submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get input va tao array
                    int first = (int) firstField.getValue();
                    int last = (int) lastField.getValue();
                    int number = (int) numberField.getValue();
                    mainPanel.setArray(number, first, last);
                    firstField.setText("");
                    lastField.setText(""); 
                    numberField.setText("");

                    // Set Timer trong mainPanel
                    mainPanel.setTimer(new Timer(100,mainPanel));

        
                } catch (Exception ex) {
                    // Handle 
                    JOptionPane.showMessageDialog(mainPanel, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
 
        //Option box
        String[] options = {"Choose your sorting algorithms", "Quick Sort", "Merge Sort", "Bubble Sort", "Selective Sort"};
        optionsBox = new JComboBox<>(options);
        optionsBox.addActionListener(new OptionListener(mainPanel));

        setLayout(new GridLayout(3, 3)); // 3 rows, 3 columns

        add(new JLabel("First number:"));
        add(firstField);
        add(optionsBox);

        add(new JLabel("Last number:"));
        add(lastField);
        add(submitButton);
        
        add(new JLabel("Amount:"));
        add(numberField);
    }
}
