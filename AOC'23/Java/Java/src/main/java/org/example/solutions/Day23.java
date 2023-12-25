package org.example.solutions;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day23 {
    static String[] grid;
    static int[] row = {1, 0, -1, 0};
    static int[] col = {0, 1, 0, -1};
    static long ans;
    static int N;
    static boolean visited[][];
    static void dfs(int row,int col,int steps){
        if(row == 23 && col == 22){
            if(steps > ans)
                ans=steps;
            visited[row][col] = false;
        }
        if(row<0 || row>N+2 || col<0 || col>N+2)
            return;
        int ax=row,ay=col;
        char ch = grid[row].charAt(col);
        switch (ch) {
            case '>':
                if ((grid[ax].charAt(ay + 1) != '#') && (!visited[ax][ay+1]))
                    dfs(ax,ay+1,steps+1);
                break;
            case '<':
                if ((grid[ax].charAt(ay - 1) != '#') && (!visited[ax][ay-1]))
                    dfs(ax,ay-1,steps+1);
                break;
            case '^':
                if ((grid[ax-1].charAt(ay) != '#') && (!visited[ax-1][ay]))
                    dfs(ax-1,ay,steps+1);
                break;
            case '#':
                break;
            case '.':
                if ((grid[ax-1].charAt(ay) != '#') && (!visited[ax-1][ay]))
                    dfs(ax-1,ay,steps+1);
                if ((grid[ax].charAt(ay - 1) != '#') && (!visited[ax][ay-1]))
                    dfs(ax,ay-1,steps+1);
                if ((grid[ax].charAt(ay + 1) != '#') && (!visited[ax][ay+1]))
                    dfs(ax,ay+1,steps+1);
                if ((grid[ax+1].charAt(ay) != '#') && (!visited[ax+1][ay]))
                    dfs(ax+1,ay,steps+1);
                break;
            default:
                if ((grid[ax+1].charAt(ay) != '#') && (!visited[ax+1][ay]))
                    dfs(ax+1,ay,steps+1);
                break;

        }
        visited[row][col] = false;
    }
    public static void main(String[] args) throws java.lang.Exception {
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_23.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        ans = Long.MIN_VALUE;
        N = 23;
        int i=1;
        grid = new String[N + 2];
        grid[0] = "#".repeat(N + 2);
        while ((line = br.readLine()) != null) {
            String s = "#" + line + "#";
            grid[i] = s;
            i++;
        }
        grid[N + 1] = "#".repeat(N + 2);
        visited = new boolean[N+2][N+2];
        dfs(1,2,0);
        for(var h1: visited){
            for(var t1: h1){
                if(t1)
                System.out.print('O');
                else
                    System.out.print('.');


            }
            System.out.println("");

        }
        System.out.println(ans);
    }

}
