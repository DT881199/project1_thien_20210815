package Algorithms;

import java.util.List;

public class InsertionSort{

    public static void insertionSort(int[] array, List<int[]> actionArray, List<int[]> statusArrays){
        int n = array.length;

        for (int i = 1; i < n; ++i) {
            
            int[] status = new int[n];
            int m = 0;
            for(int k : statusArrays.get(statusArrays.size()-1)){
                status[m] = k;
                m++;
            }
            
            int key = array[i];
            int keyBox = status[i];
            int j = i - 1;

            // Move elements of array[0..i-1] that are greater than key to one position ahead of their current position
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                status[j + 1] = status[j];
                j = j - 1;
            }
            array[j + 1] = key;
            status[j + 1] = keyBox;

            statusArrays.add(status);

            int[] action = {i, j+1};
            actionArray.add(action);
        }
    }
}
