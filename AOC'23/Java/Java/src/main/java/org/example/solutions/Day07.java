package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day07 {
    static HashMap<Character,Integer> mappings = new HashMap<>(){
        {
            put('A',1);
            put('K',2);
            put('Q',3);
            //put('J',4); //change for part 2
            put('T',4);
            put('9',5);
            put('8',6);
            put('7',7);
            put('6',8);
            put('5',9);
            put('4',10);
            put('3',11);
            put('2',12);
            put('J',13);
        }
    };
    static int getCardKind(String s){
        char[] charArr = s.toCharArray();
        Arrays.sort(charArr);
        s = new String(charArr);
        int cnt=1;
        s = s + ".";
        int jCount = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length()-1;i++){
            if(s.charAt(i) == s.charAt(i+1))
                cnt++;
            else {
                if(s.charAt(i) == 'J'){
                    jCount = cnt;
                }
                sb.append(String.valueOf(cnt));
                cnt=1;
            }
        }

        String finalStr = sb.toString();
        char [] ch = finalStr.toCharArray();
        Arrays.sort(ch);
        finalStr = new String(ch);
        //System.out.println(finalStr);

        if(finalStr.equals("5"))
            return 5;
        if(finalStr.equals("14")) {
            if(jCount == 1 || jCount == 4)
                return 5;
            return 4;
        }
        if(finalStr.equals("23")) {
            if(jCount == 2 || jCount == 3)
                return 5;
            return 3;
        }
        if(finalStr.equals("113")) {
            if(jCount == 1)
                return 4;
            if(jCount == 3)
                return 4;
            return 2;
        }
        if(finalStr.equals("122")) {
            if(jCount == 1)
                return 3;
            if(jCount == 2)
                return 4;
            return 1;
        }
        if(finalStr.equals("1112")) {
            if(jCount == 1)
                return 2;
            if(jCount == 2)
                return 2;
            return 0;
        }
        if(finalStr.equals("11111")) {
            if(jCount == 1)
                return 0;
            return -1;
        }
        return -1;
    }
    static class CustomSortingUsingComparator implements Comparator<ArrayList<String>>{

        public int compare(ArrayList<String>a,ArrayList<String>b){
            String one = a.get(0);
            String two = b.get(0);
            if(getCardKind(one) == getCardKind(two)){
                //do next level sorting
                //System.out.println("next level sort "+one+" "+two);

                for(int i=0;i<5;i++){
                    if(mappings.get(one.charAt(i)) < mappings.get(two.charAt(i))){
                        return 1;
                    }
                    else if(mappings.get(one.charAt(i)) > mappings.get(two.charAt(i)))
                        return -1;
                    else
                       continue;
                }
                return 0;
            }
            else {
                if(getCardKind(one) < getCardKind(two)){
                    return -1;
                }
                else {
                    return 1;
                }
            }
        }

    }
    public static void main(String [] args) throws java.lang.Exception{
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_07.txt");
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<ArrayList<String>> arrList = new ArrayList<>();
        while((line = br.readLine()) != null){
            String [] input = line.trim().split(" ");
            ArrayList<String> tmp = new ArrayList<>();
            //System.out.println(input);
            tmp.add(input[0].trim());
            tmp.add(input[1].trim());
            arrList.add(tmp);
        }
        System.out.println("before sorting");
        for(var d:arrList){
            System.out.println(d.toString());
        }
        Collections.sort(arrList,new CustomSortingUsingComparator());
        System.out.println("after sorting");
        long ans=0,cn=1;
        for(var d:arrList){
            ans = ans + (cn * (Integer.parseInt(d.get(1))));
            cn++;
            //System.out.println(d.toString());
        }
        System.out.println(ans);

    }
}
