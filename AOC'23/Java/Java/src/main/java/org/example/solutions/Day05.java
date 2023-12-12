package org.example.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Day05 {
    static HashMap<Long,Long> seed_to_soil =  new HashMap<Long,Long>();
    static HashMap<Long,Long> soil_to_fert =  new HashMap<Long,Long>();
    static HashMap<Long,Long> fert_to_water =  new HashMap<Long,Long>();
    static HashMap<Long,Long> water_to_light =  new HashMap<Long,Long>();
    static HashMap<Long,Long> light_to_temp =  new HashMap<Long,Long>();
    static HashMap<Long,Long> temp_to_humid =  new HashMap<Long,Long>();
    static HashMap<Long,Long> location_to_humid =  new HashMap<Long,Long>();
    static long final_ans = 999999999;

    public static void main(String [] args) throws java.lang.Exception{
        File file = new File("E:\\advent-of-code\\AOC'23\\Inputs\\input_05.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        long cnt = 1;
        //boolean seed_to_soil = false,soil_to_fertilizer = false,fertilizer_to_water =false,water_to_light=false,light_to_temperature=false,
        ArrayList<Long> seeds = new ArrayList<Long>();
        while((line = br.readLine()) != null){
            // //longln("input: "+line);
            if(cnt == 1){
                String []seedsArray = line.split(":")[1].trim().split(" ");
                //System.out.println("seed array: " + Long.parseLong(seedsArray[(int) 0]));
                for(long itr = 0;itr<(seedsArray.length-1);itr+=2) {
                    //seeds.add(Long.parseLong(s));   //part one seed array
                    //seed array for part 2
                    long first1 =Long.parseLong(seedsArray[(int) itr]);
                    long range = Long.parseLong(seedsArray[(int) (itr + 1)]);
                    //range--;
                    long num1 = first1;
                    while(range-- >0){
                        seeds.add(num1);
                        //System.out.println(num1);

                        num1++;
                    }

                }

            }
            else if(line.length() == 0)
                continue;
            else if(line.trim().equals("seed-to-soil map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    ////longln("input: "+tmp1);

                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");

                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    //a3--;
                    for(long seed : seeds){
                        if(seed >=a2 && seed<(a2+a3))
                        {

                            seed_to_soil.put(seed,(a1+seed-a2));

                        }
                        else{
                            if(seed_to_soil.get(seed) == null)
                                seed_to_soil.put(seed,seed);

                        }
                    }
                    ////longln("seed to soil size "+seed_to_soil.size());


                }
                for(Map.Entry<Long,Long> entry : seed_to_soil.entrySet()) {
                    Long key = entry.getKey();
                    Long value = entry.getValue();
                    // //longln("last one = " + key + "-> "+value);
                }
                //longln("13 seed soil" + seed_to_soil.get(13));

            }
            else if(line.trim().equals("soil-to-fertilizer map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    // //longln("input: "+tmp1);

                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");

                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    //a3--;
                    for(Map.Entry<Long,Long> entry : seed_to_soil.entrySet()) {
                        Long key = entry.getKey();
                        Long value = entry.getValue();


                        if(value >=a2 && value<(a2+a3))
                        {
                            soil_to_fert.put(value,(a1+value-a2));

                        }
                        else{
                            if(soil_to_fert.get(value) == null)
                                soil_to_fert.put(value,value);

                        }
                    }
                    seed_to_soil.clear();
                    seed_to_soil = null;
                    ////longln("soil to fert size "+soil_to_fert.size());

                }
                //longln("13 soil fert" + soil_to_fert.get(13));
            }
            else if(line.trim().equals("fertilizer-to-water map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    ////longln("input: "+tmp1);

                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");

                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    //a3--;
                    for(Map.Entry<Long,Long> entry : soil_to_fert.entrySet()) {
                        Long key = entry.getKey();
                        Long value = entry.getValue();


                        if(value >=a2 && value<(a2+a3))
                        {
                            //longln("check");
                            //longln(value+" "+a1+" "+a2+" "+a3+" ");

                            fert_to_water.put(value,(a1+value-a2));

                        }
                        else{
                            //longln("check1");
                            //longln(value+" "+a1+" "+a2+" "+a3+" ");
                            if(fert_to_water.get(value) == null)
                                fert_to_water.put(value,value);

                        }
                    }
                    soil_to_fert.clear();
                    soil_to_fert = null;
                }
                ////longln("fert to water size "+fert_to_water.size());
                //longln("81 fert water" + fert_to_water.get(52));

            }
            else if(line.trim().equals("water-to-light map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    // //longln("input: "+tmp1);

                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");

                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    //a3--;
                    for(Map.Entry<Long,Long> entry : fert_to_water.entrySet()) {
                        Long key = entry.getKey();
                        Long value = entry.getValue();


                        if(value >=a2 && value<(a2+a3))
                        {
                            water_to_light.put(value,(a1+value-a2));

                        }
                        else{
                            if(water_to_light.get(value) == null)
                                water_to_light.put(value,value);

                        }
                    }
                    fert_to_water.clear();
                    fert_to_water = null;
                }
                ////longln("water to light size "+water_to_light.size());
                //longln("81 water light" + water_to_light.get(81));

            }
            else if(line.trim().equals("light-to-temperature map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    // //longln("input: "+tmp1);

                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");

                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    // a3--;
                    for(Map.Entry<Long,Long> entry : water_to_light.entrySet()) {
                        Long key = entry.getKey();
                        Long value = entry.getValue();


                        if(value >=a2 && value<(a2+a3))
                        {
                            light_to_temp.put(value,(a1+value-a2));

                        }
                        else{
                            if(light_to_temp.get(value) == null)
                                light_to_temp.put(value,value);

                        }
                    }
                    water_to_light.clear();
                    water_to_light = null;
                }
                ////longln("light to temp size "+light_to_temp.size());
                //longln("74 lght temp" + light_to_temp.get(74));

            }
            else if(line.trim().equals("temperature-to-humidity map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    ////longln("input: "+tmp1);

                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");

                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    //a3--;
                    for(Map.Entry<Long,Long> entry : light_to_temp.entrySet()) {
                        Long key = entry.getKey();
                        Long value = entry.getValue();


                        if(value >=a2 && value<(a2+a3))
                        {
                            temp_to_humid.put(value,(a1+value-a2));

                        }
                        else{
                            if(temp_to_humid.get(value) == null)
                                temp_to_humid.put(value,value);

                        }
                    }
                    light_to_temp.clear();
                    light_to_temp = null;
                }
                ////longln("temp to humid size "+temp_to_humid.size());
                //longln("78 temp hum" + temp_to_humid.get(78));

            }
            else if(line.trim().equals("humidity-to-location map:")){
                long p =0;
                while(true){
                    String tmp1 = br.readLine();
                    ////longln("input: "+tmp1);

                    if(tmp1 == null || tmp1.length() == 0)
                        break;
                    String [] sa = tmp1.trim().split(" ");

                    long a1 = Long.parseLong(sa[0]);
                    long a2 = Long.parseLong(sa[1]);
                    long a3 = Long.parseLong(sa[2]);
                    //a3--;
                    for(Map.Entry<Long,Long> entry : temp_to_humid.entrySet()) {
                        Long key = entry.getKey();
                        Long value = entry.getValue();
                        ////longln("temp to humid = " + key + "-> "+value);


                        if(value >=a2 && value<(a2+a3))
                        {
                            location_to_humid.put(value,(a1+value-a2));
                            //longln((a1+value - a2));

                        }
                        else{
                            if(location_to_humid.get(value) == null)
                                location_to_humid.put(value,value);
                            //longln(value);

                        }
                    }
                    temp_to_humid.clear();
                    temp_to_humid = null;
                }
                ////longln("humid to loca size "+location_to_humid.size());
                //final_ans = 999999999;
                for(Map.Entry<Long,Long> entry : location_to_humid.entrySet()) {
                    Long key = entry.getKey();
                    Long value = entry.getValue();
                    if(value < final_ans)
                        final_ans = value;
                    //longln("last one = " + key + "-> "+value);

                }
                location_to_humid.clear();
                location_to_humid = null;
                ////longln("78 hum loc" + location_to_humid.get(78));

            }
            cnt++;
        }
        //longln(final_ans);
        System.out.println(final_ans);
        seeds.clear();
        seeds = null;
    }
}
