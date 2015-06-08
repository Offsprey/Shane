package a4.GameObjects;

import a4.IDrawable;
import a4.ITransformable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class CarWheel implements ITransformable, IDrawable{
    private Point fr, fl, rr,rl ;
    private Color myColor ;
    private AffineTransform myTranslation ;
    private AffineTransform myRotation ;
    private AffineTransform myScale ;
    
    private int dir = 0;


	public CarWheel()
    {
        myTranslation = new AffineTransform();
        myRotation = new AffineTransform();
        myScale = new AffineTransform();
        
        fr = new Point(8, -3);
        fl = new Point(-8, -3);
        rr = new Point(8, 3);
        rl = new Point(-8, 3);
    }
    

    
    public int getDir() {
		return dir;
	}



	public void setDir(int dir) {
		this.dir = dir;
	}

    
    @Override
    public void translate(int x, int y) {
        this.myTranslation.translate(x, y); 
    }

    @Override
    public void scale(int xScale, int yScale) {
        this.myScale.scale(yScale, yScale);
    }

    @Override
    public void rotate(int heading) {
        this.myRotation.rotate(heading);
    }

    @Override
    public void draw(Graphics g, boolean drawSelected) {
        Graphics2D g2d = (Graphics2D) g ;
        
        AffineTransform saveAT = g2d.getTransform() ;
        // Append this shape's transforms to the graphics object's transform. Note the
        // ORDER: Translation will be done FIRST, then Scaling, and lastly Rotation
        myRotation.rotate(Math.toRadians(-dir));
        
        g2d.transform(myScale);
        g2d.transform(myTranslation);
        g2d.transform(myRotation);
        
        g2d.drawLine(fr.x, fr.y, fl.x,fl.y);
        g2d.drawLine(fl.x, fl.y, rl.x, rl.y);
        g2d.drawLine(rl.x, rl.y, rr.x, rr.y);
        g2d.drawLine(rr.x, rr.y, fr.x, fr.y);
        
        myRotation.rotate(Math.toRadians(dir));
        
        g2d.setTransform (saveAT) ;
    }
    
}
