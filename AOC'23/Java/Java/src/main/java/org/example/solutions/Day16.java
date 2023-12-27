package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.KeyPair;
import java.util.*;

//try bfs
public class Day16 {
    static ArrayList<String> grid;
    static HashMap<String,ArrayList<Character>> occ;
    static HashMap<ArrayList<Object>,Integer> mp;
    static StringBuilder path,traversePath ;
    static String checkChar;
    static int [] row = {-1,0,1,0};
    static int [] col = {0,1,0,-1};

    static long ans,N,M,limit;
    static boolean [][] visited ;
    static boolean flg = false,northBeams=false,eastBeams=false,southBeams=false,westBeams=false,isInExtremeRow=false;
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
            if(mp.get(new ArrayList<>(Arrays.asList(getCell(i,j),direction))) != null){
               // ans--;
                return;
            }
            else{
               //ans++;
                mp.put(new ArrayList<>(Arrays.asList(getCell(i,j),direction)),1);
            }
            //ans++;
            if(! visited[i][j])
                ans++;
            visited[i][j] = true;
            //only one time for setting rays correctly
            if(isInExtremeRow){
                isInExtremeRow = false;
                char globalDirection;
                if(southBeams)globalDirection='s';
                else if(northBeams)globalDirection='n';
                else if(eastBeams)globalDirection='e';
                else globalDirection='w';
                switch (globalDirection){
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
            else if(grid.get(i).charAt(j) == '/'){
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
        mp = new HashMap<>();
        while ((line = br.readLine()) != null) {
            StringBuilder sb = new StringBuilder();
            for (char ch : line.toCharArray()) {
                if (ch == '\\')
                    sb.append('C');
                else
                    sb.append(ch);
            }
            grid.add(sb.toString());
        }
        N = grid.size();
        M = grid.get(0).length();
        visited = new boolean[grid.size()][grid.get(0).length()];
//        dfs(0,0,'w');

        //part 2 code
        ans=0;
        long maxAns=Long.MIN_VALUE;

        // 1] beams from north direction
        northBeams = true;
        southBeams = false;
        eastBeams = false;
        westBeams = false;
        isInExtremeRow = true;
        int i=0;
        for(int j=(int)M-1;j>=0;j--){
            //if(j==4)
            dfs(i,j,'n');
            Thread.sleep(100000);
            if(maxAns > 6999)
            System.out.println("max till now "+maxAns);

            if(ans > maxAns)
                maxAns = ans;
            //break;
            //set to default
            ans=0;
            for(int ax=0;ax<N;ax++){
                for(int ay=0;ay<M;ay++){
                    visited[ax][ay] = false;
                }
            }
            isInExtremeRow = true;
            mp.clear();
        }


        // 2] beams from east direction
        northBeams = false;
        southBeams = false;
        eastBeams = true;
        westBeams = false;
        isInExtremeRow = true;
        int j3=(int)(M-1);
        for(int i4=0;i4<N;i4++){
            dfs(i4,j3,'e');
            Thread.sleep(100000);
            if(maxAns > 6999)
                System.out.println("max till now "+maxAns);

            if(ans > maxAns)
                maxAns = ans;
            //set to default
            ans=0;
            for(int ax=0;ax<N;ax++){
                for(int ay=0;ay<M;ay++){
                    visited[ax][ay] = false;
                }
            }
            isInExtremeRow = true;
            mp.clear();
        }


        // 2] beams from west direction
         northBeams = false;
        southBeams = false;
        eastBeams = false;
        westBeams = true;
        isInExtremeRow = true;

        int j1=0;

        for(int i1=0;i1<N;i1++){
            dfs(i1,j1,'w');
            Thread.sleep(100000);
            if(maxAns > 6999)
                System.out.println("max till now "+maxAns);

            if(ans > maxAns)
                maxAns = ans;
            //set to default
            ans=0;
            for(int ax=0;ax<N;ax++){
                for(int ay=0;ay<M;ay++){
                    visited[ax][ay] = false;
                }
            }
            isInExtremeRow = true;
            mp.clear();
        }

        // 3] beams from south direction
         northBeams = false;
        southBeams = true;
        eastBeams = false;
        westBeams = false;
        isInExtremeRow = true;

        int i2=(int) (N-1);
        for(int j2=0;j2<M;j2++){
            dfs(i2,j2,'s');
            Thread.sleep(100000);
            if(maxAns > 6999)
                System.out.println("max till now "+maxAns);

            if(ans > maxAns)
                maxAns = ans;
            //set to default
            ans=0;
            for(int ax=0;ax<N;ax++){
                for(int ay=0;ay<M;ay++){
                    visited[ax][ay] = false;
                }
            }
            isInExtremeRow = true;
            mp.clear();
        }


        System.out.println(maxAns);

/*
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

*/
    }
}
