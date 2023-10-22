import java.util.Random;

public class LogLane extends Lane {
    public LogLane(double y, int direction, double speed) {
        super(y, direction, speed);
    }

    public void update() {
        super.update();
        if (getItems().size() == 0) {
            getItems().add(new Log((direction == 3) ? 800 : 0, y, direction, speed, (int) (Math.random() * 3)));
        }
        
        while (getItems().size() < 5) {
            getItems().add(new Log(getItems().get(getItems().size() - 1).getX() + (((getItems().get(getItems().size() - 1).getRectangle().getWidth()) + 170) * ((getDirection() == 2)?1:-1)), y, direction, speed, (int) (Math.random() * 3)));
            
        }
        
        if (getItems().get(0).getX() < 0 - getItems().get(0).getRectangle().getWidth() || getItems().get(0).getX() > getItems().get(0).getRectangle().getWidth() + 800)
            getItems().remove(0);

        // System.out.println(getItems().size());

    }
}
