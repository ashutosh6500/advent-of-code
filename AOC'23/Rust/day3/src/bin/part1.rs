use std::env;
fn main() {
    env::set_var("RUST_BACKTRACE", "full");

    let input = include_str!("./input3.txt");
    let output = part1(input);
    dbg!(output);
}

fn part1(input: &str) -> String {
    let lines: Vec<&str> = input.lines().collect();
    let mut total = 0;

    let num_rows = lines.len();
    let num_cols = lines[0].chars().filter(|&c| c != ' ').count();
    
    for (r, line) in lines.iter().enumerate(){
        let mut curr_num = String::new();
        let mut sc = 0;
        let mut ec = 0;

        for (c, ch) in line.chars().enumerate(){
            if ch.is_ascii_digit() {
                curr_num.push(ch);
                // println!("{:?}  curr_num {:?}", ch, curr_num);
                ec = c+1;
                if curr_num.len() == 1 {
                    sc = c;
                }
            } else {
                if !curr_num.is_empty(){
                    // dbg!(lines[r].chars());
                    // println!("{:?}", lines[r].chars().nth(c));
                    let valid = is_valid_gear(r as i32, sc as i32, ec as i32, num_rows as i32, num_cols as i32, &lines);
                    println!("Number found : {}, Vaild : {}", curr_num, valid);
                    if valid {
                        total += curr_num.parse::<i32>().unwrap();
                    }

                }
                curr_num.clear();
            }
                if c == (num_cols-1) && !curr_num.is_empty(){
                    // println!("{:?}", c);
                    let valid = is_valid_gear(r as i32, sc as i32, ec as i32, num_rows as i32, num_cols as i32, &lines);
                    println!("Number found : {}, Vaild : {}", curr_num, valid);
                    if valid {
                        total += curr_num.parse::<i32>().unwrap();
                    }
                }
        }
    }

    return total.to_string();
}

fn is_valid_gear(r: i32, sc: i32, ec: i32, num_rows: i32, num_cols: i32, grid: &Vec<&str>) -> bool {
    // println!("{} {} {}", r, sc, ec);
    if is_symbol(r, sc-1, num_rows, num_cols, grid) || is_symbol(r, ec, num_rows, num_cols, grid){
        // println!("{} {}   {} {}", r, sc-1, r+1, ec+1);
        return true;
    }

    for c in (sc-1)..(ec+1){
        if is_symbol(r-1, c, num_rows, num_cols, grid) || is_symbol(r+1, c, num_rows, num_cols, grid){
            // println!("{} {}   {} {}", r-1, c, r+1, c);
            return true;
        }
    }

    return false;
}

fn is_symbol(r: i32, c: i32, num_rows: i32, num_cols: i32, grid: &Vec<&str>) -> bool{
    if !(r as i32 >= 0 && r < num_rows && c as i32 >= 0 && c < num_cols){
        // println!("{}  {}", r, c);
        return false;
    }

    return grid[r as usize].chars().nth(c as usize).unwrap() != '.' && !grid[r as usize].chars().nth(c as usize).unwrap().is_digit(10);
}
