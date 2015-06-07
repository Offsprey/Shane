package a4;

import a4.GameObjects.Car;
import a4.GameObjects.Pylon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
//Test view
public class GraphicsView extends JPanel implements IObserver{

    public GraphicsView()
    {
        //this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(new TitledBorder("This Area for Testing and Future Use"));
        this.setPreferredSize(new Dimension(600,600));
        this.setVisible(true);
    }
    
    @Override
    public void update(IObservable o, Object arg) {
        
//        GameWorldProxy gwp = (GameWorldProxy)o;
//        
//        Graphics gfx = this.getGraphics();
//        if (gfx != null)
//        {
//            drawPylon(gwp.getPylon(1));
//            drawPylon(gwp.getPylon(2));
//            drawPylon(gwp.getPylon(3));
//            drawPylon(gwp.getPylon(4));
//            gfx.setColor(Color.blue);
//            drawCar(gwp.getPlayerCar());
//            gfx.setColor(Color.red);
//            drawCar(gwp.getNpcById(1));
//            gfx.setColor(Color.orange);
//            drawCar(gwp.getNpcById(2));           
//        }                
    }
    
//    private void drawPylon(Pylon dPylon)
//    {
//        this.getGraphics().drawOval(dPylon.getoLoc().x, dPylon.getoLoc().y, dPylon.getRadius() * 2, dPylon.getRadius() * 2);
//        char[] pylonLbl = String.valueOf(dPylon.getSeqNum()).toCharArray();
//        this.getGraphics().drawChars(pylonLbl, 0, 1, dPylon.getoLoc().x + 8, dPylon.getoLoc().y + 15);
//    }
//    
//    private void drawCar(Car dCar)
//    {
//        Graphics gfx = this.getGraphics();
//
//        gfx.drawRect(dCar.getoLoc().x, dCar.getoLoc().y, dCar.getLength(), dCar.getWidth());        
//        Color cColor = new Color(dCar.getoColor()[0],dCar.getoColor()[1],dCar.getoColor()[2]);
//        gfx.setColor(cColor);
//        gfx.fillRect(dCar.getoLoc().x, dCar.getoLoc().y, dCar.getLength()-1, dCar.getWidth()-1);
//    }    
}