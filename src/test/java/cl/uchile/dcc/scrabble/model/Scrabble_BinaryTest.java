package cl.uchile.dcc.scrabble.model;

import cl.uchile.dcc.scrabble.model.notnumber.Scrabble_Bool;
import cl.uchile.dcc.scrabble.model.notnumber.Scrabble_String;
import cl.uchile.dcc.scrabble.model.number.Scrabble_Binary;
import cl.uchile.dcc.scrabble.model.number.Scrabble_Float;
import cl.uchile.dcc.scrabble.model.number.Scrabble_Int;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Scrabble_BinaryTest {
    private Scrabble_Binary bin1;
    private Scrabble_Binary bin2;
    private Scrabble_Binary bin3;
    private Scrabble_Binary bin4;
    private Scrabble_String str1;
    private Scrabble_Bool t;
    private Scrabble_Bool f;
    private Scrabble_Float flt1;
    private Scrabble_Int int1;
    private Scrabble_Int int2;
    private final String value = "010110"; // 22
    private final String value2 = "101100001"; // 353
    private final String value3 = "11111111111111111111111111100010"; // -30
    private final String value4 = "00000000000000000000010011101010";
    private final double d = 22.0;
    private final boolean b1 = true;
    private final boolean b2 = false;
    private final int num = 564738;
    private final int num2 = 2;

    @BeforeEach
    void setUp(){
        bin1 = new Scrabble_Binary(value);
        bin2 = new Scrabble_Binary(value2);
        bin3 = new Scrabble_Binary(value3);
        bin4 = new Scrabble_Binary(value4);
        str1 = new Scrabble_String(value);
        t = new Scrabble_Bool(b1);
        f = new Scrabble_Bool(b2);
        flt1 = new Scrabble_Float(d);
        int1 = new Scrabble_Int(num);
        int2 = new Scrabble_Int(num2);
    }

    @Test
    void constructorTest(){
        Scrabble_Binary expected = new Scrabble_Binary("010110");

        // same class and value
        assertEquals(expected, bin1);
        assertEquals(expected.hashCode(), bin1.hashCode());

        // different class
        assertNotEquals(expected, str1);
        assertNotEquals(expected.hashCode(), str1.hashCode());

        // same class, different value
        assertNotEquals(expected, bin2);
        assertNotEquals(expected.hashCode(), bin2.hashCode());

    }

    @Test
    void toStringTest(){
        String expected = "010110";
        assertEquals(expected, bin1.toString());
    }

    @Test
    void toScrabbleStringTest(){
        Scrabble_String expected = new Scrabble_String("010110");
        assertEquals(expected, bin1.toScrabbleString());
        assertEquals(expected.hashCode(), (bin1.toScrabbleString()).hashCode());
    }

    @Test
    void toBinaryTest(){
        Scrabble_Binary expected = new Scrabble_Binary("101100001");
        assertEquals(expected, bin2.toBinary());
        assertEquals(expected.hashCode(), (bin2.toBinary()).hashCode());
    }

    @Test
    void toIntTest(){
        Scrabble_Int expected1 = new Scrabble_Int(22);
        Scrabble_Int expected2 = new Scrabble_Int(-30);
        Scrabble_Int expected3 = new Scrabble_Int(1258);

        assertEquals(expected1, bin1.toInt());
        assertEquals(expected1.hashCode(), bin1.toInt().hashCode());

        assertEquals(expected2, bin3.toInt());
        assertEquals(expected2.hashCode(), bin3.toInt().hashCode());

        assertEquals(expected3, bin4.toInt());
        assertEquals(expected3.hashCode(), bin4.toInt().hashCode());
    }

    @Test
    void toFloatTest(){
        Scrabble_Float expected = new Scrabble_Float(d);
        assertEquals(expected, bin1.toFloat());
        assertEquals(expected.hashCode(), bin1.toFloat().hashCode());
    }

    @Test
    void orTest(){
        IScrabble expected = new Scrabble_Binary("11111111111111111111111111111111");
        IScrabble expected2 = new Scrabble_Binary(value4);
        IScrabble expected3 = new Scrabble_Binary("00000000000000000000010011111110");
        // Scrabble Binary or Scrabble Bool
            // true
        assertEquals(expected, bin4.or(t));
        assertEquals(expected.hashCode(), bin4.or(t).hashCode());
            // false
        assertEquals(expected2, bin4.or(f));
        assertEquals(expected2.hashCode(), bin4.or(f).hashCode());
        // Scrabble Binary or Scrabble Binary
        assertEquals(expected3, bin1.or(bin4));
        assertEquals(expected3.hashCode(), bin1.or(bin4).hashCode());

        assertEquals(expected3, bin4.or(bin1));
        assertEquals(expected3.hashCode(), bin4.or(bin1).hashCode());

        // ScrabbleBinary || ScrabbleInt
        assertNull(bin1.or(int1));

        // ScrabbleBinary || ScrabbleFloat
        assertNull(bin1.or(flt1));

        // ScrabbleBinary || ScrabbleString
        assertNull(bin1.or(str1));

    }

    @Test
    void andTest(){
        IScrabble expected = new Scrabble_Binary("000000");
        IScrabble expected2 = new Scrabble_Binary(value);
        IScrabble expected3 = new Scrabble_Binary("000010");
        // Scrabble Binary and Scrabble Bool
            // false
        assertEquals(expected, bin1.and(f));
        assertEquals(expected.hashCode(), bin1.and(f).hashCode());
            // bin1
        assertEquals(expected2, bin1.and(t));
        assertEquals(expected2.hashCode(), bin1.and(t).hashCode());
        // Scrabble Binary and Scrabble Binary
        assertEquals(expected3, bin1.and(bin4));
        assertEquals(expected3.hashCode(), bin1.and(bin4).hashCode());

        assertEquals(expected3, bin4.and(bin1));
        assertEquals(expected3.hashCode(), bin4.and(bin1).hashCode());

        // ScrabbleBinary && ScrabbleInt
        assertNull(bin1.and(int1));

        // ScrabbleBinary && ScrabbleFloat
        assertNull(bin1.and(flt1));

        // ScrabbleBinary && ScrabbleString
        assertNull(bin1.and(str1));
    }

    @Test
    void notTest(){
        IScrabble expected = new Scrabble_Binary("11111111111111111111101100010101");
        assertEquals(expected, bin4.not());
        assertEquals(expected.hashCode(), bin4.not().hashCode());
    }

    @Test
    void addTest(){
        IScrabble expected1 = new Scrabble_Binary("10011001100");
        IScrabble expected2 = new Scrabble_Binary("10001001111101100011");
        // Scrabble Binary + Scrabble Binary
        assertEquals(expected1, bin3.add(bin4));
        assertEquals(expected1.hashCode(), bin3.add(bin4).hashCode());
        // Scrabble Binary + Scrabble Int
        assertEquals(expected2, bin2.add(int1));
        assertEquals(expected2.hashCode(), bin2.add(int1).hashCode());
        // Scrabble Binary + Scrabble Float -> null
        assertNull(bin1.add(flt1));

        // ScrabbleBinary + ScrabbleBool
        assertNull(bin1.add(t));

        // ScrabbleBinary + ScrabbleString
        assertNull(bin1.add(str1));
    }

    @Test
    void subTest(){
        IScrabble expected = new Scrabble_Binary("11111111111111111111111010110101");
        IScrabble expected2 = new Scrabble_Binary("11111111111101110110001101011111");
        // Scrabble Binary - Scrabble Binary
        assertEquals(expected, bin1.sub(bin2));
        assertEquals(expected.hashCode(), bin1.sub(bin2).hashCode());
        // Scrabble Binary - Scrabble Int
        assertEquals(expected2, bin2.sub(int1));
        assertEquals(expected2.hashCode(), bin2.sub(int1).hashCode());
        // Scrabble Binary - Scrabble Float -> null
        assertNull(bin4.sub(flt1));

        // ScrabbleBinary - ScrabbleBool
        assertNull(bin1.sub(t));

        // ScrabbleBinary - ScrabbleString
        assertNull(bin1.sub(str1));
    }

    @Test
    void multTest(){
        IScrabble expected = new Scrabble_Binary("1111001010110");
        IScrabble expected2 = new Scrabble_Binary("101111011001010000101100");
        // Scrabble Binary * Scrabble Binary
        assertEquals(expected, bin1.mult(bin2));
        assertEquals(expected.hashCode(), bin1.mult(bin2).hashCode());
        // Scrabble Binary - Scrabble Int
        assertEquals(expected2, bin1.mult(int1));
        assertEquals(expected2.hashCode(), bin1.mult(int1).hashCode());
        // Scrabble Binary - Scrabble Float -> null
        assertNull(bin4.mult(flt1));

        // ScrabbleBinary * ScrabbleBool
        assertNull(bin1.mult(t));

        // ScrabbleBinary * ScrabbleString
        assertNull(bin1.mult(str1));
    }

    @Test
    void divTest(){
        IScrabble expected = new Scrabble_Binary("10000");
        IScrabble expected2 = new Scrabble_Binary("1011");
        IScrabble expected3 = new Scrabble_Binary("0");
        // Scrabble Binary / Scrabble Binary
        assertEquals(expected, bin2.div(bin1));
        assertEquals(expected.hashCode(), bin2.div(bin1).hashCode());
        // Scrabble Binary / Scrabble Int (Scrabble Binary>Scrabble Int)
        assertEquals(expected2, bin1.div(int2));
        assertEquals(expected2.hashCode(), bin1.div(int2).hashCode());
        // Scrabble Binary / Scrabble Int (Scrabble Binary < Scrabble Int)
        assertEquals(expected3, bin1.div(int1));
        assertEquals(expected3.hashCode(), bin1.div(int1).hashCode());
        // Scrabble Binary / Scrabble Float -> null
        assertNull(bin4.div(flt1));

        // ScrabbleBinary / ScrabbleBool
        assertNull(bin1.div(t));

        // ScrabbleBinary / ScrabbleString
        assertNull(bin1.div(str1));
    }
}
