package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

//try bfs
public class Day16 {
    static ArrayList<String> grid;
    static HashMap<String,ArrayList<Character>> occ;
    static StringBuilder path,traversePath ;
    static String checkChar;
    static int [] row = {-1,0,1,0};
    static int [] col = {0,1,0,-1};

    static long ans,N,M,limit;
    static boolean [][] visited ;
    static boolean flg = false;
    public static String getCell(int i,int j){
        return new String(String.valueOf(i)+","+String.valueOf(j));
    }
    public static boolean isValid(int i,int j){
        if(i<0 || j<0 || i>=N || j>=M)
            return false;
        return true;
    }
    public static void dfs(int i,int j,char direction){
        if(isValid(i,j)){
            if(visited[i][j])
                return;
            visited[i][j] = true;
            ans++;
            if(grid.get(i).charAt(j) == '/'){
                switch (direction){
                    case 'w':
                        dfs(i-1,j,'s');
                        break;
                    case 'n':
                        dfs(i,j-1,'e');
                        break;
                    case 'e':
                        dfs(i+1,j,'n');
                        break;
                    case 's':
                        dfs(i,j+1,'w');
                        break;
                }
            }
            else if(grid.get(i).charAt(j) == 'C'){
                //System.out.println("here " + direction + " "+ i + " "+j);
                switch (direction){
                    case 'w':
                        //System.out.println("directed to south");
                        dfs(i+1,j,'n');
                        break;
                    case 'n':
                        dfs(i,j+1,'w');
                        break;
                    case 'e':
                        dfs(i-1,j,'s');
                        break;
                    case 's':
                        dfs(i,j-1,'e');
                        break;
                }

            }
            else if(grid.get(i).charAt(j) == '.'){
                switch (direction){
                    case 'w':
                        dfs(i,j+1,'w');
                        break;
                    case 'n':
                        dfs(i+1,j,'n');
                        break;
                    case 'e':
                        dfs(i,j-1,'e');
                        break;
                    case 's':
                        dfs(i-1,j,'s');
                        break;
                }

            }
            else if(grid.get(i).charAt(j) == '-'){
                switch (direction){
                    case 'w':
                        dfs(i,j+1,'w');
                        break;
                    case 'n':

                        dfs(i,j-1,'e');

                        dfs(i,j+1,'w');
                        break;
                    case 'e':
                        dfs(i,j-1,'e');
                        break;
                    case 's':

                        dfs(i,j+1,'w');

                        dfs(i,j-1,'e');
                        break;
                }

            }
            else {
                switch (direction){
                    case 'w':
                        dfs(i+1,j,'n');
                        dfs(i-1,j,'s');
                        break;
                    case 'n':
                        dfs(i+1,j,'n');
                        break;
                    case 'e':
                        dfs(i-1,j,'s');
                        dfs(i+1,j,'n');
                        break;
                    case 's':
                        dfs(i-1,j,'s');
                        break;
                }
            }
            //visited[i][j] = false;
        }
    }
    public static void main(String [] args) throws java.lang.Exception {
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_16.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        grid = new ArrayList<>();
        String line = "";
        while((line = br.readLine()) != null){
            StringBuilder sb = new StringBuilder();
            for(char ch : line.toCharArray()){
                if(ch == '\\')
                    sb.append('C');
                else
                    sb.append(ch);
            }
            grid.add(sb.toString());
        }
        System.out.println(grid.get(6));
        limit =1000;
        N = grid.size();
        M= grid.get(0).length();
        ans=0;
        visited = new boolean[grid.size()][grid.get(0).length()];
        occ = new HashMap<>();
        path = new StringBuilder();
        traversePath = new StringBuilder();
        checkChar = "";
        dfs(0,0,'w');
        //bfs way
        // w -> -2 ,e->-1 , n -> -3 , s->-4


        for(var t:visited){
           for(var y:t){
               if(y)
               System.out.print("#");
               else
                   System.out.print(".");
           }
           System.out.println("");
        }
        System.out.println(ans);
    }
}
