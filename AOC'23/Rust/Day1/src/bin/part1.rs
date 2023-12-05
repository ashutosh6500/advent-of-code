fn main() {
    let input = include_str!("./input1.txt");
    let output = part1(input);
    dbg!(output);
}

fn part1(input: &str) -> String {
    let lines = input.lines();
    let mut output = 0;
    for line in lines{
        let nums = line.chars().filter(|ch| ch.is_ascii_digit()).map(|ch| ch as u8 - b'0').collect::<Vec<_>>();
        let first = *nums.iter().nth(0).unwrap();
        let last = *nums.iter().last().unwrap();
        output += (first as i32) * 10 + last as i32;
    }
    output.to_string()
}
