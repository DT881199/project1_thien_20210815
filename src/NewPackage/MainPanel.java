package NewPackage;
import java.awt.*;

import javax.swing.*;

import Listeners.ICustomListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPanel extends JPanel {

    private List<Box> boxes;
    private JButton SortButton;
    private int[] randomArray;
    private Graphics g;

    //Constructor
    public MainPanel() {
        boxes = new ArrayList<Box>();
        SortButton = new JButton("Sort");
        SortButton.setBackground(getForeground());
        add(SortButton);
    }

    //Set array
    public void setArray(int boxNumber, int first, int last){
        if (boxNumber <= 0 || first > last) {
            throw new IllegalArgumentException("Invalid arguments");
        }   

        randomArray = new int[boxNumber];
        Random random = new Random();

        for (int i = 0; i < boxNumber; i++) {
            randomArray[i] = random.nextInt(last - first + 1) + first;
        }
        int rank = 0;
        int position = 0;
        for(int value : randomArray){
            (this.boxes).add(new Box(position, 10+rank, 500, 50, 100, value,1));
            rank += 100;
            position++;
        }
    }

    //Set Listenner
    public void setListenner(ICustomListener a){
        a.setMainPanel(this);
        this.SortButton.addActionListener(a);
    }

    //Getters
    public int[] getRandomArray() {
        return randomArray;
    }

    public Graphics getG() {
        return g;
    }   

    public List<Box> getBoxes() {
        return boxes;
    }

    //Paint
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doPaint(g);
        this.g = g;
    }

    public void doPaint(Graphics g){
        for(Box b : this.boxes){
            g.setColor(Color.GREEN);
            g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            g.setColor(Color.RED);
            g.drawString(Integer.toString(b.getValue()), b.getX()+10, b.getY()+10);
        }
    }
    
}