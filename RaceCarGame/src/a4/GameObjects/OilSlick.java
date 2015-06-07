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
public class OilSlick extends FixedObject{
    
    private int height, length;
    private AffineTransform myTranslation;
    private AffineTransform myRotation;
    private AffineTransform myScale;

    public OilSlick(int length, int height, int[] color, GamePoint loc, AffineTransform aTranslation) {
        super(color, loc);
        this.height = height;
        this.length = length;
        
        myTranslation = aTranslation;
        myRotation = new AffineTransform();
        myScale = new AffineTransform();
        
        myTranslation.translate(loc.getX(), loc.getY());
        
    }

    @Override
    public AffineTransform getTranslation() {
        return this.myTranslation;
    }
    
    

    @Override
    public String toString()
    {
        return "OilSlick: loc=" + this.oLoc.getX() + "," + this.oLoc.getY() + " color=["
                + this.oColor[0] + "," + this.oColor[1] + "," + this.oColor[2]
                + "] width=" + this.height + " length=" + this.length;
    }

    @Override
    public boolean contains(double x, double y) {
        int cornerX = (int)(getoLoc().getDoubleX() - (length/2));
        int cornerY = (int)(getoLoc().getDoubleY() - (height/2));
        boolean isXrange = (x >= (cornerX - SELECT_BUFFER) && x <= (cornerX + length + SELECT_BUFFER));
        boolean isYrange = (y >= (cornerY - SELECT_BUFFER) && y <= (cornerY + height + SELECT_BUFFER));
        
        return (isXrange && isYrange);
    }

    @Override
    public void drawSelected(Graphics g) {
        int cornerX = (int)(getoLoc().getDoubleX() - (length/2));
        int cornerY = (int)(getoLoc().getDoubleY() - (height/2));
        //draw
        //creates a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();
        //g2d.setColor(Color.black);
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
        g2d.setStroke(dashed);
        g2d.drawRect(cornerX - SELECT_BUFFER, cornerY - SELECT_BUFFER, length + SELECT_BUFFER*2, height + SELECT_BUFFER*2);        

        //gets rid of the copy
        g2d.dispose();
    }

    @Override
    public void drawNormal(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g ;
        
        AffineTransform theTransform = g2d.getTransform();
        g2d.transform(myTranslation);
        g2d.transform(myRotation);
        
        //Draw
        g.setColor(Color.black);
        g.fillOval(0, 0, length, height);
        
        g2d.setTransform(theTransform);

    }

    @Override
    public void handleCollision(ICollider otherObject, GameWorld gw) {
        
    }

    @Override
    public int getDrawSize() {
        if (height > length)
            return height;
        else
            return length;
            
    }
    
}
