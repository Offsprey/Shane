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
public class FuelCan extends FixedObject{
    
    private int size;
    private final int DRAW_SIZE = 20;
    private AffineTransform myTranslation;
    private AffineTransform myRotation;
    private AffineTransform myScale;
    
    
    public FuelCan(int size, int[] color, GamePoint loc, AffineTransform aTranslation) {
        
        super(color, loc);
        
        this.size = size;
        
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

    public int getSize() {
        return size;
    }    

    @Override
    public String toString()
    {
        return "FuelCan: loc=" + this.oLoc.getX() + "," + this.oLoc.getDoubleY() + " color=["
                + this.oColor[0] + "," + this.oColor[1] + "," + this.oColor[2]
                + "] size=" + this.size;
    }

    @Override
    public boolean contains(double x, double y) {
        int cornerX = (int)(getoLoc().getDoubleX() - (size/2));
        int cornerY = (int)(getoLoc().getDoubleY() - (size/2));
        boolean isXrange = (x >= (cornerX - SELECT_BUFFER) && x <= (cornerX + DRAW_SIZE + SELECT_BUFFER));
        boolean isYrange = (y >= (cornerY - SELECT_BUFFER) && y <= (cornerY + DRAW_SIZE + SELECT_BUFFER));
        
        return (isXrange && isYrange);
    }

    @Override
    public void drawSelected(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g ;
        
        AffineTransform theTransform = g2d.getTransform();
        g2d.transform(myTranslation);
        g2d.transform(myRotation);
        
        int cornerX = (int)(getoLoc().getDoubleX() - (DRAW_SIZE/2));
        int cornerY = (int)(getoLoc().getDoubleY() - (DRAW_SIZE/2));
        //draw
        //creates a copy of the Graphics instance
        //Graphics2D g2d = (Graphics2D) g.create();
        //g2d.setColor(Color.black);
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
        g2d.setStroke(dashed);
        g2d.drawRect(SELECT_BUFFER, SELECT_BUFFER, DRAW_SIZE + SELECT_BUFFER*2, DRAW_SIZE + SELECT_BUFFER*2);        

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
        g.fillRect(0, 0, DRAW_SIZE, DRAW_SIZE);  
        g.setColor(Color.black);
        g.drawRect(0, 0, DRAW_SIZE, DRAW_SIZE);
        char[] sizeLbl = String.valueOf(this.size).toCharArray();
        g.drawChars(sizeLbl, 0, 1, 7, 15);
        //g.drawC
        
        g2d.setTransform(theTransform);
    }

    @Override
    public void handleCollision(ICollider otherObject, GameWorld gw) {
        gw.flagToDelete(this);
        gw.addNewFuel();
    }

    @Override
    public int getDrawSize() {
        return DRAW_SIZE;
    }

}
