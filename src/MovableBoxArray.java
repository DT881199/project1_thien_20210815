import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MovableBoxArray extends JFrame {

    private List<MovableBox> boxes;
    private JButton moveButton;

    public MovableBoxArray() {
        boxes = new ArrayList<>();
        boxes.add(new MovableBox(50, 50, 50, 50, 1));
        boxes.add(new MovableBox(150, 50, 50, 50, 2));
        boxes.add(new MovableBox(250, 50, 50, 50, 3));

        moveButton = new JButton("Move Box");
        moveButton.setBackground(getForeground());
        moveButton.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                moveSelectedBox();
            }
        });
        setLayout(new FlowLayout());
        moveButton.setSize(new Dimension(10,100));
        getContentPane().add(moveButton);
        getContentPane().addMouseListener(new MouseAdapter() {
            private MovableBox selectedBox;


            @Override
            public void mousePressed(MouseEvent e) {
                for (MovableBox box : boxes) {
                    if (box.contains(e.getPoint())) {
                        selectedBox = box;
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedBox = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedBox != null) {
                    selectedBox.setX(e.getX() - selectedBox.getWidth() / 2);
                    selectedBox.setY(e.getY() - selectedBox.getHeight() / 2);
                    repaint();
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setVisible(true);
    }
    private void moveSelectedBox() {
        // Move the first box to a new location (you can customize this logic)
        if (!boxes.isEmpty()) {
            MovableBox firstBox = boxes.get(0);
            firstBox.setX(firstBox.getX() + 10); // Move 10 pixels to the right
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (MovableBox box : boxes) {
            box.draw(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovableBoxArray::new);
    }

    private static class MovableBox {
        private int x, y, width, height, value;

        public MovableBox(int x, int y, int width, int height, int value) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.value = value;
        }

        public boolean contains(Point point) {
            return new Rectangle(x, y, width, height).contains(point);
        }

        public void draw(Graphics g) {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
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
    }
}