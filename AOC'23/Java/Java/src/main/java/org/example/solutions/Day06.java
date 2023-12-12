package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Day06 {
    static long time1,distance1;
    public static void main(String [] args) throws java.lang.Exception{
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_06.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = "";
        String [] timeArray = br.readLine().trim().split(":")[1].split(" ");
       // System.out.println();
        String [] distanceArray = br.readLine().trim().split(":")[1].split(" ");
        String [] timearray1 = new String[4];
        String [] distancearray1=new String[4];

        StringBuilder time = new StringBuilder(); //for part 2
        StringBuilder distance = new StringBuilder(); // for part 2
        int j=0;
        for(String gh:timeArray){
            if(gh == null || gh.length()==0){
                continue;
            }
            timearray1[j] = gh;
            time.append(gh);
            j++;
        }
        j=0;
        for(String gh:distanceArray){
            if(gh == null || gh.length()==0){
                continue;
            }
            distancearray1[j] = gh;
            distance.append(gh);
            j++;
        }
        time1 = Long.parseLong(time.toString());
        distance1 = Long.parseLong(distance.toString());
        //part 2 code
        long ans = 1;
        long cnt= 0;
        long itr = 1,itr2=time1-1;
        for(itr=1;itr<time1;itr++){
            long product = itr2 * itr;
            if(product > distance1)
                cnt++;
            itr2--;
        }
       // ans*=cnt;

        /*
        //part one code
        for(long i =0 ;i<timearray1.length;i++) {
            //System.out.print(timeArray[(int) i] + " ");


            long time = Long.parseLong(timearray1[(int)i]);
            long distance = Long.parseLong(distancearray1[(int)i]);
            long cnt= 0;
            long itr = 1,itr2=time-1;
            for(itr=1;itr<time;itr++){
                long product = itr2 * itr;
                if(product > distance)
                    cnt++;
                itr2--;
            }
            ans*=cnt;
        }
        */

        System.out.println(cnt);
    }

}
