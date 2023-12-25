package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

//do in cpp
public class Day21 {
    public static boolean isValid(int i,int j){
        if(i<0 || j<0 || i>=N || j>=M)
            return false;
        return true;
    }
    public static String getCell(int i,int j){
        return new String(String.valueOf(i)+","+String.valueOf(j));
    }

    static int N,M;
    static ArrayList<String> grid;
    public static void main(String [] args) throws java.lang.Exception {
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_21.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        grid = new ArrayList<>();
        String line = "";
        while ((line = br.readLine()) != null) {
            grid.add(line);
        }
        long ans=1;
        N = grid.size();
        M = grid.get(0).length();
        boolean [][] visited = new boolean[grid.size()][grid.get(0).length()];
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        for(int i=0;i<grid.size();i++){
            for(int j=0;j<grid.get(0).length();j++){
                if(grid.get(i).charAt(j) == 'S')
                {
                    visited[i][j] =true;
                    queue.add(new ArrayList<>(Arrays.asList(i,j)));
                    break;
                }
            }
        }
        int moves = 4,cnt1=0;
        ans=1;
        while(!queue.isEmpty() && moves>0){
            int cnt= queue.size();
            System.out.println(queue);
            //cnt1 = cnt;
            moves--;
            for(int i = 0;i<cnt;i++){
                ArrayList<Integer> tmp = queue.remove();
                int ax = tmp.get(0);
                int ay = tmp.get(1);
                visited[ax][ay] = true;
                    if(isValid(ax+1,ay) && (visited[ax+1][ay])  && (grid.get(ax+1).charAt(ay) == '.')){
                    queue.add(new ArrayList<>(Arrays.asList(ax+1,ay)));
                    ans++;
                }
                    if(isValid(ax-1,ay) && (visited[ax-1][ay]) && (grid.get(ax-1).charAt(ay) == '.')){
                    queue.add(new ArrayList<>(Arrays.asList(ax-1,ay)));
                    ans++;
                }
                    if(isValid(ax,ay+1) && (visited[ax][ay+1]) && (grid.get(ax).charAt(ay+1) == '.')){
                    queue.add(new ArrayList<>(Arrays.asList(ax,ay+1)));
                    ans++;
                }
                    if(isValid(ax,ay-1) && (visited[ax][ay-1]) && (grid.get(ax).charAt(ay-1) == '.')){
                    queue.add(new ArrayList<>(Arrays.asList(ax,ay-1)));
                    ans++;
                }
                visited[ax][ay] = false;
            }
        }
        HashMap<String,Integer>occ=new HashMap<>();
System.out.println(queue);
        for(var ty : queue){
            int i = ty.get(0),j=ty.get(1);
            if(isValid(i+1,j) && grid.get(i+1).charAt(j) == '.') {
                occ.put(new String(String.valueOf(i + 1) + "," + String.valueOf(j)), 1);
            }
            if(isValid(i-1,j) && grid.get(i-1).charAt(j) == '.') {
                occ.put(new String(String.valueOf(i - 1) + "," + String.valueOf(j)), 1);
            }
            if(isValid(i,j-1) && grid.get(i).charAt(j-1) == '.') {
                occ.put(new String(String.valueOf(i) + "," + String.valueOf(j - 1)), 1);
            }
            if(isValid(i,j+1) && grid.get(i).charAt(j+1) == '.') {
                occ.put(new String(String.valueOf(i) + "," + String.valueOf(j + 1)), 1);
            }
        }

        System.out.println(occ);
    }
}
