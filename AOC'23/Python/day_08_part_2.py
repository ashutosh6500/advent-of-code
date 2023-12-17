import math

file_path = "Inputs/day_08_input_2.txt"

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
curr_nodes = [
    node for node in element_nodes.keys() if node[-1] == "A"
]

def check_end_nodes(nodes):
    for node in nodes:
        if node[-1] != "Z":
            return False
    return True

## naive approach

# while not check_end_nodes(curr_nodes):
#     for instruction in instructions:
#         for i, node in enumerate(curr_nodes):
#             if instruction == "L":
#                 curr_nodes[i] = element_nodes[node]["left"]
#             else:
#                 curr_nodes[i] = element_nodes[node]["right"]
#         total_steps += 1

# print(total_steps)

min_steps = [0]*len(curr_nodes)

for i, node in enumerate(curr_nodes):
    while node[-1] != "Z":
        for instruction in instructions:
            if instruction == "L":
                node = element_nodes[node]["left"]
            else:
                node = element_nodes[node]["right"]
            min_steps[i] += 1

print(math.lcm(*min_steps))