package a4.GameObjects;

import a4.GamePoint;
import a4.GameWorld;
import a4.ICollider;
import a4.ISteerable;
import a4.WinStrategy;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public abstract class Car extends MovableObject implements ISteerable{
    
    private int maxSpeed, steerDir, length, height, pylonNum, maxDamage, steerOffset, gear; 
    private double fuelLvl, dmgLvl;
    protected Point fr, fl, rr,rl ;
    protected AffineTransform myTranslation;
    protected AffineTransform myRotation;
    protected AffineTransform myScale;
    
    
    protected CarBody body;
    
    private boolean isOilSlick;
    
    //private int maxDamage = 20;

    public Car(int maxSpeed, double fuelLvl, double dmgLvl, int steerDir, int length, int height, int pylonNum, int heading, int gear, int[] color, GamePoint loc, AffineTransform aTranslation) {
        super(heading, color, loc);
        this.maxSpeed = maxSpeed;
        this.fuelLvl = fuelLvl;
        this.dmgLvl = dmgLvl;
        this.steerDir = steerDir;
        this.length = length;
        this.height = height;
        this.pylonNum = pylonNum;
        this.gear = gear;
        
        fr = new Point(20, -10);
        fl = new Point(-20, -10);
        rr = new Point(20, 10);
        rl = new Point(-20, 10);
        
        body = new CarBody();
        
        myTranslation = new AffineTransform();
        myRotation = new AffineTransform();
        myRotation.rotate(Math.toRadians(heading));
        myScale = new AffineTransform();
        myScale.scale(-1, 1);
        
        myTranslation.translate(loc.getX(), loc.getY());
        
        isOilSlick = false;
    }
    
    @Override
    public void move(int msec)
    {
        updateHeading();
        
        //double θ = 90 - this.heading;
        
        double radians = (Math.PI * this.heading) / 180;
        
        double deltaX = Math.cos(radians) * this.getSpeed()  * (msec/1000.0);
        double deltaY = Math.sin(radians) * this.getSpeed()  * (msec/1000.0);
        
        GamePoint newLocation = new GamePoint();
        newLocation.setLocation(this.oLoc.getDoubleX() + deltaX,this.oLoc.getDoubleY() + deltaY);

        this.oLoc = newLocation;  
        
        myRotation.setToRotation(Math.toRadians(heading));
        myTranslation.translate(deltaX, deltaY);
    }    
    
    @Override
    public boolean contains(double x, double y) {
        int cornerX = (int)(getoLoc().getDoubleX() - (getLength()/2));
        int cornerY = (int)(getoLoc().getDoubleY() - (getHeight()/2));
        boolean isXrange = (x >= (cornerX - 5) && x <= (cornerX + this.length + 5));
        boolean isYrange = (y >= (cornerY - 5) && y <= (cornerY + this.height + 5));
        
        return (isXrange && isYrange);
    }
    
    @Override
    public void drawSelected(Graphics g) {
        //draw
        //creates a copy of the Graphics instance
        int cornerX = (int)(getoLoc().getDoubleX() - (getLength()/2));
        int cornerY = (int)(getoLoc().getDoubleY() - (getHeight()/2));
        Graphics2D g2d = (Graphics2D) g.create();
        //g2d.setColor(Color.black);
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
        g2d.setStroke(dashed);
        g2d.drawRect(cornerX - 5, cornerY - 5, getLength() + 10, getHeight() + 10);        

        //gets rid of the copy
        g2d.dispose();
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    } 
    
    public void setFuel(int fuelLvl)
    {
        this.fuelLvl = fuelLvl;
    }
    
    public boolean isDisabled()
    {        
        //Check Damage and Fuel
        return (this.dmgLvl >= this.maxDamage) || (this.fuelLvl <= 0);        
    }

    public int[] getoColor() {
        return oColor;
    }    

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }        
    
    @Override
    public int getSpeed()
    {
        return getGroundSpeed();
    }
    
    public int getGroundSpeed()
    {
        //Get speed loss due to damage
        
        double baseSpeed = ((gear / 10.0) * maxSpeed) ;
        
        double dmgRatio = dmgLvl / maxDamage;
        double dmgEffect = 1.0 - dmgRatio;
        int effectiveMaxSpeed =  (int)(baseSpeed * dmgEffect);        
       
        
        //Only change speed if not in oil slick
        if (!this.isOilSlick)            
            this.speed = effectiveMaxSpeed;
        
        return this.speed;
    }
    
    public void updateHeading()
    {
        //Only change direction if not in oil slick
        if (!isOilSlick)
        {
            this.heading = this.steerDir;
        }
    }
    
    public void updatePylon(int nPylonNum)
    {
        if (this.pylonNum + 1 == nPylonNum)
           this.pylonNum++;
    }    
    
    public int ACcelDEcel(int delta)
    {       
        gear += delta;
        
        if (gear > 10)
            gear = 10;
        else if (gear < 0)
            gear = 0;
        
        this.speed = this.getGroundSpeed();
        
        return this.speed;
    }
        
