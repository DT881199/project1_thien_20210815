package Algorithms;
import java.awt.Color;

import javax.swing.SwingWorker;

import NewPackage.Box;
import NewPackage.MainPanel;

public class MergeSort extends SwingWorker<Void, Integer> {
    
    MainPanel mainPanel;
    Box[] boxArray;

    public MergeSort(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.boxArray = mainPanel.getBoxes().toArray(new Box[0]);
    }

    @Override
    protected Void doInBackground() throws InterruptedException{
        int[] array = this.mainPanel.getRandomArray();
        System.out.println("merging");
        this.mergeSort(array, 0, array.length - 1, mainPanel);
        
        return null;
    }

    @Override
    protected void done() {
            
        //Enable setupPanel:
        this.mainPanel.getMainFrame()
        .setEnabledPanel(this.mainPanel.getMainFrame().getSetupPanel(), true);
        this.mainPanel.setRunning(false);
    }
    
    // Merge sort function
    public void mergeSort(int[] arr, int left, int right, MainPanel mainPanel) throws InterruptedException {
        if (left < right) {
            // Find the middle point
            int middle = (left + right) / 2;
            System.out.println("still merging");

            // Recursively sort the first and second halves
            mergeSort(arr, left, middle, mainPanel);
            mergeSort(arr, middle + 1, right, mainPanel);

            // Merge the sorted halves
            merge(arr, left, middle, right, mainPanel);
        }
    }

    // Merge two subarrays of arr[] and boxArray[]
    public void merge(int[] arr, int left, int middle, int right, MainPanel mainPanel) throws InterruptedException {
        // Find sizes of two subarrays to be merged
        System.out.println("merging value:" + left + " and " + right + " by " + middle);
        System.out.println("merging value:" + mainPanel.getBoxes().get(left).getValue() + " and " + mainPanel.getBoxes().get(right).getValue() );
        
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays and merging sequences
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        Box[] leftBoxArray = new Box[n1];
        Box[] rightboxArray = new Box[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i) {
            leftArray[i] = arr[left + i];
            leftBoxArray[i] = boxArray[left + i];
            leftBoxArray[i].setColor(Color.BLUE);
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = arr[middle + 1 + j];
            rightboxArray[j] = boxArray[middle + 1 + j];
            rightboxArray[j].setColor(Color.YELLOW);
        }

        System.out.println("entered 2");
        // Initial indexes of the merged subarrays
        int i = 0, j = 0;

        // Initial index of the merging array
        int k = left;

        System.out.println("entered ");
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                System.out.println("entered left");
                boxArray[k] = leftBoxArray[i];
                i++;
            } else {
                System.out.println("entered right");
                arr[k] = rightArray[j];
                boxArray[k] = rightboxArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[], if there are any
        while (i < n1) {
            arr[k] = leftArray[i];
            boxArray[k] = leftBoxArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[], if there are any
        while (j < n2) {
            arr[k] = rightArray[j];
            boxArray[k] = rightboxArray[j];
            j++;
            k++;
        }

        Box.MergeBoxes(boxArray, left, right, mainPanel);
        Thread.sleep((right-left+1)*650 + 500);
        for (int m = left; m <= right; m++) {
            boxArray[m].setColor(Color.GREEN);
        }
    }
}
