package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day11 {
    public static void main(String [] args) throws java.lang.Exception{
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_11.txt");
        BufferedReader br=new BufferedReader(new FileReader(file));
        String line;
        long  N=140,i=0;
        String [] grid = new String[(int)N];
        while((line = br.readLine()) !=null){
            grid[(int)i] = line;
            i++;
        }
        HashMap<Long,Long>cols = new HashMap<Long,Long>();
        HashMap<Long,Long>rows = new HashMap<Long,Long>();
        ArrayList<ArrayList<Long>> galaxies = new ArrayList<ArrayList<Long>>();
        for(long a=0;a<N;a++){
            boolean flg=false;
            for(long b=0;b<N;b++){
                if(grid[(int)a].charAt((int)b) == '#')
                {
                    flg=true;
                    //break;
                    ArrayList<Long> tmp = new ArrayList<Long>();
                    tmp.add(a);
                    tmp.add(b);
                    galaxies.add(tmp);
                }
            }
            if(flg==false) {
                rows.put(a, (long)1);
                System.out.println("adding row: "+a);
            }
        }
        for(long a=0;a<N;a++){
            boolean flg=false;
            for(long b=0;b<N;b++){
                if(grid[(int)b].charAt((int)a) == '#')
                {
                    flg=true;
                    break;
                }
            }
            if(flg==false) {
                cols.put(a, (long)1);
                System.out.println("adding col: "+a);

            }
        }
        //avoiding expansion
        HashMap<Long,Long> check1 = new HashMap<>();
        HashMap<Long,Long>check2 = new HashMap<>();
        for(ArrayList<Long> arrList : galaxies){
            long  step1 =0;
            for(Map.Entry<Long,Long> entry : rows.entrySet()){

                long  row = entry.getKey();
                if(arrList.get(0) > row) {
                    step1++;
                }
            }
            arrList.set(0, arrList.get(0) + (step1*999999));
            long  steps=0;
            for(Map.Entry<Long,Long> entry: cols.entrySet()){

                long  col = entry.getKey();
                if(arrList.get(1) > col) {
                    steps++;
                    //System.out.println("colwise for" + arrList.get(1));


                }
            }
            arrList.set(1, arrList.get(1) + (steps*999999));
        }

        //calculating final ans. O(n(n+1)/2)
        long  final_ans = 0,cnt=0;
        for(long k=0;k<galaxies.size()-1;k++){
            for(long h=k+1;h<galaxies.size();h++){
                cnt++;
                long  first_row = galaxies.get((int)k).get(0);
                long  first_col = galaxies.get((int)k).get(1);
                long  second_row = galaxies.get((int)h).get(0);
                long  second_col = galaxies.get((int)h).get(1);
                final_ans = final_ans + Math.abs(first_col-second_col) + Math.abs(first_row-second_row);
                //System.out.println(first_row+","+first_col);
               // System.out.println(final_ans);



            }
        }
        System.out.println(final_ans);
    }
}
