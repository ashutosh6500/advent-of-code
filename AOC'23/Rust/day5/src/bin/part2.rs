// use std::env;
fn main() {
    // env::set_var("RUST_BACKTRACE", "full");

    let input = include_str!("./input5.txt");
    let output = part2(input);
    dbg!(output);
}

fn part2(input: &str) -> String {
    let mut lines = input.lines();

    let seeds: Vec<i64> = lines.nth(0).unwrap().split_once(": ").unwrap().1.split(' ').map(|seed| seed.parse().unwrap()).collect();
    let mut char_maps: Vec<CharMap> = Vec::new();

    let mut curmap = CharMap::default();
    for line in lines {
        if line.contains(":"){
            char_maps.push(curmap);
            curmap = CharMap::default();
            continue;
        }
        if !line.is_empty(){
            let nums: Vec<i64> = line.split(' ').map(|num| num.parse().unwrap()).collect();
            curmap.add_map(nums[0], nums[1], nums[2]);
        }
    }

    if !curmap.map.is_empty(){
        char_maps.push(curmap);
    }
    char_maps.remove(0);

    let seed_ranges = seeds.chunks(2).map(|vec| std::ops::Range{start: vec[0], end: vec[0] + vec[1]},).collect::<Vec<_>>();
    let mut location = 1_i64;
    loop {
        let mut cur = location;
        for char_map in char_maps.iter().rev(){
            cur = char_map.rev_apply_map(cur)
        }
        for sr in seed_ranges.iter(){
            if sr.contains(&cur){
                return location.to_string();
            }
        }
        location += 1;
    }

}

#[derive(Debug, Default)]
struct CharMap{
    map: Vec<SMap>
}

impl CharMap{
    fn add_map(&mut self, dest: i64, src: i64, len: i64){
        self.map.push(SMap{
            range: std::ops::Range{
                start: src,
                end: src+len
            },
            delta: dest - src,
        });
    }

    fn apply_map(&self, val: i64) -> i64 {
        for map in &self.map{
            if map.range.contains(&val){
                return val + map.delta;
            }
        }
        return val;
    }

    fn rev_apply_map(&self, val:i64) -> i64 {
        for map in &self.map{
            let rev = val - map.delta;
            if map.range.contains(&rev){
                return rev;
            }
        }
        return val;
    }
}

#[derive(Debug, Default)]
struct SMap{
    range: std::ops::Range<i64>,
    delta: i64
}
