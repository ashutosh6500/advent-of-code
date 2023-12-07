fn main() {
    let input = include_str!("./input2.txt");
    let output = part1(input);
    dbg!(output);
}

fn part1(input: &str) -> String {
    let mut game_list = Vec::<Vec<Turn>>::new();
    let games = input.lines();
    for game in games{
        let (_, turns) = game.split_once(": ").unwrap();
        let turns = turns.split("; ").collect::<Vec<_>>();
        let mut turn_list = Vec::new();

        for turn in turns{
            let cubes = turn.split(", ").collect::<Vec<_>>();
            let mut turn = Turn {red: 0, blue: 0, green: 0};

            for cube in cubes{
                let (num_cubes, cube_color) = cube.split_once(" ").unwrap();
                let num_cubes = num_cubes.parse().unwrap();
                match &cube_color[0..1] {
                    "r" => turn.red = num_cubes,
                    "g" => turn.green = num_cubes,
                    "b" => turn.blue = num_cubes,
                    _ => panic!("BUG")
                }
            }
            turn_list.push(turn)
        }
        game_list.push(turn_list);
    }

    let mut total = 0;
    'next_game: for (index, game) in game_list.iter().enumerate(){
        println!("###### {:?}", game);
        for turn in game{
            println!("{:?}", turn);
            if turn.red > 12 || turn.green > 13 || turn.blue > 14 {
                continue 'next_game;
            }
        }
        total += index + 1;
    }
    println!("{:?}", total);
    "Unsolved".to_string()    
}

#[derive(Debug)]
struct Turn {
    red: usize,
    green: usize,
    blue: usize,
}
