package SortingAlgorithmsWorker;
import javax.swing.SwingWorker;

import NewPackage.MainPanel;

public class MergeSortWorker extends SwingWorker<Void, Integer> {
    
    MainPanel mainPanel;

    public MergeSortWorker(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    protected Void doInBackground()throws InterruptedException{
        int[] array = this.mainPanel.getRandomArray();
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
    public void mergeSort(int[] arr, int left, int right, MainPanel mainPanel) {
        if (left < right) {
            // Find the middle point
            int middle = (left + right) / 2;

            // Recursively sort the first and second halves
            mergeSort(arr, left, middle, mainPanel);
            mergeSort(arr, middle + 1, right, mainPanel);

            // Merge the sorted halves
            merge(arr, left, middle, right, mainPanel);
        }
    }

    // Merge two subarrays of arr[]
    public void merge(int[] arr, int left, int middle, int right, MainPanel mainPanel) {
        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to temporary arrays
        System.arraycopy(arr, left, leftArray, 0, n1);
        System.arraycopy(arr, middle + 1, rightArray, 0, n2);

        // Merge the temporary arrays

        // Initial indexes of the merged subarrays
        int i = 0, j = 0;

        // Initial index of the merged subarray array
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[], if there are any
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[], if there are any
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

}
