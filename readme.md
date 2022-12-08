# a09-akari

## Introduction

In this assignment, you will use the model-view-controller design pattern together with the JavaFX UI library to design a complete, functioning GUI implementation of the single-player logic puzzle akari.

If you've never heard of akari before, I highly suggest starting by checking out [the akari Wikipedia page](https://en.wikipedia.org/wiki/Light_Up_(puzzle)). After you do that, [try solving a few akari puzzles](https://www.puzzle-light-up.com/) yourself to make sure you've got the hang of the game mechanics.

Users must be able to use your app to play akari and solve a variety of built-in puzzles. The functionality required for full credit is listed at the end of this readme. In terms of visual style, you may follow the aesthetic of the website linked above or you may come up with your own style. Feel free to theme your user interface, move components around in different locations, add new features, or make other significant changes in the way you see fit.

This assignment is unique because it has a hand-graded portion and an autograded portion. **The autograder verifies only that your model is correct, but does not check your controller or view.** The controller and view will be hand-graded by a COMP 301 learning assistant (LA) after the assignment due date. The LA will run your application and try playing a few games using your user interface. Your grade for that portion of the assignment will be based off of whether the LA has a good user experience while using your application. This means you won't get feedback about your controller and view implementations until after the assignment due date.

Once you're familiar with how akari works, take a look at the starter code defined in the repository. Three packages have been created to help you organize your code according to the model-view-controller pattern. In these packages, a few starter interfaces and classes have been defined to help you structure your app.

**It is not strictly necessary to use all the provided starter interfaces.** Most of them are just there to give your app some structure and help you plan your code. If you would rather come up with your own design, there are a few rules that your code must follow so that your code can be autograded correctly. First, you **must** provide a `ModelImpl` and `PuzzleImpl` class as specified below. Second, running the `main()` method of the provided `Main` class **must** launch your GUI (this is how the LAs will launch your app for manual grading). Other than these two requirements, you are free to safely ignore the rest of the provided interfaces.



## Puzzles

The `Puzzle` interface represents an empty akari puzzle to be solved by the user. A `Puzzle` object should encapsulate a two-dimensional grid to represent the puzzle board. Each cell on the grid is one of three types: corridor, wall, or clue (see the enumeration defined in `CellType.java`). If a cell is a clue, then it is associated with a number between 0-4. The number indicates the number of lamps that should be placed by the user adjacent to the cell to solve the puzzle.

### PuzzleImpl

Create a new class in the `model` package named `PuzzleImpl` which implements the `Puzzle` interface. Your `PuzzleImpl` class should have a single constructor with the following signature:
```java
public PuzzleImpl(int[][] board) {
  // Your constructor code here
}
```

The constructor takes as input a two-dimensional integer array named `board`. You should assume that the first dimension of `board` represents the rows of the puzzle and the second dimension represents the columns. For example, `board[0][0]` represents the top-left-most cell in the puzzle, `board[0][1]` represents the cell directly to the right, and `board[1][0]` represents the cell directly below.

The values stored in the `board` array describe the cell contents according to the following mapping:
* `0` indicates the cell is a clue cell with value "0"
* `1` indicates the cell is a clue cell with value "1"
* `2` indicates the cell is a clue cell with value "2"
* `3` indicates the cell is a clue cell with value "3"
* `4` indicates the cell is a clue cell with value "4"
* `5` indicates the cell is a wall cell
* `6` indicates the cell is a corridor cell

See the `SamplePuzzles.java` file for 5 example puzzle boards written out using this encoding.

> This single-digit integer encoding for writing out puzzle boards was chosen simply because it takes few characters to type out a board.


### PuzzleLibraryImpl

The provided `PuzzleLibrary` interface represents a list of `Puzzle` objects to be solved by the user. This interface will be used by the model in the next section. A simple `PuzzleLibraryImpl` class is provided with the starter code, which is a simple list-backed implementation of `PuzzleLibrary`. You need not make changes to the `PuzzleLibrary.java` or `PuzzleLibraryImpl.java` files, but will need to use them below to instantiate `ModelImpl`.



## Model

The `Model` interface represents the model of MVC, and therefore contains all data necessary to display the current state of the application. In particular, the `Model` for akari should encapsulate the following concepts:

1. A `PuzzleLibrary` instance which stores available akari puzzles for the user to solve
2. A way to identify which puzzle is currently active; for example, maybe an index into the puzzle library
3. The locations of all lamps which have been added by the user to the active puzzle (lamp locations are not stored in the `Puzzle` object since they are not part of the puzzle itself, but are instead marked in by the user)
4. An algorithm for determining whether the active puzzle has been solved
5. A `List<ModelObserver>` of active model observers in support of the observer design pattern


### ModelImpl

