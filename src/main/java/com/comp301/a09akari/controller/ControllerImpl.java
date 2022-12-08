package com.comp301.a09akari.controller;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import java.util.Random;

public class ControllerImpl implements ClassicMvcController {
  private Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  public void clickNextPuzzle() {
    if (model.getPuzzleLibrarySize() - 1 == model.getActivePuzzleIndex()) {
      model.setActivePuzzleIndex(0);
    } else {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    }
  }

  public void clickPrevPuzzle() {
    if (0 == model.getActivePuzzleIndex()) {
      model.setActivePuzzleIndex(model.getPuzzleLibrarySize() - 1);
    } else {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    }
  }

  public void clickRandPuzzle() {
    Random randomPuzzle = new Random();
    int randomPuzzleIndex = randomPuzzle.nextInt(model.getPuzzleLibrarySize());
    while (model.getActivePuzzleIndex() == randomPuzzleIndex) {
      randomPuzzleIndex = randomPuzzle.nextInt(model.getPuzzleLibrarySize());
    }
    model.setActivePuzzleIndex(randomPuzzleIndex);
  }

  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  public void clickCell(int r, int c) {
    if (model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      if (model.isLamp(r, c)) {
        model.removeLamp(r, c);
      } else {
        model.addLamp(r, c);
      }
    }
  }
}
