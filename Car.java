public class Car extends FroggerItem{
    public static final int SEMI = 0;
    public static final int LIMO = 1;
    public static final int CAR_1 = 2;
    public static final int CAR_2 = 3;

    public Car(double x, double y, int direction, double speed, int type){
        super(x, y, (type == 0)?120:(type == 1)?80:40, 40, direction, speed, type);
    }

    public int getWidth(){return (int)width;}
}
