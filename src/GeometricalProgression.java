import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GeometricalProgression {

    public static class Value {
        public long cnt;
        public long chainCnt;

        public Value(long cnt, long chainCnt) {
            this.cnt = cnt;
            this.chainCnt = chainCnt;
        }
    }

    //
    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        long cnt = 0L;
        Map<Long, Value> m = new HashMap<>();

        for (Long val: arr) {
            if (m.containsKey(val)) {
                Value mapObj = m.get(val);
                mapObj.cnt++;
                mapObj.chainCnt += getChainVal(m, val, r);
                m.put(val, mapObj);
            } else {
                m.put(val, new Value(1, getChainVal(m, val, r)));
            }


            if (r != 1 && val % r == 0 && m.containsKey(val / r)) {
                cnt = cnt + m.get(val / r).chainCnt;
            } else if (r == 1 && m.containsKey(val) && m.get(val).cnt > 2) {
                long currCnt = m.get(val).cnt;
                cnt = cnt - calcForOne(currCnt - 1) + calcForOne(currCnt);
            }
        }
        return cnt;
    }

    public static long calcForOne(long val){
        return val * (val - 1) * (val - 2) / 6;
    }

    public static long getChainVal(Map<Long, Value> m, long val, long r) {
        return (val%r == 0 && m.containsKey(val / r)) ? m.get(val / r).cnt : 0L;
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        //System.out.println(countTriplets(List.of(1l ,2l ,2l ,4L), 2));
        //System.out.println(countTriplets(List.of(1L, 3L, 9L, 9L, 27L, 81L), 3));
        //System.out.println(countTriplets(List.of(1L, 5L, 5L, 25L, 125L), 5));
        System.out.println(countTriplets(List.of(1L, 2L, 1L, 2L, 4L), 2));
        System.out.println(countTriplets(List.of(1L, 1L, 3L, 3L, 9L, 9L), 3));

        //System.out.println(countTriplets(List.of(1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L), 1));
//        System.out.println(countTriplets(List.of(), 3));
//        System.out.println(countTriplets(List.of(1L, 1L, 1L, 1L), 1L));
        //if (1==1) return;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("X:\\Scripts\\Multiform test\\new 18.txt"));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        System.out.println(countTriplets(arr, r));

        bufferedReader.close();

    }
}
