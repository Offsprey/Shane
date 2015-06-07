package a4.GameObjects;

import a4.IDrawable;
import a4.ITransformable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class CarBody implements ITransformable, IDrawable{
    private Point fr, fl, rr,rl ;
    private Color myColor ;
    private AffineTransform myTranslation ;
    private AffineTransform myRotation ;
    private AffineTransform myScale ;

    protected CarAxle front;
    protected CarAxle rear;
    
    public CarBody()
    {        
        myTranslation = new AffineTransform();
        myRotation = new AffineTransform();
        myScale = new AffineTransform();
        
        front = new CarAxle();
        front.translate(-15, 0);
        
        rear = new CarAxle();
        rear.translate(15, 0);       
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
        
        front.draw(g, drawSelected);
        rear.draw(g, drawSelected);

    }
}