You must create a class named `ModelImpl` in the `model` package which implements the `Model` interface. This is required for your submission to be autograded. The `ModelImpl` class must expose a constructor with the following signature:
```java
public ModelImpl(PuzzleLibrary library) {
  // Your constructor code here
}
```
Initially, the active puzzle should be set to the first one (index 0) in the puzzle library.

To implement the model's `isSolved()` method, you'll have to devise an algorithm to check that (1) all corridors in the board are lit, (2) all clues are satisfied, and (3) no lamps are illegally placed. This can be tricky and will probably take some time to get right.

The `ModelImpl` class should be a "subject" with respect to the observer design pattern. This is because your view class will likely register one or more active observers of the model, and will re-render itself in response to model changes. See the `ModelObserver` interface below for more information about how to implement this.


### ModelObserver

the `ModelObserver` interface defines a single method, `update()`, and is used together with the `ModelImpl` class to implement the observer design pattern.

`ModelImpl` should therefore notify its active observers whenever *any* `Model` field value is changed. **Hint:** Exactly 4 of the 14 methods involve manipulating the state of the `Model` instance (not including `addObserver()` and `removeObserver()`). These 4 methods should therefore trigger the observer notification process.

You need not implement the `ModelObserver` interface to receive full credit from the autograder. However, you may wish to implement it in your `View` class so your view can respond to model changes.






## Controller

All code related to your application's controller should be placed in the `controller` package.

Remember, the controller in MVC is intended to act as the "glue" between the model and the view. **You are free to implement your controller as you see fit; the controller is not graded by the autograder.** Instead, the controller will be hand-graded when a learning assistant tries to play your game by running the `Main.main()` method.

To help you get started, two interfaces named `ClassicMvcController` and `AlternateMvcController` are included with the starter code. You may choose one of these interfaces to help structure your controller class, based on whether you intend to use "classic" or "alternate" MVC for your application. Alternatively, you may decide not to use either of these interfaces, and instead design the controller entirely on your own.

> Since the controller is not autograded, you may freely make changes to the `ClassicMvcController` and/or the `AlternateMvcController` interfaces.

To use `ClassicMvcController` or `AlternateMvcController`, first create a new class in the `controller` package named `ControllerImpl` which implements your chosen interface. Your `ControllerImpl` class should encapsulate a `Model` object as an instance field. Therefore, add a constructor to the `ControllerImpl` class with the following signature:
```java
public ControllerImpl(Model model) {
  // Constructor code goes here
}
```

Most of the `Controller` methods will simply be delegated/forwarded directly to the encapsulated `Model` instance. However, a few methods like `clickNextPuzzle()`, `clickPrevPuzzle`, and `clickRandPuzzle()` may require a few lines of code to correctly control the model.



## View

All code related to the application's view should be placed in the `view` package.

Remember, the `view` in MVC is intended to hold all code related to the GUI. **You are free to implement your view as you see fit; the view is not graded by the autograder.** Instead, the view will be hand-graded when a learning assistant tries to play your game by running the `Main.main()` method.

Regardless of the structure that you use for your view, you are required to use JavaFX as your GUI library. Therefore, the code in the `view` package will primarily create and manipulate JavaFX objects. To help you get started with a good structure for your app, one class (`AppLauncher`) and one interface (`FXComponent`) are provided.


### AppLauncher

