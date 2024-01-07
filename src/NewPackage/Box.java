package NewPackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;

import MovingListener.Insert_Listener;
import MovingListener.Merge_Listener;
import MovingListener.Swap_Listener;

public class Box {

    private static int distance;

    private int x;
    private int y;
    private int width;
    private int height;

    private String value;
    private Color color;


    public Box(int x, int y, int width, int height, int value, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.value = Integer.toString(value);
        this.color = color;
    }

    //-------         * All changes on Box List, timer and UI must be made on EDT *          -------//

    public static void MergeBoxes(Box[] mergedBoxArray,int left, int right, MainPanel mainPanel) {
        
        //All changes on Box List, timer and UI must be made on EDT
        SwingUtilities.invokeLater(() -> {
            //Update the real Box list using the array
            List<Box> boxList = new ArrayList<Box>(Arrays.asList(mergedBoxArray));
            mainPanel.setBoxes(boxList);

            mainPanel.getTimer().addActionListener(new Merge_Listener(mainPanel.getTimer(), mainPanel.getBoxes(), left, right));
        });
    }

    //Insert fromPos --> toPos, move those in middle 
    public static void InsertBox(int fromPos, int toPos, MainPanel mainPanel) {
        
        //All changes on Box List, timer and UI must be made on EDT
        SwingUtilities.invokeLater(() -> {
            if(fromPos == toPos) return;

            List<Box> boxes = mainPanel.getBoxes();
            Box insertingBox = boxes.get(fromPos);      
            for(int i = fromPos; i > toPos; i--){
                boxes.set(i, boxes.get(i-1));
            }
            boxes.set(toPos, insertingBox);
            
            mainPanel.getTimer().addActionListener(new Insert_Listener(mainPanel.getTimer(), boxes, fromPos, toPos));
        });
    }

    public static void SwapBox(int position1, int position2, MainPanel mainPanel) {

        //All changes on Box List, timer and UI must be made on EDT
        SwingUtilities.invokeLater(() -> {
            if(position1 == position2) return;

            List<Box> boxes = mainPanel.getBoxes();
            Box box1 = boxes.get(position1);
            Box box2 = boxes.get(position2);
            boxes.set(position1, box2);
            boxes.set(position2, box1);

            mainPanel.getTimer().addActionListener(new Swap_Listener(mainPanel.getTimer(), box1, box2));
        });
    }

    public static void move2Up_Down(Box a, Box b, int speed){
        a.setY(a.getY() - speed);
        b.setY(b.getY() + speed);
    }

    public static void move2Right_Left(Box a, Box b, int speed){
        a.setX(a.getX() + speed);
        b.setX(b.getX() - speed);

    }

    public static void move1Left(Box a, int speed){
        a.setX(a.getX() - speed);
    }

    public static void move1Right(Box a, int speed){
        a.setX(a.getX() + speed);
    }

    public static void move1Up(Box a, int speed){
        a.setY(a.getY() - speed);
    }

    public static void move1Down(Box a, int speed){
        a.setY(a.getY() + speed);
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        g.setColor(Color.RED);
        g.drawLine(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY());
        g.drawLine(this.getX(), this.getY(), this.getX(), this.getY() + this.getHeight());
        g.drawLine(this.getX() + this.getWidth(), this.getY() , this.getX() + this.getWidth(), this.getY() + this.getHeight());
        g.drawLine(this.getX(), this.getY() + this.getHeight(), this.getX() + this.getWidth(), this.getY() + this.getHeight());

        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);g.setColor(Color.BLACK);
        g.drawString(this.getValue(), this.getX()+(int)(Box.distance/1.75), this.getY()+(int)(Box.distance*1.5));
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    
    public static void setDistance(int distance) {
        Box.distance = distance;
    }

    public static int getDistance() {
        return Box.distance;
    }

}