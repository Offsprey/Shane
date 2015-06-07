package a4;

import a4.ActionCommands.AboutCommand;
import a4.ActionCommands.AccelCommand;
import a4.ActionCommands.AddFuelCommand;
import a4.ActionCommands.CollideBirdCommand;
import a4.ActionCommands.CollideNpcCommand;
import a4.ActionCommands.DeccelCommand;
import a4.ActionCommands.DeleteCommand;
import a4.ActionCommands.EnterOilCommand;
import a4.ActionCommands.ExitOilCommand;
import a4.ActionCommands.FuelCommand;
import a4.ActionCommands.LeftCommand;
import a4.ActionCommands.NewCommand;
import a4.ActionCommands.PylonCommand;
import a4.ActionCommands.QuitCommand;
import a4.ActionCommands.RightCommand;
import a4.ActionCommands.SaveCommand;
import a4.ActionCommands.SelectCommand;
import a4.ActionCommands.SoundCommand;
import a4.ActionCommands.StrategyCommand;
import a4.ActionCommands.TickCommand;
import a4.ActionCommands.TimerCommand;
import a4.ActionCommands.ZoomInCommand;
import a4.ActionCommands.ZoomOutCommand;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * @author Shane
 * offsprey14@gmail.com
 */
public class Game extends JFrame{
    private GameWorld gw;
    private MapView mv;
    private ScoreView sv;
    private Timer timer;
    
    
    
