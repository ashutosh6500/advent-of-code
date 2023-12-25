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
            ArrayList<String>gridForCols = new ArrayList<String>();
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
            String firstColumnString = "",lastColumnString="";
            for(long x=0;x<grid.get(0).length();x++){
                StringBuilder colWise1 = new StringBuilder();
                for(long y=0;y< grid.size();y++){
                    colWise1.append(grid.get((int)y).charAt((int)x));
                }
                String str = colWise1.toString();
                gridForCols.add(str);
                if(x==0)
                    firstColumnString = str;
                if(x == grid.get(0).length()-1)
                    lastColumnString = str;
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
            //checking horizontal mirror from top
            System.out.println("checking horizontal mirror from top");
            boolean rowWiseFromTop = true,done=false;
            long roWiseFromTopIndex=0;
            if(rowWise.get(grid.get(0)).size() > 1)
            {
                for(long ele : rowWise.get(grid.get(0))){
                    rowWiseFromTop =true;
                    long h1 = 1,h2=ele;
                    while(h1<h2){
                        if(grid.get((int)h1-1).equals(grid.get((int)h2-1)))
                        {
                            //
                            if(h2-h1 == 1){
                                done = true;
                                roWiseFromTopIndex = h1;
                                break;
                            }
                        }
                        else {
                            rowWiseFromTop = false;
                            break;
                        }
                        h1++;h2--;
                    }
                    if(done)
                        break;
                }
            }
            if(done)
                ans = ans + (100 * roWiseFromTopIndex);
            //checking horizontal mirror from bottom

            if(done == false)
            {
                System.out.println("checking horizontal mirror from bottom");

                    long rowWiseBottomIndex = 0;
                    boolean done1 = false;
                    for(long ele : rowWise.get(grid.get(grid.size()-1))){

                        long h1 = grid.size(),h2=ele;
                        while(h2<h1){
                            if(grid.get((int)h1-1).equals(grid.get((int)h2-1)))
                            {
                                //
                                if(h1-h2 == 1){
                                    done1 = true;
                                    rowWiseBottomIndex = h2;
                                    break;
                                }
                            }
                            else {

                                break;
                            }
                            h1--;h2++;
                        }
                        if(done1)
                            break;
                    }
                    if(done1)
                        ans = ans + (100*rowWiseBottomIndex);
                    System.out.println(done1);

                    if(done1 == false)
                    {
                        //cheking columwise from top
                        System.out.println("checking columwise from top");

                        boolean done2 = false;
                        long columnWiseFromLeftIndex = 0;
                        if(colWise.get(firstColumnString).size() > 1){
                            for(long ele : colWise.get(firstColumnString)){
                                long h1 = 1,h2=ele;
                                while(h1 < h2){
                                    if(gridForCols.get((int)h1-1).equals(gridForCols.get((int)h2-1)))
                                    {
                                        if(h2-h1 == 1){
                                            done2 = true;
                                            columnWiseFromLeftIndex = h1;
                                            break;
                                        }
                                    }
                                    else
                                        break;
                                    h1++;h2--;
                                }
                                if(done2)
                                    break;
                            }
                        }
                        if(done2)
                        {
                            ans = ans + columnWiseFromLeftIndex;
                        }
                        if(done2 == false)
                        {
                            //cheking columwise from bottom
                            System.out.println("checkingcolumwise from bottom");

                            boolean done3 = false;
                            long columnWiseFromRightIndex = 0;
                            if(colWise.get(lastColumnString).size() > 1){
                                for(long ele : colWise.get(lastColumnString)){
                                    long h1 = ele,h2=gridForCols.size();
                                    while(h1 < h2){
                                        if(gridForCols.get((int)h1-1).equals(gridForCols.get((int)h2-1)))
                                        {
                                            if(h2-h1 == 1){
                                                done3 = true;
                                                columnWiseFromRightIndex = h1;
                                                break;
                                            }
                                        }
                                        else
                                            break;
                                        h1++;h2--;
                                    }
                                    if(done3)
                                        break;
                                }
                                if(done3) {
                                    ans = ans + columnWiseFromRightIndex;
                                }

                            }
                        }
                    }

            }


            if(line == null)
                break;
        }
        System.out.println(ans);
    }
}
