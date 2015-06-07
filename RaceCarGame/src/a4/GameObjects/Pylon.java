package a4.GameObjects;

import a4.GamePoint;
import a4.GameWorld;
import a4.ICollider;
import a4.WinStrategy;
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
public class Pylon extends FixedObject{
    
   private int radius, seqNum;
   private AffineTransform myTranslation;
    private AffineTransform myRotation;
    private AffineTransform myScale;

    public Pylon(int radius, int seqNum, int[] color, GamePoint loc, AffineTransform aTranslation) {
        super(color, loc);
        this.radius = radius;
        this.seqNum = seqNum;
        
        myTranslation = aTranslation;
        myRotation = new AffineTransform();
        myScale = new AffineTransform();
        myScale.scale(1,-1);
        
        myTranslation.translate(loc.getX(), loc.getY());
    }
    
    @Override
    public AffineTransform getTranslation() {
        return this.myTranslation;
    }
    
    public AffineTransform getTranslationTransform(){
        return ((AffineTransform) myTranslation.clone());
    }
   
    public int getSeqNum() {
        return seqNum;
    }

    public int getRadius() {
        return radius;
    }   
    
    @Override
    public void setoColor(int[] color)
    {
       //Do Nothing
    }
    
   @Override
   public String toString()
   {
       String retStr = "Pylon: loc=" +  this.oLoc.getX() + "," + this.oLoc.getY();
       retStr += " color=[" + this.oColor[0] + "," + this.oColor[1] + "," + this.oColor[2] + "]";
       retStr += " radius=" + radius + " seqNum=" + seqNum;
       return retStr;
   }

    @Override
    public boolean contains(double x, double y) {
        int cornerX = (int)(getoLoc().getDoubleX() - (radius));
        int cornerY = (int)(getoLoc().getDoubleY() - (radius));
        boolean isXrange = (x >= (cornerX - SELECT_BUFFER) && x <= (cornerX + radius*2 + SELECT_BUFFER));
        boolean isYrange = (y >= (cornerY - SELECT_BUFFER) && y <= (cornerY + radius*2 + SELECT_BUFFER));
        
        return (isXrange && isYrange);
    }

    @Override
    public void drawSelected(Graphics g) {
        int cornerX = (int)(getoLoc().getDoubleX() - (radius));
        int cornerY = (int)(getoLoc().getDoubleY() - (radius));
        //draw
        //creates a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();
        //g2d.setColor(Color.black);
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
        g2d.setStroke(dashed);
        g2d.drawRect(cornerX - SELECT_BUFFER, cornerY - SELECT_BUFFER, (radius * 2) + SELECT_BUFFER*2, (radius * 2) + SELECT_BUFFER*2);        

        //gets rid of the copy
        g2d.dispose();
    }

    @Override
    public void drawNormal(Graphics g) {
        
        
        Graphics2D g2d = (Graphics2D) g ;
        
        AffineTransform theTransform = g2d.getTransform();
        g2d.transform(myTranslation);
        g2d.transform(myRotation);
        g2d.transform(myScale);
        
        g.setColor(Color.lightGray);
        g.fillOval(0, 0, getRadius() * 2, getRadius() * 2);  
        g.setColor(Color.black);
        g.drawOval(0, 0, getRadius() * 2, getRadius() * 2);
        char[] pylonLbl = String.valueOf(getSeqNum()).toCharArray();
        g.drawChars(pylonLbl, 0, 1, 7, 15);
        
        g2d.setTransform(theTransform);
    }

    @Override
    public void handleCollision(ICollider otherObject, GameWorld gw) {
        //do nothing           
    }

    @Override
    public int getDrawSize() {
        return radius * 2;
    }
}
