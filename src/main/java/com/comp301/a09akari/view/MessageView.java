package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class MessageView implements FXComponent {
  private ClassicMvcController controller;
  private Model model;

  public MessageView(ClassicMvcController ac, Model m) {
    this.controller = ac;
    this.model = m;
  }

  @Override
  public Parent render() {
    StackPane format = new StackPane();
    Label message;
    int i = model.getActivePuzzleIndex();
    int totalPuzzles = model.getPuzzleLibrarySize();

    if (model.isSolved()) {
      message = new Label("Congrats! You solved Puzzle " + ((i++)+1) + " out of " + (totalPuzzles) + " ");
    } else {
      message =
          new Label("Sorry, Puzzle " + ((i++)+1) + " out of " + (totalPuzzles) + " has not been solved.");
    }
    format.getChildren().add(message);
    return format;
  }
}
