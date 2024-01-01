package Listeners;

import java.awt.event.ActionEvent;
import java.util.List;

import Interfaces.ICustomListener;
import NewPackage.MainPanel;
import SortingAlgorithmsWorker.MergeSortWorker;

    //Listening to SortButton
public class MergeSortListener implements ICustomListener{
    
    MainPanel mainPanel;

    /*************Status right before action*************/
    List<int[]> statusArrays;
    List<int[]> actionArrays;

    public MergeSortListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.mainPanel.getRunning()){//Chay timer va worker
            
            //Disable setupPanel:
            this.mainPanel.getMainFrame()
            .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), false);

            this.mainPanel.setRunning(true);
            this.mainPanel.setWorker(new MergeSortWorker(this.mainPanel));

            System.out.println("Running merge");
            this.mainPanel.getTimer().start();
            this.mainPanel.getWorker().execute();
            
        }
        
        else this.mainPanel.setRunning(false);
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
