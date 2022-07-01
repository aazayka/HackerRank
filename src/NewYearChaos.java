import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NewYearChaos {


    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        Integer[] arr = q.toArray(new Integer[q.size()]);
        int moves = 0;
        for (int i = arr.length - 2; i >=0 ; i--) {
            if (canMove(arr[i], i + 1)){
                if (arr[i] > arr[i+1]) {
                    System.out.println("change " + arr[i] + " and " + arr[i+1] + " position " + i);
                    Integer tmp = arr[i+1];
                    arr[i+1] = arr[i];
                    arr[i] = tmp;
                    moves++;
                }
            } else {
                System.out.println("Too chaotic");
                return;
            }
        }

        for (int i = arr.length - 2; i >=0 ; i--) {
            if (arr[i] > arr[i+1]) {
                System.out.println("change " + arr[i] + " and " + arr[i+1] + " position " + i);
                Integer tmp = arr[i+1];
                arr[i+1] = arr[i];
                arr[i] = tmp;
                moves++;
            }

        }

        System.out.println(moves);
    }

    public static boolean canMove(int val, int position){
        if(val > position + 2 ) return false;
        return true;
    }

    public static void main(String[] args) {
	// write your code here
        //minimumBribes(List.of(1, 2, 5, 3, 7, 8, 6, 4));
        minimumBribes(List.of(2, 5, 1, 3, 4));
    }
}
