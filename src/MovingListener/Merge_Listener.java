package MovingListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import NewPackage.Box;

public class Merge_Listener implements ActionListener{

    Timer timer;
    List<Box> boxes;
    int left; 
    int right;
    int middle;
    int executionCount;

    public Merge_Listener(Timer timer, List<Box> boxes, int left, int right, int middle){
        this.left = left;
        this.right = right;
        this.middle = middle;
        this.boxes = boxes;

        this.executionCount = 0;
        this.timer = timer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Start merging
        int n1 = middle - left + 1;
        int n2 = right - middle;

        Box[] leftArray = new Box[n1];
        Box[] rightArray = new Box[n2];
        Box[] boxArray = boxes.toArray(new Box[0]);
        System.arraycopy(boxArray, left, leftArray, 0, n1);
        System.arraycopy(boxArray, middle + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (Integer.parseInt(leftArray[i].getValue()) <= Integer.parseInt(rightArray[j].getValue())) {
                boxArray[k] = leftArray[i];
                
                i++;
            } else {
                boxArray[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            boxArray[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            boxArray[k] = rightArray[j];
            j++;
            k++;
        }
    }   
}
