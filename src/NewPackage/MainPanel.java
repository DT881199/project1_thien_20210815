package NewPackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Interfaces.ICustomListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPanel extends JPanel implements ActionListener{

    private List<Box> boxes;
    private JButton SortButton;
    private int[] randomArray;
    private Graphics g;
    private Timer timer;
    private SwingWorker<Void, Integer> worker;
    private Boolean running;
    private MainFrame mainFrame;


    //Constructor
    public MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new FlowLayout());
        this.boxes = new ArrayList<Box>();
        this.timer = new Timer(50,this);
        this.running = false;
    }

    //Timer se repaint
    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
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

        this.boxes.clear();
        
        int distance = 500/boxNumber;
        Box.setDistance(distance);
        int x = 40;
        for(int value : randomArray){
            (this.boxes).add(new Box(x, 200, 2*distance, 3*distance, value, Color.GREEN));
            x += 3*distance;
        }
        this.repaint();
    }

//*******************************Setter & Getter*******************************//
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setWorker(SwingWorker<Void, Integer> worker) {
        this.worker = worker;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    public void setSortButton(ICustomListener customListener) {
        if(this.SortButton != null){
            this.remove(this.SortButton);
        }

        this.SortButton = new JButton("Sort");
        this.SortButton.setBackground(Color.BLUE);
        this.SortButton.addActionListener(customListener);
        System.out.println("here3");
        this.add(this.SortButton);
        this.SortButton.revalidate();
        this.repaint();
    }

    //Getters
    public int[] getRandomArray() {
        return randomArray;
    }
        
    public SwingWorker<Void, Integer> getWorker() {
        return worker;
    }

    public Graphics getG() {
        return g;
    }   

    public List<Box> getBoxes() {
        return boxes;
    }

    public Boolean getRunning() {
        return running;
    }

    public Timer getTimer() {
        return timer;
    }

    public JButton getSortButton() {
        return SortButton;
    }
    
    public MainFrame getMainFrame() {
        return mainFrame;
    }
//*****************************************************************************//

    //Paint
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Box b : this.boxes){
            b.draw(g);
        }
    }
}