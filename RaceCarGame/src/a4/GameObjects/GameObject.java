package a4.GameObjects;
import a4.GamePoint;
import a4.ICollider;
import a4.IDrawable;
import a4.ISelectable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public abstract class GameObject implements IDrawable, ISelectable, ICollider{
    
    protected boolean selected;
    protected int[] oColor;
    protected GamePoint oLoc;
    protected final int SELECT_BUFFER = 5;    
    
    public abstract AffineTransform getTranslation();
    
    public GameObject(int[] color, GamePoint loc)
    {
        oColor = color;
        oLoc = loc;
        selected = false;
    }

    @Override
    public abstract String toString(); 
    
    @Override
    public void draw(Graphics g, boolean drawSelected)
    {
        if (drawSelected)
        {
            if (this.isSelected())  
                g.setColor(Color.red);
            else
                g.setColor(Color.black);
            this.drawSelected(g);
            this.drawNormal(g);
        }
        else
        {
            this.drawNormal(g);
        }
    }
         
    public void setoColor(int[] oColor) {
        this.oColor = oColor;
    }

    public void setoLoc(GamePoint gp)
    {
        this.oLoc = gp;
    }
    
    public GamePoint getoLoc() {
        return oLoc;
    }  
    
    public static double getPolarCoord(GamePoint start, GamePoint end)
    {
        double rise = end.getY() - start.getY();
        double run = end.getX() - start.getX();
        
        double theta = Math.atan2(rise, run);
        if (theta < 0)
            theta += 2 * Math.PI;
        
        theta *= (180 / Math.PI);
        
         return theta;
    }
    
    @Override
    public boolean isSelected()
    {
            return selected;
    }
    
    @Override
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
    
    @Override
    public boolean collidesWith(ICollider otherObject) {
        boolean result = false;
        int thisCenterX = this.getoLoc().getX();
        int thisCenterY = this.getoLoc().getY();
        int otherCenterX = ((GameObject)otherObject).getoLoc().getX();
        int otherCenterY = ((GameObject)otherObject).getoLoc().getY();
        // find dist between centers (use square, to avoid taking roots)
        int dx = thisCenterX - otherCenterX;
        int dy = thisCenterY - otherCenterY;
        int distBetweenCentersSqr = (dx*dx + dy*dy);
        // find square of sum of radii
        int thisRadius = ((GameObject)otherObject).getDrawSize()/2;
        int otherRadius = this.getDrawSize()/2;
        int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
        + otherRadius*otherRadius);
        if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
        return result ;
    }
}
