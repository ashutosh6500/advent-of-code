package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day20 {
    static long lcm(ArrayList<Long> numbers)
    {
        return numbers.stream().reduce(
                (long)1, (x, y) -> (x * y) / gcd(x, y));
    }

    static long gcd(long a, long b)
    {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
    public static void main(String [] args) throws java.lang.Exception {
        long finalLowCnt=0,finalHighCnt=0;
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_20.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        long lowRays = 0, highRays = 0,low=0,high=0;
        HashMap<String, ArrayList<String>> moduleMappings = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> conjuctionModules = new HashMap<>();
        HashMap<String,Boolean> flipflopStates = new HashMap<>();
        while ((line = br.readLine()) != null) {
            String[] arrOfstr = line.trim().split("->");
            String [] eles = arrOfstr[1].trim().split(",");
            String module = arrOfstr[0].trim();
            if(module.equals("broadcaster"))
            {
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add("b");
                for(String h : eles){
                    tmp.add(h.toString().trim());
                }
                moduleMappings.put(module,tmp);
            }
            else if(module.charAt(0) == '&'){
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add("c");
                for(String h : eles){
                    tmp.add(h.toString().trim());
                }
                moduleMappings.put(module.substring(1),tmp);
            }
            else{
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add("f");
                flipflopStates.put(module.substring(1),false);
                for(String h : eles){
                    tmp.add(h.toString().trim());
                }
                moduleMappings.put(module.substring(1),tmp);
            }
        }
        for(Map.Entry<String,ArrayList<String>> entry : moduleMappings.entrySet()){
            String module = entry.getKey().trim();
            for(var e:entry.getValue()){
                e=e.trim();
                if(moduleMappings.get(e) !=  null && moduleMappings.get(e).get(0).equals("c")){
                    if(conjuctionModules.get(e) != null){
                        HashMap<String,Integer> f = conjuctionModules.get(e);
                        f.put(module,0);
                        //System.out.println(f);
                        conjuctionModules.put(e,f);
                    }
                    else
                    conjuctionModules.put(e,new HashMap<String,Integer>(){{put(module,0);};});
                }
            }
        }
        Queue<ArrayList<String>> queue = new LinkedList<>();
        int itr1=0;
        ArrayList<String> tmp = moduleMappings.get("broadcaster");
        int j=0;
       // System.out.println(tmp.size());
        for(int i=1;i<tmp.size();i++){
            ArrayList<String> tmp1 = new ArrayList<>();
            tmp1.add(tmp.get(i));
            tmp1.add("low");
            queue.add(tmp1);
        }
        long fv=-1,vt=-1,kk=-1,xr=-1;
        lowRays = queue.size();
        long ans=0;
       // low = queue.size();
        long steps = 10000;
        while(steps-- >0) {
            ans++;
            while (!queue.isEmpty()) {
               //System.out.println(queue);
                long cnt = queue.size();
                for (long i = 0; i < cnt; i++) {
                    ArrayList<String> front = queue.remove();
                    if((front.get(0).trim().equals("sq")) && (front.get(1).trim().equals("high")))
                    {
                        System.out.println("got high to sq after these steps "+ans);
                    }

                    if (front.get(1).trim().equals("low"))
                        low++;
                    else
                        high++;
                    if (((moduleMappings.get(front.get(0).trim()) != null) && (moduleMappings.get(front.get(0).trim()).get(0).trim().equals("f")))) {
                        if (front.get(1).trim().equals("low")) {
                            int itr = 0;
                            if (flipflopStates.get(front.get(0).trim()))
                                flipflopStates.put(front.get(0).trim(), false);
                            else
                                flipflopStates.put(front.get(0).trim(), true);
                            for (var t : moduleMappings.get(front.get(0).trim())) {
                                itr++;
                                if (itr == 1)
                                    continue;
                                String pulse = "";
                                if (flipflopStates.get(front.get(0).trim())) {
                                    queue.add(new ArrayList<>(Arrays.asList(t, "high")));
                                    pulse = "high";
                                    highRays++;
                                } else {
                                    queue.add(new ArrayList<>(Arrays.asList(t, "low")));
                                    pulse = "low";
                                    lowRays++;
                                }
                                if (conjuctionModules.get(t.trim()) != null) {

                                        for (Map.Entry<String, Integer> entry1 : conjuctionModules.get(t.trim()).entrySet()) {
                                            if (front.get(0).trim().equals(entry1.getKey())) {
                                                if (pulse.equals("high"))
                                                    entry1.setValue(1);
                                                else
                                                    entry1.setValue(0);
                                            }
                                        }
                                }
                            }
                        } else {
                        }
                    } else if ((moduleMappings.get(front.get(0).trim()) != null) && (moduleMappings.get(front.get(0).trim()).get(0).equals("c"))) {
                       // System.out.println(conjuctionModules);
                        String pulse = "";
                        int ycnt = 0;
                        StringBuilder sb = new StringBuilder();
                        for (Map.Entry<String, Integer> entry1 : conjuctionModules.get(front.get(0).trim()).entrySet()) {
                            sb.append(String.valueOf(entry1.getValue()));
                            ycnt++;
                        }
                        String sb1 = sb.toString();
                        if ((sb1.equals("1".repeat(ycnt))))
                            pulse = "low";
                        else
                            pulse = "high";
                        int itr = 0;
                        for (var t : moduleMappings.get(front.get(0).trim())) {
                            itr++;
                            if (itr == 1)
                                continue;
                            if((pulse.equals("high") && (t.trim().equals("sq")) && (front.get(0).trim().equals("fv")))){
                                if(fv == -1)
                                    fv= ans;
                            }
                            if((pulse.equals("high") && (t.trim().equals("sq")) && (front.get(0).trim().equals("kk")))){
                                if(kk == -1)
                                    kk= ans;
                            }
                            if((pulse.equals("high") && (t.trim().equals("sq")) && (front.get(0).trim().equals("vt")))){
                                if(vt == -1)
                                    vt= ans;
                            }
                            if((pulse.equals("high") && (t.trim().equals("sq")) && (front.get(0).trim().equals("xr")))){
                                if(xr == -1)
                                    xr= ans;
                            }
                            queue.add(new ArrayList<>(Arrays.asList(t, pulse)));
                            if (pulse.equals("low"))
                                lowRays++;
                            else
                                highRays++;
                            //updateing conjuctions to recall last values
                            if (conjuctionModules.get(t.trim()) != null) {

                                for (Map.Entry<String, Integer> entry1 : conjuctionModules.get(t.trim()).entrySet()) {
                                    if (front.get(0).trim().equals(entry1.getKey())) {
                                        if (pulse.equals("high"))
                                            entry1.setValue(1);
                                        else
                                            entry1.setValue(0);
                                    }
                                }

                            }

                        }
                    }
                }
            }
        //re init
            queue.clear();
            finalHighCnt = finalHighCnt + high;
            high=0;
            finalLowCnt = finalLowCnt + (low + 1);
            low=0;
            for(int i=1;i<tmp.size();i++){
                ArrayList<String> tmp1 = new ArrayList<>();
                tmp1.add(tmp.get(i));
                tmp1.add("low");
                queue.add(tmp1);
            }
            //ans++;
        }
        System.out.println(xr+" "+vt+" "+kk+" "+fv);
//        ArrayList<Long> nums = new ArrayList<>(Arrays.asList(3769,3797,3931,3863));
//        long LCM = lcm(nums);
//        System.out.println("LCM of " + nums + " is "+ LCM);
//        System.out.println(finalLowCnt*finalHighCnt);
    }
}
