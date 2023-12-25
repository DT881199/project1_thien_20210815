package MovingListener;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

import Interfaces.ICustomListener;
import NewPackage.Box;
import NewPackage.MainPanel;

public class Moving_1_Box_Listener implements ICustomListener{

    int type;
    Box box;
    MainPanel mainPanel;
    int executionCount;

    public Moving_1_Box_Listener(int type) {
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this.type) {
            case 0:
                Box.move1Down(box);
                executionCount++;
                this.mainPanel.repaint();
                if(executionCount > 5) ((Timer)e.getSource()).stop();
                break;
            case 1:
                Box.move1Up(box);
                executionCount++;
                this.mainPanel.repaint();
                if(executionCount > 5) ((Timer)e.getSource()).stop();
                break;
            case 2:
                Box.move1Left(box);
                this.mainPanel.repaint();
                break;
            case 3:
                Box.move1Right(box);
                this.mainPanel.repaint();
                break;
            default:
                break;
        }
        mainPanel.repaint();
    }

    @Override
    public void setMainPanel(MainPanel mainPanel) {
       this.mainPanel = mainPanel;
    }    
}
