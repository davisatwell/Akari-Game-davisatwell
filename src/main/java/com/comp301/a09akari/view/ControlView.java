package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent {
  private ClassicMvcController controller;
  private Model model;

  public ControlView(ClassicMvcController ac, Model m) {
    this.controller = ac;
    this.model = m;
  }

  @Override
  public Parent render() {
    VBox format = new VBox();
    Button resetPuzzle = new Button("Reset Puzzle.");
    resetPuzzle.setOnAction((ActionEvent event) -> controller.clickResetPuzzle());

    Button nextPuzzle = new Button("Next Puzzle ->");
    nextPuzzle.setOnAction((ActionEvent e) -> controller.clickNextPuzzle());

    Button prevPuzzle = new Button("Previous Puzzle <-");
    prevPuzzle.setOnAction((ActionEvent e) -> controller.clickPrevPuzzle());

    Button randPuzzle = new Button("Random");
    randPuzzle.setOnAction((ActionEvent e) -> controller.clickRandPuzzle());

    format.getChildren().addAll(prevPuzzle, nextPuzzle, randPuzzle, resetPuzzle);

    format.setAlignment(Pos.BASELINE_CENTER);
    return format;
  }
}
