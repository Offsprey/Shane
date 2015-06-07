package a4;

import a4.GameObjects.GameObject;
import a4.GameObjects.NonPlayerCar;
import a4.GameObjects.Pylon;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class WinStrategy implements IDrivingStrategy{

    private NonPlayerCar car;
    private Pylon pylon;

    public WinStrategy(NonPlayerCar car, Pylon pylon) {
        this.car = car;
        this.pylon = pylon;
    }    
    
    @Override
    public void apply() {
        //set heading to current target pylon
        if (pylon != null)
        {
            int targetDirection = (int) GameObject.getPolarCoord(car.getoLoc(), pylon.getoLoc());
            car.setSteerDir(targetDirection);
        }
    }
    
    @Override
    public String toString()
    {
        return "Win";
    }
}
