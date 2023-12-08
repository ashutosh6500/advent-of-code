package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day02 {
    static int maxRed=0,maxGreen=0,maxBlue=0;
    public static boolean solvePart1(String s){
        String [] sepColon = s.split(":");
        String[] arrOfStr1 = sepColon[1].split("; ");
        for(String s1 : arrOfStr1){
            s1 = s1.trim();
            String [] arrOfStr2 = s1.split(", ");
            for(String s2 : arrOfStr2){
                s2 = s2.trim();
                String [] final_string = s2.split(" ");
                if(final_string[1].equals("red") && (Integer.parseInt(final_string[0]) > 12))
                    return false;
                if(final_string[1].equals("blue") && (Integer.parseInt(final_string[0]) > 14))
                    return false;
                if(final_string[1].equals("green") && (Integer.parseInt(final_string[0]) > 13))
                    return false;
            }
            //System.out.println();
        }
        return true;
    }
    public static long solvePart2(String s){
        String [] sepColon = s.split(":");

        String[] arrOfStr1 = sepColon[1].split("; ");
        for(String s1 : arrOfStr1){
            s1 = s1.trim();
            String [] arrOfStr2 = s1.split(", ");
            for(String s2 : arrOfStr2){
                s2 = s2.trim();
                String [] final_string = s2.split(" ");
                System.out.println(final_string[0] + " " + final_string[1]);
                if(final_string[1].equals("red")){
                    if((Integer.parseInt(final_string[0]) > maxRed))
                        maxRed = Integer.parseInt(final_string[0]);
                }
                else if(final_string[1].equals("green")){
                    if((Integer.parseInt(final_string[0]) > maxGreen))
                        maxGreen = Integer.parseInt(final_string[0]);
                }
                else if(final_string[1].equals("blue")){
                    if((Integer.parseInt(final_string[0]) > maxBlue))
                        maxBlue = Integer.parseInt(final_string[0]);
                }
            }
            //System.out.println("-----------");
        }
        long product = maxBlue * maxGreen;
        product = product * maxRed;
        maxRed = maxBlue = maxGreen = 0;
        return product;
    }
    public static void main(String [] args) throws java.lang.Exception{
        File file=new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_02.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(file));
        String content = "";
        long ans=0,itr=1;
        while ((content = br.readLine()) != null){
            //System.out.println(content);
            //if(solvePart2(content))
                ans = ans + solvePart2(content);
            itr++;
        }
        System.out.println(ans);
    }
}
