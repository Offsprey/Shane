package a4.GameObjects;

import a4.GamePoint;
import a4.GameWorld;
import a4.ICollider;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class Bird extends MovableObject{
    
    private int size;
    private AffineTransform myTranslation;
    private AffineTransform myRotation;
    private AffineTransform myScale;

    public Bird(int size, int heading, int speed, int[] color, GamePoint loc, AffineTransform aTranslation) {
        super(heading, color, loc);
        this.setSpeed(speed);
        this.size = size;
        
        myTranslation = aTranslation;
        myRotation = new AffineTransform();
        myRotation.rotate(Math.toRadians(heading));
        myScale = new AffineTransform();
        
        myTranslation.translate(loc.getX(), loc.getY());
    }  
    
    public void move(int msec)
    {           
        
        double radians = (Math.PI * this.heading) / 180;
        
        double deltaX = Math.cos(radians) * this.getSpeed()  * (msec/1000.0);
        double deltaY = Math.sin(radians) * this.getSpeed()  * (msec/1000.0);
        
        GamePoint newLocation = new GamePoint();
        newLocation.setLocation(this.oLoc.getDoubleX() + deltaX,this.oLoc.getDoubleY() + deltaY);

        this.oLoc = newLocation;    
        
        myTranslation.translate(deltaX, deltaY);
     
    }
    
    @Override
    public AffineTransform getTranslation() {
        return this.myTranslation;
    }
    
    public int getSize() {
        return size;
    }
    
    @Override
    public void setoColor(int[] color)
    {
       //Do Nothing
    }

    @Override
    public String toString() {
        return "Bird: loc=" + this.oLoc.getX() + "," + this.oLoc.getY() + " color=["
                + this.oColor[0] + "," + this.oColor[1] + "," + this.oColor[2]
                + "] heading=" + this.heading + " speed=" + this.speed
                + " size=" + this.size;
    
    }
    
    @Override
    public boolean contains(double x, double y) {
        int cornerX = (int)(getoLoc().getDoubleX() - (size/2));
        int cornerY = (int)(getoLoc().getDoubleY() - (size/2));
        boolean isXrange = (x >= (cornerX - SELECT_BUFFER) && x <= (cornerX + size + SELECT_BUFFER));
        boolean isYrange = (y >= (cornerY - SELECT_BUFFER) && y <= (cornerY + size + SELECT_BUFFER));
        
        return (isXrange && isYrange);
    }

    @Override
    public void drawSelected(Graphics g) {
        int cornerX = (int)(getoLoc().getDoubleX() - (size/2));
        int cornerY = (int)(getoLoc().getDoubleY() - (size/2));
        //draw
        //creates a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();
        //g2d.setColor(Color.black);
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
        g2d.setStroke(dashed);
        g2d.drawRect(cornerX - SELECT_BUFFER, cornerY - SELECT_BUFFER, size + SELECT_BUFFER*2, size + SELECT_BUFFER*2);        

        //gets rid of the copy
        g2d.dispose();
    }

    @Override
    public void drawNormal(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g ;
        
        AffineTransform theTransform = g2d.getTransform();
        g2d.transform(myTranslation);
        g2d.transform(myRotation);
        
        g.setColor(Color.black);
        g.drawOval(0, 0, size, size);
        
        g2d.setTransform(theTransform);
    }

    @Override
    public void handleCollision(ICollider otherObject, GameWorld gw) {
        if (otherObject instanceof Car)
        {
            gw.flagToDelete(this);
            gw.addNewBird();
        }
    }

    @Override
    public int getDrawSize() {
        return size;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    

}
