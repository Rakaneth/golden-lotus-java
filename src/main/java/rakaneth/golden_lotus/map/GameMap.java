package rakaneth.golden_lotus.map;

import squidpony.squidgrid.mapping.DungeonUtility;
import squidpony.squidmath.Coord;
import squidpony.squidmath.GreasedRegion;
import squidpony.squidmath.IRNG;

import java.awt.*;
import java.util.HashMap;

import static rakaneth.golden_lotus.GameUtils.clamp;
import static rakaneth.golden_lotus.GameUtils.between;

public class GameMap {
    public class MapConnection {
        public final String destID;
        public final Coord destCoord;
        public final byte direction;

        /**
         * Makes a new MapConnection.
         * @param destID Map ID for the destination map.
         * @param destCoord Coord for the destination map.
         * @param direction 0 for down, 1 for up, 2 for out.
         */
        MapConnection(String destID, Coord destCoord, byte direction) {
            this.destID = destID;
            this.destCoord = destCoord;
            this.direction = direction;
        }
    }
    private char[][] baseMap;
    private double[][] resistances;
    private final String id;
    private final String name;
    private boolean lit;
    private HashMap<Coord, MapConnection> connections;
    private Color wallColor;
    private Color floorColor;
    private GreasedRegion floors;
    public final int width;
    public final int height;
    private GreasedRegion scratch;
    private IRNG rng;

    public GameMap(String id, String name, char[][] map, boolean lit, Color wallColor, Color floorColor, IRNG rng) {
        this.id = id;
        this.name = name;
        this.baseMap = map;
        this.lit = lit;
        this.wallColor = wallColor;
        this.floorColor = floorColor;
        this.resistances = DungeonUtility.generateResistances(baseMap);
        this.floors = new GreasedRegion(baseMap, '.');
        this.width = baseMap.length;
        this.height = baseMap[0].length;
        this.scratch = new GreasedRegion(width, height);
        this.rng = rng;
        this.connections = new HashMap<>();
    }

    public char[][] getBaseMap() { return baseMap; }
    public String getName() { return name; }
    public String getID() { return id; }
    public boolean isLit() { return lit; }
    public Color getWallColor() { return wallColor; }
    public Color getFloorColor() { return floorColor; }
    public boolean inBounds(int x, int y) {
        return between(x, 0, width - 1) && between(y, 0, height - 1);
    }

    public boolean inBounds(Coord c) {
        return inBounds(c.x, c.y);
    }

    public char getTile(int x, int y) {
        if (inBounds(x, y)) return baseMap[x][y];
        else return 0;
    }

    public char getTile(Coord c) {
        return getTile(c.x, c.y);
    }

    public void setTile(int x, int y, char c) {
        if (!inBounds(x, y)) return;
        baseMap[x][y] = c;
        DungeonUtility.generateResistances(baseMap);
        this.floors.refill(baseMap, '.');
    }

    public void setTile(Coord c, char ch) {
        setTile(c.x, c.y, ch);
    }

    public Coord randomFloor() {
        return floors.singleRandom(rng);
    }

    public Coord randomFloorWithin(int x, int y, int r) {
        return scratch.empty().insert(Coord.get(x, y)).flood(floors, r).singleRandom(rng);
    }

    public Coord randomFloorWithin(Coord c, int r) {
        return randomFloorWithin(c.x, c.y, r);
    }

    /**
     * Connects this to otherMap.
     * @param otherMap The map to connect to.
     * @param fromCoord Coord on this map (from)
     * @param toCoord Coord on other map (to)
     * @param direction 0 for down, 1 for up, 2 for out.
     * @param twoWay true if the connection is two-way.
     */
    public void connect(GameMap otherMap, Coord fromCoord, Coord toCoord, byte direction, boolean twoWay) {
        MapConnection conn = new MapConnection(otherMap.getID(), toCoord, direction);
        connections.putIfAbsent(fromCoord, conn);
        char toSet;
        byte opp;
        switch(direction) {
            case 1:
                toSet = '<';
                opp = 0;
                break;
            case 2:
                toSet = '0';
                opp = 2;
                break;
            default:
                toSet = '>';
                opp = 1;
        }
        setTile(fromCoord, toSet);
        if (twoWay) otherMap.connect(this, toCoord, fromCoord, opp, false);
    }

    public MapConnection getConnection(Coord c) {
        return connections.getOrDefault(c, null);
    }

}
