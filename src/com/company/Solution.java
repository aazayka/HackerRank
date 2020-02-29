package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution
{
static class Node{
    Node less;
    Node more;
    int val;

    public Node(int val) {
        this.val = val;
    }
}
static class Tree{
    Node median;
    int cnt;
    double getMedian(){
        if(cnt%2 == 1) return (double) median.val;
        else return ((double) median.val + (double) median.more.val) / 2;
    }

    void add(Node n){
        cnt++;
        if(cnt == 1){
            median = n;
            return;
        }
        Node last = median;
        int firstMove = Integer.signum(n.val - median.val);
        if (firstMove < 0){
            while(last.less != null && last.less.val > n.val){
                last = last.less;
            }
            n.more = last;
            n.less = last.less;
            if (last.less != null) last.less.more = n;
            last.less = n;
            if(cnt%2 == 0) median = median.less;
        } else {
            while(last.more != null && last.more.val < n.val){
                last = last.more;
            }
            n.less = last;
            n.more = last.more;
            if (last.more != null) last.more.less = n;
            last.more = n;
            if(cnt%2 == 1) median = median.more;

        }
    }
}



    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(new File("resources/in.txt"));

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/out.txt"));

        int aCount = Integer.parseInt(scanner.nextLine().trim());

        int[] a = new int[aCount];

        Tree t = new Tree();
        for (int aItr = 0; aItr < aCount; aItr++) {
            int aItem = Integer.parseInt(scanner.nextLine().trim());
            t.add(new Node(aItem));

            bufferedWriter.write(String.valueOf(t.getMedian()));

            if (aItr !=aCount - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
