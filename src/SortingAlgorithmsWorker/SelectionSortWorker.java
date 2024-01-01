package SortingAlgorithmsWorker;

import java.awt.Color;

import javax.swing.SwingWorker;

import NewPackage.Box;
import NewPackage.MainPanel;

public class SelectionSortWorker extends SwingWorker<Void, Integer>{

    
    MainPanel mainPanel;

    public SelectionSortWorker(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    protected Void doInBackground()throws InterruptedException{
        int[] array = this.mainPanel.getRandomArray();
        for(Box box : this.mainPanel.getBoxes()){
            box.setColor(Color.BLUE);
        }
        this.selectionSort(array, this.mainPanel);
        this.mainPanel.getBoxes().get(mainPanel.getBoxes().size()-1).setColor(Color.GREEN); 

        return null;
    }

    @Override
    protected void done() {
            
        //Enable setupPanel:
        this.mainPanel.getMainFrame()
        .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), true);
        this.mainPanel.setRunning(false);
    }

    public void selectionSort(int[] array, MainPanel mainPanel) throws InterruptedException {
        int n = array.length;

        // Traverse the array
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted part of the array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;

            System.out.println("Swapped");
            Box.SwapBox(i, minIndex, mainPanel);
            
            if(i != minIndex) {
                Thread.sleep(1500);
            }
            else{
                Thread.sleep(50);
            } 
            this.mainPanel.getBoxes().get(i).setColor(Color.GREEN);            
        }
    }
}

