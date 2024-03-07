# Sudoku-Solver

## Description
As the name already indicates, the sudoku-solver is able to solve sudoku's. The time it takes to solve a sudoku is displayed after the solution in the terminal. 

## Usage
To use it, you have to add your sudoku as a String of numbers to the main in the SudokuSolver.java file. Replace the current sudokuString with your String. Use 0 for empty spots and make sure your String is 81 characters long.

## Architecture
The sudoku solver programm is made up of three different classes; 
1. Sudoku class: This uses the sudokuString into a sudoku board. This board is a twodimensional array with rows and colomns. Another twodimensional array is used to keep track of which spots on the board have been solved. Additionally, the actually solver, called checker, is also here.
2. Coordinate class: This class is used to keep track of where every number is located on the board. 
3. SudokuSolver class: Has the main method and brings everything together. Should be used to run the programm.

## Validation
Sudokus that have already been tested:
1. 000820090500000000308040007100000040006402503000090010093004000004035200000700900    = solved in 8ms 
2. 306508400520000000087000031003010080900863005050090600130000250000000074005206300    = solved in 2ms
3. 003020600900305001001806400008102900700000008006708200002609500800203009005010300    = solved in 1ms
4. 300200000000107000706030500070009080900020004010800050009040301000702000000008006    = solved in 8ms

## Project Euler problem 96
This sudoku solver was used to solve problem 96 of Project Euler. This problem can be found here: https://projecteuler.net/problem=96
To summarize, the sudoku.txt file has 50 different sudokus that need to be solved. The 3-digit number found in the top left corner of all solution grids needs to be collected. The sum of these 3-digit numbers is the solution of this Project Euler problem.

It takes this sudoku solver around 340ms to solve Project Euler's problem 96.
To get the solution, you run the SudokuSolver.java as described in the usage section. This will provide the previous solution for the sudoku with the provided sudokuString, but also the solution for Project Euler's problem 96 and the time  it took to solve the problem. 


