package MovingListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import NewPackage.Box;

public class Start_Swap_Listener implements ActionListener{

    Timer timer;
    int preX1;
    int preX2;
    Box box1;
    Box box2;
    int executionCount;

    public Start_Swap_Listener(Timer timer,Box box1, Box box2) {
        this.box1 = box1;
        this.box2 = box2;
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
            Box.move2Up_Down(box1, box2);
        }
        //Dung lai o 6, chuyen sang Mid
        else if(this.executionCount > 5){
            //Finish swapping
            this.executionCount++;
            Box.move2Up_Down(box2, box1);
            if(this.executionCount == 11) {
                //End swapping
                this.timer.removeActionListener(this);
            }
        } 
        else{
            //Mid swapping
            Box.move2Right_Left(box1, box2);
            if(box2.getX() <= this.preX1){
                box2.setX(preX1);
                box1.setX(preX2);
                this.executionCount = 6;
            }
        }  
    }       
}
