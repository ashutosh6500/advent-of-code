package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Day08 {
    public static long gcd(long num1, long num2)
    {
        if (num2 == 0)
            return num1;
        return gcd(num2, num1 % num2);
    }

    public static long lcm_of_array(ArrayList<Long> arr)
    {
        long lcm = arr.get(0);
        for (long i = 1; i < arr.size(); i++) {
            long num1 = lcm;
            long num2 = arr.get((int)i);
            long gcd_val = gcd(num1, num2);
            lcm = (lcm * arr.get((int)i)) / gcd_val;
        }
        return lcm;
    }
    public static void main(String [] args) throws java.lang.Exception {
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_08.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        ArrayList<String> partTwoStrArr = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine()) != null){
            if(line == null || line.length() == 0)
                break;
            sb.append(line);
        }
        HashMap<String, ArrayList<String>> mappings = new HashMap<String, ArrayList<String>>();
        String sequence = sb.toString();
        System.out.println(sequence);
        while((line = br.readLine()) != null) {
            String [] arrOfStr = line.trim().split("=");
            String key = arrOfStr[0].trim();
            //System.out.println("key is "+key);
            String one = arrOfStr[1].trim().split(",")[0].trim().substring(1);
            String two = arrOfStr[1].trim().split(",")[1].trim().substring(0,3);
            //System.out.println(one + " " + two);
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(one);
            tmp.add(two);
            mappings.put(key,tmp);
            if(key.charAt(2) == 'A')
                partTwoStrArr.add(key);
        }
        //System.out.println(mappings);
        long cnt= 0,i=0;
        /*
        //code for part one
        String currStr="AAA";
        while(true){
            System.out.println(currStr);
            if(currStr.equals("ZZZ"))
                break;
            if(i>=sequence.length())
                i=0;
            if(sequence.charAt((int)i) == 'L')
                currStr = mappings.get(currStr).get(0);
            else
                currStr = mappings.get(currStr).get(1);
            i++;
            cnt++;
        }
        System.out.println(cnt);
        */
        //lcm way for part 2
        ArrayList<Long> lcmArray = new ArrayList<>();
        for(String h : partTwoStrArr){
            cnt = 0;
            String currStr=h;
            while(true){
                //System.out.println(currStr);
                if(currStr.charAt(2) == 'Z')
                    break;
                if(i>=sequence.length())
                    i=0;
                if(sequence.charAt((int)i) == 'L')
                    currStr = mappings.get(currStr).get(0);
                else
                    currStr = mappings.get(currStr).get(1);
                i++;
                cnt++;
            }
            lcmArray.add(cnt);
        }
        System.out.println(lcm_of_array(lcmArray));
        /*
         //code for part two (failed due to TLE)
        long check =0;
        boolean flg = true;
        while(flg){
            if(i>=sequence.length())
                i=0;
            check = 0;
            int y = 0;
            for(String s : partTwoStrArr){
                //System.out.print(s + " ");
                if(sequence.charAt((int)i) == 'L'){
                    //s.replaceFirst(s,mappings.get(s).get(0));
                    partTwoStrArr.set(y,mappings.get(s).get(0));
                    y++;
                    if(mappings.get(s).get(0).charAt(2) == 'Z')
                        check++;
                }
                else {
                    //s.replaceFirst(s,mappings.get(s).get(1));
                    partTwoStrArr.set(y,mappings.get(s).get(1));
                    y++;
                    if(mappings.get(s).get(1).charAt(2) == 'Z')
                        check++;
                }
            }
            if(check == partTwoStrArr.size())
                flg=false;
            cnt++;
            i++;
        }
        System.out.println(cnt);
        */

    }
}
