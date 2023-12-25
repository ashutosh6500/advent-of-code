package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Day15 {
    public static void main(String [] args) throws java.lang.Exception {
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_15.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";

        line = br.readLine();
        String[] arrOfStr = line.trim().split(",");
        //System.out.println(Arrays.deepToString(arrOfStr));
        long ans=0;
        for(String s:arrOfStr){
            long currValue = 0;
            for(int i=0;i<s.length();i++){
                char ch = s.charAt(i);
                currValue = currValue + ((int)ch);
                currValue*=17;
                currValue = currValue % 256;
            }
            ans = ans + currValue;
        }
        System.out.println(ans);
    }
}
