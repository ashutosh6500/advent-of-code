package org.example.solutions;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day10 {
    static String [] grid;
    public static void main(String [] args) throws java.lang.Exception{
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_10.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int N = 140,i=1;
        grid = new String[N+2];
        grid[0] = ".".repeat(N+2);
        //grid = new String[N+2];
        while((line = br.readLine()) != null){
            String s = "." + line + ".";
            grid[i] = s;
            i++;
        }
        grid[N+1] = ".".repeat(N+2);
        //bfs


        HashMap<String,Boolean> mp = new HashMap<String,Boolean>(); //works like visited array
        Queue<Object[]> q = new LinkedList<Object[]>();
        for(int x=1;x<=N;x++){
            for(int y=1;y<=N;y++){
                if(grid[x].charAt(y) == 'S')
                {
                    String mapping = String.valueOf(x) + "_" + String.valueOf(y);
                    mp.put(mapping,true);
                    Object [] obj = new Object[]{x,y,'-',0}; //assume s is f
                    q.add(obj);
                    //assume S is f
                    grid[x] = grid[x].substring(0,y) + "-" + grid[x].substring(y+1);
                    break;
                }
            }
        }
        for(Object[] o : q){
            System.out.print(o[0].toString()+" "+o[1]+" "+o[2]+" "+o[3]);
        }
        System.out.println();

        int ans = Integer.MIN_VALUE;
        while(! q.isEmpty()){
            int cnt = q.size();
            for(int itr = 0;itr<cnt;itr++){
                Object[] objects=q.remove();
                /*
                System.out.println("after popping ");
                for(Object[] o : q){
                    System.out.print(o[0].toString()+" "+o[1]+" "+o[2]+" "+o[3]);
                }
                System.out.println();
                */

                int ax = (int)objects[0];
                int ay = (int)objects[1];
                char ch = (char)objects[2];
                int tot = (int)objects[3];
                if(tot > ans)
                    ans = tot;
                String tmp1 = String.valueOf(ax)+"_"+String.valueOf(ay);
                mp.put(tmp1,true);//visited
                //checking right directions
                if(ch == '|'){
                    String ax1 = String.valueOf(ax-1),ay1 = String.valueOf(ay);
                    String tmp = ax1 + "_" + ay1;
                    if(grid[ax-1].charAt(ay) != '.')
                    {
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax - 1, ay, grid[ax - 1].charAt(ay), tot + 1};
                            q.add(objArr);
                        }
                    }
                    if(grid[ax+1].charAt(ay) != '.')
                    {
                        ax1 = String.valueOf(ax + 1);
                        ay1 = String.valueOf(ay);
                        tmp = ax1 + "_" + ay1;
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax + 1, ay, grid[ax + 1].charAt(ay), tot + 1};
                            q.add(objArr);
                        }
                    }
                }
                else if(ch == '-'){
                    String ax1 = String.valueOf(ax),ay1 = String.valueOf(ay+1);
                    String tmp = ax1 + "_" + ay1;
                    if(grid[ax].charAt(ay+1) != '.')
                    {
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax, ay+1, grid[ax].charAt(ay+1), tot + 1};
                            q.add(objArr);
                        }
                    }
                    if(grid[ax].charAt(ay-1) != '.')
                    {
                        ax1 = String.valueOf(ax);
                        ay1 = String.valueOf(ay-1);
                        tmp = ax1 + "_" + ay1;
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax, ay-1, grid[ax].charAt(ay-1), tot + 1};
                            q.add(objArr);
                        }
                    }
                }
                else if(ch == 'L'){
                    String ax1 = String.valueOf(ax-1),ay1 = String.valueOf(ay);
                    String tmp = ax1 + "_" + ay1;
                    if(grid[ax-1].charAt(ay) != '.')
                    {
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax - 1, ay, grid[ax - 1].charAt(ay), tot + 1};
                            q.add(objArr);
                        }
                    }
                    if(grid[ax].charAt(ay+1) != '.')
                    {
                        ax1 = String.valueOf(ax);
                        ay1 = String.valueOf(ay+1);
                        tmp = ax1 + "_" + ay1;
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax, ay+1, grid[ax].charAt(ay+1), tot + 1};
                            q.add(objArr);
                        }
                    }
                }
                else if(ch == 'J'){
                    String ax1 = String.valueOf(ax-1),ay1 = String.valueOf(ay);
                    String tmp = ax1 + "_" + ay1;
                    if(grid[ax-1].charAt(ay) != '.')
                    {
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax - 1, ay, grid[ax - 1].charAt(ay), tot + 1};
                            q.add(objArr);
                        }
                    }
                    if(grid[ax].charAt(ay-1) != '.')
                    {
                        ax1 = String.valueOf(ax);
                        ay1 = String.valueOf(ay-1);
                        tmp = ax1 + "_" + ay1;
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax, ay-1, grid[ax].charAt(ay-1), tot + 1};
                            q.add(objArr);
                        }
                    }
                }
                else if(ch == '7'){
                    String ax1 = String.valueOf(ax),ay1 = String.valueOf(ay-1);
                    String tmp = ax1 + "_" + ay1;
                    if(grid[ax].charAt(ay-1) != '.')
                    {
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax, ay-1, grid[ax].charAt(ay-1), tot + 1};
                            q.add(objArr);
                        }
                    }
                    if(grid[ax+1].charAt(ay) != '.')
                    {
                        ax1 = String.valueOf(ax + 1);
                        ay1 = String.valueOf(ay);
                        tmp = ax1 + "_" + ay1;
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax + 1, ay, grid[ax + 1].charAt(ay), tot + 1};
                            q.add(objArr);
                        }
                    }
                }
                else if(ch == 'F'){
                    String ax1 = String.valueOf(ax),ay1 = String.valueOf(ay+1);
                    String tmp = ax1 + "_" + ay1;
                    if(grid[ax].charAt(ay+1) != '.')
                    {
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax, ay+1, grid[ax].charAt(ay+1), tot + 1};
                            q.add(objArr);
                        }
                    }
                    if(grid[ax+1].charAt(ay) != '.')
                    {
                        ax1 = String.valueOf(ax + 1);
                        ay1 = String.valueOf(ay);
                        tmp = ax1 + "_" + ay1;
                        if (mp.get(tmp) != null) {
                            //add code to finalize solution.
                        } else {
                            Object[] objArr = new Object[]{ax + 1, ay, grid[ax + 1].charAt(ay), tot + 1};
                            q.add(objArr);
                        }
                    }
                }
                /*
                System.out.println("after adding ");
                for(Object[] o : q){
                    System.out.println(o[0].toString()+" "+o[1]+" "+o[2]+" "+o[3]);

                }
                System.out.println();
                */

            }

        }
        System.out.println(ans);
    }
}
