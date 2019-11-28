package rakaneth.golden_lotus.ui.screens;

import asciiPanel.AsciiPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.KeyEvent;

abstract public class BaseScreen {
    private static final Logger logger = LogManager.getLogger("BaseScreen");
    protected AsciiPanel terminal;
    protected String name;

    public BaseScreen(String name, AsciiPanel terminal) {
        this.name = name;
        this.terminal = terminal;
    }

    public String getName() { return name; }

    public void enter() {
        logger.debug("Entering {} screen", name);
    }

    public void exit() {
        logger.debug("Exiting {} screen", name);
    }

    abstract public void render();
    abstract public void handle(KeyEvent key);
}
