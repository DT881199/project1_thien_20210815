package Listeners;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingWorker;

import Algorithms.QuickSort;
import Interfaces.ICustomListener;
import NewPackage.Box;
import NewPackage.MainPanel;


    //Listening to SortButton
public class QuickSortListener implements ICustomListener{

    MainPanel mainPanel;

    /*************Status right before action*************/
    List<int[]> statusArrays;
    List<int[]> actionArrays;
    private int index;
    

    public QuickSortListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.statusArrays = new ArrayList<int[]>();
        this.actionArrays = new ArrayList<int[]>();

        this.index = -1;

        this.statusArrays.add(mainPanel.getOriginArray());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Chay timer va worker khi running = false
        if(!this.mainPanel.getRunning() && e.getSource().equals(mainPanel.getSortButton())){

            //Disable setupPanel and buttons:
            this.mainPanel.getMainFrame()
            .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), false);
            this.mainPanel.getBackwardButton().setEnabled(false);
            this.mainPanel.getForwardButton().setEnabled(false);

            //Run algorithms
            this.mainPanel.setRunning(true);
            QuickSort.quickSort(mainPanel.getRandomArray()
                               , 0, (mainPanel.getRandomArray().length - 1)
                               , actionArrays, statusArrays);

            //Set Worker 
            this.mainPanel.setWorker(new SwingWorker<Void, Integer>() {
                @Override
                protected Void doInBackground() throws InterruptedException{
                    while(mainPanel.getRunning()){
                        index++;
                        Box.SwapBox(actionArrays.get(index)[0], actionArrays.get(index)[1], mainPanel);
                        if(actionArrays.get(index)[0] != actionArrays.get(index)[1]) {
                            Thread.sleep(1500);
                        }
                        else{
                            Thread.sleep(50);
                        }
                    }
                    return null;
                }

                @Override
                protected void done() {
                    //Enable setupPanel:
                    mainPanel.getMainFrame()
                    .setEnabledPanel(mainPanel.getMainFrame().getSetupPanel(), true);
                    mainPanel.setRunning(false);
                }
            });
          
            //Run Timer and Worker 
            System.out.println("Running");
            this.mainPanel.getTimer().start();
            this.mainPanel.getWorker().execute();
        }
        
        else if(this.mainPanel.getRunning() && e.getSource().equals(mainPanel.getSortButton())){
                this.mainPanel.setRunning(false);
                this.mainPanel.getBackwardButton().setEnabled(true);
                this.mainPanel.getForwardButton().setEnabled(true);
                System.out.println("Not Running");
            }
        else if(e.getSource().equals(mainPanel.getBackwardButton())){
            if(index > 0){
                index--;
                List<Box> boxList = new ArrayList<Box>();
                for(int i : statusArrays.get(index+1)){
                    boxList.add(mainPanel.getOriginBoxes().get(i));
                }
                mainPanel.setBoxes(boxList);
                this.mainPanel.repaint();
            }
            else index = -1;
        }
        else{
            index++;
            this.mainPanel.repaint();
        }
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    
}
