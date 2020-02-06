package rakaneth.golden_lotus.ui.panels;

import asciiPanel.AsciiPanel;
import rakaneth.golden_lotus.GameConfig;
import rakaneth.golden_lotus.GameUtils;
import rakaneth.golden_lotus.map.GameMap;

public class MapPanel extends Panel {
    private GameMap map;

    public MapPanel(AsciiPanel terminal, GameMap map, int x, int y, int w, int h) {
        super(terminal, x, y, w, h);
        this.map = map;
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
        writeChar('X', px, py, px, py);
    }

    public void writeChar(char c, int px, int py, int cx, int cy) {
        int left = camCalc(cx, map.width, width);
        int top = camCalc(cy, map.height, height);
        int sx = px-left;
        int sy = py-top;
        if (sx >= 0 && sy >= 0)
            write(c, sx, sy);
    }
}
