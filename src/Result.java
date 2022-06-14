import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Result {
    /*
     * Complete the 'sockMerchant' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY ar
     */

    public static int sockMerchant(int n, List<Integer> ar) {
        // Write your code here
        int result = 0;
        HashSet<Integer> elems = new HashSet<>();
        for (Integer el:
             ar) {
            if(elems.contains(el)){
                result++;
                elems.remove(el);
            } else {
                elems.add(el);
            }
        }
        return result;
    }
    public static void main(String[] args) {
	// write your code here
        System.out.println(sockMerchant(7, Arrays.asList(new Integer[]{10, 20, 20, 10, 10, 30, 50, 10, 20})));
    }
}
