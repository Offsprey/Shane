package a4;

import a4.GameObjects.GameObject;
import a4.GameObjects.MovableObject;
import a4.GameObjects.Car;
import a4.GameObjects.Bird;
import a4.GameObjects.FuelCan;
import a4.GameObjects.NonPlayerCar;
import a4.GameObjects.OilSlick;
import a4.GameObjects.PlayerCar;
import a4.GameObjects.Pylon;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Random;

import java.util.Vector;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public final class GameWorld implements IObservable, IGameWorld{
    
    //private Vector<GameObject> gObjects;
    
    private Vector<IObserver> observers;
    
    private Vector<GameObject> deleteList;
    
    private GameObjectCollection gObjects;
    //Keep track on npc's by id
    private int npcIdCounter = 0;
    private boolean isPaused;
    
    private GamePoint startingPoint;
    private AffineTransform affineStartPoint;
    
    private Point mapLocation;

    public Point getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(Point mapLocation) {
        this.mapLocation = mapLocation;
    }
    
    public static final int[] gameBounds = {600,600};
    private Random rnd;
    
    private int gameClock;
    private int gameLives;
    
    private boolean sound = false;
    
    public GameWorld()
    {
        observers = new Vector<IObserver>();
        deleteList = new Vector<GameObject>();
        
        rnd = new Random();  
        gameClock = 0;
        gameLives = 3;
        gObjects = new GameObjectCollection();
        isPaused = false;
        
    }    
    
    //Create Game Objects
    @Override
    public void initLayout(){       
        
        AffineTransform carStart = new AffineTransform();
        carStart.translate(5,50);
        
        
        Pylon startPylon = new Pylon(10,1,new int[]{64,64,64},randomPoint(), carStart);
        gObjects.add(startPylon);
        
        startingPoint = startPylon.getoLoc();

        affineStartPoint = ((Pylon) startPylon).getTranslationTransform();
        
        PlayerCar nCar = makePlayerCar();
        gObjects.add(nCar);
        
        gObjects.add(makePylon(2));
        gObjects.add(makePylon(3));
        gObjects.add(makePylon(4));
        gObjects.add(makePylon(5));
        
       NonPlayerCar nPC = makeNPCar();
        nPC.setStrategy(new WinStrategy(nPC,getPylon(2)));
        gObjects.add(nPC);
        
        nPC = makeNPCar();
        nPC.setStrategy(new KillStrategy(nPC,nCar));
        gObjects.add(nPC);
        
        nPC = makeNPCar();        
        nPC.setStrategy(new KillStrategy(nPC,nCar));
        gObjects.add(nPC);
        
        gObjects.add(makeFuel());
        
        gObjects.add(makeFuel());
        
        gObjects.add(makeOilSlick());
        
        gObjects.add(makeOilSlick());
        
        gObjects.add(makeBird());
        
        gObjects.add(makeBird());        
    }

    @Override
    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }
    
    
    
    private Pylon makePylon(int pylonNumber)
    {
        //public Pylon(int radius, int seqNum, int[] color, Point loc)
        return new Pylon(10,pylonNumber,new int[]{64,64,64},randomPoint(), randomAT());
    }
    
    private PlayerCar makePlayerCar()
    {
        int LENGTH = 30;
        int HEIGHT = 15;
        int MAX_SPEED = 45;
        
        //public PlayerCar(int maxSpeed, int fuelLvl, int dmgLvl, int steerDir, int length, 
        //int width, int pylonNum, int heading, int speed, int[] color, Point loc)
        startingPoint = new GamePoint (startingPoint.getDoubleX(), startingPoint.getDoubleY());
        
        return new PlayerCar(MAX_SPEED,10,0,0,LENGTH,HEIGHT,1,0,0,randomColor(),startingPoint, affineStartPoint);
    }
    
    private NonPlayerCar makeNPCar()
    {
        int LENGTH = 30;
        int HEIGHT = 15;
        int NPC_SPEED = 10; 
        
        
        //public NonPlayerCar(int maxSpeed, int fuelLvl, int dmgLvl, int steerDir, int length, 
        //int width, int pylonNum, int heading, int speed, int[] color, Point loc)
        return new NonPlayerCar(NPC_SPEED,10,0,0,LENGTH,HEIGHT,1,0,NPC_SPEED, randomColor(),this.randomPoint(), npcIdCounter += 1, randomAT());        
    }
    
    private OilSlick makeOilSlick()
    {
        //public OilSlick(int width, int length, int[] color, java.awt.Point loc)
        return new OilSlick((rnd.nextInt(20)) + 20,(rnd.nextInt(20)) + 20,randomColor(),randomPoint(), randomAT());
    }
    
    public void addNewFuel()
    {
        gObjects.add(makeFuel());
    }
    
    private FuelCan makeFuel()
    {
        //public FuelCan(int size, int[] color, java.awt.Point loc)
        return new FuelCan((rnd.nextInt(6)) + 1,randomColor(),randomPoint(), randomAT());
    }
    
    public void addNewBird()
    {
        gObjects.add(makeBird());
    }
    
    private Bird makeBird()
    {        
        //public Bird(int size, int heading, int speed, int[] color, Point loc)
        int MIN_SPEED = 10;
        int MAX_SPEED = 30;
        
        int birdSpeed = rnd.nextInt(MIN_SPEED) + (MAX_SPEED - MIN_SPEED);
                
        return new Bird((rnd.nextInt(20)) + 10,rnd.nextInt(360) ,birdSpeed ,randomColor(),randomPoint(), randomAT());
    }
    public GameWorldProxy getGameWorldProxy()
    {
        return new GameWorldProxy(this);
    }
    
    @Override
    public boolean isSound() {
        return sound;
    }

    @Override
    public void setSound(boolean sound) {
        this.sound = sound;
    }    
    
    public AffineTransform randomAT() {
        AffineTransform randAff = new AffineTransform();
        double x= rnd.nextDouble() % this.gameBounds[0];
        double y= rnd.nextDouble() % this.gameBounds[1];
        randAff.translate(x,y);
        return randAff;

    }
    
    @Override
    public void tick()
    {
        int msec = 20;
        PlayerCar car = getPlayerCar();
        //Update Heading 
        //Move MovableObjects
        GameObjectIterator ir = gObjects.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next();
            
            if (gObject instanceof MovableObject)
            {
                ((MovableObject)gObject).move(msec);
                //Make sure object is in bounds
                this.inBounds((MovableObject)gObject);
            }
        }
        //Reduce Fuel
        car.addFuel(-1 * (msec / 1000.0));
        
        
        //check collisions
        this.checkCollisions();
        
        //Check Car Status
        if (car.isDisabled())
        {
            this.gameLives--;
            car.setDmgLvl(0);
            car.setFuel(10);
        
            if (this.gameLives <= 0)
            {
                System.out.println("Game Over!");
            }
        }       
        
        //Increment Clock
        gameClock += msec;
        
        if (gameClock % 10000 == 0)
            this.switchStrategy();
    }
    
    private int inBounds(MovableObject mObject)
    {//gameBounds
        if (mObject.getoLoc().getY() < 0)            
        {
            mObject.setHeading(90);
            if (mObject instanceof Car)
                ((Car)mObject).setSteerDir(90);
            return 0;
        }
        if (mObject.getoLoc().getY() > gameBounds[1])
        {
            mObject.setHeading(270);
            if (mObject instanceof Car)
                ((Car)mObject).setSteerDir(270);
            return 2;
        }
        if (mObject.getoLoc().getX() < 0)            
        {
            mObject.setHeading(0);
            if (mObject instanceof Car)
                ((Car)mObject).setSteerDir(0);
            return 3;
        }
        if (mObject.getoLoc().getX() > gameBounds[0])
        {
            mObject.setHeading(180);
            if (mObject instanceof Car)
                ((Car)mObject).setSteerDir(180);
           return 1;     
        }
        
        return -1;
    }
    
    public GamePoint randomPoint()
    {
        GamePoint rPoint = new GamePoint(rnd.nextInt(gameBounds[0] - 40) + 20, rnd.nextInt(gameBounds[1] - 40) + 20);
        return rPoint;
    }
    
    public int[] randomColor()
    {
        int[] rColor = new int[3];
        rColor[0] = rnd.nextInt(256);
        rColor[1] = rnd.nextInt(256);
        rColor[2] = rnd.nextInt(256);
        
        return rColor;
    }
    
    @Override
    public void accel()
    {
        Car car = getPlayerCar();
        if (car != null)
            car.ACcelDEcel(1);
    }
    
    @Override
    public void decel()
    {
        Car car = getPlayerCar();
        if (car != null)
            car.ACcelDEcel(-1);
    }
    
    @Override
    public void left()
    {
        Car car = getPlayerCar();
        if (car != null)
            car.adjustSteerDir(-5);
    }
    
    @Override
    public void right()
    {
        Car car = getPlayerCar();
        if (car != null)
            car.adjustSteerDir(5);
    }
    
    @Override
    public void newOilSlick()
    {
        //public OilSlick(int width, int length, int[] color, java.awt.Point loc)
        OilSlick nSlick = new OilSlick((rnd.nextInt(20)) + 1,(rnd.nextInt(40)) + 1,randomColor(),randomPoint(), randomAT());
        gObjects.add(nSlick);
    }
    
    @Override
    public void collide()
    {
        Car car = getPlayerCar();
        if (car != null)
            car.doDmg(5);
    }
    
    @Override
    public void pylonReached(int pylonNum)
    {
        Car car = getPlayerCar();
        if (car != null)
            car.updatePylon(pylonNum);
    }
    
    @Override
    public void enterSlick()
    {
        Car car = getPlayerCar();
        if (car != null)
            car.setOilSlick(true);
    } 
    
    @Override
    public void exitSlick()
    {
        Car car = getPlayerCar();
        if (car != null)
            car.setOilSlick(false);
    }
    
    @Override
    public void addFuel(int fuel)
    {
        Car car = getPlayerCar();
        if (car != null)
            car.addFuel(fuel);
    }
    
    @Override
    public void collideWithNPC(int npcId)
    {
        Car car = getPlayerCar();
        NonPlayerCar npc = this.getNpcById(npcId);
        
        car.doDmg(5);
        if (npc != null)
            npc.doDmg(5);
    }
    
    @Override
    public void pretendCollideFuel()
    {
        FuelCan fuel = this.getFirstFuel();
        Car car = getPlayerCar();
        
        //Makesure fuel and car exist
        if (fuel != null && car != null)
        {
            car.addFuel(fuel.getSize());
            gObjects.remove(fuel);
            
            //public FuelCan(int size, int[] color, java.awt.Point loc)
            FuelCan nCan = new FuelCan((rnd.nextInt(6)) + 1,randomColor(),randomPoint(), randomAT());
            gObjects.add(nCan);
        }      
        
    }
    
    @Override
    public void hitBird(int dmg)
    {
        Car car = getPlayerCar();
        if (car != null)
            car.doDmg(dmg);
    }
    
    @Override
    public FuelCan getFirstFuel()
    {
        GameObjectIterator ir = gObjects.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next();
            if (gObject instanceof FuelCan)
                return (FuelCan)gObject;
        }
        
        return null;
    }
    
    
    public void checkCollisions()
    {
        this.resetAllCollisions();
        
        //Look at all objects  to see if they move
        GameObjectIterator movableIr = gObjects.getIterator();
        while (movableIr.hasNext())
        {
            GameObject thisObject = movableIr.next();
            //if they move check other objects for collisions
            if (thisObject instanceof MovableObject)
            {
                MovableObject mObject = (MovableObject)thisObject;
                GameObjectIterator allIr = gObjects.getIterator();
                
                while (allIr.hasNext())
                {
                    GameObject aObject = allIr.next();
                    //if object isn't itself
                    if (mObject != aObject)
                    {
                        
                        if (mObject.collidesWith(aObject) || mObject.previousCollision(aObject))// && !mObject.previousCollision(aObject)
                        {
                            
                            mObject.handleCollision(aObject, this);                            
                            
                            //flag both objects as collided
                            mObject.addCollision(aObject);
                            
                            //if other object wont throw its own handle collision
                            //throw it for them
                            if (aObject instanceof MovableObject)
                                ((MovableObject) aObject).addCollision(mObject);
                            else
                                aObject.handleCollision(mObject, this);
                            mObject.addCollision(aObject);
                        }
                    }
                }        
            }                
        }
        
        //delete all pending
        this.processDelete();
    }
    
    @Override
    public void newColors()
    {
        GameObjectIterator ir = gObjects.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next();
            gObject.setoColor(this.randomColor());
        }
    }
    
    private void resetAllCollisions()
    {
        GameObjectIterator movableIr = gObjects.getIterator();
        while (movableIr.hasNext())
        {
            GameObject gObject = movableIr.next();
            if (gObject instanceof MovableObject)
            {
                ((MovableObject)gObject).resetCollisions();
            }
        }
    }
    
    public void selectObjects(int x, int y, boolean multiSelect)
    {
        GameObjectIterator ir = gObjects.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next();
            if (gObject instanceof ISelectable)
            {
                ISelectable sel = (ISelectable)(gObject);
                if (sel.contains(x, y))
                {
                    sel.setSelected(!sel.isSelected());
                    System.out.println(sel.toString());
                }
                else if (!multiSelect)
                    sel.setSelected(false);                    
            }                
        }
    }
    
    public void clearSelectObjects()
    {
        this.selectObjects(-1, -1, false);
    }
    
    public void moveSelectedObjects(int newX, int newY)
    {
        GameObjectIterator ir = gObjects.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next();
            if (gObject instanceof ISelectable)
            {
                ISelectable sel = (ISelectable)(gObject);
                if (sel.isSelected())
                    gObject.setoLoc(new GamePoint(newX, newY));
            }                
        }
    }
    
    public void deleteSelectedObjects()
    {
        GameObjectIterator ir = gObjects.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next();
            if (gObject instanceof Pylon)
            {
                //gObjects.add(this.makePylon(((Pylon)gObject).getSeqNum()));
                //gObjects.remove(gObject);
                
                //deleting pylon will cause NPC Win Strategy to throw null
                //changing location of pylon will have same result
                //without exceptions
                gObject.setoLoc(this.randomPoint());
            }
            if (gObject instanceof OilSlick || gObject instanceof FuelCan)
            {
                gObjects.remove(gObject);
            }
            
        }
    }
    
    @Override
    public void switchStrategy()
    {
        GameObjectIterator ir = gObjects.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next();
            if (gObject instanceof NonPlayerCar)
            {
                NonPlayerCar npc = (NonPlayerCar)gObject;
                if (npc.getStrategy() instanceof WinStrategy)
                    npc.setStrategy(new KillStrategy(npc,this.getPlayerCar()));
                else if (npc.getStrategy() instanceof KillStrategy)
                {
                    //npc.updatePylon(npc.getPylonNum() + 1);
                    npc.setStrategy(new WinStrategy(npc,getPylon(npc.getPylonNum())));  
                }
            }                
        }
    }
    
    @Override
    public PlayerCar getPlayerCar()
    {
        GameObjectIterator ir = gObjects.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next();
            if (gObject instanceof PlayerCar)
                return (PlayerCar)gObject;
        }
        
        return null;
    }
    
    @Override
    public NonPlayerCar getNpcById(int npcId)
    {
        GameObjectIterator ir = gObjects.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next();
            if (gObject instanceof NonPlayerCar)
            {
                NonPlayerCar npc = (NonPlayerCar)gObject;
                if (npc.getNpcId() == npcId)
                    return npc;
            }                
        }
        
        return null;
    }
    
    public void processDelete()
    {
        int[] indices = new int[deleteList.size()];
        
        for (GameObject gObject : deleteList)
        {
            gObjects.remove(gObject);
        }
        
        deleteList.removeAllElements();
    }
    
    
    
    public void flagToDelete(GameObject gObject)
    {
        deleteList.add(gObject);
    }
       
