package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Puzzle;

public interface AlternateMvcController {
  /** Handles the click action to go to the next puzzle */
  void clickNextPuzzle();

  /** Handles the click action to go to the previous puzzle */
  void clickPrevPuzzle();

  /** Handles the click action to go to a random puzzle */
  void clickRandPuzzle();

  /** Handles the click action to reset the currently active puzzle */
  void clickResetPuzzle();

  /** Handles the click event on the cell at row r, column c */
  void clickCell(int r, int c);
  // end of classic mvc design
  /** Returns true if the CORRIDOR cell at row r, column c is lit */
  boolean isLit(int r, int c);

  /** Returns true if the CORRIDOR cell at row r, column c is a lamp */
  boolean isLamp(int r, int c);

  /** Returns true if the CLUE cell at row r, column c is satisfied by nearby lamps */
  boolean isClueSatisfied(int r, int c);

  /** Returns true if the active puzzle is solved */
  boolean isSolved();

  /** Getter method for the active puzzle */
  Puzzle getActivePuzzle();
}
