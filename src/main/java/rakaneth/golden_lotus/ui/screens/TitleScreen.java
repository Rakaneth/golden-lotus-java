package rakaneth.golden_lotus.ui.screens;

import asciiPanel.AsciiPanel;
import rakaneth.golden_lotus.GameUtils;
import rakaneth.golden_lotus.map.GameMap;
import rakaneth.golden_lotus.ui.panels.MapPanel;
import rakaneth.golden_lotus.ui.panels.Panel;
import squidpony.squidgrid.mapping.DungeonGenerator;
import squidpony.squidmath.StatefulRNG;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TitleScreen extends BaseScreen {
    private Panel title;
    private MapPanel mapPanel;
    private GameMap map;
    private static final Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
    private int centerX;
    private int centerY;

    public TitleScreen(AsciiPanel terminal) {
        super("title", terminal);
        title = new Panel(terminal, 5, 5, 35, 10, "Tales of the Golden Lotus");
        centerX = 0;
        centerY = 0;
        DungeonGenerator dg = new DungeonGenerator(75, 50);
        map = new GameMap("test-map", "Test Map", dg.generate(), true, AsciiPanel.brightBlack, new Color(50, 50, 50), new StatefulRNG());
        mapPanel = new MapPanel(terminal, map, 40, 5, 50, 25);
    }

    @Override
    public void render() {
        title.border();
        mapPanel.renderMap(centerX, centerY);
    }

    @Override
    public void handle(KeyEvent key) {
        switch(key.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                centerY = GameUtils.clamp(++centerY, 0, map.height - 1);
                break;
            case KeyEvent.VK_UP:
                centerY = GameUtils.clamp(--centerY, 0, map.height - 1);
                break;
            case KeyEvent.VK_LEFT:
                centerX = GameUtils.clamp(--centerX, 0, map.width - 1);
                break;
            case KeyEvent.VK_RIGHT:
                centerX = GameUtils.clamp(++centerX, 0, map.width - 1);
                break;
        }
        logger.log(Level.INFO, "Key " +  key.getKeyChar() + " pressed");
    }
}
