package NewPackage;

import java.awt.*;

public class Box {
    private int x;
    private int y;
    private int width;
    private int height;
    private int value;
    private int speed;
    private int position;

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

    public Box(int position, int x, int y, int width, int height, int value, int speed) {
        this.position = position;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.value = value;
        this.speed = speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
        g.setColor(Color.GREEN);
        g.drawString(String.valueOf(value), x + width / 2 - 5, y + height / 2 + 5);
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