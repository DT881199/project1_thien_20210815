package com.MovingListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import com.Main.Box;

public class Merge_Listener implements ActionListener{

    Timer timer;
    List<Box> boxes;
    int left; 
    int right;
    int preX;
    int position;
    int executionCount;

    public Merge_Listener(Timer timer, List<Box> boxes, int left, int right){
        this.left = left;
        this.right = right;

        this.position = left;
        this.preX = boxes.get(this.position).getX();

        this.boxes = boxes;
        
        this.executionCount = 0;
        this.timer = timer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Start merging

        if(executionCount < 10){
            this.executionCount++;
            Box.move1Down(boxes.get(this.position), 20);

            double speed = (double)((this.preX - 40 - Box.getDistance()*3*this.position)/10);
            if(speed > 0) Box.move1Left(boxes.get(this.position), (int)Math.ceil(speed));
            else if(speed < 0) Box.move1Left(boxes.get(this.position), (int)Math.floor(speed));
            
            if(
                (speed > 0 && boxes.get(this.position).getX() - 40 <= Box.getDistance()*3*this.position)    
                 ||
                (speed < 0 && boxes.get(this.position).getX() - 40 >= Box.getDistance()*3*this.position)
            ){
                boxes.get(this.position).setX(Box.getDistance()*3*this.position + 40);
            }

            if(this.executionCount == 10){
                boxes.get(this.position).setX(Box.getDistance()*3*this.position + 40);
                this.position++;
  
                if(this.position > this.right){
                    this.executionCount = 11;
                }
                else{
                    this.executionCount = 0;
                    this.preX = boxes.get(this.position).getX();
                }
            }
        }
        else if(this.executionCount > 10){
            //Finish merging
            this.executionCount++;

            for(int i = left; i <= right; i++){
                Box.move1Up(boxes.get(i), 20);
            }

            if(this.executionCount == 21){
                this.timer.removeActionListener(this);
            }
        } 
    }   
}
