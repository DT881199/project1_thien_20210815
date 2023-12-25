package SortingAlgorithmsWorker;

import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.Timer;

import MovingListener.Start_Swap_Listener;
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
                Thread.sleep(100*10 + 100*(j-i)*80/20 + 100);  
                //***********************//
            }
        }

        // Visualize Swap array[i+1] and array[high] (put the pivot in its correct position)
        //***********************//
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        
        Box.SwapBox(i+1,high,mainPanel);
        Thread.sleep(100*10 + 100*(high-i-1)*80/20 + 100);  
        //***********************//

        return i + 1;   
    }


}