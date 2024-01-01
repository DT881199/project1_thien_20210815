package Listeners;


import java.awt.event.ActionEvent;
import java.util.List;

import Interfaces.ICustomListener;
import NewPackage.MainPanel;
import SortingAlgorithmsWorker.BubbleSortWorker;

    //Listening to SortButton
public class BubbleSortListener implements ICustomListener{

    MainPanel mainPanel;

    /*************Status right before action*************/
    List<int[]> statusArrays;
    List<int[]> actionArrays;

    public BubbleSortListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
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
