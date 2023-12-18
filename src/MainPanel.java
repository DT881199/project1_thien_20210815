import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPanel extends JPanel {

    private List<Box> boxes;
    private JButton SortButton;

    //Constructor
    public MainPanel() {
        boxes = new ArrayList<Box>();
        SortButton = new JButton("Sort");
        SortButton.setBackground(getForeground());
        
        repaint();
        
    }

    //Set array
    public void setArray(int boxNumber, int first, int last){
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
            (this.boxes).add(new Box(10+rank, 50, 20, 50, value,1));
            rank += 100;
        }
    }

    //Set Listenner
    public void setListenner(ActionListener a){
        this.SortButton.addActionListener(a);
    }

    //Paint
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