import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
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
        formatter.setAllowsInvalid(false);
        firstField  = new JFormattedTextField(formatter);  firstField.setColumns(10);
        lastField   = new JFormattedTextField(formatter);  lastField.setColumns(10);
        numberField = new JFormattedTextField(formatter);  numberField.setColumns(10);

        submitButton = new JButton("Submit");
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleInput();
            }

            private void handleInput() {
                try {
                    // Get the input from the formatted text field
                    int first = (int) firstField.getValue();
                    int last = (int) lastField.getValue();
                    int number = (int) numberField.getValue();
        
                } catch (NumberFormatException ex) {
                    // Handle the case where the input is not a valid integer
                    JOptionPane.showMessageDialog(new Panel(), "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
 
    String[] options = {"Quick Sort", "Merge sort", "Bubble Sort"};
    optionsBox = new JComboBox<>(options);
    optionsBox.addActionListener(new OptionListener(mainPanel));

    setLayout(new FlowLayout());
    add(optionsBox);
    add(firstField);
    add(lastField);
    add(numberField);


    add(submitButton);
    }
}
