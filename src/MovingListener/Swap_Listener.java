package MovingListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Main.Box;

public class Swap_Listener implements ActionListener{

    Timer timer;
    int preX1;
    int preX2;
    Box box1;
    Box box2;
    int executionCount;
    Color preColor1;
    Color preColor2;

    public Swap_Listener(Timer timer,Box box1, Box box2) {
        this.box1 = box1;
        this.box2 = box2;

        this.preColor1 = box1.getColor();
        this.preColor2 = box2.getColor();
        this.box1.setColor(Color.ORANGE);
        this.box2.setColor(Color.ORANGE);

        this.preX1 = box1.getX();
        this.preX2 = box2.getX();

        this.executionCount = 0;
        this.timer = timer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Start swapping
        if(executionCount < 5){
            this.executionCount++;
            Box.move2Up_Down(box1, box2, 20);
        }

        //Dung lai o 6, chuyen sang Mid
        else if(this.executionCount > 5){

            //Finish swapping
            this.executionCount++;
            Box.move2Up_Down(box2, box1, 20);

            if(this.executionCount == 11) {
                //End swapping
                this.box1.setColor(this.preColor1);
                this.box2.setColor(this.preColor2);
                this.timer.removeActionListener(this);
            }
        }

        else{
            //Mid swapping
            double speed = (double)((preX2-preX1)/10);
            Box.move2Right_Left(box1, box2, (int)Math.ceil(speed));
            if(box2.getX() <= this.preX1){
                box2.setX(preX1);
                box1.setX(preX2);
                this.executionCount = 6;
            }
        }
    }   
}
