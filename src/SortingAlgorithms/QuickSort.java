package SortingAlgorithms;

import java.util.Timer;

import NewPackage.Box;
import NewPackage.MainPanel;

public class QuickSort {

    public static void  quickSort(int[] array, int low, int high, MainPanel mainPanel) {
        
        if (low < high) {
            int partitionIndex = QuickSort.partition(array, low, high, mainPanel);

            quickSort(array, low, partitionIndex - 1, mainPanel);
            quickSort(array, partitionIndex + 1, high, mainPanel);
        }
    }
    private static int partition(int[] array, int low, int high, MainPanel mainPanel) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;

                // Visual Swap array[i] and array[j]
                //***********************//
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                //***********************//
            }
        }

        // Visual Swap array[i+1] and array[high] (put the pivot in its correct position)
        //***********************//
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        //***********************//
        QuickSort.SwapBox(i+1,high,mainPanel);
        return i + 1;
    }

    public static void SwapBox(int position1, int position2, MainPanel mainPanel){
        if(position1 == position2) return;

        java.util.List<Box> boxes = mainPanel.getBoxes();
        Box box1 = mainPanel.getBoxes().get(position1);
        Box box2 = mainPanel.getBoxes().get(position2);
        int oldX2 = box2.getX(); int oldX1 = box1.getX();
        boxes.set(position1, box2);
        boxes.set(position2, box1);

        for(int i = 0; i < 10; i++){
            Box.move2Up_Down(box1, box2);
            mainPanel.repaint();
        }
        while(box1.getX() < oldX2){
            Box.move2Right_Left(box1, box2);
            mainPanel.repaint();
        }
        box1.setX(oldX2); box2.setX(oldX1);
        for(int i = 0; i < 10; i++){
            Box.move2Up_Down(box2, box1);
            mainPanel.repaint();
        }
    }
}