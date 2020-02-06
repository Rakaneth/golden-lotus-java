package rakaneth.golden_lotus.entity;

import rakaneth.golden_lotus.interfaces.Drawable;
import rakaneth.golden_lotus.interfaces.Locatable;
import squidpony.squidmath.Coord;

import java.awt.*;

public class Thing implements Drawable, Locatable {
    protected Coord pos;
    protected char glyph;
    protected Color bg;
    protected Color fg;
    protected String name;
    protected String id;
    protected String desc;


    public Thing(String id, String name, char glyph, Coord pos, Color fg, Color bg, String desc) {
        this.id = id;
        this.name = name;
        this.glyph = glyph;
        this.fg = fg;
        this.bg = bg;
        this.desc = desc;
        this.pos = pos;
    }

    public Thing() {
        this("no-id", "No name", 'X', Coord.get(0, 0), Color.YELLOW, Color.BLACK, "No description");
    }

    @Override
    public char getGlyph() {
        return glyph;
    }

    @Override
    public Color getFG() {
        return fg;
    }

    @Override
    public Color getBG() {
        return bg;
    }

    @Override
    public Coord getPos() {
        return pos;
    }

    public void setPos(int x, int y) {
        pos = Coord.get(x, y);
    }

    public void setPos(Coord c) {
        pos = c;
    }
}
