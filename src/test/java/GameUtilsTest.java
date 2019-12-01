import org.junit.Assert;
import org.junit.Test;
import rakaneth.golden_lotus.GameUtils;

public class GameUtilsTest {
    @Test
    public void testClamp() {
        int lowBound = GameUtils.clamp(1, 5, 10);
        int inBetween = GameUtils.clamp(5, 1, 10);
        int highBound = GameUtils.clamp(10, 1, 5);
        Assert.assertEquals(5, lowBound);
        Assert.assertEquals(5, inBetween);
        Assert.assertEquals(5, highBound);
    }

    @Test
    public void testBetween() {
        boolean tooLow = GameUtils.between(1, 5, 10);
        boolean inBetween = GameUtils.between(5, 1, 10);
        boolean tooHigh = GameUtils.between(10, 1, 5);
        Assert.assertFalse(tooLow);
        Assert.assertFalse(tooHigh);
        Assert.assertTrue(inBetween);
    }
}
