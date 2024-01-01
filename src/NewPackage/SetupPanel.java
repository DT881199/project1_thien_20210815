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

        //Number formatter
        NumberFormatter formatter = new NumberFormatter();
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(true);
        formatter.setOverwriteMode(true);
        firstField  = new JFormattedTextField(formatter);
        lastField   = new JFormattedTextField(formatter);
        numberField = new JFormattedTextField(formatter);

        Font font = new Font("Arial",Font.PLAIN, 16);
        firstField.setFont(font); lastField.setFont(font); numberField.setFont(font); 

        //Submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    // Get input va tao array
                    int first = (int) firstField.getValue();
                    int last = (int) lastField.getValue();
                    int number = (int) numberField.getValue();

                    System.out.println(number+" " + first + " " + last);
                    mainPanel.setArray(number, first, last);

                    // Set Timer trong mainPanel
                    mainPanel.setTimer(new Timer(50,mainPanel));
                    

            }
        });
 
        //Option box
        String[] options = {"Choose your sorting algorithms"
                          , "Quick Sort", "Merge Sort", "Bubble Sort"
                          , "Insertion Sort", "Selection Sort"};
        optionsBox = new JComboBox<>(options);
        optionsBox.setFont(font);
        optionsBox.addActionListener(new OptionListener(mainPanel));

        this.setLayout(new GridLayout(3, 3)); // 3 rows, 3 columns
        
        JLabel label = new JLabel("First number:");
        label.setFont(font);
        this.add(label);
        this.add(firstField);
        this.add(optionsBox);

        JPanel innerPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,4));
        label = new JLabel("Second number:");
        label.setFont(font);
        this.add(label);
        this.add(lastField);
        innerPanel2.add(submitButton);
        this.add(innerPanel2);
        
        label = new JLabel("Amount:");
        label.setFont(font);
        this.add(label);
        this.add(numberField);
    }
}
