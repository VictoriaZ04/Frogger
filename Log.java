public class Log extends FroggerItem{
    public static final int SHORT = 0;
    public static final int MEDIUM = 1;
    public static final int LONG = 2;

    public Log(double x, double y, int direction, double speed, int type){
        super(x, y, (type == 0)?80:(type == 1)?120:200, 40, direction, speed, type);
    }

    public int getWidth(){return (int)width;}
}
