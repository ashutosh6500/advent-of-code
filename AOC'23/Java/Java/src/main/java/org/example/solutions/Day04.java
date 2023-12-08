package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
public class Day04 {
    static TreeMap<Integer,Integer> matches = new TreeMap<Integer,Integer>();
    public static long solve(String s,int cardNo){
        String [] sepColon = s.split(":");
        /*
        for(String sa : sepColon)
            System.out.print(sa + " ");
        System.out.println();
        */

        String [] sepOr = sepColon[1].trim().split("\\|");
        /*
        for(String sa : sepOr)
            System.out.println(sa + " ");
        System.out.println();
        */

        String [] finalList1 = sepOr[0].trim().split(" ");
        /*
        for(String sa : finalList1)
            System.out.print(sa + " ");
        System.out.println();
        */

        String [] finalList2 = sepOr[1].trim().split(" ");
        /*
        for(String sa : finalList2)
            System.out.println(sa);
        System.out.println();
        */

        HashMap<String,Integer>mp = new HashMap<String, Integer>();
        for(String i: finalList1){
            if(i.length() == 0)
                continue;
            if(mp.get(i) == null){
                mp.put(i,1);
                //System.out.println("put " + i + i.length());
            }

        }
        System.out.println("hashmap size: " + mp.size());
        double k = 0;
        for(String h : finalList2){
            if(h.length() == 0)
                continue;
            if(mp.get(h) != null){
                k++;
            }
        }
        //System.out.println(k);
        matches.put(cardNo,(int)k);
        double power = Math.pow(2,k-1);
        return (long)power;
    }

    public static void main(String [] args) throws java.lang.Exception{
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_04.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        int cnt=1;
        long ans =0;
        while((line = br.readLine()) != null){
            ans = ans + solve(line,cnt);
            cnt++;
            //break;
        }
        System.out.println("cnt is "+cnt); // part 1 ans
        //code for part 2
        int [] arr = new int[cnt-1];
        for(int j=0;j<arr.length;j++)
            arr[j] = 1;
        for(Map.Entry<Integer,Integer> entry : matches.entrySet()){
            Integer key= entry.getKey();
            Integer value = entry.getValue();
            int i = key;
            int val = arr[key-1];
            while(value-- >0){
                if(i >= arr.length)
                    break;
                arr[i] = arr[i] + val;
                i++;
            }

        }
        int final_ans = 0;
        for(int h: arr){
            final_ans = final_ans + h;
        }
        System.out.println("ans is " + final_ans);

    }
}