    public Game(){
        
        //Create Gameworld
        gw = new GameWorld();
        
        //Init Views
        mv = new MapView(gw.getGameWorldProxy());
        
        sv = new ScoreView();
           
        gw.addObserver(mv);
        gw.addObserver(sv);
        
        //Setup layout
        gw.initLayout();
        
        JPanel centerPanel = new JPanel();
        //centerPanel.add(mv);
        centerPanel.add(mv); 
        
        this.add(centerPanel, BorderLayout.CENTER);
        
        //Print intial map
        System.out.print(gw.toString()); 
        
        gw.notifyObservers();
        gw.setMapLocation(centerPanel.getLocation());
        
        
        //Init Commands    
        SelectCommand selCmd = SelectCommand.getInstance();
        SelectCommand.setTarget(gw);
        
        mv.addMouseMotionListener(selCmd);
        mv.addMouseListener(selCmd);
        
        ZoomInCommand zICmd = ZoomInCommand.getInstance();
        ZoomInCommand.setTarget(mv);
        
        ZoomOutCommand zOCmd = ZoomOutCommand.getInstance();
        ZoomOutCommand.setTarget(mv);
        
        AddFuelCommand afCmd = AddFuelCommand.getInstance();
        AddFuelCommand.setTarget(gw);
        
        DeleteCommand dCmd = DeleteCommand.getInstance();
        DeleteCommand.setTarget(gw);
        
        NewCommand nCmd = NewCommand.getInstance();
        NewCommand.setTarget(this);
        
        SaveCommand saCmd = SaveCommand.getInstance();
        SaveCommand.setTarget(this);
        
        AboutCommand abCmd = AboutCommand.getInstance();
        AboutCommand.setTarget(this);
        
        SoundCommand soCmd = SoundCommand.getInstance();
        SoundCommand.setTarget(gw);
        
        TickCommand tCmd = TickCommand.getInstance();
        TickCommand.setTarget(gw);
        
        timer = new Timer( 20, tCmd);
        
        PylonCommand pCmd = PylonCommand.getInstance();
        PylonCommand.setTarget(gw);
        
        StrategyCommand sCmd = StrategyCommand.getInstance();
        StrategyCommand.setTarget(gw);
//        
//        CollideBirdCommand cbCmd = CollideBirdCommand.getInstance();
//        CollideBirdCommand.setTarget(gw);
//        
//        CollideNpcCommand cnCmd = CollideNpcCommand.getInstance();
//        CollideNpcCommand.setTarget(gw);
//        
//        EnterOilCommand eoCmd = EnterOilCommand.getInstance();
//        EnterOilCommand.setTarget(gw);
//        
//        ExitOilCommand xoCmd = ExitOilCommand.getInstance();
//        ExitOilCommand.setTarget(gw);
//        
//        FuelCommand fCmd = FuelCommand.getInstance();
//        FuelCommand.setTarget(gw);
        
        QuitCommand qCmd = QuitCommand.getInstance();
        QuitCommand.setTarget(this);        
        
        AccelCommand aCmd = AccelCommand.getInstance();
        AccelCommand.setTarget(gw);
        
        DeccelCommand bCmd = DeccelCommand.getInstance();
        DeccelCommand.setTarget(gw);
        
        RightCommand rCmd = RightCommand.getInstance();
        RightCommand.setTarget(gw);
        
        LeftCommand lCmd = LeftCommand.getInstance();
        LeftCommand.setTarget(gw);
        
        TimerCommand tiCmd = TimerCommand.getInstance();
        TimerCommand.setTargets(timer, gw, this);
        
        //Init Frame        
        this.setPreferredSize(new Dimension(1000,800));
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
        setTitle("A4");
        setSize(800,600);
        
        
        //Add Components        
        this.add(sv,BorderLayout.NORTH);
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(new TitledBorder("Commands"));
        leftPanel.setLayout(new GridLayout(10,1));
        
        JButton addFuelBut = new JButton();
        addFuelBut.setAction(afCmd);
        addFuelBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        leftPanel.add(addFuelBut);
        
        JButton zoomInBut = new JButton();
        zoomInBut.setAction(zICmd);
        zoomInBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        leftPanel.add(zoomInBut);
        
        JButton zoomOutBut = new JButton();
        zoomOutBut.setAction(zOCmd);
        zoomOutBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        leftPanel.add(zoomOutBut);
        
        JButton deleteBut = new JButton();
        deleteBut.setAction(dCmd);
        deleteBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        leftPanel.add(deleteBut);
        
        
//        JButton collideNpcBut = new JButton();
//        collideNpcBut.setAction(cnCmd);
//        collideNpcBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
//        leftPanel.add(collideNpcBut);
//        
//        
//        JButton collidePylonBut = new JButton();
//        collidePylonBut.setAction(pCmd);
//        collidePylonBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
//        leftPanel.add(collidePylonBut);
//        
//        
//        JButton collideBirdBut = new JButton();
//        collideBirdBut.setAction(cbCmd);
//        collideBirdBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
//        leftPanel.add(collideBirdBut);
//        
//        
//        JButton fuelBut = new JButton();
//        fuelBut.setAction(fCmd);
//        fuelBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
//        leftPanel.add(fuelBut);
//        
//        
//        JButton enterSlickBut = new JButton();
//        enterSlickBut.setAction(eoCmd);
//        enterSlickBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
//        leftPanel.add(enterSlickBut);
//        
//        
//        JButton exitSlickBut = new JButton();
//        exitSlickBut.setAction(xoCmd);
//        exitSlickBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
//        leftPanel.add(exitSlickBut);
//        
//        
//        JButton strategyBut = new JButton();
//        strategyBut.setAction(sCmd);
//        strategyBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
//        leftPanel.add(strategyBut);    
        
        
//        JButton tickBut = new JButton();
//        tickBut.setAction(tCmd);
//        tickBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
//        leftPanel.add(tickBut);
        
        JButton timerBut = new JButton();
        timerBut.setAction(tiCmd);
        timerBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        leftPanel.add(timerBut);
        
        
        JButton quitBut = new JButton();
        quitBut.setAction(qCmd);
        quitBut.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        leftPanel.add(quitBut);        
        
        this.add(leftPanel, BorderLayout.WEST);
        
             
                
        
        
        //Init Key Maps
        int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap iMap = centerPanel.getInputMap(mapName);
        ActionMap aMap = centerPanel.getActionMap();
        
//        KeyStroke nKey = KeyStroke.getKeyStroke('c');
//        iMap.put(nKey, cnCmd);
//        aMap.put(cnCmd, cnCmd);
        
        KeyStroke pKey = KeyStroke.getKeyStroke('p');
        iMap.put(pKey, pCmd);
        aMap.put(pCmd, pCmd);
        
//        KeyStroke cbKey = KeyStroke.getKeyStroke('i');
//        iMap.put(cbKey, cbCmd);
//        aMap.put(cbCmd, cbCmd);
        
//        KeyStroke fKey = KeyStroke.getKeyStroke('f');
//        iMap.put(fKey, fCmd);
//        aMap.put(fCmd, fCmd);
//        
//        KeyStroke eoKey = KeyStroke.getKeyStroke('o');
//        iMap.put(eoKey, eoCmd);
//        aMap.put(eoCmd, eoCmd);
//        
//        KeyStroke xoKey = KeyStroke.getKeyStroke('x');
//        iMap.put(xoKey, xoCmd);
//        aMap.put(xoCmd, xoCmd);
        
        KeyStroke sKey = KeyStroke.getKeyStroke('s');
        iMap.put(sKey, sCmd);
        aMap.put(sCmd, sCmd);
        
        KeyStroke tKey = KeyStroke.getKeyStroke('t');
        iMap.put(tKey, tCmd);
        aMap.put(tCmd, tCmd);
        
        KeyStroke qKey = KeyStroke.getKeyStroke('q');
        iMap.put(qKey, qCmd);
        aMap.put(qCmd, qCmd);
        
        KeyStroke aKey = KeyStroke.getKeyStroke('a');
        iMap.put(aKey, aCmd);
        KeyStroke upKey = KeyStroke.getKeyStroke("UP");
        iMap.put(upKey, aCmd);
        aMap.put(aCmd, aCmd);
        
        KeyStroke bKey = KeyStroke.getKeyStroke('b');
        iMap.put(bKey, bCmd);
        KeyStroke downKey = KeyStroke.getKeyStroke("DOWN");
        iMap.put(downKey, bCmd);
        aMap.put(bCmd, bCmd);
        
        KeyStroke lKey = KeyStroke.getKeyStroke('l');
        iMap.put(lKey, lCmd);
        KeyStroke leftKey = KeyStroke.getKeyStroke("LEFT");
        iMap.put(leftKey, lCmd);
        aMap.put(lCmd, lCmd);
        
        KeyStroke rKey = KeyStroke.getKeyStroke('r');
        iMap.put(rKey, rCmd);
        KeyStroke rightKey = KeyStroke.getKeyStroke("RIGHT");
        iMap.put(rightKey, rCmd);
        aMap.put(rCmd, rCmd);
        
        
        
        
        //Init JMenu
        JMenuBar mBar = new JMenuBar();
        
        JMenuItem mItem;
        
        JMenu fMenu = new JMenu("File");
        
        mItem = new JMenuItem("New");
        fMenu.add(mItem);
        mItem.setAction(nCmd);
        
        mItem = new JMenuItem("Save");
        fMenu.add(mItem);
        mItem.setAction(saCmd);
        
        mItem = new JMenuItem("About");
        fMenu.add(mItem);
        mItem.setAction(abCmd);
        
        JCheckBoxMenuItem cbItem = new JCheckBoxMenuItem("Sound");
        cbItem.setAction(soCmd);
        fMenu.add(cbItem);
        
        mItem = new JMenuItem("Quit");
        fMenu.add(mItem);
        mItem.setAction(qCmd);
        mItem.setMnemonic('q');
        
        
        
        JMenu cMenu = new JMenu("Commands");
        
        
        
//        mItem = new JMenuItem("Collide With NPC");
//        cMenu.add(mItem);
//        mItem.setAction(cnCmd);
//        mItem.setMnemonic('c');
        
        mItem = new JMenuItem("Collide With Pylon");
        cMenu.add(mItem);
        mItem.setAction(pCmd);
        mItem.setMnemonic('p');
//        
//        mItem = new JMenuItem("Collide With Bird");
//        cMenu.add(mItem);
//        mItem.setAction(cbCmd);
//        mItem.setMnemonic('i');
        
//        mItem = new JMenuItem("Picked Up FuelCan");
//        cMenu.add(mItem);
//        mItem.setAction(fCmd);
//        mItem.setMnemonic('f');
//        
//        mItem = new JMenuItem("Enter Oil Slick");
//        cMenu.add(mItem);
//        mItem.setAction(eoCmd);
//        mItem.setMnemonic('o');
//        
//        mItem = new JMenuItem("Exit Oil Slick");
//        cMenu.add(mItem);
//        mItem.setAction(xoCmd);
//        mItem.setMnemonic('x');
        
        mItem = new JMenuItem("Switch Strategy");
        cMenu.add(mItem);
        mItem.setAction(sCmd);
        mItem.setMnemonic('s');
        
        mItem = new JMenuItem("Tick");
        cMenu.add(mItem);
        mItem.setAction(tCmd);
        mItem.setMnemonic('t');
        
        mItem = new JMenuItem("Quit");
        cMenu.add(mItem);
        mItem.setAction(qCmd);
        mItem.setMnemonic('q');
        
        
        mBar.add(fMenu);
        mBar.add(cMenu);
        
        this.setJMenuBar(mBar);
        
        
        timer.start();
        
        //Show Frame
        setVisible(true);        
    }
    
