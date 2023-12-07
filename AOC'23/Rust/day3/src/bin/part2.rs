use std::env;
// use std::collections::HashMap;
use std::collections::HashSet;

fn main() {
    env::set_var("RUST_BACKTRACE", "full");

    let input = include_str!("./input3.txt");
    let output = part2(input);
    dbg!(output);
}

fn part2(input: &str) -> String {

    let mut grid: Vec<Vec<char>> = Vec::new();

    for line in input.lines(){
        let row: Vec<char> = line.trim().chars().collect();
        grid.push(row);
    }
    // for row in &grid {
    //     println!("{:?}", row);
    // }

    let lines: Vec<&str> = input.lines().collect();
    // let mut total: i32 = 0;

    let num_rows = lines.len();
    let num_cols = lines[0].chars().count();

    let mut star_coords: Vec<(i32, i32)>  = Vec::new();
    // let mut num_map: HashMap<String, Vec<(i32, i32)>> = HashMap::new();
    
    for (r, line) in lines.iter().enumerate(){
        // let mut curr_num = String::new();
        // let mut num_coords: Vec<(i32, i32)> = Vec::new();
        // let mut sc = 0;
        // let mut ec = 0;

        

        for (c, ch) in line.chars().enumerate(){

            if ch == '*' {
                // println!("{:?}  {:?}" , r, c)
                star_coords.push((r as i32, c as i32));
            }
        }
    }

    let mut total = 0;

    let dirs: Vec<(i32, i32)> = vec![(1, 0), (-1, 0), (0, 1), (0, -1), (1, 1), (1, -1), (-1, 1), (-1, -1)];
    for sc in &star_coords {
        let mut neighbours: HashSet<_> = HashSet::new();
        let (r, c) = sc;
        for dir in &dirs {
            let (x, y) = dir;
            let rx = r + x;
            let cy = c + y;
            if rx >= 0 as i32  && rx < num_rows as i32 && cy >= 0 && cy  < num_cols as i32{
                if grid[rx as usize][cy as usize].is_digit(10){
                    // println!("{} {}", rx, cy);
                    let num = find_number(rx as usize, cy as usize, &grid, num_cols);
                    // println!("{}", num);
                    if num != -1{
                        neighbours.insert(num);
                    }
                }
            }
        }
        let mut sub_total = 1;
        if neighbours.len() == 2{
            println!("{}  {} neighs{:?}",r, c, neighbours);
            for n in &neighbours{
                sub_total *= n;
                
            }
            total += sub_total;
        }
    }

    return total.to_string();
}

fn find_number(r: usize, c: usize, grid: &Vec<Vec<char>>, num_cols: usize) -> i32{
    // println!("{} {}", r, c);
    if c+1 < num_cols && (c as i32-1)  >= 0{
        // if !grid[r][c+1].is_digit(10) && !grid[r][c-1].is_digit(10){
        //     return (grid[r][c].to_string()).parse().unwrap();
        // }

        // if grid[r][c+1].is_digit(10) && grid[r][c-1].is_digit(10){
        //     return (grid[r][c-1].to_string() + &grid[r][c].to_string() + &grid[r][c+1].to_string()).parse().unwrap();
        //     // return 100 * grid[r][c-1] as i32 + 10 * grid[r][c] as i32 + 100 * grid[r][c+1] as i32;
        // } 
        // if !grid[r][c+1].is_digit(10) && grid[r][c-1].is_digit(10) {
        //     if c as i32 -2 < 0 || !grid[r][c-2].is_digit(10) || !grid[r][c-1].is_digit(10){
        //         return (grid[r][c].to_string()).parse().unwrap();
        //     }
        //     if c as i32 -2 < 0 || !grid[r][c-2].is_digit(10){
        //         return (grid[r][c-1].to_string() + &grid[r][c].to_string()).parse().unwrap();
        //     }
        //     return (grid[r][c-2].to_string() + &grid[r][c-1].to_string() + &grid[r][c].to_string()).parse().unwrap();
        //     // return 100 * grid[r][c-2] as i32 + 10 * grid[r][c-1] as i32 + 100 * grid[r][c] as i32;
        // } 
        // if grid[r][c+1].is_digit(10) && !grid[r][c-1].is_digit(10) {
        //     if c+2 > num_cols || !grid[r][c+2].is_digit(10) || !grid[r][c+1].is_digit(10){
        //         return grid[r][c].to_string().parse().unwrap();
        //     }
        //     if c+2 > num_cols || !grid[r][c+2].is_digit(10){
        //         return (grid[r][c].to_string() + &grid[r][c+1].to_string()).parse().unwrap();
        //     }
        //     return (grid[r][c].to_string() + &grid[r][c+1].to_string() + &grid[r][c+2].to_string()).parse().unwrap();
        //     // return 100 * grid[r][c] as i32 + 10 * grid[r][c+1] as i32 + 100 * grid[r][c+2] as i32;
        // }
        let mut cl = c;
        let mut cr = c;

        while cl > 0 {
            if grid[r][cl].is_digit(10){
                cl -= 1;
            } else {
                break;
            }
        }
        // cl += 1;

        while cr < num_cols{
            if grid[r][cr].is_digit(10){
                cr += 1;
            } else {
                break;
            }
        }
        if !grid[r][cl].is_digit(10) {
            cl += 1
        }
        let num: String = grid[r][cl..cr].into_iter().collect();
        // println!("{:?}", num);
        return num.parse().unwrap(); //grid[r][cl..cr].to_string().parse().unwrap();
    }
    return -1;
}
