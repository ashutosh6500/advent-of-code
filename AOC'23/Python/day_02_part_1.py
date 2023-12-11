input_path = "../Inputs/day_02_input_1.txt"

with open(input_path, 'r') as file:
	lines = file.readlines()
input_games = [line.strip() for line in lines]

max_dict = {
	"red": 12,
	"green": 13,
	"blue": 14
}

idsum = 0

for game in input_games:
	game_id = int(game.split("Game ")[1].split(":")[0])
	sets = game.split(": ")[1].split(";")
	add_id = True

	for set_ in sets:
		balls = set_.split(',')
		rgb_dict = {
			"red": 0,
			"green": 0,
			"blue": 0
		}
		for ball in balls:
			if "red" in ball:
				replace_str = "red"
			elif "green" in ball:
				replace_str = "green"
			elif "blue" in ball:
				replace_str = "blue"
			
			rgb_dict[replace_str]+=int(ball.replace(replace_str, "").strip())
		
		for k in rgb_dict:
			if rgb_dict[k]>max_dict[k]:
				add_id = False
				break
		
		if not add_id:
			break
		
	if add_id:
		idsum += game_id
print(idsum)
