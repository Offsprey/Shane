package a4;

import a4.GameObjects.FuelCan;
import a4.GameObjects.NonPlayerCar;
import a4.GameObjects.PlayerCar;
import a4.GameObjects.Pylon;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class GameWorldProxy implements IGameWorld, IObservable{

    GameWorld gw;

    public GameWorldProxy(GameWorld gw) {
        this.gw = gw;
    }     
    
    @Override
    public void initLayout() {
        //Do Nothing
    }

    @Override
    public boolean isSound() {
        return gw.isSound();
    }

    @Override
    public void setSound(boolean sound) {
        //Do Nothing
    }

    @Override
    public void tick() {
        //Do Nothing
    }

    @Override
    public void accel() {
        //Do Nothing
    }

    @Override
    public void decel() {
        //Do Nothing
    }

    @Override
    public void left() {
        //Do Nothing
    }

    @Override
    public void right() {
        //Do Nothing
    }

    @Override
    public void newOilSlick() {
        //Do Nothing
    }

    @Override
    public void collide() {
        //Do Nothing
    }

    @Override
    public void pylonReached(int pylonNum) {
        //Do Nothing
    }

    @Override
    public void enterSlick() {
        //Do Nothing
    }

    @Override
    public void exitSlick() {
        //Do Nothing
    }

    @Override
    public void addFuel(int fuel) {
        //Do Nothing
    }

    @Override
    public void collideWithNPC(int npcId) {
        //Do Nothing
    }

    @Override
    public void pretendCollideFuel() {
        //Do Nothing
    }

    @Override
    public void hitBird(int dmg) {
        //Do Nothing
    }

    @Override
    public FuelCan getFirstFuel() {
        return gw.getFirstFuel();
    }

    @Override
    public void newColors() {
        //Do Nothing
    }

    @Override
    public void switchStrategy() {
        //Do Nothing
    }

    @Override
    public PlayerCar getPlayerCar() {
        return gw.getPlayerCar();
    }

    @Override
    public NonPlayerCar getNpcById(int npcId) {
        return gw.getNpcById(npcId);
    }

    @Override
    public Pylon getPylon(int pylonNum) {
        return gw.getPylon(pylonNum);
    }

    @Override
    public String getHudData() {
        return gw.getHudData();
    }

    @Override
    public int getGameClock() {
        return gw.getGameClock();
    }

    @Override
    public int getGameLives() {
        return gw.getGameLives();
    }

    @Override
    public int getPylon() {
        return gw.getPylon();
    }

    @Override
    public double getFuel() {
        return gw.getFuel();
    }

    @Override
    public double getDmg() {
        return gw.getDmg();
    }

    @Override
    public String toString() {
        return gw.toString();
    }

    @Override
    public void addObserver(IObserver ob) {
        //Do Nothing
    }

    @Override
    public void notifyObservers() {
        gw.notifyObservers();
    }
    
    @Override
    public void notifyObservers(Object ob) {
        gw.notifyObservers(ob);
    }
    
    @Override
    public GameObjectIterator getIterator() {
        return gw.getIterator();
    }

    @Override
    public boolean isPaused() {
        return gw.isPaused();
    }

    @Override
    public void setPaused(boolean isPaused) {
        //do nothing
    }

    
}
