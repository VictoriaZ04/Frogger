import java.awt.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.image.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import java.applet.*;
import java.net.URL;


public class Panel extends JPanel implements KeyListener, Runnable {

    BufferedImage buffer;
    BufferedImage car1_Left = ImageIO.read(new File("Frogger Images/Car 1 - Left.png"));
    BufferedImage car1_Right = ImageIO.read(new File("Frogger Images/Car 1 - Right.png"));
    BufferedImage car2_Left = ImageIO.read(new File("Frogger Images/Car 2 - Left.png"));
    BufferedImage car2_Right = ImageIO.read(new File("Frogger Images/Car 2 - Right.png"));
    BufferedImage limo_Left = ImageIO.read(new File("Frogger Images/Limo - Left.png"));
    BufferedImage limo_Right = ImageIO.read(new File("Frogger Images/Limo - Right.png"));
    BufferedImage semi_Left = ImageIO.read(new File("Frogger Images/Semi - Left.png"));
    BufferedImage semi_Right = ImageIO.read(new File("Frogger Images/Semi - Right.png"));
    BufferedImage frogUp = ImageIO.read(new File("Frogger Images/Frog Up.png"));
    BufferedImage frogDown = ImageIO.read(new File("Frogger Images/Frog Down.png"));
    BufferedImage frogLeft = ImageIO.read(new File("Frogger Images/Frog Left.png"));
    BufferedImage frogRight = ImageIO.read(new File("Frogger Images/Frog Right.png"));
    BufferedImage hsTurtle = ImageIO.read(new File("Frogger Images/HS-Turtle.png"));
    BufferedImage hmTurtle = ImageIO.read(new File("Frogger Images/HM-Turtle.png"));
    BufferedImage hlTurtle = ImageIO.read(new File("Frogger Images/HL-Turtle.png"));
    BufferedImage sTurtle = ImageIO.read(new File("Frogger Images/S-Turtle.png"));
    BufferedImage mTurtle = ImageIO.read(new File("Frogger Images/M-Turtle.png"));
    BufferedImage lTurtle = ImageIO.read(new File("Frogger Images/L-Turtle.png"));
    BufferedImage sLog = ImageIO.read(new File("Frogger Images/S-Log.png"));
    BufferedImage mLog = ImageIO.read(new File("Frogger Images/M-Log.png"));
    BufferedImage lLog = ImageIO.read(new File("Frogger Images/L-Log.png"));
    BufferedImage lilyPad = ImageIO.read(new File("Frogger Images/lilyPad.png"));
    BufferedImage frogLife = ImageIO.read(new File("Frogger Images/Frog Life.png"));

    File frogeFile;
    AudioInputStream frogeStream;
    Clip froge; 
        

    FroggerGame game = new FroggerGame();

    public Panel() throws IOException, UnsupportedAudioFileException, LineUnavailableException{
        File carFile = new File("Sounds/Car.wav");
        AudioInputStream carStream = AudioSystem.getAudioInputStream(carFile);
        Clip car = AudioSystem.getClip(); 
        car.open(carStream); 
		car.loop(Clip.LOOP_CONTINUOUSLY); 

        File waterFile = new File("Sounds/River.wav");
        AudioInputStream waterStream = AudioSystem.getAudioInputStream(waterFile);
        Clip water = AudioSystem.getClip(); 
        water.open(waterStream); 
		water.loop(Clip.LOOP_CONTINUOUSLY); 

        frogeFile = new File("Sounds/Froge.wav");
        frogeStream = AudioSystem.getAudioInputStream(frogeFile);
        froge = AudioSystem.getClip(); 
        froge.open(frogeStream); 



        setSize(800, 600); // width,height
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        Thread t = new Thread(game);
        t.start();
        addKeyListener(this);
        Thread j = new Thread(this);
        j.start();

    }

