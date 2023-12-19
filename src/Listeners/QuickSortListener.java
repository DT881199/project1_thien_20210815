package Listeners;

import java.awt.event.ActionEvent;

import NewPackage.MainPanel;
import SortingAlgorithms.QuickSort;

public class QuickSortListener implements ICustomListener{
    MainPanel mainPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        QuickSort.quickSort(mainPanel.getRandomArray(), 0, mainPanel.getRandomArray().length -1, mainPanel);
        mainPanel.repaint();
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
}
