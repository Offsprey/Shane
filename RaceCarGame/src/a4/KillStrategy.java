package a4;

import a4.GameObjects.GameObject;
import a4.GameObjects.NonPlayerCar;
import a4.GameObjects.PlayerCar;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class KillStrategy implements IDrivingStrategy{
    
    private NonPlayerCar car;
    private PlayerCar pCar;

    public KillStrategy(NonPlayerCar car, PlayerCar pCar) {
        this.car = car;
        this.pCar = pCar;
    }    

    @Override
    public void apply() {
        //set heading to location of Player Car
        int targetDirection = (int) GameObject.getPolarCoord(car.getoLoc(), pCar.getoLoc());
        car.setSteerDir(targetDirection);
    }
    
    @Override
    public String toString()
    {
        return "Kill";
    }
    
}
