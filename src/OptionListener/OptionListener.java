package OptionListener;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.Timer;

import Interfaces.ICustomListener;
import Listeners.BubbleSortListener;
import Listeners.MergeSortListener;
import Listeners.QuickSortListener;
import Listeners.SelectiveSortListener;
import NewPackage.MainPanel;

public class OptionListener implements ICustomListener{
    private MainPanel mainPanel;

    @Override
    public void actionPerformed(ActionEvent e) {

        String selectedOption = (String) ((JComboBox<?>)e.getSource()).getSelectedItem();

        this.mainPanel.setRunning(false);

        if(selectedOption == "Quick Sort"){
            System.out.println("here");
            this.mainPanel.setSortButton(new QuickSortListener(mainPanel));
        }
        else if(selectedOption == "Merge Sort"){
            this.mainPanel.setSortButton(new MergeSortListener(mainPanel));
        }
        else if(selectedOption == "Bubble Sort"){
            this.mainPanel.setSortButton(new BubbleSortListener(mainPanel));
        }
        else{
            this.mainPanel.setSortButton(new SelectiveSortListener(mainPanel));
        }
        
        System.out.println("here2");
        this.mainPanel.repaint();
    }

    public OptionListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}