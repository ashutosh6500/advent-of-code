with open('Inputs/day_03_input_1.txt') as f:
    lines = f.readlines()

lines = [list(line.strip()) for line in lines]
part_numbers = []

for i in range(len(lines)):
    line = lines[i]
    running_str = ""
    is_part_no = False
    continue_part_no = True

    for j in range(len(line)):
        if line[j].isdigit():
            running_str += line[j]
            adjacent_chars = []
            if i - 1 >= 0:
                adjacent_chars.append(lines[i-1][j])
            if i + 1 < len(lines):
                adjacent_chars.append(lines[i+1][j])
            if j - 1 >= 0:
                adjacent_chars.append(lines[i][j-1])
            if j + 1 < len(line):
                adjacent_chars.append(lines[i][j+1])
            if i - 1 >= 0 and j - 1 >= 0:
                adjacent_chars.append(lines[i-1][j-1])
            if i - 1 >= 0 and j + 1 < len(line):
                adjacent_chars.append(lines[i-1][j+1])
            if i + 1 < len(lines) and j - 1 >= 0:
                adjacent_chars.append(lines[i+1][j-1])
            if i + 1 < len(lines) and j + 1 < len(line):
                adjacent_chars.append(lines[i+1][j+1])
                
            adjacent_chars = [char for char in adjacent_chars if char != '']
            non_digit_chars = [char for char in adjacent_chars if not char.isdigit() and char != '.']

            if len(non_digit_chars) > 0 or is_part_no:
                is_part_no = True
                if j + 1 < len(line):
                    if line[j+1].isdigit():
                        continue_part_no = True
                    else:
                        continue_part_no = False
                else:
                    continue_part_no = False
            
            if j == len(line) - 1:
                if is_part_no:
                    part_numbers.append(int(running_str))
                running_str = ""
                is_part_no = False
                continue_part_no = True
        else:
            if is_part_no:
                if not continue_part_no:
                    part_numbers.append(int(running_str))
                    running_str = ""
                    is_part_no = False
                    continue_part_no = True
            else:
                running_str = ""
                is_part_no = False
                continue_part_no = True
print(sum(part_numbers))