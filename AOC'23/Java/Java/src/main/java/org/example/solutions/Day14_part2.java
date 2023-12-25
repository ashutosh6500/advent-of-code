package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Day14_part2 {
    static long [] northCapacityArray ;
    static long [] westCapacityArray ;
    static long [] southCapacityArray ;
    static long [] eastCapacityArray ;
    static ArrayList<String> grid;
    public static void tiltNorth(){

    }
    public static void main(String [] args) throws java.lang.Exception {
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_14.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        grid = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            grid.add(line);
        }
        long load = 0;
        northCapacityArray = new long[grid.get(0).length()];
        for(int i=0;i<grid.size();i++){
            for(int j=0;j<grid.get(0).length();j++){
                if(i == 0){
                    if(grid.get(i).charAt(j) == 'O')
                        load = load + grid.size();
                    if(grid.get(i).charAt(j) == '.')
                        northCapacityArray[j] = 0;
                    else
                        northCapacityArray[j] = 1;
                }
                else
                {
                    if(grid.get(i).charAt(j) == '.'){
                        //nothing to do
                    }
                    else if(grid.get(i).charAt(j) == 'O'){
                        load = load + (grid.size() - northCapacityArray[j]);
                        northCapacityArray[j] ++;
                    }
                    else{
                        northCapacityArray[j] = i+1;
                    }
                }
            }
        }
        System.out.println(load);
    }
}
