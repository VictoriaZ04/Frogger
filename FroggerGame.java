import java.time.LocalTime;

public class FroggerGame implements Runnable {
    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int PLAYER_WINS = 2;

    public static final int MAX_LIFE_TIME = 80;
    public int status;
    public int startLifeTime;
    public boolean reachedMiddle;
    public int lives;
    public Frog player;
    public LogLane[] logLanes = new LogLane[3];
    public TurtleLane[] turtleLanes = new TurtleLane[2];
    public CarLane[] carLanes = new CarLane[5];
    public LilyPad[] lilyPad = new LilyPad[4];

    public FroggerGame() {
        status = PLAYING;
        player = new Frog(380, 525);
        startLifeTime = LocalTime.now().toSecondOfDay();

        reachedMiddle = false;

        lives = 3;

        lilyPad[0] = new LilyPad(128, 40);
        lilyPad[1] = new LilyPad(168 * 2 - (40), 40);
        lilyPad[2] = new LilyPad(168 * 3 - 40, 40);
        lilyPad[3] = new LilyPad(168 * 4 - 40, 40);

        carLanes[0] = new CarLane(480, 2, .10);
        carLanes[1] = new CarLane(440, 3, .05);
        carLanes[2] = new CarLane(400, 2, .10);
        carLanes[3] = new CarLane(360, 3, .15);
        carLanes[4] = new CarLane(320, 2, .10);

        logLanes[0] = new LogLane(200, 3, .10);
        logLanes[1] = new LogLane(160, 3, .10);
        logLanes[2] = new LogLane(80, 3, .10);

        turtleLanes[0] = new TurtleLane(120, 2, .10);
        turtleLanes[1] = new TurtleLane(240, 2, .10);
    }

    public Frog getPlayer() {
        return player;
    }

    public LogLane[] getLogLanes() {
        return logLanes;
    }

    public TurtleLane[] getTurtleLanes() {
        return turtleLanes;
    }

    public CarLane[] getCarLanes() {
        return carLanes;
    }

    public LilyPad[] getLilyPads() {
        return lilyPad;
    }

    public int getLives() {
        return lives;
    }

    public void playerDeath() {
        lives--;
        player.setX(380);
        player.setY((reachedMiddle)?280:520);
        player.getRectangle().setLocation(380, (reachedMiddle)?285:525);
        startLifeTime = LocalTime.now().toSecondOfDay();
        

        if (lives == 0) {
            status = DEAD;
        }
    }

    public int getTimeLeft() {
        return MAX_LIFE_TIME - (LocalTime.now().toSecondOfDay() - startLifeTime);
    }

    public void carCheck(){
        for(int i = 0; i < 5; i++){
            carLanes[i].update();
        }
        
        for(int i = 0; i < 5; i++){
            for(int c = 0; c < carLanes[i].getItems().size(); c++){
                if(player.getRectangle().intersects(carLanes[i].getItems().get(c).getRectangle())){
                    playerDeath();

                }
            }
        }
    }
    public void logCheck(){
        for(int i = 0; i < 3; i++){
            logLanes[i].update();
        }
        
    }
    public void turtleCheck(){
        for(int i = 0; i < 2; i++){
            turtleLanes[i].update();
        }
        
    }
    public void lilyCheck(){
        boolean win = true;
        for(int i = 0; i < 4; i++){
            if(player.getRectangle().intersects(lilyPad[i].getRectangle()) && !lilyPad[i].getFrog()){
                player.setX(380);
                player.setY(525);
                lilyPad[i].setFrog(true);
                startLifeTime = LocalTime.now().toSecondOfDay();
                reachedMiddle = false;
            }
            if(!lilyPad[i].getFrog())
                win = false;

        }
    }
    public void runChecks(){
        lilyCheck();
        logCheck();
        carCheck();
        turtleCheck();
    }


    @Override
    public void run() {
        for(int i = 0; i < 10000; i++){
            turtleCheck();
        }
        while (true) {

            runChecks();

            boolean check = false;
            for(int i = 0; i < 2; i++){
                for(FroggerItem l:turtleLanes[i].getItems()){
                    if(player.getRectangle().intersects(l.getRectangle()) && (int)l.getStatus() % 4 != 2){
                        check = true;

                    }
                }
            }
            for(int i = 0; i < 3; i++){
                for(FroggerItem l:logLanes[i].getItems()){
                    if(player.getRectangle().intersects(l.getRectangle())){
                        check = true;

                    }
                }
            }

            if(reachedMiddle && player.getY() < 280 && !check){
                playerDeath();
            }
                win();
                player.update();

                if(LocalTime.now().toSecondOfDay() - startLifeTime > MAX_LIFE_TIME){
                    playerDeath();

                    // startLifeTime = LocalTime.now().toSecondOfDay();
                }
                if(player.getY() < 280){
                    reachedMiddle = true;
                }

                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    // prints the error message
                    System.out.println("Error sleeping: " + e);
                }


        }
    }

    public boolean win() {
        for (LilyPad l :lilyPad) {
            if (!l.frog)
                return false;
        }
        status = PLAYER_WINS;
        return true;
    }

    public void reset(){
        lives = 3;
        startLifeTime = LocalTime.now().toSecondOfDay();
        reachedMiddle = false;
        player.setX(380);
        player.setY(525);
        player.getRectangle().setLocation((int)player.getX(), (int)player.getY());
    }

}
