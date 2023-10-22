import java.awt.Rectangle;

public class FroggerItem {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public double x;
    public double y;
    public int direction;
    public int type;
    public double speed;
    public Rectangle rect;
    public double width;
    public double height;
    public double status = 0;
    
    public FroggerItem(double x, double y, double width, double height, int direction, double speed, int type){
        this.speed = speed;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.type = type;
        this.width = width;
        this.height = height;
        rect = new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

    public double getX(){return x;}
    public double getY(){return y;}
    public int getType(){return type;}
    public int getDirection(){return direction;}
    public Rectangle getRectangle(){return rect;}
    public void setX(double x){this.x = x;}
    public void setY(double y){this.y = y;}
    public void setDirection(int direction){this.direction = direction;}
    public void setRectangle(Rectangle rect){this.rect = rect;}

    public double getStatus(){return status;}
    
    public void update(){
        setY(getY() + ((direction == LEFT)?-1:1) * speed);
    }
}
