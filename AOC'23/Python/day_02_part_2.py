from functools import reduce

input_path = "../Inputs/day_02_input_2.txt"

with open(input_path, 'r') as file:
	lines = file.readlines()
input_games = [line.strip() for line in lines]
powers = []
initial_set_of_cubes = {
	"green": 0,
	"red": 0,
	"blue": 0
}


for game in input_games:
	game_id = int(game.split("Game ")[1].split(":")[0])
	game_set_of_cubes = initial_set_of_cubes.copy()
	balls = game.split(": ")[1].replace(";", ",").split(", ")
	for ball in balls:
		if "red" in ball:
			replace_str = "red"
		elif "green" in ball:
			replace_str = "green"
		elif "blue" in ball:
			replace_str = "blue"
		num_balls = int(ball.replace(replace_str, "").strip())
		if num_balls>game_set_of_cubes[replace_str]:
			game_set_of_cubes[replace_str] = num_balls
	powers.append(reduce(lambda x, y: x*y, game_set_of_cubes.values()))

print(sum(powers))
	
