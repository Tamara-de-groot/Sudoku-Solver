package sudokuSolver.domain;

import java.io.File;
import java.net.URL;

public class SudokuSolver {
    public static void main(String[] args) {
        String sudokuString
        // ="000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        // ="306508400520000000087000031003010080900863005050090600130000250000000074005206300";
        // ="003020600900305001001806400008102900700000008006708200002609500800203009005010300";
                = "300200000000107000706030500070009080900020004010800050009040301000702000000008006";
        Sudoku sudoku = new Sudoku(sudokuString);
        URL path = Sudoku.class.getResource("sudoku.txt");
        File sudokuFile = new File(path.getFile());
        sudoku.initialDisplay();
        final long startTime = System.currentTimeMillis();
        sudoku.checker(new Coordinate(0, 0));
        final long endTime = System.currentTimeMillis();
        sudoku.finalDisplay();
        System.out.println("Solved in:" + (endTime - startTime) + "ms");
        System.out.println(
                "Project Euler SuDoku solve 50 sudoku's and count sum of first three numbers of every sudoku:");
        final long startTimeProjectEuler = System.currentTimeMillis();
        int sum = sudoku.sumOfAllThreeNumbersOfSolvedSudoku(sudokuFile);
        final long endTimeProjectEuler = System.currentTimeMillis();
        System.out.println("The solution is: " + sum);
        System.out.println("And it was solved in: " + (endTimeProjectEuler - startTimeProjectEuler) + "ms");
    }

}