    public void paint(Graphics g) {
        // code to draw to the screen using Graphics Commands
        Graphics bg = buffer.createGraphics();

        // BACKGROUND
        bg.setColor(Color.BLUE);
        bg.fillRect(0, 40, 800, 240);
        bg.setColor(Color.GREEN);
        bg.fillRect(0, 0, 800, 40);
        for (int i = 0; i < 5; i++) {
            bg.fillRect(i * 168, 40, 128, 40);
        }
        bg.fillRect(0, 280, 800, 40);
        bg.fillRect(0, 520, 800, 40);

        bg.setColor(Color.GRAY);
        bg.fillRect(0, 320, 800, 200);

        bg.setColor(Color.YELLOW);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                bg.fillRect(j * 93 + 10, i * 40 + 360, 73, 4);
            }
        }

        bg.setColor(Color.BLACK);
        bg.fillRect(0, 560, 800, 40);

        bg.setColor(Color.RED);
        bg.setFont(new Font("Times New Roman", Font.BOLD, 32));
        bg.drawString("Lives: ", 50, 588);
        bg.drawString("Time Left: ", 400, 588);

        if(game.getTimeLeft() < 20)
            bg.setColor(Color.RED);
        else if(game.getTimeLeft() < 50)
            bg.setColor(Color.YELLOW);
        else
            bg.setColor(Color.GREEN);
        if(game.status == game.PLAYING)
            bg.fillRect(550, 570, game.getTimeLeft() * 2, 20);

        for(int i = 0; i < game.getLives(); i++){
            bg.drawImage(frogLeft, 150 + (i * 50), 560, null);
        }


        // LOG
        for (LogLane lane : game.getLogLanes()) {
            for (FroggerItem c : lane.getItems()) {
                bg.drawImage(
                        (c.getType() == 0)?sLog:(c.getType() == 1)?mLog:lLog,
                        (int) c.getX(), (int) c.getY(), null);
            }
        }


        //TURTLE
        
        for (TurtleLane lane : game.getTurtleLanes()) {
            for (FroggerItem c : lane.getItems()) {
                if((int)c.getStatus() % 4 == 0){
                    bg.drawImage((c.getType() == 0)?sTurtle:(c.getType() == 1)?mTurtle:lTurtle,(int) c.getX(), (int) c.getY(), null);
                }
                if((int)c.getStatus() % 4 == 1 || (int)c.getStatus() % 4 == 3){
                    bg.drawImage((c.getType() == 0)?hsTurtle:(c.getType() == 1)?hmTurtle:hlTurtle,(int) c.getX(), (int) c.getY(), null);
                }
            }
        }

        
        //CAR
        for (CarLane lane : game.getCarLanes()) {
            for (FroggerItem c : lane.getItems()) {
                bg.drawImage(
                        (c.getDirection() == 2)
                                ? ((c.getType() == 0) ? semi_Left
                                        : (c.getType() == 1) ? limo_Left : (c.getType() == 2) ? car1_Left : car2_Left)
                                : ((c.getType() == 0) ? semi_Right
                                        : (c.getType() == 1) ? limo_Right
                                                : (c.getType() == 2) ? car1_Right : car2_Right),
                        (int) c.getX(), (int) c.getY(), null);
            }
        }

        // LILLY PAD & FROGE
        bg.drawImage(lilyPad, 128, 42, null);
        bg.drawImage(lilyPad, 168 * 2 - (40), 42, null);
        bg.drawImage(lilyPad, 168 * 3 - 40, 42, null);
        bg.drawImage(lilyPad, 168 * 4 - 40, 42, null);
        for (int i = 0; i < 4; i++) {
            if (i == 0 && game.getLilyPads()[i].getFrog()) {
                bg.drawImage(frogUp, 128, 42, null);
            }
            if (game.getLilyPads()[i].getFrog()) {
                bg.drawImage(frogUp, 168 * (i + 1) - (40), 42, null);
            }
        }
        if(game.status == game.PLAYING){
            int dir = game.getPlayer().getDirection();

            bg.drawImage((dir == 0) ? frogUp : (dir == 1) ? frogDown : (dir == 2) ? frogLeft : frogRight,
                    (int) game.getPlayer().getX(), (int) game.getPlayer().getY(), null);
        }
    
        
        

        
        // WIN
        if (game.status == game.PLAYER_WINS) {
            bg.setColor(Color.BLACK);
            bg.drawString("YOU WIN!", 300, 270);
            bg.drawString("Press \"n\" to replay!", 250, 310);
        } else if (game.status == game.DEAD) {
            bg.setColor(Color.BLACK);
            bg.drawString("YOU DIED!", 300, 270);
            bg.drawString("Press \"n\" to replay!", 250, 310);
        }

        // DRAW SCREEN
        g.drawImage(buffer, 0, 0, null);

    }

    @Override
    public void addNotify() {

        super.addNotify();
        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (game.status == game.PLAYING) {
            if (e.getKeyChar() == 'w') {
                froge.start();
                game.getPlayer().setDirection(0);
                game.getPlayer().setY(game.getPlayer().getY() - 40);

            } else if (e.getKeyChar() == 's') {
                froge.start();

                game.getPlayer().setDirection(1);
                game.getPlayer().setY(game.getPlayer().getY() + 40);
            } else if (e.getKeyChar() == 'a') {
                froge.start();

                game.getPlayer().setDirection(2);
                game.getPlayer().setX(game.getPlayer().getX() - 42);
            } else if (e.getKeyChar() == 'd') {
                froge.start();

                game.getPlayer().setDirection(3);
                game.getPlayer().setX(game.getPlayer().getX() + 42);
            }
            game.getPlayer().getRectangle().setLocation((int) game.getPlayer().getX(), (int) game.getPlayer().getY());
        } else {
            if (e.getKeyChar() == 'n') {
                reset();
            }
        }
        // game.lilyCheck();

        // repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                // prints the error message
                System.out.println("Error sleeping: " + e);
            }
        }
    }

    public void reset() {
        game.getPlayer().setX(378);
        game.getPlayer().setY(525);
        game.getPlayer().getRectangle().setLocation((int) game.getPlayer().getX(), (int) game.getPlayer().getY());

        game.status = game.PLAYING;
        game.reset();

        for (int i = 0; i < 4; i++)
            game.getLilyPads()[i].setFrog(false);

        repaint();
    }

}