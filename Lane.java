import java.util.ArrayList;
import java.awt.Rectangle;

public class Lane {
    double y;
    int direction;
    double speed;
    ArrayList<FroggerItem> items = new ArrayList<FroggerItem>();
    public static Rectangle screen = new Rectangle(0,0,920,720);
    
    public Lane(double y, int direction, double speed){
        this.speed = speed;
        this.direction = direction;
        this.y = y;
    }

    public double getY(){return y;}
    public double getSpeed(){return speed;}
    public int getDirection(){return direction;}
    public void update(){
        for(FroggerItem i:items){
            i.setX(i.getX() + (speed * ((direction == 2)?-1:1)));
            i.getRectangle().setLocation((int) i.getX(), (int)y);
        }
    }
    public ArrayList<FroggerItem> getItems(){ return items;}
}
    
