fn main() {
    let input = include_str!("./input1.txt");
    let output = part2(input);
    dbg!(output);
}

fn part2(input: &str) -> String {
    let lines = input.lines();

    let nums = [
        "one", "1", "two", "2", "three", "3", "four", "4", "five", "5", "six", "6", "seven", "7", "eight", "8", "nine", "9" 
    ];

    let mut ouput = 0;

    for line in lines{
        let mut first = None;
        'out: for i in 0..line.len() {
            for (index, num) in nums.iter().enumerate() {
                if i + num.len() > line.len(){
                    continue;
                }

                if line[i..i+num.len()] == **num {
                    first = Some(1+index / 2);
                    break 'out;
                }
            }
        }
        let Some(first) = first else {panic!("invalid input")};

        let mut last = None;
        'out: for i in (0..line.len()).rev() {
            for (index, num) in nums.iter().enumerate() {
                if i + num.len() > line.len(){
                    continue;
                }
                if line[i..i+num.len()] == **num {
                    last = Some(1 + index/2);
                    break 'out;
                }
            }
        }
        let Some(last) = last else {panic!("invalid input")};

        ouput += 10 * first as i32 + last as i32;
    }

    ouput.to_string()
}