package azelea.codewars;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Common Denominators <5kyu>
 * 
 * @description
 *              Common denominators
 * 
 *              You will have a list of rationals in the form
 * 
 *              { {numer_1, denom_1} , ... {numer_n, denom_n} }
 *              or
 *              [ [numer_1, denom_1] , ... [numer_n, denom_n] ]
 *              or
 *              [ (numer_1, denom_1) , ... (numer_n, denom_n) ]
 *              where all numbers are positive ints. You have to produce a
 *              result in the form:
 * 
 *              (N_1, D) ... (N_n, D)
 *              or
 *              [ [N_1, D] ... [N_n, D] ]
 *              or
 *              [ (N_1', D) , ... (N_n, D) ]
 *              or
 *              {{N_1, D} ... {N_n, D}}
 *              or
 *              "(N_1, D) ... (N_n, D)"
 *              depending on the language (See Example tests) in which D is as
 *              small as possible and
 * 
 *              N_1/D == numer_1/denom_1 ... N_n/D == numer_n,/denom_n.
 *              Example:
 *              convertFracs [(1, 2), (1, 3), (1, 4)] `shouldBe` [(6, 12), (4,
 *              12), (3, 12)]
 *              Note:
 *              Due to the fact that the first translations were written long
 *              ago - more than 6 years - these first translations have only
 *              irreducible fractions.
 * 
 *              Newer translations have some reducible fractions. To be on the
 *              safe side it is better to do a bit more work by simplifying
 *              fractions even if they don't have to be.
 * 
 *              Note for Bash:
 *              input is a string, e.g "2,4,2,6,2,8" output is then "6 12 4 12 3
 *              12"
 * 
 * @link https://www.codewars.com/kata/54d7660d2daf68c619000d95
 */
public class Fracts {
    public static String convertFrac(long[][] lst) {
        if (lst.length == 0) {
            return "";
        }

        for (long[] ls : lst) {
            long gcd = gcd(ls[0], ls[1]);
            ls[0] /= gcd;
            ls[1] /= gcd;
        }

        long denom = Arrays.stream(lst).mapToLong((i) -> i[1]).reduce((l, r) -> lcm(l, r)).getAsLong();
        return Arrays.stream(lst).map(i -> String.format("(%d,%d)", denom * i[0] / i[1], denom))
                .collect(Collectors.joining());
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}