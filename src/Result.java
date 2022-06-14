public class Result {

    public static int countingValleys(int steps, String path) {
        // Write your code here
        int alt = 0;
        int val = 0;
        for (int i = 0; i < steps; i++) {
            if (alt == 0 && path.charAt(i) == 'D') {
                val++;
            }
            if (path.charAt(i) == 'D') {
                alt--;
            } else {
                alt++;
            }
        }
        return val;
    }

    public static void main(String[] args) {
	// write your code here
        System.out.println(countingValleys(8, "DDUUUUDD"));
    }
}
