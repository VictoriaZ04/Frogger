public class LilyPad extends FroggerItem {
    
    public boolean frog = false;

    public LilyPad(double x, double y){
        super(x, y, 40, 40, 0, 0, 0);
    }

    public void setFrog(boolean frog){this.frog = frog;}
    public boolean getFrog(){return frog;}
    
}
