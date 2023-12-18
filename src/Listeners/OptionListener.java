import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class OptionListener implements ActionListener{
    private MainPanel panel;

    public OptionListener(MainPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedOption = (String) ((JComboBox<?>)e.getSource()).getSelectedItem();
        if(selectedOption == "Quick Sort"){
            panel.setListenner(new QuickSortListener());
        }
        else if(selectedOption == "Merge Sort"){
            panel.setListenner(new MergeSortListener());
        }
        else if(selectedOption == "Bubble Sort"){
            panel.setListenner(new BubbleSortListener());
        }
        else{
            panel.setListenner(new SelectiveSortListener());
        }
    }
    
}