file_path = "Inputs/day_07_input_1.txt"

with open(file_path, "r") as file:
    lines = file.readlines()

card_values_order = "J23456789TJQKA"

def get_score(hand):
    counts_dict = {}

    for card in hand.replace("J", ""):
        if card not in counts_dict:
            counts_dict[card] = 1
        else:
            counts_dict[card] += 1
        
    counts_list = sorted(list(counts_dict.values()))

    if len(counts_list) > 0:
        counts_list[-1] += hand.count("J")
    else:
        counts_list.append(5)

    if 5 in counts_list:
        score = 10
    elif 4 in counts_list:
        score = 9
    elif 3 in counts_list:
        if 2 in counts_list:
            score = 8
        else:
            score = 7
    elif 2 in counts_list:
        if counts_list.count(2) == 2:
            score = 6
        else:
            score = 5
    else:
        score = 4
    
    indices = [card_values_order.index(card[0]) for card in hand]
    return score, indices

hands = []
for line in lines:
    hand, bid = line.strip().split(" ")
    hands.append((hand, int(bid)))

scores = []
for hand, bid in hands:
    score, indices = get_score(hand)
    scores.append((hand, (score, indices), bid))

# sort scores
scores.sort(key=lambda x: x[1])

total_score = 0
for i, score in enumerate(scores):
    total_score += score[2] * (i+1)

print(total_score)
