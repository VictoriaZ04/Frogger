public class Frog extends FroggerItem{

    public static final int UP = 0;
    public static final int DOWN = 1;

    public Frog(double x, double y){
        super(x, y, 40, 40, UP, 40, 0);
    }

    @Override
    public void update(){
        if(y < 280){
            if(y >= 240){
                x -= .10;
            }
            else if(y >= 160){
                x += .10;
            }
            else if( y >= 120){
                x -= .10;
            }
            else if(y >= 80){
                x += .10;
            }
        }
        if(x < 0){
            x = 0;
        }
        if(x > 756){
            x = 756;
        }
        if(y < 79
         ){
            y = 80;
        }
        if(y > 520){
            y = 520;
        }

        rect.setLocation((int)x, (int) y);


    }
  
}
