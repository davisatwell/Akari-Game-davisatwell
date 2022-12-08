package com.comp301.a09akari;

public class SamplePuzzles {
  // The following mapping between cells and integers is used for the puzzles in this file
  //   0 <--> Clue cell with value "0"
  //   1 <--> Clue cell with value "1"
  //   2 <--> Clue cell with value "2"
  //   3 <--> Clue cell with value "3"
  //   4 <--> Clue cell with value "4"
  //   5 <--> Wall with no clue
  //   6 <--> Empty corridor

  // Note: "Easy" 7x7 Puzzle ID 930887 from https://www.puzzle-light-up.com/specific.php
  public static int[][] PUZZLE_01 = {
    {6, 6, 6, 6, 1, 6, 6},
    {6, 6, 6, 5, 6, 6, 6},
    {0, 6, 6, 6, 6, 6, 6},
    {6, 5, 6, 6, 6, 4, 6},
    {6, 6, 6, 6, 6, 6, 5},
    {6, 6, 6, 2, 6, 6, 6},
    {6, 6, 5, 6, 6, 6, 6},
  };

  // Note: This is the puzzle shown on the wikipedia page for Akari
  // See: https://en.wikipedia.org/wiki/Light_Up_(puzzle)
  public static int[][] PUZZLE_02 = {
    {5, 6, 6, 5, 6, 6, 6, 6, 6, 5},
    {6, 6, 6, 6, 6, 6, 6, 5, 6, 6},
    {6, 3, 6, 6, 6, 6, 0, 6, 6, 6},
    {6, 6, 2, 6, 6, 5, 6, 6, 6, 1},
    {6, 6, 6, 1, 0, 5, 6, 6, 6, 6},
    {6, 6, 6, 6, 1, 5, 5, 6, 6, 6},
    {5, 6, 6, 6, 2, 6, 6, 2, 6, 6},
    {6, 6, 6, 5, 6, 6, 6, 6, 5, 6},
    {6, 6, 1, 6, 6, 6, 6, 6, 6, 6},
    {0, 6, 6, 6, 6, 6, 1, 6, 6, 0},
  };

  // Note: "Easy" 7x7 Puzzle ID 9070893 from https://www.puzzle-light-up.com/specific.php
  public static int[][] PUZZLE_03 = {
    {6, 6, 5, 6, 6, 6, 6},
    {6, 5, 6, 6, 6, 4, 6},
    {6, 6, 6, 6, 6, 6, 5},
    {6, 6, 6, 6, 6, 6, 6},
    {3, 6, 6, 6, 6, 6, 6},
    {6, 2, 6, 6, 6, 5, 6},
    {6, 6, 6, 6, 0, 6, 6},
  };

  // Note: "Normal" 10x10 Puzzle ID 6424638 from https://www.puzzle-light-up.com/specific.php
  public static int[][] PUZZLE_04 = {
    {6, 1, 6, 6, 6, 6, 5, 6, 6, 6},
    {6, 6, 6, 6, 6, 6, 6, 6, 6, 5},
    {6, 6, 5, 5, 6, 6, 6, 2, 6, 6},
    {2, 6, 6, 5, 6, 6, 1, 5, 6, 6},
    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
    {6, 6, 5, 2, 6, 6, 0, 6, 6, 1},
    {6, 6, 2, 6, 6, 6, 5, 1, 6, 6},
    {2, 6, 6, 6, 6, 6, 6, 6, 6, 6},
    {6, 6, 6, 5, 6, 6, 6, 6, 5, 6},
  };

  // Note: Your app should work with non-square puzzles like the one here
  public static int[][] PUZZLE_05 = {
    {6, 6, 5, 6, 6, 6},
    {6, 5, 6, 6, 6, 3},
    {6, 6, 6, 6, 6, 6},
    {6, 6, 6, 6, 6, 6},
    {3, 6, 6, 6, 6, 6},
    {6, 2, 6, 6, 6, 6},
    {6, 6, 6, 6, 0, 6},
  };
}
