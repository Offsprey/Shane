package a4.ActionCommands;

import a4.GameWorld;
import a4.MapView;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class ZoomInCommand extends AbstractAction{

    private static ZoomInCommand instance;
    private static MapView target;
    
            
    private ZoomInCommand()
    {
        super("Zoom In");
    }
    
    //Make Singleton
    public static ZoomInCommand getInstance() {
        if (instance == null)
            instance = new ZoomInCommand();
        return instance;
    }
    
    public static void setTarget(MapView mv)
    {
        ZoomInCommand.target = mv;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (target != null)
        {
            ((MapView)target).zoomIn();
        }
    }    
}
