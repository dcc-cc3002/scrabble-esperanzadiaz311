package model;

import cl.uchile.dcc.scrabble.model.Scrabble_Bool;
import cl.uchile.dcc.scrabble.model.Scrabble_String;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Scrabble_BoolTest {
    private Scrabble_Bool s_bool;
    private Scrabble_Bool s_bool2;
    private Scrabble_String s_str;
    private boolean b = true;
    private boolean b2 = false;
    private String str = "true";

    @BeforeEach
    void setUp(){
        s_bool = new Scrabble_Bool(b);
        s_bool2 = new Scrabble_Bool(b2);
        s_str = new Scrabble_String(str);
    }

    @Test
    void constructorTest(){
        Scrabble_Bool expected = new Scrabble_Bool(true);

        assertEquals(expected, s_bool);
        assertEquals(expected.hashCode(), s_bool.hashCode());

        assertNotEquals(expected, s_bool2);
        assertNotEquals(expected.hashCode(), s_bool2.hashCode());

        assertNotEquals(expected, s_str);
        assertNotEquals(expected.hashCode(), s_str.hashCode());
    }

    @Test
    void toStringTest(){
        String expected = "true";
        assertEquals(expected, s_bool.toString());
    }

    @Test
    void toScrabbleString(){
        Scrabble_String expected = new Scrabble_String("true");
        assertEquals(expected, s_bool.toScrabbleString());
        assertEquals(expected.hashCode(), (s_bool.toScrabbleString()).hashCode());
    }

    @Test
    void toBoolTest(){
        Scrabble_Bool expected = new Scrabble_Bool(true);
        assertEquals(expected, s_bool.toBool());
        assertEquals(expected.hashCode(), (s_bool.toBool()).hashCode());
    }
}