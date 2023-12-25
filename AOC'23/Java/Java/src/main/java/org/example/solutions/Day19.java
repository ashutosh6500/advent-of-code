package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day19 {
    public static void main(String [] args) throws java.lang.Exception{
        // your code goes here
        File file=new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_19.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(file));
        String line = "";
        long ans = 0;
        HashMap<String, ArrayList<String>> mp = new HashMap<>();
        while((line = br.readLine()) != null) {
           // System.out.println(line);
            if(line.length() == 0)
                break;
            String [] input = line.trim().split("}")[0].trim().split("\\{");
            String label = input[0].trim();
            ArrayList<String> arrofStr = new ArrayList<>(Arrays.asList(input[1].trim().split(",")));
            mp.put(label.toString(),arrofStr);
            //System.out.println(Arrays.deepToString(line.split("}")));;
        }
        System.out.println(mp);
        while((line = br.readLine()) != null){
            if(line.length() == 0)
                break;
            System.out.println(line);
            line = line.substring(1,line.length()-1);
            //System.out.println(line);
            HashMap<String,Long> mapping = new HashMap<>();
            for(var tmp : line.trim().split(",")){
                mapping.put(tmp.trim().split("=")[0].trim(),Long.parseLong(tmp.trim().split("=")[1].trim()));

            }
            //calculate ans
            System.out.println(mapping);
            boolean run = true;
            ArrayList<String> start = mp.get(new String("in"));
            //System.out.println(start.toString());
            while(run){
                long itr = 1;
                for(var pair : start){
                    System.out.println("pair "+pair);
                    if(itr == start.size()){
                        if(pair.equals("A")){
                            ans = ans + (mapping.get("s") + mapping.get("x")+mapping.get("m")+mapping.get("a"));
                            run =false;
                            break;
                        }
                        if(pair.equals("R")){
                            run = false;
                            break;
                        }
                        start = mp.get(pair);
                        break;
                    }
                    if(pair.equals(new String("R"))) {
                        run = false;
                        break;
                    }
                    else if(pair.equals("A")){
                        ans = ans + (mapping.get("s") + mapping.get("x")+mapping.get("m")+mapping.get("a"));
                        run =false;
                        break;
                    }
                    else{
                        String condition = pair.trim().split(":")[0].trim();
                        String nextLabel = pair.trim().split(":")[1].trim();
                        if(condition.charAt(1) == '<'){
                            long a1 = mapping.get(String.valueOf(condition.charAt(0)));
                            long a2 = Long.parseLong(condition.substring(2));
                            System.out.println("compare between "+a1 +" "+a2);
                            if(a1 < a2){
                                if(nextLabel.equals("A"))
                                {
                                    ans = ans + (mapping.get("s") + mapping.get("x")+mapping.get("m")+mapping.get("a"));
                                    run =false;
                                    break;
                                }
                                if(nextLabel.equals("R"))
                                {
                                    run = false;
                                    break;
                                }
                                start = mp.get(nextLabel);
                                break;
                            }
                        }
                        else{
                            long a1 = mapping.get(String.valueOf(condition.charAt(0)));
                            long a2 = Long.parseLong(condition.substring(2));
                            if(a1 > a2){
                                if(nextLabel.equals("A"))
                                {
                                    ans = ans + (mapping.get("s") + mapping.get("x")+mapping.get("m")+mapping.get("a"));
                                    run =false;
                                    break;
                                }
                                if(nextLabel.equals("R"))
                                {
                                    run = false;
                                    break;
                                }
                                start = mp.get(new String(nextLabel));
                                break;
                            }
                        }
                    }
                    itr++;
                }
            }
        }
        System.out.println(ans);
        }
}
