package org.example.solutions;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.*;

public class Day03 {
    static String [] grid;
    static HashMap<String, List<String>> occ = new HashMap<String,List<String>>(); //for part 2
    static int N = 140, M =140,ans=0;
    static int [] row = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int [] col = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static boolean checkValidity(int i,int j){
        for(int k=0;k<8;k++){
            int dx = i + row[k];
            int dy = j + col[k];
            if(Character.isDigit(grid[dx].charAt(dy)))
                continue;
            else if((grid[dx].charAt(dy)) == '.')
                continue;
            else
                return true;
        }
        return false;
    }
    public static String checkValidityForPart2(int i,int j){
        for(int k=0;k<8;k++){
            int dx = i + row[k];
            int dy = j + col[k];
            if((grid[dx].charAt(dy)) == '*') {
                String ax = String.valueOf(dx) + "_" + String.valueOf(dy);
                //List<String> lis = new ArrayList<String>();

                //occ.put(ax,lis);
                return ax;
            }

        }
        return "";
    }
    public static long solve(){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){

                if(grid[i].charAt(j) == '.')
                    continue;
                else if(Character.isDigit(grid[i].charAt(j))){
                    StringBuilder sb = new StringBuilder();
                    boolean flg = false;
                    String tmp = "";
                    //sb.append(grid[i].charAt(j));
                    while(j<=M && (Character.isDigit(grid[i].charAt(j)))){
                        if(flg == false && (checkValidityForPart2(i,j) != "")){
                            tmp = checkValidityForPart2(i,j);
                            flg = true;
                        }
                        sb.append(grid[i].charAt(j));
                        j++;
                    }
                    if(flg == true)
                    {

                        if(occ.get(tmp) != null){
                            occ.get(tmp).add(sb.toString());
                        }
                        else {

                                ArrayList<String> arrL = new ArrayList<String>();
                                arrL.add(sb.toString());
                                occ.put(tmp, arrL);

                        }
                        System.out.println("taken: " + sb.toString());
                        ans = ans + Integer.parseInt(sb.toString());
                        sb = new StringBuilder();
                        flg = false;
                    }
                }

            }
        }
        long gearRatio =0;
        Iterator itr1 = occ.entrySet().iterator();
        while (itr1.hasNext()) {

            Map.Entry mapElement
                    = (Map.Entry)itr1.next();
           // int marks = ((int)mapElement.getValue() + 10);
            ArrayList<String> arrlist = (ArrayList<String >)mapElement.getValue();
            if(arrlist.size() == 2){
                long product = 1;
                for(String f: arrlist){
                    long gear = Long.parseLong(f);
                    product = product * gear;
                }
                gearRatio = gearRatio + product;
            }

        }
        return gearRatio;
    }
    public static void main(String [] args) throws java.lang.Exception{
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_03.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        int cnt = 1;
        grid = new String[N+2];
        String pattern = ".".repeat(M+2);
        grid[0] = new String(pattern);
        while((line = br.readLine()) != null){
            line = "." + line + ".";
            grid[cnt] =  new String(line);
            cnt++;
        }
        //System.out.println(cnt);
        String pattern1 = ".".repeat(M+2);
        grid[N+1] =  new String(pattern1);

        System.out.println(solve());
        //System.out.println(grid[N+1] + " " + grid[N-1] + grid[N]);

    }
}

