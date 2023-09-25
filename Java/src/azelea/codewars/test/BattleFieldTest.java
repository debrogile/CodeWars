package azelea.codewars.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import azelea.codewars.BattleField;


public class BattleFieldTest {

  private static int[][] battleField = {{1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                                        {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                                        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
                                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                                        {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                                        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    @Test
    public void test() {
        assertEquals(true, BattleField.fieldValidator(battleField));
    }
}