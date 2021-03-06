package rakaneth.golden_lotus;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import rakaneth.golden_lotus.ui.screens.ScreenManager;
import rakaneth.golden_lotus.ui.screens.TitleScreen;
import rakaneth.golden_lotus.GameConfig;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class App
    extends JFrame
    implements KeyListener {
    private static final long serialVersionUID = 47;
    private AsciiPanel terminal;

    public App() {
        super();
        terminal = new AsciiPanel(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, AsciiFont.TALRYTH_15_15);
        ScreenManager.getInstance().push(new TitleScreen(terminal));
        add(terminal);
        addKeyListener(this);
        pack();
        repaint();
    }

    public void repaint() {
        terminal.clear();
        ScreenManager.getInstance().render();
        super.repaint();
    }

    public void keyPressed(KeyEvent e) {
        ScreenManager.getInstance()
            .peek()
            .handle(e);
        repaint();
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        App app = new App();
        GameConfig.DEBUG = Arrays.asList(args).contains("--debug");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
