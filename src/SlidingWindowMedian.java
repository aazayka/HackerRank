/**
 * Solution is not complete - need to add some hack to delete element from correct heap if both of them containing same tops
 */


import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SlidingWindowMedian {

    static PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> right = new PriorityQueue<>();
    static int cntLeft;
    static int cntRight;
    static HashMap<Integer, Integer> deleted = new HashMap<>();
    static LinkedList<Integer> list = new LinkedList<>();

    public static int activityNotifications(List<Integer> expenditure, int d) {
        // Write your code here
        int cnt = 0;
        int result = 0;

        for (Integer elem : expenditure
                ) {
            System.out.println("=========================================");
            System.out.println("add " + elem);
            System.out.println("Before");
            printHeaps();
            cnt++;
            if (cnt > d) {
                final double median = getMedian();
                System.out.println("median=" + median);
                if(elem >= median * 2) {
                    System.out.println("elem=" + elem + " increase");
                    result++;
                }
            }

            add(elem);

            System.out.println("Before delete");
            printHeaps();
            if (cnt > d) {
                deleteFirst();
            }

            System.out.println("After");
            printHeaps();

        }

        return result;
    }

    private static double getMedian() {
        if ((cntRight + cntLeft)%2 == 0) {
            final double v = (peek(left, right, cntLeft) + peek(right, left, cntRight)) / 2.0;
            return v;
        } else {
            return peek(right, left, cntRight);
        }

    }

    private static void printHeaps() {
        System.out.println("***Left, count=" + cntLeft + ", real size=" + left.size());
        left.stream().forEach(System.out::println);

        System.out.println("***Right, count=" + cntRight + ", real size=" + right.size());
        right.stream().forEach(System.out::println);

        System.out.println("***deleted****");
        deleted.forEach((k,v) -> System.out.println("elem=" + k + ", count=" + v));
    }

    private static int peek(PriorityQueue<Integer> source, PriorityQueue<Integer> add, int count) {
        while(!source.isEmpty()) {
            int elem = source.peek();
            if (deleted.containsKey(elem) && deleted.get(elem) > 0 ) {
                deleted.put(elem, deleted.get(elem) - 1);
                if(source.size() > count) {
                    source.remove();
                } else {
                    add.remove();
                }
            } else {
                return elem;
            }
        }

        if (source.isEmpty() && !add.isEmpty()) {
            while(!add.isEmpty()) {
                int elem = add.peek();
                if (deleted.containsKey(elem) && deleted.get(elem) > 0) {
                    deleted.put(elem, deleted.get(elem) - 1);
                    add.remove();
                } else {
                    return elem;
                }
            }
        } else {
            throw new IllegalArgumentException("Peek: both collections are empty");
        }

        throw new IllegalArgumentException("Can't peek element - end");
    }

    private static void deleteFirst() {
        if (list.isEmpty()) throw new IllegalArgumentException("Delete on empty list");

        final Integer elem = list.removeFirst();
        System.out.println("  deleting " + elem);

        int maxLeft = peek(left, right, cntLeft);
        int minRight = peek(right, left, cntRight);

        deleted.merge(elem, 1, Integer::sum);

        if (elem < minRight || (elem == maxLeft && maxLeft == minRight && cntLeft >= cntRight)) {
            System.out.println("Delete from left");
             cntLeft--;
        } else {
            System.out.println("Delete from right");
            cntRight--;
        }
        rebalance();
    }

    private static void add(Integer elem) {
        list.add(elem);

        if (elem < left.peek() || (elem < right.peek() && cntLeft < cntRight)) {
            addLeft(elem);
        }  else if (elem > right.peek()) {
            addRight(elem);
        }
//
//        if ((!left.isEmpty() && elem <= left.peek()) || (left.isEmpty() && !right.isEmpty() && elem <= right.peek())) {
//            addLeft(elem);
//        } else {
//            addRight(elem);
//        }

        //rebalance to keep cntLeft = cntRight, cntRight+1
        rebalance();
    }

    private static void addLeft(Integer elem) {
        left.add(elem);
        cntLeft++;
        System.out.println(elem + " add left, cnt=" + cntLeft + ", size=" + left.size());
    }

    private static void addRight(Integer elem) {
        right.add(elem);
        cntRight++;
        System.out.println(elem + " add right, cnt=" + cntRight + ", size=" + right.size());
    }

    private static void rebalance() {
        if (left.isEmpty() && right.isEmpty()) {
            throw new IllegalArgumentException("Nothing to rebalance");
        }
        if (cntLeft > cntRight) {
            final int elem = peek(left, cntLeft);
            System.out.println("Move " + elem + " left to right");
            cntLeft--;cntRight++;
            left.remove();
            right.add(elem);
        } else if (cntRight > cntLeft + 1) {
            final int elem = peek(right, cntRight);
            System.out.println("Move " + elem + " right to left");
            cntRight--;cntLeft++;
            right.remove();
            left.add(elem);
        }
        //System.out.println("After rebalance: " + cntLeft + " - " + cntRight + ", size= " + left.size() + " - " + right.size());
    }

    private static int peek(PriorityQueue<Integer> source, int cnt) {
        while(source.size() > cnt) {
            int elem = source.peek();
            if (deleted.containsKey(elem) && deleted.get(elem) > 0 ) {
                deleted.put(elem, deleted.get(elem) - 1);
                source.remove();
            } else {
                return elem;
            }
        }

        return source.peek();
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        //System.out.println(activityNotifications(List.of(10,20,30,40,50), 3));
        //System.out.println(activityNotifications(List.of(2,3,4,2,3,6,8,4,5), 5));
                System.out.println(activityNotifications(List.of(1,2,3,4,1,2,3,4,1,2,3,4), 4));

        if(1==1) return;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Work\\Java\\HackerRank\\files\\input01.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        System.out.println(SlidingWindowMedian.activityNotifications(expenditure, d));

        bufferedReader.close();

    }
}
