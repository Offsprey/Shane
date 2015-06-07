/*
 */
package a4;

import a4.GameObjects.FuelCan;
import a4.GameObjects.NonPlayerCar;
import a4.GameObjects.PlayerCar;
import a4.GameObjects.Pylon;

/**
 *
 * @author Shane
 */
public interface IGameWorld {
    public void initLayout();
    public boolean isSound();
    public void setSound(boolean sound);
    public GameObjectIterator getIterator();
    public void tick();
    public void accel();
    public void decel();
    public void left();
    public void right();
    public void newOilSlick();
    public void collide();
    public void pylonReached(int pylonNum);
    public void enterSlick();
    public void exitSlick();
    public void addFuel(int fuel);
    public void collideWithNPC(int npcId);
    public void pretendCollideFuel();
    public void hitBird(int dmg);
    public FuelCan getFirstFuel();
    public void newColors();
    public void switchStrategy();
    public PlayerCar getPlayerCar();
    public NonPlayerCar getNpcById(int npcId);    
    public Pylon getPylon(int pylonNum);    
    public String getHudData();
    public int getGameClock();
    public int getGameLives();
    public int getPylon();    
    public double getFuel();    
    public double getDmg();  
    public boolean isPaused() ;
    public void setPaused(boolean isPaused) ;
    @Override
    public String toString();
    
}
