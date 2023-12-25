package NewPackage;

import java.awt.*;
import java.util.List;

import MovingListener.Start_Swap_Listener;

public class Box {
    private int x;
    private int y;
    private int width;
    private int height;
    private int value;
    private int speed;

    public static void SwapBox(int position1, int position2, MainPanel mainPanel) {
        if(position1 == position2) return;

        List<Box> boxes = mainPanel.getBoxes();
        Box box1 = boxes.get(position1);
        Box box2 = boxes.get(position2);
        boxes.set(position1, box2);
        boxes.set(position2, box1);

        mainPanel.getTimer().addActionListener(new Start_Swap_Listener(mainPanel.getTimer(), box1, box2));
    }

    public static void move2Up_Down(Box a, Box b){
        a.setY(a.getY() - a.speed);

        b.setY(b.getY() + b.speed);
    }

    public static void move2Right_Left(Box a, Box b){
        a.setX(a.getX() + a.speed);
        b.setX(b.getX() - b.speed);

    }

    public static void move1Left(Box a){
        a.setX(a.getX() - a.speed);
    }

    public static void move1Right(Box a){
        a.setX(a.getX() + a.speed);
    }

    public static void move1Up(Box a){
        a.setX(a.getY() - a.speed);
    }

    public static void move1Down(Box a){
        a.setX(a.getY() + a.speed);
    }

    public Box(int x, int y, int width, int height, int value, int speed) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.value = value;
        this.speed = speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        g.setColor(Color.RED);
        g.drawLine(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY());
        g.drawLine(this.getX(), this.getY(), this.getX(), this.getY() + this.getHeight());
        g.drawLine(this.getX() + this.getWidth(), this.getY() , this.getX() + this.getWidth(), this.getY() + this.getHeight());
        g.drawLine(this.getX(), this.getY() + this.getHeight(), this.getX() + this.getWidth(), this.getY() + this.getHeight());

        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);g.setColor(Color.BLACK);
        g.drawString(Integer.toString(this.getValue()), this.getX()+17, this.getY()+40);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public int getValue() {
        return value;
    }

}