    public void setPauseMode()
    {       
        DeleteCommand dCmd = DeleteCommand.getInstance();
        dCmd.setEnabled(true);        
        
        AddFuelCommand afCmd = AddFuelCommand.getInstance();
        afCmd.setEnabled(true);     
        
        
        AccelCommand aCmd = AccelCommand.getInstance();
        aCmd.setEnabled(false);
        
        DeccelCommand bCmd = DeccelCommand.getInstance();
        bCmd.setEnabled(false);
        
        RightCommand rCmd = RightCommand.getInstance();
        rCmd.setEnabled(false);
        
        LeftCommand lCmd = LeftCommand.getInstance();
        lCmd.setEnabled(false);
    }
    
    public void setPlayMode()
    {       
        DeleteCommand dCmd = DeleteCommand.getInstance();
        dCmd.setEnabled(false);        
        
        AddFuelCommand afCmd = AddFuelCommand.getInstance();
        afCmd.setEnabled(false);     
        
        
        AccelCommand aCmd = AccelCommand.getInstance();
        aCmd.setEnabled(true);
        
        DeccelCommand bCmd = DeccelCommand.getInstance();
        bCmd.setEnabled(true);
        
        RightCommand rCmd = RightCommand.getInstance();
        rCmd.setEnabled(true);
        
        LeftCommand lCmd = LeftCommand.getInstance();
        lCmd.setEnabled(true);
    }

}
