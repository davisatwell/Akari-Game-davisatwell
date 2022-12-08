package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class View implements FXComponent {
  private ClassicMvcController controller;
  private Model model;

  public View(ClassicMvcController ac, Model m) {
    this.controller = ac;
    this.model = m;
  }

  public Parent render() {
    BorderPane format = new BorderPane();
    FXComponent messageView = new MessageView(controller, model);
    FXComponent puzzleView = new PuzzleView(controller, model);
    FXComponent controlView = new ControlView(controller, model);

    format.setCenter(puzzleView.render());
    format.setLeft(messageView.render());
    format.setRight(controlView.render());

    return format;
  }
}
