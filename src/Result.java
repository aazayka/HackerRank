import java.util.Arrays;

public class Result {

    public static String twoStrings(String s1, String s2) {
        // Write your code here
        byte[] b1 = s1.getBytes();
        byte[] b2 = s2.getBytes();
        Arrays.sort(b1);
        Arrays.sort(b2);

        int i1 = 0;
        int i2 = 0;

        while(i1 < b1.length && i2 < b2.length) {
            if (b1[i1] == b2[i2]) {
                return "YES";
            } else if (b1[i1] < b2[i2]) {
                i1++;
            } else {
                i2++;
            }
        }

        return "NO";
    }
    public static void main(String[] args) {
	// write your code here
        System.out.println(twoStrings("hi", "world"));
    }
}
