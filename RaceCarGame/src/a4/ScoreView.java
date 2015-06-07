package a4;

import a4.GameWorld;
import java.awt.FlowLayout;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class ScoreView extends JPanel implements IObserver{

    private JLabel timeLbl,lifeLbl,pylonLbl, fuelLbl, dmgLbl, sfxLbl;
    
    public ScoreView() {        
        
        FlowLayout fl = (FlowLayout)this.getLayout();
        fl.setHgap(20);
        
        timeLbl = new JLabel("Time: xx");
        this.add(timeLbl);
        
        lifeLbl = new JLabel("Lives Left: x");
        this.add(lifeLbl);
        
        pylonLbl = new JLabel("Highest Player Pylon: x");
        this.add(pylonLbl);
        
        fuelLbl = new JLabel("Player Fuel Level: xx");
        this.add(fuelLbl);
        
        dmgLbl = new JLabel("Player Damage Level: xx");
        this.add(dmgLbl);
        
        sfxLbl = new JLabel("Sound: xxx");
        this.add(sfxLbl);         
    }

    @Override
    public void update(IObservable o, Object arg) {
        
        GameWorldProxy gwp = (GameWorldProxy)o;
        
        int gameTime = (int) Math.floor(gwp.getGameClock() / 1000.0); 
        
        timeLbl.setText("Time: " + String.valueOf(gameTime));
        lifeLbl.setText("Lives Left: " + gwp.getGameLives());
        pylonLbl.setText("Highest Player Pylon:" + gwp.getPylon());
        fuelLbl.setText("Player Fuel Remaining: " + (int)Math.floor(gwp.getFuel()));
        dmgLbl.setText("Player Damage Level: " + (int)Math.floor(gwp.getDmg()));
        
        if (gwp.isSound())
            sfxLbl.setText("Sound: ON"); 
        else
            sfxLbl.setText("Sound: OFF");  
    }
    
}
