# PROJECT_I_TRANDUCTHIEN_20210815

## Overview

This project is a visualization of common sorting algorithms for the Project_I subject. It is built using Maven and provides detail steps back and forth of sorting algorithms.

## Usage

To use this application, follow these steps:

 1. **Download the JAR File:**
   - Navigate to the `target` directory in the project root.
   - Find the JAR file with a name similar to `visualization-of-sorting-algorithms-2.0.jar`.

 2. **Run the JAR File:**
   - Open a terminal or command prompt.
   - Navigate to the directory containing the JAR file.
   - Run the following command:
     
    "visualization-of-sorting-algorithms-2.0.jar"

   - You should be able to use the program.

## Detail about package

 1. **Main Package:**
   - `main` class are in `MainFrame` and it run the constructor of `MainFrame` in `Event Dispatch Thread (EDT)`. MainFrame is a JFrame that contains `MainPanel` and `SetupPanel` which are Jpanel. 
   - `SetupPanel` contains number fields for user to enter and create the array, options for displaying algorithm, the user enters attributes and submits to get the boxes array, then chooses algorithm to get the sort button.
   - `MainPanel` contain the Box, in green color at first, and the `SORT, Backward, Forward, PAUSE` buttons and it also contain the `Boxes` list, which is the current status, and the program's `Timer` .
   - `Box` class, which is the boxes that represent number in our sorting array, each Box has x, y, width, height, a common distance, value representing and color.
   - There are `"Moving" functions` in Box class (`MergeBoxes`, `InsertBox`, `SwapBox`), they first do the changing position of Box objects inside the Boxes list only, then they add to the MainPanel's `Timer` the needed ActionListener so the UI can be changed accordingly. For each algorithm there will be different usages of those functions, and those "Big Moving" functions 

 2. **Algorithms Package:**
   - Contain 5 algorithms, namingly Selection, Insertion, Bubble, Merge and Quick Sort, in 5 class. Each class have a static function inside, which takes the number array, the `statusArrays` and the `actionArrays` in to generate the solution in a number of steps with theirs corresponding status of the array.
   - `statusArrays` is a list of statusArray, each array represents a status of the Box array, the first array will be {0,1,2,...n},
   and each box will be correlated with a number base on its original position. An originBoxes list if created in MainPanel so it can be used to get the reference to any box base on its corresponding number(which is its original position).
   - `actionArrays` is a list of actionArray, each array represents a step, like which box to insert, to merge or to swap with which, and they are int[], with each element being the position to make action in the current `Boxes` array

 3. **MovingListener Package:**
   - Contain 3 ActionListeners(`Insert, Merge, Swap`), when add it to a timer, after a certain ticks, the UI will do the job of displaying one full step. They contain various "Small Moving" functions inside, that move the Box objects in small distance with each tick of the timer.
   - These ActionListeners are the one that those `"Moving" functions` in `Box` class used to add to the `MainPanel`'s `Timer`

 4. **Listenner Package:**
   - Contain 5 realizations of IcustomListener interface (which is only ActionListeners with a MainPanel inside)
   - Those listeners listen to `SORT` button, then disable the run the static functions in `Algorithms Package`, generate the solution in a number of steps into `actionArrays` list, and theirs corresponding status of the array into `statusArrays`.
   - After that, a `SwingWorker` is innitiated, which run a while loop through all the step, using `index`(start with 0) as iterator, make action (in which they call the `"Moving" functions` in Box class) according to the `actionArrays.get(index)` array , and also checks for Pausing in each loop.
   - If the user press PAUSE, the `SwingWorker` is cancelled, and the program stop at the current step, or index, if PAUSE is pressed again, another `SwingWorker` is innitiated, continues the work.
   - When paused, user can use `Backward`, `Forward` button, which will decrease/increase `index` by 1, and also reupdate the Boxes array and the UI to match with the `statusArrays.get(index)` array.

 5. **OptionListener Package:**
   - Contain OptionListener, a realization of IcustomListener, that listens to the JComboBox that give the user options to choose algorithm, then set the `SORT` button in `MainPanel` accordingly.



## Requirements:
Make sure you have Java installed on your system.

- Java 17 or later: `Download Java`(https://www.oracle.com/java/technologies/javase-downloads.html)

## Contributing

If you'd like to contribute to this project, follow these steps:

 1. Fork the repository.
 2. Create a new branch for your feature or bug fix.
 3. Make your changes and commit them.
 4. Push your changes to your fork.
 5. Submit a pull request.

## License

- No license.

## Acknowledgments

- Mention any external libraries, tools, or resources you used and give credit to their authors.

