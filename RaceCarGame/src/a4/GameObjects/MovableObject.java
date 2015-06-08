package a4.GameObjects;

import a4.GamePoint;
import a4.GameWorld;
import a4.ICollider;
import java.util.Vector;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public abstract class MovableObject extends GameObject{
    protected int heading, speed;
    
    private Vector<GameObject> collideList;
    
    public MovableObject(int heading, int[] color, GamePoint loc) {
        super(color, loc);
        this.heading = heading;
        collideList = new Vector<GameObject>();
    } 
    
    public abstract int getSpeed();
    
    public static int fixHeading(double rawDirection)
    {
        double newDirection = rawDirection;
        
        if (rawDirection < 0)
            newDirection = 360 + rawDirection;
        
        if (rawDirection >= 360)
            newDirection = rawDirection % 360;
        
        return (int)newDirection;
    }
    
    public static int fixDirection(double rawDirection)
    {
        double newDirection = rawDirection;
        
        if (rawDirection < -45)
            newDirection = -45;
        
        if (rawDirection >45)
            newDirection = 45;
        
        return (int)newDirection;
    }
    
    public abstract void move(int msec);
    
    public void bounceAway(GameObject otherObject)
    {
        int targetDirection = (int) GameObject.getPolarCoord(this.getoLoc(), otherObject.getoLoc());
        targetDirection = MovableObject.fixDirection(targetDirection + 180);        
        
        
        double radians = (Math.PI * targetDirection) / 180;
        
        double deltaX = Math.cos(radians) * 10;
        double deltaY = Math.sin(radians) * 10;
        
        GamePoint newLocation = new GamePoint();
        newLocation.setLocation(this.oLoc.getDoubleX() + deltaX,this.oLoc.getDoubleY() + deltaY);

        this.oLoc = newLocation; 
    }

    public void addCollision(GameObject gObject){
        collideList.add(gObject);
    }
    
    public void resetCollisions()
    {
        collideList = new Vector<GameObject>();
    }
    
    public void setHeading(int heading)
    {        
            this.heading = MovableObject.fixDirection(heading);
        
    }
    
    public void reverseHeading()
    {
        this.setHeading(heading + 180);
    }
    
    public boolean previousCollision(GameObject gObject)
    {
        if (this.collideList.contains(gObject))
            return true;
        else
            return false;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    
}
