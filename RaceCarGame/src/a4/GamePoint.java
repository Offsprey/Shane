package a4;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class GamePoint {
    private double x;
    private double y;

    public GamePoint()
    {
        
    }
    
    public GamePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int)x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return (int)y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public double getDoubleX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getDoubleY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public void setLocation(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    
}
