package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day05_part2 {

    static long []seedsArr ;
    static ArrayList<ArrayList<Long>> one = new ArrayList<ArrayList<Long>>();
    static ArrayList<ArrayList<Long>> two = new ArrayList<ArrayList<Long>>();
    static ArrayList<ArrayList<Long>> three = new ArrayList<ArrayList<Long>>();
    static ArrayList<ArrayList<Long>> four = new ArrayList<ArrayList<Long>>();
    static ArrayList<ArrayList<Long>> five = new ArrayList<ArrayList<Long>>();
    static ArrayList<ArrayList<Long>> six = new ArrayList<ArrayList<Long>>();
    static ArrayList<ArrayList<Long>> seven = new ArrayList<ArrayList<Long>>();

    static long answer2;
    public static void main(String [] args) throws java.lang.Exception{
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_05.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        long cnt = 1;
        HashMap<Long,Long> seeds = new HashMap<Long,Long>();
        while((line = br.readLine()) != null){
            if(cnt == 1){
                String []seedsArray = line.split(":")[1].trim().split(" ");
                for(long itr = 0;itr<(seedsArray.length-1);itr+=2) {

                    long first1 =Long.parseLong(seedsArray[(int) itr]);
                    long range = Long.parseLong(seedsArray[(int) (itr + 1)]);
                    //range--;
                    seeds.put(first1,range);
                }
            }
            else if(line.length() == 0)
                continue;
            else if(line.trim().equals("seed-to-soil map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");
                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    ArrayList<Long> arrList = new ArrayList<Long>();
                    arrList.add(a1);
                    arrList.add(a2);
                    arrList.add(a3);
                    one.add(arrList);
                }
            }
            else if(line.trim().equals("soil-to-fertilizer map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");
                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    ArrayList<Long> arrList = new ArrayList<Long>();
                    arrList.add(a1);
                    arrList.add(a2);
                    arrList.add(a3);
                    two.add(arrList);
                }
            }
            else if(line.trim().equals("fertilizer-to-water map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");
                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    ArrayList<Long> arrList = new ArrayList<Long>();
                    arrList.add(a1);
                    arrList.add(a2);
                    arrList.add(a3);
                    three.add(arrList);
                }
            }
            else if(line.trim().equals("water-to-light map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");
                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    ArrayList<Long> arrList = new ArrayList<Long>();
                    arrList.add(a1);
                    arrList.add(a2);
                    arrList.add(a3);
                    four.add(arrList);
                }
            }
            else if(line.trim().equals("light-to-temperature map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");
                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    ArrayList<Long> arrList = new ArrayList<Long>();
                    arrList.add(a1);
                    arrList.add(a2);
                    arrList.add(a3);
                    five.add(arrList);
                }
            }
            else if(line.trim().equals("temperature-to-humidity map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");
                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    ArrayList<Long> arrList = new ArrayList<Long>();
                    arrList.add(a1);
                    arrList.add(a2);
                    arrList.add(a3);
                    six.add(arrList);
                }
            }
            else if(line.trim().equals("humidity-to-location map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");
                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    ArrayList<Long> arrList = new ArrayList<Long>();
                    arrList.add(a1);
                    arrList.add(a2);
                    arrList.add(a3);
                    seven.add(arrList);
                }
            }
            cnt++;
        }
        long ans = Long.MAX_VALUE;

        for(Map.Entry<Long,Long> entry : seeds.entrySet()) {
            Long key = entry.getKey();
            Long value = entry.getValue();
            while(value-- >0){
                //finding soil for seed {key}
                long soil = -1;
                for(ArrayList<Long> arrList : one){
                    long a1 = arrList.get(0);
                    long a2 = arrList.get(1);
                    long a3 = arrList.get(2);
                    if(key >=a2 && key<(a2+a3)) {
                        soil = a1 + key - a2;
                        break;
                    }
                }
                if(soil == -1)
                    soil = key;
                //finding fertilizer
                long fertilizer = -1;
                for(ArrayList<Long> arrList : two){
                    long a1 = arrList.get(0);
                    long a2 = arrList.get(1);
                    long a3 = arrList.get(2);
                    if(soil >=a2 && soil<(a2+a3)) {
                        fertilizer = a1 + soil - a2;
                        break;
                    }
                }
                if(fertilizer == -1)
                    fertilizer = soil;

                //finding water
                long water = -1;
                for(ArrayList<Long> arrList : three){
                    long a1 = arrList.get(0);
                    long a2 = arrList.get(1);
                    long a3 = arrList.get(2);
                    if(fertilizer >=a2 && fertilizer<(a2+a3)) {
                        water = a1 + fertilizer - a2;
                        break;
                    }
                }
                if(water == -1)
                    water = fertilizer;
                //finding light
                long light = -1;
                for(ArrayList<Long> arrList : four){
                    long a1 = arrList.get(0);
                    long a2 = arrList.get(1);
                    long a3 = arrList.get(2);
                    if(water  >=a2 && water<(a2+a3)) {
                        light = a1 + water - a2;
                        break;
                    }
                }
                if(light == -1)
                    light = water;

                //finding temperature
                long temp = -1;
                for(ArrayList<Long> arrList : five){
                    long a1 = arrList.get(0);
                    long a2 = arrList.get(1);
                    long a3 = arrList.get(2);
                    if(light  >=a2 && light<(a2+a3)) {
                        temp = a1 + light - a2;
                        break;
                    }
                }
                if(temp == -1)
                    temp = light;
                //finding humidity
                long humidity = -1;
                for(ArrayList<Long> arrList : six){
                    long a1 = arrList.get(0);
                    long a2 = arrList.get(1);
                    long a3 = arrList.get(2);
                    if(temp  >=a2 && temp<(a2+a3)) {
                        humidity = a1 + temp - a2;
                        break;
                    }
                }
                if(humidity == -1)
                    humidity = temp;
                //finding location finally
                long location = -1;
                for(ArrayList<Long> arrList : seven){
                    long a1 = arrList.get(0);
                    long a2 = arrList.get(1);
                    long a3 = arrList.get(2);
                    if(humidity  >=a2 && humidity<(a2+a3)) {
                        location = a1 + humidity - a2;
                        break;
                    }
                }
                if(location == -1)
                    location = humidity;
                if(location < ans)
                    ans = location;
                key++;
            }
        }
        System.out.println("ans is "+ans);
    }
}