//    public int ACcelDEcel(int delta)
//    {       
//        int cSpeed = this.speed + delta;
//        
//        //Make sure speed input is valid
//        if (cSpeed > this.maxSpeed)
//            cSpeed = this.maxSpeed;
//        
//        if (cSpeed < 0)
//            cSpeed = 0;
//        
//        if (!this.isOilSlick)
//            this.speed = cSpeed;
//        
//        return getSpeed();        
//    }
    
    public void setOilSlick(boolean slick)
    {
        this.isOilSlick = slick;
    }
    
    public void doDmg(int amount)
    {
        this.dmgLvl += amount;
        //Update speed after damage
        getSpeed();
    }
    
    public void setDmgLvl(double amount)
    {
        this.dmgLvl = amount;
    }
    
    public void addFuel(double fuel)
    {
        this.fuelLvl += fuel;
    }
    
    public int getPylonNum() {
        return pylonNum;
    }    
    
    public double getFuelLvl() {
        return this.fuelLvl;
    }

    public double getDmgLvl() {
        return this.dmgLvl;
    }

    @Override
    public int getSteerDir() {
        return steerDir;
    }
    
    @Override
    public void setSteerDir(int newSteerDir){
        
        int effectiveDir = MovableObject.fixDirection(newSteerDir);
        
        this.steerDir = effectiveDir;
    }
    
    @Override
    public void adjustSteerDir(int newSteerDir) {
        setSteerDir(this.steerDir + newSteerDir);     
    }    
    
    @Override
    public String toString()
    {
        return "Car: loc=" + this.oLoc.getX() + "," + this.oLoc.getY() + " color=["
                + this.oColor[0] + "," + this.oColor[1] + "," + this.oColor[2]
                + "heading=" + this.heading + " speed=" + this.speed
                + " width=" + this.height + " length=" + this.length + " maxSpeed="
                + this.maxSpeed + " steerDirection=" + this.steerDir + " fuelLevel="
                + this.fuelLvl + " damage=" + this.dmgLvl + " selected=" + String.valueOf(selected);
    }

    @Override
    public int getDrawSize() {
        if (height > length)
            return height;
        else
            return length;
    }
    
    @Override
    public void reverseHeading()
    {
        super.reverseHeading();
        this.setSteerDir(this.heading);
    }

    @Override
    public void handleCollision(ICollider otherObject, GameWorld gw) {
        
        //super.handleCollision(otherObject, gw);
        
        if (otherObject instanceof Pylon)
        {
            Pylon oPylon = (Pylon)otherObject;
            if (this.getPylonNum() == oPylon.getSeqNum() - 1)
                this.updatePylon(oPylon.getSeqNum());  
        } 
        if (otherObject instanceof Car)
        {
            this.dmgLvl += 5.0;            
            
            this.bounceAway((Car)otherObject);             
            
        }
        if (otherObject instanceof Bird)
        {            
            Bird otherBird =((Bird)otherObject);
            this.dmgLvl += otherBird.getSize();            
        }
        if (otherObject instanceof FuelCan)
        {            
            FuelCan otherFuel =((FuelCan)otherObject);
            this.fuelLvl += otherFuel.getSize();            
        }
        
    }

    public AffineTransform getMyRotation() {
        return myRotation;
    }

    public void setMyRotation(AffineTransform myRotation) {
        this.myRotation = myRotation;
    }

    public AffineTransform getMyScale() {
        return myScale;
    }

    public void setMyScale(AffineTransform myScale) {
        this.myScale = myScale;
    }

    public AffineTransform getMyTranslation() {
        return myTranslation;
    }

    public void setMyTranslation(AffineTransform myTranslation) {
        this.myTranslation = myTranslation;
    }

    @Override
    public AffineTransform getTranslation() {
        return this.myTranslation;
    }
    
    
}
