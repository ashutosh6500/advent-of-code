with open('Inputs/day_04_input_1.txt') as f:
    lines = f.readlines()

cards = [line.strip() for line in lines]
total_points = 0

for card in cards:
    winning_numbers = card.split(": ")[1].split(" |")[0].split(" ")
    holding_numbers = card.split("| ")[1].split(" ")
    winning_numbers = [int(num) for num in winning_numbers if num != '']
    holding_numbers = [int(num) for num in holding_numbers if num != '']
    card_points = 0

    for num in holding_numbers:
        if num in winning_numbers:
            if card_points == 0:
                card_points += 1
            else:
                card_points *= 2
    
    total_points += card_points

print(total_points)