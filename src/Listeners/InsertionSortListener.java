package Listeners;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import Algorithms.InsertionSort;
import Interfaces.ICustomListener;
import NewPackage.Box;
import NewPackage.MainPanel;


    //Listening to SortButton
public class InsertionSortListener implements ICustomListener{

    MainPanel mainPanel;

    /*************Status right before action*************/
    List<int[]> statusArrays;
    List<int[]> actionArrays;
    private int index;
    

    public InsertionSortListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(!this.mainPanel.getRunning() 
        && (e.getSource().equals(mainPanel.getSortButton()) || e.getSource().equals(mainPanel.getPauseButton()))){

            //Disable setupPanel and buttons:
            this.mainPanel.getMainFrame()
            .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), false);
            this.mainPanel.getBackwardButton().setEnabled(false);
            this.mainPanel.getForwardButton().setEnabled(false);
            this.mainPanel.getPauseButton().setEnabled(true);

            this.mainPanel.setRunning(true);

            //Run Timer and Worker when running = false
            if(e.getSource().equals(mainPanel.getSortButton())){
                //Initiate
                this.statusArrays = new ArrayList<int[]>();
                this.actionArrays = new ArrayList<int[]>();
                this.index = -1;
                this.statusArrays.add(mainPanel.getOriginArray());

                this.mainPanel.getSortButton().setEnabled(false);

                InsertionSort.insertionSort(mainPanel.getRandomArray()
                                    , actionArrays, statusArrays);
                
                int k = 0;
                for(int[] status : statusArrays){
                    System.out.println("Step "+ k + ": ");
                    for(int i : status){
                        System.out.print(i+" ");
                    }
                    System.out.println(" ");
                }
                this.mainPanel.getTimer().start();
            }

            if(e.getSource().equals(mainPanel.getPauseButton())){
                System.out.println("UnPaused");
            }

            //Set Worker 
            this.mainPanel.setWorker(new SwingWorker<Void, Integer>() {
                @Override
                protected Void doInBackground() throws InterruptedException{
                    while(mainPanel.getRunning() && index < actionArrays.size()-1){
                        index++;

                        System.out.println("Now at: " + index);
                        System.out.println("Action at: " + actionArrays.get(index)[0] + " and " + actionArrays.get(index)[1]);

                        //Perform inserting
                        Box.InsertBox(actionArrays.get(index)[0], actionArrays.get(index)[1], mainPanel);
                        
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
                    //Enable setupPanel if finished:
                    mainPanel.setRunning(false);
                    if(index >= actionArrays.size()-1){
                        mainPanel.getMainFrame()
                            .setEnabledPanel(mainPanel.getMainFrame().getSetupPanel(), true);
                        mainPanel.getBackwardButton().setEnabled(true);
                    }
                    else{
                        mainPanel.getBackwardButton().setEnabled(true);
                        mainPanel.getForwardButton().setEnabled(true);
                    }
                }
            });
            //Run Worker 
            System.out.println("Running");
            this.mainPanel.getWorker().execute();
        }
        
        else if(this.mainPanel.getRunning() && e.getSource().equals(mainPanel.getPauseButton())){
            //Disable setupPanel and buttons:
            System.out.println("Paused");
            this.mainPanel.setRunning(false);
        }

        else if(e.getSource().equals(mainPanel.getBackwardButton())){
            mainPanel.getForwardButton().setEnabled(true);
            if(index > -1){
                index--;
                List<Box> boxList = new ArrayList<Box>();
                System.out.println("Backward to: " + index);

                int m = 0;
                for(int i : statusArrays.get(index+1)){
                    mainPanel.getOriginBoxes().get(i).setX(40 + m*3*Box.getDistance());
                    m++;
                    boxList.add(mainPanel.getOriginBoxes().get(i));
                }

                mainPanel.setBoxes(boxList);
                this.mainPanel.repaint();
            }
        } 

        else{
            if(index <= actionArrays.size()-2){
                index++;
                List<Box> boxList = new ArrayList<Box>();
                System.out.println("Forward to: " + index);
                int m = 0;
                for(int i : statusArrays.get(index+1)){
                    mainPanel.getOriginBoxes().get(i).setX(40 + m*3*Box.getDistance());
                    m++;
                    boxList.add(mainPanel.getOriginBoxes().get(i));
                }
                mainPanel.setBoxes(boxList);
                this.mainPanel.repaint();
            }
        }
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
