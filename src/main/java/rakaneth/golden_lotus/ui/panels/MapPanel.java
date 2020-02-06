package rakaneth.golden_lotus.ui.panels;

import asciiPanel.AsciiPanel;
import rakaneth.golden_lotus.GameConfig;
import rakaneth.golden_lotus.GameUtils;
import rakaneth.golden_lotus.entity.Thing;
import rakaneth.golden_lotus.interfaces.Drawable;
import rakaneth.golden_lotus.interfaces.Locatable;
import rakaneth.golden_lotus.map.GameMap;
import squidpony.squidmath.Coord;

public class MapPanel extends Panel {
    private GameMap map;
    private Thing locator;

    public MapPanel(AsciiPanel terminal, GameMap map, int x, int y, int w, int h) {
        super(terminal, x, y, w, h);
        this.map = map;
        locator = new Thing();
    }

    private int camCalc(int p, int m, int s) {
        return GameUtils.clamp(p - s / 2, 0, Math.max(0, m - s));
    }
    public void renderMap(int px, int py) {
        int left = camCalc(px, map.width, width);
        int top = camCalc(py, map.height, height);
        int wx, wy;
        char c;
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                wx = x + left;
                wy = y + top;
                c = map.getTile(wx, wy);
                if (c != (char)0) {
                    write(c, x, y);
                }
            }
        }
        //for debugging
        if (GameConfig.DEBUG) {
            locator.setPos(px, py);
            drawEntity(locator, px, py);
        }
    }

    public <T extends Locatable & Drawable> void drawEntity(T entity, int cx, int cy) {
        int left = camCalc(cx, map.width, width);
        int top = camCalc(cy, map.height, height);
        Coord pos = entity.getPos();
        int sx = pos.x-left;
        int sy = pos.y-top;
        if (sx >= 0 && sy >= 0)
            write(entity.getGlyph(), sx, sy, entity.getFG(), entity.getBG());
    }
}
