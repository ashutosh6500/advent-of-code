with open('Inputs/day_04_input_1.txt') as f:
    lines = f.readlines()

cards = [line.strip() for line in lines]
card_wins_map = {}

for card in cards:
    card_id = int(card.split(": ")[0].split("Card ")[1])
    if card_id not in card_wins_map:
        card_wins_map[card_id] = 0
    card_wins_map[card_id] += 1
    winning_numbers = card.split(": ")[1].split(" |")[0].split(" ")
    holding_numbers = card.split("| ")[1].split(" ")
    winning_numbers = [int(num) for num in winning_numbers if num != '']
    holding_numbers = [int(num) for num in holding_numbers if num != '']
    matching_numbers = 0

    for num in holding_numbers:
        if num in winning_numbers:
            matching_numbers+= 1

    for num in range(1, matching_numbers+1):
        if card_id + num not in card_wins_map:
            card_wins_map[card_id + num] = 0
        card_wins_map[card_id + num] += card_wins_map[card_id]

print(sum(card_wins_map.values()))