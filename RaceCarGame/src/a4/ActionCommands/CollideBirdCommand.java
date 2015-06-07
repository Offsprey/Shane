package a4.ActionCommands;

import a4.GameWorld;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class CollideBirdCommand extends AbstractAction{

    private static GameWorld target; 
    private static CollideBirdCommand instance;
    
    private CollideBirdCommand()
    {
        super("Collide With Bird");
    }
    
    public static void setTarget(GameWorld gw)
    {
        target = gw;
    }
    
    //Make Singleton
    public static CollideBirdCommand getInstance() {
        if (instance == null)
            instance = new CollideBirdCommand();
        return (CollideBirdCommand)instance;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (target != null)
        {
            int birdSize = 1;
            try
            {
            String inputString = JOptionPane.showInputDialog("Enter Size of Bird");
            birdSize = Integer.parseInt(inputString);     
            }
            catch (NumberFormatException nfe)
            {
                
            }
            target.hitBird(birdSize);            
        }
        target.notifyObservers();
    }
    
}
