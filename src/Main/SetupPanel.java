package Main;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
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
        submitButton = new JButton("SUBMIT");
        font = new Font("Arial",Font.BOLD, 16);
        submitButton.setFont(font);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    // Get input va tao array
                    int first = (int) firstField.getValue();
                    int last = (int) lastField.getValue();
                    int number = (int) numberField.getValue();

                    System.out.println(number+" " + first + " " + last);
                    mainPanel.setArray(number, first, last);

                    // Set Timer trong mainPanel
                    mainPanel.setTimer(new Timer(50,mainPanel));
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(mainPanel, "Invalid input!");
                }

            }
        });
 
        //Option box
        String[] options = {"Choose your sorting algorithms"
                          , "Quick Sort", "Merge Sort", "Bubble Sort"
                          , "Insertion Sort", "Selection Sort"};
        optionsBox = new JComboBox<>(options);
        optionsBox.setFont(font);
        optionsBox.addActionListener(new OptionListener(mainPanel));
        optionsBox.setPreferredSize(new Dimension(200, 45));

        this.setLayout(new GridLayout(1, 3)); // 2 rows, 3 columns

        JPanel innerPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,5));
        JPanel innerPanel2 = new JPanel(new GridLayout(3, 1));
        JPanel innerPanel3 = new JPanel(new GridLayout(3, 1));
        innerPanel1.setBackground(Color.CYAN);
        innerPanel2.setBackground(Color.CYAN);
        innerPanel3.setBackground(Color.CYAN);

        Border emptyBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.WHITE);
        Border outerBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE);
        submitButton.setBorder(BorderFactory.createCompoundBorder(outerBorder, emptyBorder));
        submitButton.setBackground(Color.BLUE);
        submitButton.setPreferredSize(new Dimension(90, 45));
        innerPanel1.add(optionsBox);
        innerPanel1.add(submitButton);

        JLabel label = new JLabel("First number:");
        label.setFont(font);
        innerPanel2.add(label);
        label = new JLabel("Second number:");
        label.setFont(font);
        innerPanel2.add(label);
        label = new JLabel("Amount:");
        label.setFont(font);
        innerPanel2.add(label);

        innerPanel3.add(firstField);
        innerPanel3.add(lastField); 
        innerPanel3.add(numberField);

        this.add(innerPanel2);
        this.add(innerPanel3);
        this.add(innerPanel1);

    }
}
