package com.main;

import java.util.ArrayList;
import java.util.Scanner;

class Task implements Runnable
{
    @Override
    public void run()
    {
        task1();
    }
    public int task1(){
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        System.out.print("Enter integers please ");
        System.out.println("Put EOF or non-integer at the end to execute: '1 2 3 4 5 eof' ");
        int[] flag = new int[1000000];
        for (int i = 0; i < flag.length; i++) flag[i] = 0;
        while (scan.hasNextInt()) list.add(scan.nextInt());
        Integer[] nums = list.toArray(new Integer[0]);
        for (int i = 0; i < nums.length; i++) {
            flag[nums[i]] += 1;
            //System.out.println(nums[i]);
        }
        for (int i = 0; i < nums.length; i++)
            if(flag[nums[i]] == 1)
                System.out.println(nums[i]);

        return 1;
    }
}
