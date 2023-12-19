package Listeners;


import java.awt.event.ActionEvent;

import NewPackage.MainPanel;

public class MergeSortListener implements ICustomListener{
    
    MainPanel mainPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        mainPanel.repaint();
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
}
