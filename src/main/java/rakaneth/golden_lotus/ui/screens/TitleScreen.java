package rakaneth.golden_lotus.ui.screens;

import asciiPanel.AsciiPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rakaneth.golden_lotus.ui.Panel;

import java.awt.event.KeyEvent;

public class TitleScreen extends BaseScreen {
    private Panel title;
    private static Logger logger = LogManager.getLogger("Title Screen");

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
        logger.debug("Key {} pressed", key.getKeyChar());
    }
}
