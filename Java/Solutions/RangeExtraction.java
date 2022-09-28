/**
 * Range Extraction <4kyu>
 * 
 * @description
 *              A format for expressing an ordered list of integers is to use a
 *              comma separated list of either
 * 
 *              individual integers
 *              or a range of integers denoted by the starting integer separated
 *              from the end integer in the range by a dash, '-'. The range
 *              includes all integers in the interval including both endpoints.
 *              It is not considered a range unless it spans at least 3 numbers.
 *              For example "12,13,15-17"
 *              Complete the solution so that it takes a list of integers in
 *              increasing order and returns a correctly formatted string in the
 *              range format.
 * 
 *              Example:
 * 
 *              Solution.rangeExtraction(new int[] {-10, -9, -8, -6, -3, -2, -1,
 *              0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20})
 *              # returns "-10--8,-6,-3-1,3-5,7-11,14,15,17-20"
 * 
 * @link https://www.codewars.com/kata/51ba717bb08c1cd60f00002f
 */
public class RangeExtraction {
    public static String rangeExtraction(int[] arr) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append(arr[i]);
            } else if (arr[i] == arr[i - 1] + 1) {
                if (i == arr.length - 1) {
                    sb.append(count > 0 ? "-" : ",");
                    sb.append(arr[i]);
                } else {
                    count++;
                }
            } else if (count == 0) {
                sb.append(",");
                sb.append(arr[i]);
            } else {
                sb.append(count == 1 ? "," : "-");
                sb.append(arr[i - 1]);
                sb.append(",");
                sb.append(arr[i]);
                count = 0;
            }
        }

        return sb.toString();
    }
}