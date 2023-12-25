package Listeners;


import java.awt.event.ActionEvent;

import Interfaces.ICustomListener;
import NewPackage.MainPanel;

public class SelectiveSortListener implements ICustomListener{

    MainPanel mainPanel;
    
    public SelectiveSortListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    //Listening to SortButton
    public void actionPerformed(ActionEvent e) {
            
        //Disable setupPanel:
        this.mainPanel.getMainFrame()
        .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), false);

    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
}
