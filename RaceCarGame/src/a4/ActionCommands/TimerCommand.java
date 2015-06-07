package a4.ActionCommands;

import a4.Game;
import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class TimerCommand extends AbstractAction{

    private static TimerCommand instance;
    private static Timer target;
    private static GameWorld target2;
    private static Game target3;
            
    private TimerCommand()
    {
        super("-PAUSE-");
    }
    
    //Make Singleton
    public static TimerCommand getInstance() {
        if (instance == null)
            instance = new TimerCommand();
        return instance;
    }
    
    public static void setTargets(Timer gTimer, GameWorld gw, Game g)
    {
        TimerCommand.target = gTimer;
        TimerCommand.target2 = gw;
        TimerCommand.target3 = g;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            if (target.isRunning())
            {
                target.stop();
                target2.clearSelectObjects();
                target2.setPaused(true);
                this.putValue(Action.NAME, "-PLAY-");
                target3.setPauseMode();                
            }
            else
            {
                target.start();
                target2.setPaused(false);
                this.putValue(Action.NAME, "-PAUSE-");
                target3.setPlayMode();
            }
            target2.notifyObservers();
        }
    }    
}
