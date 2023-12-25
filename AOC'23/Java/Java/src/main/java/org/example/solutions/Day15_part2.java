package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day15_part2  {
    public static void main(String [] args) throws java.lang.Exception {
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_15.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        line = br.readLine();
        String[] arrOfStr = line.trim().split(",");
        long ans=0;
        HashMap<String,Long> labelToBoxNo = new HashMap<>();
        HashMap<Long,ArrayList<ArrayList<String>>> boxToPairs = new HashMap<>();
        for(String s:arrOfStr){
           if(s.charAt(s.length()-1) == '-'){
               //remove operation
               if(labelToBoxNo.get(s.substring(0,s.length()-1)) != null){
                   ArrayList<ArrayList<String>> new1 = new ArrayList<ArrayList<String>>();
                   for(ArrayList<String> ele : boxToPairs.get(labelToBoxNo.get(s.substring(0,s.length()-1)))){
                       if(ele.size() == 0)
                           continue;
                       if(ele.get(0).equals(s.substring(0,s.length()-1))){
                           System.out.print("not added"+ele.get(0));
                           //labelToBoxNo.remove(s.substring(0,s.length()-1));
                       }
                       else{
                           new1.add(ele);
                       }
                   }
                   boxToPairs.remove(labelToBoxNo.get(s.substring(0,s.length()-1)));
                   boxToPairs.put(labelToBoxNo.get(s.substring(0,s.length()-1)),new1);
                   labelToBoxNo.remove(s.substring(0,s.length()-1));
               }
           }
           else{
               //ADD operation
                String [] things = s.split("=");
                long hashcode = 0;
                for(int i=0;i<things[0].length();i++){
                    char ch = things[0].charAt(i);
                    hashcode = hashcode + ((int)ch);
                    hashcode*=17;
                    hashcode = hashcode % 256;
                }
                if(labelToBoxNo.get(things[0]) != null){
                    //update in box
                    for(Map.Entry<Long,ArrayList<ArrayList<String>>> entry : boxToPairs.entrySet()){
                        for(ArrayList<String> arrList : entry.getValue()){
                            if(arrList.size() == 0)
                                continue;
                            if(arrList.get(0).equals(things[0])) {
                                arrList.set(1, things[1]);
                                break;
                            }
                        }
                    }
                }
                else if(boxToPairs.get(hashcode) != null){
                    ArrayList<String> tmp = new ArrayList<>();
                    tmp.add(things[0]);
                    tmp.add(things[1]);
                    boxToPairs.get(hashcode).add(tmp);
                    labelToBoxNo.put(things[0],hashcode);
                }
                else{
                    ArrayList<String> arrList = new ArrayList<>();
                    arrList.add(things[0]);
                    arrList.add(things[1]);
                    ArrayList<ArrayList<String>> tmp = new ArrayList<>();
                    tmp.add(arrList);
                    boxToPairs.put(hashcode,tmp);
                    labelToBoxNo.put(things[0],hashcode);
                }
           }
        }
        ans=0;
        for(Map.Entry<Long,ArrayList<ArrayList<String>>> entry : boxToPairs.entrySet()){

            long boxId = entry.getKey();
            //System.out.print("box "+boxId + " contents: ");
            int index=1;
            for(var tmp : entry.getValue()){
                System.out.println(tmp.get(0));

                if(tmp.size() == 0)
                    continue;
                long product = boxId + 1;
                product*=index;
                product*=(Long.parseLong(tmp.get(1)));
                ans = ans + product;
                index++;
            }
        }
        System.out.println(ans);
    }
}
