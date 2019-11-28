package rakaneth.golden_lotus.ui;

import asciiPanel.AsciiPanel;
import squidpony.StringKit;

import java.awt.Color;
import java.util.List;

public class Panel {
    private int x;
    private int y;
    private int width;
    private int height;
    private int x2;
    private int y2;
    private String caption;
    private AsciiPanel terminal;

    public Panel(AsciiPanel terminal, int x, int y, int w, int h, String caption) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        x2 = x + width - 1;
        y2 = y + height - 1;
        this.caption = caption;
        this.terminal = terminal;
    }

    public Panel(AsciiPanel terminal, int x, int y, int w, int h) {
        this(terminal, x, y, w, h, null);
    }

    public void write(String text, int x, int y, Color fg, Color bg) {
        List<String> wrapped = StringKit.wrap(text, width - 2);
        int sx = this.x + x;
        int sy = this.y + y;
        for (int i=0; i<wrapped.size(); i++) {
            terminal.write(wrapped.get(i), sx, sy + i, fg, bg);
        }
    }

    public void write(String text, int x, int y) {
        write(text, x, y, AsciiPanel.white, AsciiPanel.black);
    }

    public void write(char c, int x, int y, Color fg, Color bg) {
        int sx = this.x + x;
        int sy = this.y + y;
        terminal.write(c, sx, sy, fg, bg);
    }

    public void write(char c, int x, int y) {
        write(c, x, y, AsciiPanel.white, AsciiPanel.black);
    }

    public void border() {
        char ul = 0xC9;
        char ur = 0xBB;
        char ll = 0xC8;
        char lr = 0xBC;
        char vert = 0xBA;
        char horz = 0xCD;
        int rightEdge = width - 1;
        int botEdge = height - 1;

        for (int i = 1; i < width; i++) {
            write(horz, i, 0);
            write(horz, i, botEdge);
        }

        for (int j = 1; j < height; j++) {
            write(vert, 0, j);
            write(vert, rightEdge, j);
        }

        write(ul, 0, 0);
        write(ur, rightEdge, 0);
        write(ll, 0, botEdge);
        write(lr, rightEdge, botEdge);

        if (caption != null) {
            write(caption,1, 0, AsciiPanel.white, AsciiPanel.black);
        }
    }
}
