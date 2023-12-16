package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Day09 {
    public static void main(String [] args) throws java.lang.Exception{
        // your code goes here
        File file=new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_09.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(file));
        String line = "";
        long ans = 0,partTwoAns=0;

        while((line = br.readLine()) != null){
            ArrayList<Long> arrlis = new ArrayList<Long>();
            String [] nums = line.trim().split(" ");
            long [] numbers = new long[nums.length];
            int i=0;
            for(String s:nums){
                numbers[i] = Long.parseLong(s);
                i++;
            }
            int j=0,k;
            boolean flg =true;
            long range=numbers.length-1;
            ans = ans + numbers[numbers.length-1];
            arrlis.add(numbers[0]);
            while(flg) {
                flg = false;
                for (k = 0; k < range; k++) {
                    numbers[k] = numbers[k + 1] - numbers[k];
                    if(numbers[k] != 0)
                        flg = true;

                }
                arrlis.add(numbers[0]);
                ans+=numbers[k-1];

                range--;
            }
            long tmp = arrlis.get(arrlis.size()-1);
            for(long h = arrlis.size()-2;h>=0;h--){
                tmp = (arrlis.get((int)h)) - tmp;
            }
            partTwoAns+=tmp;
        }

        System.out.println(partTwoAns);
    }
}
