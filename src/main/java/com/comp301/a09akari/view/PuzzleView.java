package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class PuzzleView implements FXComponent {
  private ClassicMvcController controller;
  private Model model;

  public PuzzleView(ClassicMvcController ac, Model m) {
    this.controller = ac;
    this.model = m;
  }

  @Override
  public Parent render() {
    GridPane board = new GridPane();
    for (int r = 0; r < model.getActivePuzzle().getHeight(); r++) {
      for (int c = 0; c < model.getActivePuzzle().getWidth(); c++) {
        Button spot;
        spot = new Button();
        if (model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
          spot = new Button("  ");
          // set all sizes to ensure fixed size
          spot.setMinSize(40, 40);
          spot.setMaxSize(40, 40);
          board.add(spot, r, c);
          final int r2 = r;
          final int c2 = c;
          spot.setOnAction((ActionEvent e) -> controller.clickCell(r2, c2));
          if (model.isLit(r, c)) {
            spot.setStyle("-fx-background-color: YELLOW;");
          } else {
            spot.setStyle("-fx-background-color: WHITE;");
          }
          if (model.isLamp(r, c)) {
            spot.setStyle("-fx-background-color: GREY;");
          }
          if (model.isLamp(r, c) && model.isLampIllegal(r, c)) {
            spot.setStyle("-fx-background-color: ORANGE;");
          }
        } else if (model.getActivePuzzle().getCellType(r, c) == CellType.WALL) {
          spot = new Button("   ");
          spot.setStyle("-fx-background-color: BLACK;");
          spot.setMinSize(40, 40);
          spot.setMaxSize(40, 40);
          board.add(spot, r, c);
        } else if (model.getActivePuzzle().getCellType(r, c) == CellType.CLUE) {
          spot = new Button("" + model.getActivePuzzle().getClue(r, c));
          spot.setStyle("-fx-background-color: BLUE;");
          spot.setTextFill(Color.WHITE);
          spot.setMinSize(40, 40);
          spot.setMaxSize(40, 40);
          if (model.isClueSatisfied(r, c)) {
            spot.setTextFill(Color.BLACK);
            spot.setStyle("-fx-background-color: GREEN;");
          }
          board.add(spot, r, c);
        }
      }
    }
    return board;
  }
}
