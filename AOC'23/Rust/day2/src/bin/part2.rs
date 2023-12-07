fn main() {
    let input = include_str!("./input2.txt");
    let output = part2(input);
    dbg!(output);
}

fn part2(input: &str) -> String {
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

    for game in game_list.iter(){
        let mut min_red = 0;
        let mut min_green = 0;
        let mut min_blue = 0;

        for turn in game.iter(){
            min_red = min_red.max(turn.red);
            min_green = min_green.max(turn.green);
            min_blue = min_blue.max(turn.blue);
        }

        total += min_red * min_green * min_blue;
    }
    total.to_string()    
}

#[derive(Debug)]
struct Turn {
    red: usize,
    green: usize,
    blue: usize,
}
