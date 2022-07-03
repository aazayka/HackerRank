import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SlidingWindowMedianArr {

    public static final int ARRAY_SIZE = 201;
    static int[] elems;
    static Queue<Integer> window;
    static int lastMedian;
    static int lastMedianVal;
    static int recalcFrom;

    public static int activityNotifications(List<Integer> expenditure, int d) {
        int cnt = 0;
        elems=new int[ARRAY_SIZE];
        window = new LinkedList<>();
        lastMedian = -1;
        lastMedianVal = -1;
        int result = 0;


        for (Integer elem : expenditure) {
            cnt++;
            //System.out.println("step=" + cnt + ",elem=" + elem);
            if (cnt > d) {
                double median = getDoubleMedian(d);
                //System.out.println("Double Median = " + median);
                if (median <= elem) result++;
                removeElement();
            }
            addElement(elem);
        }

        return result;
    }


    private static void removeElement() {
        final Integer elem = window.poll();
        if (elem <= lastMedian) lastMedianVal = -1;
        elems[elem]--;

    }

    private static void addElement(Integer elem) {
        if (elem <= lastMedian) lastMedianVal = -1;
        elems[elem]++;
        window.offer(elem);
    }

    private static int getDoubleMedian(int d) {
        if (lastMedianVal != -1) return lastMedianVal;

        int pos1 = d/2;
        int pos2 = d/2+1;

        int elem1 =0;
        int elem2 =0;
        int currPos = 0;
        int i = 0;

        while(currPos < pos2 && i <= ARRAY_SIZE) {
            if ((currPos < pos1) && (currPos + elems[i] >= pos1)) {
                elem1 = i;
            }
            if ((currPos < pos2) && (currPos + elems[i] >= pos2)) {
                elem2 = i;
            }
            currPos = currPos + elems[i];
            i++;

        }

        //System.out.println("elem1=" +elem1 + ", elem2 = " + elem2);
        lastMedian = elem2;

        if(d%2 == 0) {
            lastMedianVal = elem1 + elem2;
        } else {
            lastMedianVal = 2*  elem2;
        }
        return lastMedianVal;
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        ////System.out.println(activityNotifications(List.of(10,20,30,40,50), 3));
        ////System.out.println(activityNotifications(List.of(2,3,4,2,3,6,8,4,5), 5));
        //        //System.out.println(activityNotifications(List.of(1,2,3,4,1,2,3,4,1,2,3,4), 4));

        //if(1==1) return;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Work\\Java\\HackerRank\\files\\input02.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        //System.out.println(SlidingWindowMedianArr.activityNotifications(expenditure, d));

        bufferedReader.close();

    }
}
