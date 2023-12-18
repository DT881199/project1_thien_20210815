import javax.swing.*;

import Listeners.BubbleSortListener;
import Listeners.MergeSortListener;
import Listeners.QuickSortListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovableBoxArray extends JPanel {

    private List<Box> boxes;
    private JButton QuickSortButton;
    private JButton MergeSortButton;
    private JButton BubbleSortButton;

    public MovableBoxArray() {
        boxes = new ArrayList<Box>();
        boxes.add(new Box(50, 50, 50, 50, 1,1));
        boxes.add(new Box(150, 50, 50, 50, 2,1));
        boxes.add(new Box(250, 50, 50, 50, 3,1));

        QuickSortButton = new JButton("Quick Sort");
        MergeSortButton = new JButton("Merge Sort");
        BubbleSortButton = new JButton("Bubble Sort");

        QuickSortButton.setBackground(getForeground());
        MergeSortButton.setBackground(getForeground());
        BubbleSortButton.setBackground(getForeground());

        QuickSortButton.addActionListener(new QuickSortListener());
        MergeSortButton.addActionListener(new MergeSortListener());
        BubbleSortButton.addActionListener(new BubbleSortListener());

        add(QuickSortButton);
        add(MergeSortButton);
        add(BubbleSortButton);

        repaint();
        
    }

    public void setVariables(int boxNumber, int first, int last){
        if (boxNumber <= 0 || first > last) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        int[] randomArray = new int[boxNumber];
        Random random = new Random();

        for (int i = 0; i < boxNumber; i++) {
            randomArray[i] = random.nextInt(last - first + 1) + first;
        }
        int rank = 0;
        for(int value : randomArray){
            boxes.add(new Box(10+rank, 50, 20, 50, 1,1));
            rank += 55;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        doPaint(g);
    }

    public void doPaint(Graphics g){
        for(Box b : this.boxes){
            g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
    }
}