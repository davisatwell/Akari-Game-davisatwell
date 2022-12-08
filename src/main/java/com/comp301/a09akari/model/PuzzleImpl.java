package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  public int getWidth() {
    return this.board[0].length;
  }

  public int getHeight() {
    return this.board.length;
  }

  public CellType getCellType(int r, int c) {
    if (this.board[r][c] > 6 || this.board[r][c] < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.board[r][c] == 6) {
      return CellType.CORRIDOR;
    }
    if (this.board[r][c] == 5) {
      return CellType.WALL;
    }
    if (this.board[r][c] <= 4 && this.board[r][c] >= 0) {
      return CellType.CLUE;
    }
    return null;
  }

  public int getClue(int r, int c) {
    if (this.board[r][c] == 5 || this.board[r][c] == 6) {
      throw new IllegalArgumentException();
    }
    if (this.board[r][c] > 4 || this.board[r][c] < 0) {
      throw new IndexOutOfBoundsException();
    }
    return this.board[r][c];
  }
}
