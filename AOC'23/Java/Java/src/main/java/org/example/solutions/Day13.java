package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day13 {
    public static void main(String [] args) throws java.lang.Exception{
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_13.txt");
        BufferedReader br =new BufferedReader(new FileReader(file));
        String line = "";
        long ans = 0;
        while(true){
            ArrayList<String>grid = new ArrayList<String>();
            HashMap<String,ArrayList<Long>>rowWise = new HashMap<String,ArrayList<Long>>();
            HashMap<String,ArrayList<Long>>colWise = new HashMap<String,ArrayList<Long>>();
            long i=1;
            while(true){
                line = br.readLine();
                if(line == null || line.length() == 0)
                    break;
                grid.add(line);
                if(rowWise.get(line) != null){
                    ArrayList<Long> arrList = rowWise.get(line);
                    arrList.add(i);
                    rowWise.put(line,arrList);
                }
                else {
                    ArrayList<Long> arrList =new ArrayList<Long>();
                    arrList.add(i);
                    rowWise.put(line,arrList);
                }
                i++;
            }
            for(long x=0;x<grid.get(0).length();x++){
                StringBuilder colWise1 = new StringBuilder();
                for(long y=0;y< grid.size();y++){
                    colWise1.append(grid.get((int)y).charAt((int)x));
                }
                String str = colWise1.toString();
                if(colWise.get(str) != null){
                    ArrayList<Long> arrList = colWise.get(str);
                    arrList.add(x+1);
                    colWise.put(str,arrList);
                }
                else {
                    ArrayList<Long> arrList = new ArrayList<Long>();
                    arrList.add(x+1);
                    colWise.put(str,arrList);
                }
            }

            //deciding rowwise or columwise pattern
            System.out.println("rowwise map size " + rowWise.toString());
            System.out.println("colwise map size " + colWise.toString());
            long rowCnt=0,colCnt=0,rows=0,colums=0;
            for(Map.Entry<String,ArrayList<Long>> entry : rowWise.entrySet()){
                String key = entry.getKey();
                ArrayList<Long> value = entry.getValue();
                if(value.size() == 2) {
                    rowCnt++;
                    if (Math.abs(value.get(0) - value.get(1)) == 1)
                        rows = Math.min(value.get(0), value.get(1));
                }
            }
            for(Map.Entry<String,ArrayList<Long>> entry : colWise.entrySet()){
                String key = entry.getKey();
                ArrayList<Long> value = entry.getValue();
                if(value.size() == 2) {
                    colCnt++;
                    if (Math.abs(value.get(0) - value.get(1)) == 1)
                        colums = Math.min(value.get(0), value.get(1));
                }
            }
            if(rowCnt > colCnt){
                //rowise pattern
                ans = ans + (100 * rows);
            }
            else
                ans = ans + colums;
            if(line == null)
                break;
        }
        System.out.println(ans);
    }
}
