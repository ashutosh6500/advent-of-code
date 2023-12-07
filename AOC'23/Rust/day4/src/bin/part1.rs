use std::env;
fn main() {
    env::set_var("RUST_BACKTRACE", "full");

    let input = include_str!("./input4.txt");
    let output = part1(input);
    dbg!(output);
}

fn part1(input: &str) -> String {
    let lines = input.lines();

    let mut winning_nums: Vec<Vec<i32>> = Vec::new();
    let mut chosen_nums: Vec<Vec<i32>> = Vec::new();

    for line in lines{
        let (_, nums) = line.split_once(": ").unwrap();
        let (win, choose) = nums.split_once(" | ").unwrap();

        let win_nums = win.split_whitespace().map(|w| w.parse::<i32>().unwrap()).collect::<Vec<i32>>();
        winning_nums.push(win_nums);

        let choose_nums = choose.split_whitespace().map(|w| w.parse::<i32>().unwrap()).collect::<Vec<i32>>();
        chosen_nums.push(choose_nums)
    }
    let mut total = 0;
    for i in 0..winning_nums.len(){
        let count = winning_nums[i].iter().cloned().filter(|&x| chosen_nums[i].contains(&x)).collect::<Vec<i32>>().len();
        total += if count > 0 {1 << count - 1} else {0};
        println!("{:?}   {:?}  {:?}", winning_nums[i], chosen_nums[i], count);

    }

    return total.to_string();
}
