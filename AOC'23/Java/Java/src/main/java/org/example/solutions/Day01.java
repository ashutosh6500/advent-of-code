package org.example.solutions;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.*;

public class Day01 {
    static String [] numbers = {"one","two","three","four","five","six","seven","eight","nine"};
    public static long solution(String s)
    {
        TreeMap<Integer,String>treemap = new TreeMap<Integer,String>();
        HashMap<String,String>mp = new HashMap<String,String>();
        for(int i=1;i<=9;i++)
        {
            mp.put(numbers[i-1],Integer.toString(i));
        }

        int sum = 0;
        for(int i=0;i<s.length();i++)
        {
            char ch = s.charAt(i);

            if(Character.isDigit(ch))
            {
                treemap.put(i,String.valueOf(ch));
                break;
            }

        }
        for(int i=s.length()-1;i>=0;i--)
        {
            char ch = s.charAt(i);

            if(Character.isDigit(ch))
            {
                treemap.put(i,String.valueOf(ch));
                break;
            }

        }
        //handling string cases
        for(String num : numbers)
        {
            if(s.indexOf(num) != -1)
            {
                treemap.put(s.indexOf(num),num);
            }
            if(s.lastIndexOf(num) != -1)
            {
                treemap.put(s.lastIndexOf(num),num);
            }
        }
        //System.out.println(treemap.get(treemap.firstKey()));

        String first = treemap.get(treemap.firstKey());
        String last = treemap.get(treemap.lastKey());
        String final_number= "";
        StringBuilder sb = new StringBuilder(final_number);
        if(first.length() == 1)
        {
            final_number = final_number + first;

        }
        else
        {
            final_number = final_number + mp.get(first);
        }
        if(last.length() == 1)
        {
            final_number = final_number + last;
        }
        else
        {
            final_number = final_number + mp.get(last);
        }

        //System.out.println(final_number);
        return Long.parseLong(final_number);
    }
    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        File file=new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_02.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(file));
        String st;
        long ans = 0;
        while ((st = br.readLine()) != null){
            ans = ans + solution(st);

           //System.out.println(ans);
        }
        System.out.println(ans);
    }
}
