package Listeners;


import java.awt.event.ActionEvent;

import Interfaces.ICustomListener;
import NewPackage.MainPanel;
import SortingAlgorithmsWorker.QuickSortWorker;
import SortingAlgorithmsWorker.SelectionSortWorker;

public class SelectionSortListener implements ICustomListener{

    MainPanel mainPanel;
    
    public SelectionSortListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    //Listening to SortButton
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
