public class Turtle extends FroggerItem{
    public static final int SHORT = 0;
    public static final int MEDIUM = 1;
    public static final int LONG = 2;
    public double status = (int)(Math.random() * 4);
    

    public Turtle(double x, double y, int direction, double speed, int type){
        super(x, y, (type == 0)?40:(type == 1)?80:120, 40, direction, speed, type);
    }

    public int getWidth(){return (int)width;}

    public double getStatus(){return status;}

    @Override
    public void update(){
        status += 0.0005;
    }
}
