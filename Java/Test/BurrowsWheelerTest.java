import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BurrowsWheelerTest {
    @Test
    public void decodeTests() {
        assertEquals("bananabar",     BurrowsWheeler.decode("nnbbraaaa", 4));
        assertEquals("Humble Bundle", BurrowsWheeler.decode("e emnllbduuHB", 2));
        assertEquals("Mellow Yellow", BurrowsWheeler.decode("ww MYeelllloo", 1));
    }

    @Test
    public void encodeTests() {
        assertEquals(new BWT("nnbbraaaa", 4),     BurrowsWheeler.encode("bananabar"));
        assertEquals(new BWT("e emnllbduuHB", 2), BurrowsWheeler.encode("Humble Bundle"));
        assertEquals(new BWT("ww MYeelllloo", 1), BurrowsWheeler.encode("Mellow Yellow"));
    }
}
