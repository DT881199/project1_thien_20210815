package SortingAlgorithmsWorker;

 

import javax.swing.SwingWorker;

import NewPackage.Box;
import NewPackage.MainPanel;

public class BubbleSortWorker extends SwingWorker<Void, Integer>{

    
    MainPanel mainPanel;

    public BubbleSortWorker(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    protected Void doInBackground()throws InterruptedException{
        int[] array = this.mainPanel.getRandomArray();
        this.bubbleSort(array, this.mainPanel);
        
        return null;
    }

    @Override
    protected void done() {
            
        //Enable setupPanel:
        this.mainPanel.getMainFrame()
        .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), true);
        this.mainPanel.setRunning(false);
    }

    private void bubbleSort(int[] array, MainPanel mainPanel) throws InterruptedException {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                // Visualize Swap array[i] and array[j]
                //***********************//
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    System.out.println("Swapped");
                    Box.SwapBox(j, j+1, mainPanel);
                    Thread.sleep(1500);  
                //***********************//    
                }
            }
        }
    }
}

