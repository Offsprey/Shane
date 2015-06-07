package a4.ActionCommands;

import a4.GameWorld;
import a4.MapView;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class ZoomOutCommand extends AbstractAction{

    private static ZoomOutCommand instance;
    private static MapView target;
    
            
    private ZoomOutCommand()
    {
        super("Zoom Out");
    }
    
    //Make Singleton
    public static ZoomOutCommand getInstance() {
        if (instance == null)
            instance = new ZoomOutCommand();
        return instance;
    }
    
    public static void setTarget(MapView mv)
    {
        ZoomOutCommand.target = mv;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            ((MapView)target).zoomOut();
        }
    }    
}
