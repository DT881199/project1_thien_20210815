package SortingAlgorithmsWorker;

import java.awt.Color;

import javax.swing.SwingWorker;

import NewPackage.Box;
import NewPackage.MainPanel;

public class QuickSortWorker extends SwingWorker<Void, Integer>{

    MainPanel mainPanel;

    public QuickSortWorker(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    protected Void doInBackground()throws InterruptedException{
        int[] array = this.mainPanel.getRandomArray();
        this.quickSort(array, 0, array.length - 1, this.mainPanel);
        
        return null;
    }

    @Override
    protected void done() {
            
        //Enable setupPanel:
        this.mainPanel.getMainFrame()
        .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), true);
        this.mainPanel.setRunning(false);

    }
    
    public void  quickSort(int[] array, int low, int high, MainPanel mainPanel) throws InterruptedException{
        
        if (low < high) {
            int partitionIndex = this.partition(array, low, high, mainPanel);

            this.quickSort(array, low, partitionIndex - 1, mainPanel);
            this.quickSort(array, partitionIndex + 1, high, mainPanel);
        }
    }
    private int partition(int[] array, int low, int high, MainPanel mainPanel) throws InterruptedException{
        int pivot = array[high];

        //Set color
        this.mainPanel.getBoxes().get(high).setColor(Color.RED);
        for(int k = low; k < high; k++){
            this.mainPanel.getBoxes().get(k).setColor(Color.BLUE);
        }

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;

                // Visualize Swap array[i] and array[j]
                //***********************//
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                System.out.println("Swapped");
                Box.SwapBox(i,j,mainPanel);
                if(i != j) {
                    Thread.sleep(1500);
                }
                else{
                    Thread.sleep(50);
                } 
                //***********************//
            }
        }

        // Visualize Swap array[i+1] and array[high] (put the pivot in its correct position)
        //***********************//
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        
        Box.SwapBox(i+1,high,mainPanel);
        if(i+1 != high) {
            Thread.sleep(1500);
        }
        else{
            Thread.sleep(500);
        } 
        //***********************//
        
        //Set color
        this.mainPanel.getBoxes().get(high).setColor(Color.GREEN);
        for(int k = low; k < high; k++){
            this.mainPanel.getBoxes().get(k).setColor(Color.GREEN);
        }

        return i + 1;   
    }


}