//    private void removePlayerCar()
//    {       
//        GameObjectIterator ir = gObjects.getIterator();
//        while (ir.hasNext())
//        {
//            GameObject gObject = ir.next();
//            if (gObject instanceof PlayerCar)
//            {
//                gObjects.remove(gObject);
//                return;
//            }                            
//        }
//    }
    
    @Override
    public Pylon getPylon(int pylonNum)
    {
        GameObjectIterator ir = gObjects.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next();
            if (gObject instanceof Pylon)
            {
                if (((Pylon)gObject).getSeqNum() == pylonNum)
                    return (Pylon)gObject;
            }                
        }
        
        return null;
    }
    
    @Override
    public String getHudData()
    {
        Car car = getPlayerCar();
        
        String retStr = "";
        
        retStr += "Lives: " + this.gameLives + "\nClock: " + this.gameClock + "\nPylon: "
                + car.getPylonNum() + "\nFuel: " + car.getFuelLvl() + "\nDamage: "
                + car.getDmgLvl() + "\n";
        
        return retStr;
    }

    @Override
    public int getGameClock() {
        return gameClock;
    }

    @Override
    public int getGameLives() {
        return gameLives;
    }
    
    @Override
    public int getPylon()
    {
        Car car = this.getPlayerCar();
        if (car != null)            
            return car.getPylonNum();
        else
            return -1;
    }
    
    @Override
    public double getFuel()
    {
        Car car = this.getPlayerCar();
        if (car != null)            
            return car.getFuelLvl();
        else
            return -1;
    }
    
    @Override
    public double getDmg()
    {
        Car car = this.getPlayerCar();
        if (car != null)            
            return car.getDmgLvl();
        else
            return -1;
    }
    
    @Override
    public String toString()
    {
        String retString = "";
        GameObjectIterator ir = gObjects.getIterator();
        while (ir.hasNext())
        {
            GameObject gObject = ir.next();
            retString += gObject.toString() + "\n";
        }
        
        return retString;
    }

    @Override
    public void addObserver(IObserver ob) {
        observers.add(ob);
    }

    @Override    
    public void notifyObservers() {
        for (IObserver ob : observers)
        {
            ob.update(this.getGameWorldProxy(), null);
        }
    }
    
    @Override
    public void notifyObservers(Object arg) {
        for (IObserver ob : observers)
        {
            ob.update(this.getGameWorldProxy(), arg);
        }
    }

    @Override
    public GameObjectIterator getIterator() {
        return gObjects.getIterator();
    }

    

}
