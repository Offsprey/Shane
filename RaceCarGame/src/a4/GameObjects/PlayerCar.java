package a4.GameObjects;

import a4.GamePoint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class PlayerCar extends Car{
    

    public PlayerCar(int maxSpeed, int fuelLvl, int dmgLvl, int steerDir, int length, int height, int pylonNum, int heading, int speed, int[] color, GamePoint loc,  AffineTransform aTranslation) {
        super(maxSpeed, fuelLvl, dmgLvl, steerDir, length, height, pylonNum, heading, speed, color, loc,  aTranslation);
        
        this.setMaxDamage(1000);
    }

    @Override
    public void drawNormal(Graphics g) {
        //Draw
        Graphics2D g2d = (Graphics2D) g ;
        
        AffineTransform theTransform = g2d.getTransform();
        g2d.transform(myTranslation);
        g2d.transform(myRotation);
        g2d.transform(myScale);
        
        
        Color cColor = new Color(getoColor()[0],getoColor()[1],getoColor()[2]);
        g.setColor(cColor);
        
//        g.fillRect(0, 0, getLength(), getHeight());
//        g.setColor(Color.black);
//        g.drawRect(0, 0, getLength(), getHeight()); 
        
        g2d.drawLine(fr.x, fr.y, fl.x,fl.y);
        g2d.drawLine(fl.x, fl.y, rl.x, rl.y);
        g2d.drawLine(rl.x, rl.y, rr.x, rr.y);
        g2d.drawLine(rr.x, rr.y, fr.x, fr.y);
        
        
        body.draw(g, selected); 
        
        g2d.setTransform(theTransform);

    }   
}
