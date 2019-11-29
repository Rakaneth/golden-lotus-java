package rakaneth.golden_lotus.ui.screens;

import asciiPanel.AsciiPanel;
import rakaneth.golden_lotus.ui.Panel;

import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TitleScreen extends BaseScreen {
    private Panel title;
    private static final Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);

    public TitleScreen(AsciiPanel terminal) {
        super("title", terminal);
        title = new Panel(terminal, 5, 5, 35, 10, "Tales of the Golden Lotus");
    }

    @Override
    public void render() {
        title.border();
    }

    @Override
    public void handle(KeyEvent key) {
        logger.log(Level.INFO, "Key " +  key.getKeyChar() + " pressed");
    }
}
