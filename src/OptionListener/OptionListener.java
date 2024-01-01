package OptionListener;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import Interfaces.ICustomListener;
import Listeners.BubbleSortListener;
import Listeners.InsertionSortListener;
import Listeners.MergeSortListener;
import Listeners.QuickSortListener;
import Listeners.SelectionSortListener;
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
            System.out.println("here4");
            this.mainPanel.setSortButton(new MergeSortListener(mainPanel));
        }
        else if(selectedOption == "Bubble Sort"){
            System.out.println("here");
            this.mainPanel.setSortButton(new BubbleSortListener(mainPanel));
        }
        else if(selectedOption == "Insertion Sort"){
            System.out.println("here");
            this.mainPanel.setSortButton(new InsertionSortListener(mainPanel));
        }
        else{
            System.out.println("here");
            this.mainPanel.setSortButton(new SelectionSortListener(mainPanel));
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