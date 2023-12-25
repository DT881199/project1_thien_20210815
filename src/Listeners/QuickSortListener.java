package Listeners;

import java.awt.event.ActionEvent;

import Interfaces.ICustomListener;
import NewPackage.MainFrame;
import NewPackage.MainPanel;
import SortingAlgorithmsWorker.QuickSortWorker;

public class QuickSortListener implements ICustomListener{
    MainPanel mainPanel;

    public QuickSortListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    //Listening to SortButton
    public void actionPerformed(ActionEvent e) {
        if(!this.mainPanel.getRunning()){//Chay timer va worker khi running = false

            //Disable setupPanel:
            this.mainPanel.getMainFrame()
            .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), false);

            this.mainPanel.setRunning(true);
            this.mainPanel.setWorker(new QuickSortWorker(this.mainPanel));

            System.out.println("Running");
            this.mainPanel.getTimer().start();
            this.mainPanel.getWorker().execute();
            
        }
        
        else {this.mainPanel.setRunning(false);
        System.out.println("NotRunning");}
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
}
