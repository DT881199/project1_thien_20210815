package Listeners;


import java.awt.event.ActionEvent;

import Interfaces.ICustomListener;
import NewPackage.MainPanel;
import SortingAlgorithmsWorker.BubbleSortWorker;

public class BubbleSortListener implements ICustomListener{

    MainPanel mainPanel;

    public BubbleSortListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    //Listening to SortButton
    public void actionPerformed(ActionEvent e) {
        if(!this.mainPanel.getRunning()){//Chay timer va worker
        
            //Disable setupPanel:
            this.mainPanel.getMainFrame()
            .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), false);

            this.mainPanel.setRunning(true);
            this.mainPanel.setWorker(new BubbleSortWorker(this.mainPanel));

            System.out.println("Running");
            this.mainPanel.getTimer().start();
            this.mainPanel.getWorker().execute();
            
        }
        
        else {
            this.mainPanel.setRunning(false);
            System.out.println("NotRunning");
        }
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
    
}
