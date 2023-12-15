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


times = lines[0].split(": ")[1].strip().split(" ")
times = [int(time) for time in times if time != '']
distances = lines[1].split(": ")[1].strip().split(" ")
distances = [int(distance) for distance in distances if distance != '']

races_time_to_distance_map = {
    times[i]: distances[i] for i in range(len(times))
}

all_race_ways = []

for race_time in races_time_to_distance_map:
    race_record_distance = races_time_to_distance_map[race_time]
    all_race_ways.append(
        get_num_ways(race_time, race_record_distance)
    )

result = reduce(operator.mul, all_race_ways, 1)
print(result)