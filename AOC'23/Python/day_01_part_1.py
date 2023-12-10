file_path = '../Questions/day_01_input_1.txt'

with open(file_path, 'r') as file:
	lines = file.readlines()
	
list_of_texts = [line.strip() for line in lines]
list_of_found_numbers = []

for text in list_of_texts:
	digits_list = [digit for digit in text if digit.isdigit()]
	number = int(digits_list[0] + digits_list[-1])
	list_of_found_numbers.append(number)

print(sum(list_of_found_numbers))
