file_path = "Inputs/day_08_input_1.txt"

with open(file_path, "r") as file:
    lines = file.readlines()

instructions = lines[0].strip()
element_nodes = {}

for line in lines[1:]:
    if line.strip():
        parent, children = line.split(" = ")
        parent = parent.strip()
        left_child = children.split(",")[0].split("(")[1]
        right_child = children.split(", ")[1].split(")")[0]

        if parent not in element_nodes:
            element_nodes[parent] = {
                "left": left_child,
                "right": right_child
            }

total_steps = 0
curr_node = 'AAA'

while curr_node!='ZZZ':
    for instruction in instructions:
        if instruction == "L":
            curr_node = element_nodes[curr_node]["left"]
        else:
            curr_node = element_nodes[curr_node]["right"]
        total_steps += 1

print(total_steps)
