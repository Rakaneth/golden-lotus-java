package rakaneth.golden_lotus;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;

import javax.swing.JFrame;

public class App extends JFrame {
    private static final long serialVersionUID = 47;
    private AsciiPanel terminal;

    public App() {
        super();
        terminal = new AsciiPanel(100, 40, AsciiFont.TALRYTH_15_15);
        terminal.write("Tales of the Golden Lotus", 1, 1);
        add(terminal);
        pack();
    }

    public static void main(String[] args) {
        App app = new App();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
