path = "Inputs/day_05_input_1.txt"

with open(path, 'r') as f:
    lines = f.readlines()

seeds = []
seed_to_soil_map = {}
soil_to_fertilizer_map = {}
fertilizer_to_water_map = {}
water_to_light_map = {}
light_to_temperature_map = {}
temperature_to_humidity_map = {}
humidity_to_location_map = {}
max_location_no = 0

i = 0

def map_ranges(input_ranges, src_dest_map):
    for range_ in input_ranges:
        split_numbers = range_.split(" ")
        split_numbers = [int(num) for num in split_numbers if num != '']
        source_range_start = split_numbers[1]
        destination_range_start = split_numbers[0]
        range_length = split_numbers[2]

        src_dest_map[
            (source_range_start, source_range_start+range_length-1)
        ] = (destination_range_start, destination_range_start+range_length-1)


def get_mapped_destn_value(source_value, src_dest_map):
    for range_tup in src_dest_map:
        if source_value >= range_tup[0] and source_value <= range_tup[1]:
            destn_tup = src_dest_map[range_tup]
            return source_value + (destn_tup[0] - range_tup[0])
    
    return source_value


while i<len(lines):
    if "seeds:" in lines[i]:
        seeds = lines[i].split("seeds: ")[1].split(" ")
        seeds = [int(seed.replace("\n", "")) for seed in seeds if seed != '']
    
    if "seed-to-soil map:" in lines[i]:
        i+=1
        seed_to_soil_input_ranges = []
        while lines[i] != "\n":
            seed_to_soil_input_ranges.append(lines[i].replace("\n", ""))
            i+=1
        
        map_ranges(seed_to_soil_input_ranges, seed_to_soil_map)
    
    if "soil-to-fertilizer map:" in lines[i]:
        i+=1
        soil_to_fertilizer_input_ranges = []
        while lines[i] != "\n":
            soil_to_fertilizer_input_ranges.append(lines[i].replace("\n", ""))
            i+=1
        
        map_ranges(soil_to_fertilizer_input_ranges, soil_to_fertilizer_map)
    
    if "fertilizer-to-water map:" in lines[i]:
        i+=1
        fertilizer_to_water_input_ranges = []
        while lines[i] != "\n":
            fertilizer_to_water_input_ranges.append(lines[i].replace("\n", ""))
            i+=1
        
        map_ranges(fertilizer_to_water_input_ranges, fertilizer_to_water_map)
    
    if "water-to-light map:" in lines[i]:
        i+=1
        water_to_light_input_ranges = []
        while lines[i] != "\n":
            water_to_light_input_ranges.append(lines[i].replace("\n", ""))
            i+=1
        
        map_ranges(water_to_light_input_ranges, water_to_light_map)
    
    if "light-to-temperature map:" in lines[i]:
        i+=1
        light_to_temperature_input_ranges = []
        while lines[i] != "\n":
            light_to_temperature_input_ranges.append(lines[i].replace("\n", ""))
            i+=1
        
        map_ranges(light_to_temperature_input_ranges, light_to_temperature_map)
    
    if "temperature-to-humidity map:" in lines[i]:
        i+=1
        temperature_to_humidity_input_ranges = []
        while lines[i] != "\n":
            temperature_to_humidity_input_ranges.append(lines[i].replace("\n", ""))
            i+=1
        
        map_ranges(temperature_to_humidity_input_ranges, temperature_to_humidity_map)
    
    if "humidity-to-location map:" in lines[i]:
        i+=1
        humidity_to_location_input_ranges = []
        while i<len(lines):
            humidity_to_location_input_ranges.append(lines[i].replace("\n", ""))
            i+=1
        
        map_ranges(humidity_to_location_input_ranges, humidity_to_location_map)
        max_location_no = max([tup[1] for tup in humidity_to_location_map.values()])

    i+=1

min_location_no = max_location_no

for s in range(0, len(seeds), 2):
    for seed in range(seeds[s], seeds[s]+seeds[s+1]):
        soil = get_mapped_destn_value(seed, seed_to_soil_map)
        fertilizer = get_mapped_destn_value(soil, soil_to_fertilizer_map)
        water = get_mapped_destn_value(fertilizer, fertilizer_to_water_map)
        light = get_mapped_destn_value(water, water_to_light_map)
        temperature = get_mapped_destn_value(light, light_to_temperature_map)
        humidity = get_mapped_destn_value(temperature, temperature_to_humidity_map)
        location_no = get_mapped_destn_value(humidity, humidity_to_location_map)

        if location_no < min_location_no:
            min_location_no = location_no

print(min_location_no)