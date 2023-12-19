package Listeners;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import NewPackage.MainPanel;

public class OptionListener implements ICustomListener{
    private MainPanel mainPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedOption = (String) ((JComboBox<?>)e.getSource()).getSelectedItem();
        if(selectedOption == "Quick Sort"){
            mainPanel.setListenner(new QuickSortListener());
        }
        else if(selectedOption == "Merge Sort"){
            mainPanel.setListenner(new MergeSortListener());
        }
        else if(selectedOption == "Bubble Sort"){
            mainPanel.setListenner(new BubbleSortListener());
        }
        else{
            mainPanel.setListenner(new SelectiveSortListener());
        }
    }


    public OptionListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }


    @Override
    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
}