package rakaneth.golden_lotus.ui.screens;

import asciiPanel.AsciiPanel;


import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

abstract public class BaseScreen {
    private final static Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
    protected AsciiPanel terminal;
    protected String name;

    public BaseScreen(String name, AsciiPanel terminal) {
        this.name = name;
        this.terminal = terminal;
    }

    public String getName() { return name; }

    public void enter() {
        logger.log(Level.INFO, "Entering " + name +  " screen");
    }

    public void exit() {
        logger.log(Level.INFO, "Exiting " + name +   " screen");
   }

    abstract public void render();
    abstract public void handle(KeyEvent key);
}
