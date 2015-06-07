package a4.GameObjects;

import a4.GamePoint;
import a4.GameWorld;
import a4.ICollider;
import a4.IDrivingStrategy;

import a4.KillStrategy;
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
public class NonPlayerCar extends Car{
    
    private int npcId;
    private IDrivingStrategy strat;

    public NonPlayerCar(int maxSpeed, int fuelLvl, int dmgLvl, int steerDir, int length, int height, int pylonNum, int heading, int gear, int[] color, GamePoint loc, int npcId,  AffineTransform aTranslation) {
        super(maxSpeed, fuelLvl, dmgLvl, steerDir, length, height, pylonNum, heading, gear, color, loc, aTranslation);
                this.npcId = npcId;
                this.setMaxDamage(1000);
    }

    public int getNpcId() {
        return npcId;
    }    

    public void setStrategy(IDrivingStrategy nStrat) {
        strat = nStrat;
    } 
    
    public IDrivingStrategy getStrategy()
    {
        return strat;
    }
    
    @Override
    public void move(int msec)
    {
        strat.apply();
        super.move(msec);
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " strategy=" + strat.toString(); 
    }

    @Override
    public void drawNormal(Graphics g) {
        Graphics2D g2d = (Graphics2D) g ;
        
        AffineTransform theTransform = g2d.getTransform();
        g2d.transform(myTranslation);
        g2d.transform(myRotation);
        
        
        Color cColor = new Color(getoColor()[0],getoColor()[1],getoColor()[2]);
        g.setColor(cColor);
        
        g.fillRect(0, 0, getLength(), getHeight());
        g.setColor(Color.black);
        g.drawRect(0, 0, getLength(), getHeight()); 
        
        
        g2d.setTransform(theTransform);
//        int cornerX = (int)(getoLoc().getDoubleX() - (getLength()/2));
//        int cornerY = (int)(getoLoc().getDoubleY() - (getHeight()/2));
//        Color cColor = new Color(getoColor()[0],getoColor()[1],getoColor()[2]);
//        g.setColor(cColor);
//        g.drawRect(cornerX, cornerY, getLength(), getHeight());
//        g.drawRect(cornerX + 1, cornerY + 1, getLength() - 2, getHeight() - 2);
        
    }
    
    @Override
    public void handleCollision(ICollider otherObject, GameWorld gw) {
        super.handleCollision(otherObject, gw);
        
        if (otherObject instanceof Pylon)
        {
            if (this.getStrategy() instanceof WinStrategy)
                        this.setStrategy(new WinStrategy(this,gw.getPylon(this.getPylonNum() + 1)));
        }
        if (otherObject instanceof PlayerCar)
        {
            if (this.getStrategy() instanceof KillStrategy)
                        this.setStrategy(new WinStrategy(this,gw.getPylon(this.getPylonNum() + 1)));
           
        }
    } 
}
