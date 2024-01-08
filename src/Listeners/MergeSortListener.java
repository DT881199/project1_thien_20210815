package Listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;
import Algorithms.MergeSort;
import Interfaces.ICustomListener;
import Main.Box;
import Main.MainPanel;


    //Listening to SortButton
public class MergeSortListener implements ICustomListener{

    private MainPanel mainPanel;

    /*************Status right before action*************/
    private List<int[]> statusArrays;
    private List<int[]> actionArrays;
    private Box[] boxArray;
    private int index;
    

    public MergeSortListener(MainPanel mainPanel) {
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

                this.boxArray = mainPanel.getOriginBoxes().toArray(new Box[0]);

                this.mainPanel.getSortButton().setEnabled(false);

                MergeSort.mergeSort(mainPanel.getRandomArray() 
                                    , 0, boxArray.length-1
                                    , actionArrays, statusArrays);

                for(int[] a : actionArrays){
                    System.out.println(a[0] + " " + a[1]);
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
                        
                        //ActionArray and BoxArray are set at index status
                        int left = actionArrays.get(index)[0];
                        int right = actionArrays.get(index)[1];
                        int middle = actionArrays.get(index)[2];
                        System.out.println("Now at: " + index);
                        System.out.println("Action at: " + left + " and " + right + " and " + middle);

                        int m = 0;
                        for(int i : statusArrays.get(index)){
                            boxArray[m] = mainPanel.getOriginBoxes().get(i);
                            m++;                    
                        }
                        //Set Colors at index status
                        for (int i = 0; i < middle - left + 1; ++i) {
                            boxArray[left + i].setColor(Color.BLUE);
                        }
                        for (int i = 0; i < right - middle; ++i) {
                            boxArray[middle + 1 + i].setColor(Color.YELLOW);
                        }

                        //Update BoxArray to (index+1) status
                        m = 0;
                        for(int i : statusArrays.get(index+1)){
                            boxArray[m] = mainPanel.getOriginBoxes().get(i);
                            m++;                    
                        }

                        //Perform swapping
                        Box.MergeBoxes(boxArray, left, right, mainPanel); 
                        
                        Thread.sleep((right-left+1)*650 + 500);

                        //Reset color
                        for (int j = left; j <= right; j++) {
                            boxArray[j].setColor(Color.GREEN);
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
                        mainPanel.getSortButton().setEnabled(true);
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
