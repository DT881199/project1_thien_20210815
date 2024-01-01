package Listeners;


import java.awt.event.ActionEvent;
import java.util.List;

import Interfaces.ICustomListener;
import NewPackage.MainPanel;
import SortingAlgorithmsWorker.QuickSortWorker;
import SortingAlgorithmsWorker.SelectionSortWorker;

    //Listening to SortButton
public class SelectionSortListener implements ICustomListener{

    MainPanel mainPanel;

    /*************Status right before action*************/
    List<int[]> statusArrays;
    List<int[]> actionArrays;

    public SelectionSortListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            
        //Disable setupPanel:
            this.mainPanel.getMainFrame()
            .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), false);

            this.mainPanel.setRunning(true);
            this.mainPanel.setWorker(new SelectionSortWorker(this.mainPanel));

            System.out.println("Running");
            this.mainPanel.getTimer().start();
            this.mainPanel.getWorker().execute();
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
}
