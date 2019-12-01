package rakaneth.golden_lotus;

import java.util.Comparator;

public class GameUtils {
    static public <T extends Comparable<T>> T clamp(T val, T low, T high) {
        if (val.compareTo(low) < 0) return low;
        else if (val.compareTo(high) > 0) return high;
        else return val;
    }

    static public <T extends Comparable<T>> boolean between(T val, T low, T high) {
        return clamp(val, low, high).equals(val);
    }
}
