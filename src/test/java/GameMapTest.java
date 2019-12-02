import asciiPanel.AsciiPanel;
import junit.framework.TestCase;
import rakaneth.golden_lotus.map.GameMap;
import squidpony.squidgrid.mapping.DungeonGenerator;
import squidpony.squidmath.Coord;
import squidpony.squidmath.StatefulRNG;

import java.awt.*;

public class GameMapTest extends TestCase {
    private GameMap tmap;
    private GameMap tmap2;

    protected void setUp() {
        DungeonGenerator dg = new DungeonGenerator(50, 45);
        char[][] base = dg.generate();
        char[][] base2 = dg.generate();
        tmap = new GameMap("test-map", "Test Map", base, true, AsciiPanel.brightBlack, new Color(50, 50, 50), new StatefulRNG());
        tmap2 = new GameMap("test-map-2", "Test Map Two", base2, false, new Color(127, 101, 91), AsciiPanel.brightBlack, new StatefulRNG());
    }

    public void testMapBasics() {
        assertEquals(50, tmap.width);
        assertEquals(45, tmap.height);
    }

    public void testChangeTile() {
        tmap.setTile(1, 1, '+');
        char shouldBeDoor = tmap.getTile(1, 1);
        assertEquals('+', shouldBeDoor);
    }

    public void testConnections() {
        Coord from = Coord.get(2, 7);
        Coord to = Coord.get(3, 5);
        tmap.connect(tmap2, from, to, (byte) 0, true);
        GameMap.MapConnection conn = tmap.getConnection(from);
        GameMap.MapConnection conn2 = tmap2.getConnection(to);
        assertNotNull(conn);
        assertNotNull(conn2);
        assertEquals("test-map-2", conn.destID);
        assertEquals(to, conn.destCoord);
        assertEquals((byte)0, conn.direction);
        assertEquals("test-map", conn2.destID);
        assertEquals(from, conn2.destCoord);
        assertEquals((byte)1, conn2.direction);
    }

}
