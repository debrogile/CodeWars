import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Sum by Factors <4kyu>
 * 
 * @description
 *              Given an array of positive or negative integers
 * 
 *              I= [i1,..,in]
 * 
 *              you have to produce a sorted array P of the form
 * 
 *              [ [p, sum of all ij of I for which p is a prime factor (p
 *              positive) of ij] ...]
 * 
 *              P will be sorted by increasing order of the prime numbers. The
 *              final result has to be given as a string in Java, C#, C, C++ and
 *              as an array of arrays in other languages.
 * 
 *              Example:
 *              I = {12, 15}; // result = "(2 12)(3 27)(5 15)"
 *              [2, 3, 5] is the list of all prime factors of the elements of I,
 *              hence the result.
 * 
 *              Notes:
 * 
 *              It can happen that a sum is 0 if some numbers are negative!
 *              Example: I = [15, 30, -45] 5 divides 15, 30 and (-45) so 5
 *              appears in the result, the sum of the numbers for which 5 is a
 *              factor is 0 so we have [5, 0] in the result amongst others.
 * 
 *              In Fortran - as in any other language - the returned string is
 *              not permitted to contain any redundant trailing whitespace: you
 *              can use dynamically allocated character strings.
 * 
 * @link https://www.codewars.com/kata/54d496788776e49e6b00052f
 */
public class SumOfDivided {
    public static String sumOfDivided(int[] l) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < l.length; i++) {
            int num = l[i];
            boolean flag = false;
            for (int k = 2; k <= Math.abs(num);) {
                if (num % k == 0) {
                    num /= k;
                    if (!flag) {
                        int sum = map.get(k) == null ? 0 : map.get(k);
                        map.put(k, sum + l[i]);
                        flag = true;
                    }
                } else {
                    k++;
                    flag = false;
                }
            }
        }

        return map.entrySet().stream().sorted(new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> a, Entry<Integer, Integer> b) {
                return a.getKey() - b.getKey();
            }
        }).map(entry -> String.format("(%d %d)", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining());
    }
}
