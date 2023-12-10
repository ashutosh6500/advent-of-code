file_path = '../Inputs/day_01_input_2.txt'

with open(file_path, 'r') as file:
	lines = file.readlines()
	
list_of_texts = [line.strip() for line in lines]
string_to_int_map = {
	"one": 1,
	"two": 2,
	"three": 3,
	"four": 4,
	"five": 5,
	"six": 6,
	"seven": 7,
	"eight": 8,
	"nine": 9
}

first = 0
second = 0
final_sum = 0

def get_numeric(text):
	if text[0].isdigit():
		return int(text[0])
	d = 0
	for digit in string_to_int_map:
		if text.startswith(digit):
		    d = digit
		    break
	return string_to_int_map.get(d, 0)

for text in list_of_texts:
	for i in range(len(text)):
		first = get_numeric(text[i:])
		if first:
			break

	for i in range(len(text)-1, -1, -1):
		second = get_numeric(text[i:])
		if second:
			break
	final_sum += 10*first + second

print(final_sum)
