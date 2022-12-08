package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private PuzzleLibrary library;
  private Puzzle activePuzzle;
  private int[][] lampBoard;
  private int counter;
  private List<ModelObserver> observers = new ArrayList<>();

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }
    this.library = library;
    this.counter = 0;
    this.activePuzzle = library.getPuzzle(counter);
    lampBoard = new int[activePuzzle.getHeight()][activePuzzle.getHeight()];
  }

  public void addLamp(int r, int c) {
    if (r < 0 || c < 0 || r >= activePuzzle.getHeight() || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lampBoard[r][c] = 1;
    notifyObservers();
  }

  public void removeLamp(int r, int c) {
    if (r < 0 || c < 0 || r >= activePuzzle.getHeight() || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lampBoard[r][c] = 0;
    notifyObservers();
  }

  public boolean isLit(int r, int c) {
    if (r < 0 || c < 0 || r >= activePuzzle.getHeight() || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (lampBoard[r][c] == 1) {
      return true;
    }
    int left = c;
    int right = c;
    int up = r;
    int down = r;

    while (left >= 0) {
      if (activePuzzle.getCellType(r, left) != CellType.CORRIDOR) {
        break;
      }
      if (lampBoard[r][left] == 1) {
        return true;
      }
      left--;
    }

    while (right < activePuzzle.getWidth()) {
      if (activePuzzle.getCellType(r, right) != CellType.CORRIDOR) {
        break;
      }
      if (lampBoard[r][right] == 1) {
        return true;
      }
      right++;
    }

    while (up >= 0) {
      if (activePuzzle.getCellType(up, c) != CellType.CORRIDOR) {
        break;
      }
      if (lampBoard[up][c] == 1) {
        return true;
      }
      up--;
    }

    while (down < activePuzzle.getHeight()) {
      if (activePuzzle.getCellType(down, c) != CellType.CORRIDOR) {
        break;
      }
      if (lampBoard[down][c] == 1) {
        return true;
      }
      down++;
    }

    return false;
  }

  public boolean isLamp(int r, int c) {
    if (r < 0 || c < 0 || r >= activePuzzle.getHeight() || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    if (lampBoard[r][c] == 1) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || c < 0 || r >= activePuzzle.getHeight() || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (!isLamp(r, c)) {
      throw new IllegalArgumentException();
    }
    // checking all directions for lamp or wall/clue
    for (int i = r - 1; i >= 0; i--) {
      if ((activePuzzle.getCellType(i, c) == CellType.CLUE)
          || (activePuzzle.getCellType(i, c) == CellType.WALL)) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }
    for (int i = c - 1; i >= 0; i--) {
      if ((activePuzzle.getCellType(r, i) == CellType.CLUE)
          || (activePuzzle.getCellType(r, i) == CellType.WALL)) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    for (int i = r + 1; i < activePuzzle.getHeight(); i++) {
      if ((activePuzzle.getCellType(i, c) == CellType.CLUE)
          || (activePuzzle.getCellType(i, c) == CellType.WALL)) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }
    for (int i = c + 1; i < activePuzzle.getWidth(); i++) {
      if ((activePuzzle.getCellType(r, i) == CellType.CLUE)
          || (activePuzzle.getCellType(r, i) == CellType.WALL)) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    return false;
  }

  public Puzzle getActivePuzzle() {
    return activePuzzle;
  }

  public int getActivePuzzleIndex() {
    return counter;
  }

  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= getPuzzleLibrarySize()) {
      throw new IndexOutOfBoundsException();
    } else {
      this.counter = index;
      this.activePuzzle = library.getPuzzle(index);
      lampBoard = new int[activePuzzle.getHeight()][activePuzzle.getWidth()];
      notifyObservers();
    }
  }

  public int getPuzzleLibrarySize() {
    return library.size();
  }

  public void resetPuzzle() {
    lampBoard = new int[activePuzzle.getHeight()][activePuzzle.getWidth()];
    notifyObservers();
  }

  public boolean isSolved() {
    for (int row = 0; row < activePuzzle.getHeight(); row++) {
      for (int col = 0; col < activePuzzle.getWidth(); col++) {
        if (activePuzzle.getCellType(row, col) == CellType.CLUE) {
          if (!isClueSatisfied(row, col)) {
            return false;
          }
        } else if (activePuzzle.getCellType(row, col) == CellType.CORRIDOR) {
          if (isLamp(row, col)) {
            if (isLampIllegal(row, col)) {
              return false;
            }
          }
          if (!isLit(row, col)) {
            return false;
          }
        }
      }
    }

    return true;
  }

  public boolean isClueSatisfied(int r, int c) {
    if (r < 0 || c < 0 || c >= activePuzzle.getWidth() || r >= activePuzzle.getHeight()) {
      throw new IndexOutOfBoundsException();
    } else if (activePuzzle.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int clue = activePuzzle.getClue(r, c);
    if (r - 1 >= 0) {
      if (activePuzzle.getCellType(r - 1, c) == CellType.CORRIDOR) {
        if (isLamp(r - 1, c)) {
          clue--;
        }
      }
    }
    if (c - 1 >= 0) {
      if (activePuzzle.getCellType(r, c - 1) == CellType.CORRIDOR) {
        if (isLamp(r, c - 1)) {
          clue--;
        }
      }
    }
    if (c + 1 < activePuzzle.getWidth()) {
      if (activePuzzle.getCellType(r, c + 1) == CellType.CORRIDOR) {
        if (isLamp(r, c + 1)) {
          clue--;
        }
      }
    }
    if (r + 1 < activePuzzle.getHeight()) {
      if (activePuzzle.getCellType(r + 1, c) == CellType.CORRIDOR) {
        if (isLamp(r + 1, c)) {
          clue--;
        }
      }
    }

    return clue == 0;
  }

  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  public void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }
}
