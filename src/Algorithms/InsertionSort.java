package Algorithms;

import javax.swing.SwingWorker;

import NewPackage.Box;
import NewPackage.MainPanel;

public class InsertionSort extends SwingWorker<Void, Integer>{
    
    MainPanel mainPanel;

    public InsertionSort(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    protected Void doInBackground() throws InterruptedException{
        int[] array = this.mainPanel.getRandomArray();
        this.insertionSort(array, this.mainPanel);
        
        return null;
    }

    @Override
    protected void done() {
            
        //Enable setupPanel:
        this.mainPanel.getMainFrame()
        .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), true);
        this.mainPanel.setRunning(false);
    }

    public void insertionSort(int[] array, MainPanel mainPanel) throws InterruptedException{
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            // Move elements of array[0..i-1] that are greater than key to one position ahead of their current position
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
            System.out.println("Inserted");
            Box.InsertBox(i, j+1, mainPanel);
            if(i != j+1) {
                Thread.sleep(1500);
            }
            else{
                Thread.sleep(50);
            }
            //Move key to j+1, from j+2 to i need to move(inclusive)
        }
    }
}