By default, this class is the launching point of your application (although you can change that if you'd like). The `Main` class is set up to forward to `AppLauncher`, which extends `Application` and therefore launches the JavaFX GUI.

To write the view, you'll need to fill in the `start()` method in `AppLauncher` to actually set up and create your UI. Inside the `start()` method, you should create a `Model` instance and a `Controller` instance.

To instantiate `ModelImpl`, you need to pass a `PuzzleLibrary` object to the constructor. Use the puzzle data provided in the `SamplePuzzles` class and the provided `PuzzleLibraryImpl` class to construct and populate a suitable `PuzzleLibrary` which can be passed to the model. Add all puzzles from `SamplePuzzles` to the `PuzzleLibrary`. In addition, you may choose to encode and include more puzzles if you want to support a larger puzzle library.


### FXComponent

Although you could put all the UI generation code directly in the `start()` method of `AppLauncher`, this might not be a good idea since it would clutter the method with a lot of JavaFX code. Instead, a better idea is to encapsulate each section of the UI in a separate class.

In GUI programming, it's common to build compound UI components which can be composed together. To this end, a suggested interface named `FXComponent` has been provided. You can use `FXComponent` to represent a compound UI component. For instance, you might decide to create one `FXComponent` class called `PuzzleView` to display the clues and the game board inside a `GridPanel`. You may then make another `FXComponent` class called `ControlView` to display the puzzle controls, including buttons, to move through the puzzle library. Finally, a third `FXComponent` class called `MessageView` may show the "success" message when the user successfully finishes the puzzle. All three of these `FXComponent` objects could then be instantiated and composed together in the `start()` method.

The `FXComponent` interface has just one method, named `render()`. `render()` is a (non-`static`) factory method which constructs the compound component's UI tree. Calling `render()` should instantiate and return a new JavaFX `Parent` object, representing the current, up-to-date scene graph for that section of the UI. Each time `render()` is called on a `FXComponent`, the `Parent` should be completely re-built from scratch, using the controller to reflect the latest state values of the application. Each `FXComponent` class should therefore encapsulate a reference to the `Controller` or `Model` for retrieving the current application state (depending on whether "classic" or "alternate" MVC is being used).

The view must be re-rerendered every time a value in the model is changed. To accomplish this, the view must register an active `ModelObserver` to observe the model instance. The easiest way to do this is by using an anonymous class or a lambda expression directly in the `start()` method of the `AppLauncher` class, since that is where you have a reference to the model. When a model change occurs, the `render()` methods should be called on each `FXComponent` instance, and the resulting new `Parent` objects should be inserted in the `Scene`, while being careful to clear any old, "stale" UI components.

Finally, the view must react to user actions, such as clicking on certain user interface elements. Do this by registering observers on the relevant JavaFX UI component events. Sometimes, an application state change is necessary in response to a user action. For example, if the user clicks the "next puzzle" button, the model must be instructed to go to the next puzzle. Make use of the controller's methods to do this. By utilizing the controller, you will enforce separation of concerns between your model and view, and enforce that the controller is an intermediary between the two.



## Running the application

This assignment uses Maven as a build manager, and JavaFX as a GUI library. Since JavaFX is an external library, it has to be included in the build process in order for the application to successfully run. JavaFX has already been added as a Maven dependency to the POM file.

To run the application with Maven in IntelliJ, follow these steps:

1. Click the vertical "Maven" expansion tab which is on the right side of the IntelliJ window.

2. Expand the "Plugins" folder.

3. Expand the "javafx" folder.

4. Double-click "javafx:run" to run the project.

**Important: This is exactly the same process that a learning assistant will use to launch your app when they manually grade it. Make sure your app can be launched using this process in your final submission to Gradescope.**



## Requirements

For full credit, your GUI application must support the following features:

1. **Cells:** A section of the UI displays the active puzzle as a grid with the correct number of rows and columns. Adjacent cells are visually distinguishable from one another. The three types of cells are visually distinct from one another: clue cells, wall cells, and corridor cells. User-placed lamps are visible on their corridor cells. Lit and unlit corridors are visually distinct from one another.

2. **Lamps:** Clicking on any corridor cell toggles whether a lamp is located at that cell. Wall and clue cells cannot contain lamps. An illegally-placed lamp (i.e. one which is in direct view of another lamp) is visually distinct from a legally-placed lamp.

3. **Light:** Every lamp lights the corridor cells in straight lines above, below, to the left, and to the right of the lamp. Light cast from a lamp stops when either a wall cell or the edge of the board is encountered. Only corridor cells can be lit.

4. **Walls:** Lamps cannot be placed on wall cells, and wall cells are visually unaffected by the presence of light.

5. **Clues:** Clue cells are visually distinct from corridor and wall cells. The clue number is visible on the clue cell. Lamps cannot be placed on clue cells. A "satisfied" clue cell (i.e. one which has the correct number of lamps placed adjacent to it) is visually distinct from an "unsatisfied" clue cell.

6. **Solving:** At any point, if the active puzzle is currently solved by the user, a message is displayed on the screen to let the user know that they completed the puzzle. For full credit, this message cannot be displayed in the console; it must be part of the GUI. The puzzle is "solved" if all corridors are lit, all clues are satisfied, and no illegal lamps are placed.

7. **Reset button:** The UI includes a clearly visible and labeled "reset" button that removes all lamps from the board when clicked so the user can start from a blank state.

8. **Puzzle library:** The starter code contains a pre-coded library of 5 "sample" puzzles to solve. The UI must provide clearly visible and labeled buttons to go to the next puzzle, to go to the previous puzzle, and to jump to a random puzzle. The "previous" button must not cause an uncaught exception if the user accidentally presses it on the first puzzle. Similarly, the "next" button must not cause an uncaught exception if the user accidentally presses it on the last puzzle.

9. **Puzzle index:** The index of the active puzzle and the total number of available puzzles are clearly displayed as part of the GUI. The displayed index must start from one, not zero. For example, the first puzzle (at index 0) might be displayed to the user as "puzzle 1 of 5."

10. **Board size**: The app supports arbitrary-sized boards with different widths and heights. To demonstrate this functionality, the provided pre-coded library of puzzles includes puzzles of at least two different sizes.
