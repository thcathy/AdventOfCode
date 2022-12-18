package com.adventofcode.year2022;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;

public class Day14Part2 {
    final static String inputFile = "2022/day14.txt";

    public static void main(String... args) throws IOException {
        Day14Part2 solution = new Day14Part2();
        solution.run();
    }

    void run() throws IOException {
        var lines = Resources.readLines(ClassLoader.getSystemResource(inputFile), Charsets.UTF_8);
        var result = totalRestSand(parse(lines));
        System.out.println("How many units of sand come to rest? " + result);
    }

    int totalRestSand(Set<Position> blocks) {
        var deepestRocks = deepestRocks(blocks);
        int total = 1;
        var start = new Position(500, 0);
        while (!blocks.contains(start)) {
            nextSand(blocks, deepestRocks);
            total++;
        }
        return total-1;
    }

    void nextSand(Set<Position> blocks, int deepestRocks) {
        var sand = new Position(500, 0);
        var floorY = deepestRocks + 2;
        while (true) {
            var next = nextPosition(sand, blocks, floorY);
            if (next == sand) {
                blocks.add(next);
                return;
            }
            sand = next;
        }
    }

    int deepestRocks(Set<Position> blocks) {
        return blocks.stream().mapToInt(p -> p.y).max().getAsInt();
    }

    Position nextPosition(Position sand, Set<Position> blocks, int floor) {
        return Stream.of(
                        new Position(sand.x, sand.y+1),
                        new Position(sand.x-1, sand.y+1),
                        new Position(sand.x+1, sand.y+1)
                )
                .filter(p -> !blocks.contains(p) && p.y < floor).findFirst().orElse(sand);
    }



    Set<Position> parse(List<String> inputs) {
        var rocks = new HashSet<Position>();
        for (String input : inputs) {
            var positions = input.split(" -> ");
            var start = Position.parse(positions[0]);
            for (int i = 1; i < positions.length; i++) {
                var end = Position.parse(positions[i]);
                if (start.x == end.x) {
                    if (start.y < end.y) {
                        for (int y = start.y; y <= end.y; y++)
                            rocks.add(new Position(start.x, y));
                    } else {
                        for (int y = end.y; y <= start.y; y++)
                            rocks.add(new Position(start.x, y));
                    }
                } else {
                    if (start.x < end.x) {
                        for (int x = start.x; x <= end.x; x++)
                            rocks.add(new Position(x, start.y));
                    } else {
                        for (int x = end.x; x <= start.x; x++)
                            rocks.add(new Position(x, start.y));
                    }
                }
                start = end;
            }
        }
        return rocks;
    }

    record Position(int x, int y) {
        static Position parse(String s) {
            var input = s.split(",");
            return new Position(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }
    }

    @Test
    public void unitTest() throws Exception {
        var lines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("2022/day14_test.txt").toURI()));
        var rocks = parse(lines);
        assertEquals(20, rocks.size());
        assertEquals(93, totalRestSand(parse(lines)));
    }
}
