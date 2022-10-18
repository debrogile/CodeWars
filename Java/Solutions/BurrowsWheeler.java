import java.util.Arrays;

/**
 * Burrows-Wheeler-Transformation <4kyu>
 * 
 * @description Motivation
 *              When compressing sequences of symbols, it is useful to have many
 *              equal
 *              symbols follow each other, because then they can be encoded with
 *              a run length
 *              encoding. For example, RLE encoding of "aaaabbbbbbbbbbbcccccc"
 *              would give
 *              something like 4a 11b 6c.
 * 
 *              (Look here for learning more about the run-length-encoding.)
 * 
 *              Of course, RLE is interesting only if the string contains many
 *              identical
 *              consecutive characters. But what bout human readable text? Here
 *              comes the
 *              Burrows-Wheeler-Transformation.
 * 
 *              Transformation
 *              There even exists a transformation, which brings equal symbols
 *              closer
 *              together, it is called the Burrows-Wheeler-Transformation. The
 *              forward
 *              transformation works as follows: Let's say we have a sequence
 *              with length n,
 *              first write every shift of that string into a n x n matrix:
 * 
 *              Input: "bananabar"
 * 
 *              b a n a n a b a r
 *              r b a n a n a b a
 *              a r b a n a n a b
 *              b a r b a n a n a
 *              a b a r b a n a n
 *              n a b a r b a n a
 *              a n a b a r b a n
 *              n a n a b a r b a
 *              a n a n a b a r b
 *              Then we sort that matrix by its rows. The output of the
 *              transformation then
 *              is the last column and the row index in which the original
 *              string is in:
 * 
 *              .-.
 *              a b a r b a n a n
 *              a n a b a r b a n
 *              a n a n a b a r b
 *              a r b a n a n a b
 *              b a n a n a b a r <- 4
 *              b a r b a n a n a
 *              n a b a r b a n a
 *              n a n a b a r b a
 *              r b a n a n a b a
 *              '-'
 * 
 *              Output: ("nnbbraaaa", 4)
 *              To handle the two kinds of output data, we will use the
 *              preloaded class BWT,
 * 
 *              Of course we want to restore the original input, therefore you
 *              get
 *              the following hints:
 * 
 *              The output contains the last matrix column.
 *              The first column can be acquired by sorting the last column.
 *              For every row of the table: Symbols in the first column follow
 *              on
 *              symbols in the last column, in the same way they do in the input
 *              string.
 *              You don't need to reconstruct the whole table to get the input
 *              back.
 *              Goal
 *              The goal of this Kata is to write both, the encode and decode
 *              functions. Together they should work as the identity function on
 *              lists. (Note: For the empty input, the row number is ignored.)
 * 
 *              Further studies
 *              You may have noticed that symbols are not always consecutive,
 *              but
 *              just in proximity, after the transformation. If you're
 *              interested
 *              in how to deal with that, you should have a look at this Kata.
 */
class BWT {
    public String s;
    public int n;

    public BWT(String s, int n) {
        this.s = s;
        this.n = n;
    }

    @Override
    public String toString() {
        return s + "," + n;
    }

    @Override 
    public boolean equals(Object o) {
        return o.toString().equals(toString());
    }
}

public class BurrowsWheeler {
    public static BWT encode(String s) {
        int len = s.length();
        if (len == 0) {
            return new BWT("", -1);
        }

        String[] matrix = new String[len];
        for (int i = 0; i < len; i++) {
            matrix[i] = s.substring(len - i, len) + s.substring(0, len - i);
        }

        Arrays.sort(matrix);

        int n = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (s.equals(matrix[i])) {
                n = i;
            }

            sb.append(matrix[i].charAt(len - 1));
        }

        return new BWT(sb.toString(), n);
    }

    public static String decode(String s, int n) {
        if (s.length() < 0) {
            return "";
        }

        int[] f = mergeSort(s.toCharArray());

        StringBuilder sb = new StringBuilder();
        int index = n;
        do {
            index = f[index];
            sb.append(s.charAt(index));
        } while (index != n);
        return sb.toString();
    }

    /**
     * 归并排序
     * 
     * @param arr
     * @return
     */
    private static int[] mergeSort(char[] arr) {
        // 记录排序后的字符在原数组中的下标
        int[] pos = new int[arr.length];

        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = i;
        }

        for (int n = 1; n < arr.length; n *= 2) {
            int lbegin = 0;
            int rbegin = n;
            int lend = lbegin + n;
            int rend = Math.min(rbegin + n, arr.length);

            for (int i = 0; i < arr.length;) {
                if (lbegin >= lend) {
                    do {
                        pos[i++] = temp[rbegin++];
                    } while (rbegin < rend);
                } else if (rbegin >= rend) {
                    do {
                        pos[i++] = temp[lbegin++];
                    } while (lbegin < lend);
                } else {
                    int left = temp[lbegin];
                    int right = temp[rbegin];
                    if (arr[left] <= arr[right]) {
                        pos[i++] = left;
                        lbegin++;
                    } else {
                        pos[i++] = right;
                        rbegin++;
                    }
                }

                if (lbegin >= lend && rbegin >= rend) {
                    lbegin += n;
                    rbegin = lend = Math.min(lbegin + n, arr.length);
                    rend = Math.min(rbegin + n, arr.length);
                }
            }

            for (int i = 0; i < arr.length; i++) {
                temp[i] = pos[i];
            }
        }

        return pos;
    }
}
