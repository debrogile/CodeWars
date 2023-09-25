package azelea.codewars;

/**
 * Battleship field validator <3kyu>
 * 
 * @description Write a method that takes a field for well-known board game
 *              "Battleship" as an argument and returns true if it has a valid
 *              disposition of ships, false otherwise. Argument is guaranteed to
 *              be 10*10 two-dimension array. Elements in the array are numbers,
 *              0 if the cell is free and 1 if occupied by ship.
 * 
 *              Battleship (also Battleships or Sea Battle) is a guessing game
 *              for two players. Each player has a 10x10 grid containing several
 *              "ships" and objective is to destroy enemy's forces by targetting
 *              individual cells on his field. The ship occupies one or more
 *              cells in the grid. Size and number of ships may differ from
 *              version to version. In this kata we will use Soviet/Russian
 *              version of the game.
 * 
 *              Before the game begins, players set up the board and place the
 *              ships accordingly to the following rules:
 *              There must be single battleship (size of 4 cells), 2 cruisers
 *              (size 3), 3 destroyers (size 2) and 4 submarines (size 1). Any
 *              additional ships are not allowed, as well as missing ships.
 *              Each ship must be a straight line, except for submarines, which
 *              are just single cell.
 * 
 *              The ship cannot overlap or be in contact with any other ship,
 *              neither by edge nor by corner.
 * 
 *              This is all you need to solve this kata. If you're interested in
 *              more information about the game, visit this link.
 * 
 * @link https://www.codewars.com/kata/52bb6539a4cf1b12d90005b7
 */
public class BattleField {
    public static boolean fieldValidator(int[][] field) {
        // 记录每种船的数量
        int[] ships = new int[4];
        // 临时数组，记录船体大小，正数表示横向，负数表示纵向
        int[][] temp = new int[field.length][field[0].length];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (getFieldValue(field, i, j) == 0) {
                    continue;
                }

                int rightBottom = getFieldValue(field, i + 1, j + 1);
                // 对角相邻，不合法
                if (rightBottom == 1) {
                    return false;
                }

                if (!validInternal(i, j, field, temp, ships)) {
                    return false;
                }
            }
        }

        return ships[0] == 4 && ships[1] == 3 && ships[2] == 2 && ships[3] == 1;
    }

    /**
     * 获取船体，超出边界返回0
     */
    private static int getFieldValue(int[][] field, int i, int j) {
        if (i < 0 || i >= field.length || j < 0 || j >= field[i].length) {
            return 0;
        }
        return field[i][j];
    }

    private static boolean validInternal(int i, int j, int[][] field, int[][] temp, int[] ships) {
        int right = getFieldValue(field, i, j + 1);
        int bottom = getFieldValue(field, i + 1, j);
        // 横向和纵向都有相邻，不合法
        if (right == 1 && bottom == 1) {
            return false;
        }

        int size = temp[i][j];
        // 超长、横向的船与其他船相邻、纵向的船与其他船相邻，不合法
        if ((size > 4 || size < -4) ||
                (size > 0 && bottom == 1) ||
                (size < 0 && right == 1)) {
            return false;
        }

        if (size > 0) {
            horizontal(i, j, right, temp, ships);
        } else if (size < 0) {
            vertical(i, j, bottom, temp, ships);
        } else {
            initialOrientation(i, j, bottom, right, temp, ships);
        }

        return true;
    }

    /**
     * 横向船体判断
     */
    private static void horizontal(int i, int j, int right, int[][] temp, int[] ships) {
        int size = temp[i][j];
        if (right == 1) {
            // 没有达到船尾，长度+1
            temp[i][j + 1] = size + 1;
        } else {
            // 船尾
            ships[size - 1]++;
        }
    }

    /**
     * 纵向船体判断
     */
    private static void vertical(int i, int j, int bottom, int[][] temp, int[] ships) {
        int size = temp[i][j];
        if (bottom == 1) {
            // 没有达到船尾，长度+1
            temp[i + 1][j] = size - 1;
        } else {
            // 船尾
            ships[-size - 1]++;
        }
    }

    /**
     * 初始化船体方向
     */
    private static void initialOrientation(int i, int j, int bottom, int right, int[][] temp, int[] ships) {
        if (right == 1) {
            temp[i][j + 1] = 2;
        } else if (bottom == 1) {
            temp[i + 1][j] = -2;
        } else {
            ships[0]++;
        }
    }
}