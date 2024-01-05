package MovingListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import NewPackage.Box;

public class Insert_Listener implements ActionListener{

    private Timer timer;
    private int executionCount;
    private int fromPos; 
    private int toPos;
    private List<Box> boxes;
    private int[] prePos;
    private double speed1;
    private double speed2;

    public Insert_Listener(Timer timer, List<Box> boxes, int fromPos, int toPos) {
        this.boxes = boxes;
        this.executionCount = 0;
        this.timer = timer;
        this.toPos = toPos;
        this.fromPos = fromPos;

        this.speed1 = (double)(Box.getDistance()*3/10);
        this.speed2 = (double)((boxes.get(toPos).getX()-boxes.get(toPos+1).getX())/10);

        this.prePos = new int[fromPos-toPos+1];

        boxes.get(toPos).setColor(Color.RED);

        /** 0 --> fromPos-toPos-1 || toPos+1 --> fromPos **/
        for(int i = 0; i < fromPos-toPos; i++){
            prePos[i] = this.boxes.get(i+toPos+1).getX();
            this.boxes.get(i+toPos+1).setColor(Color.ORANGE);
        }
        this.prePos[fromPos-toPos] = this.boxes.get(toPos).getX();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Start inserting
        if(executionCount < 5){
            this.executionCount++;
            Box.move1Up(boxes.get(this.toPos), 20);
        }
        //Dung lai o 6, chuyen sang Mid
        else if(this.executionCount > 5){
            //Finish inserting
            this.executionCount++;
            Box.move1Down(boxes.get(this.toPos), 20);
            
            if(this.executionCount == 11){
                //End inserting
                boxes.get(toPos).setColor(Color.GREEN);
                for(int i = 0; i < fromPos-toPos; i++){
                    this.boxes.get(i+toPos+1).setColor(Color.GREEN);
                }              
                this.timer.removeActionListener(this);
            }
        } 
        //Dung lai o 6, chuyen sang Mid
        else{
            //Mid inserting
            Box.move1Left(boxes.get(toPos), (int)Math.ceil(this.speed2));
            for(int i = fromPos; i > toPos; i--){
                Box.move1Right(boxes.get(i), (int)Math.ceil(this.speed1));
            }
            if(boxes.get(toPos+1).getX() >= this.prePos[1]){
                for(int i = toPos; i <= fromPos; i++){
                    boxes.get(i).setX(this.prePos[i-toPos]);
                }
                this.executionCount = 6;
            }
        }
    }  
}       
