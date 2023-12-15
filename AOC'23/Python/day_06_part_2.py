from functools import reduce
import operator

file_path = "Inputs/day_06_input_1.txt"

with open(file_path, 'r') as f:
    lines = f.readlines()

def get_num_ways(max_time, min_distance):
    total_ways = 0

    for wait_time in range(max_time):
        speed = wait_time
        distance_traveled = speed*(max_time-wait_time)
        if distance_traveled > min_distance:
            total_ways += 1
    
    return total_ways


time = int(lines[0].split(": ")[1].strip().replace(" ", "").strip())
distance = int(lines[1].split(": ")[1].strip().replace(" ", "").strip())
num_ways = get_num_ways(time, distance)
print(num_ways)