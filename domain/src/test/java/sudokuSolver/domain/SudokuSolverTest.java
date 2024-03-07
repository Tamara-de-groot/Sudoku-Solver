package sudokuSolver.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class SudokuSolverTest {

    @Test
    public void testIfBoardHasSolutionAfterChecker() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        sudoku.checker(new Coordinate(0, 0));
        assertNotEquals(0, sudoku.getBoard()[0][0]);
        assertNotEquals(0, sudoku.getBoard()[2][0]);
    }

}
