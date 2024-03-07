package sudokuSolver.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

public class SudokuTest {

    @Test
    public void testIfSudokuHasBoard() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        assertEquals(8, sudoku.getBoard()[0][3]);
        assertEquals(5, sudoku.getBoard()[1][0]);
    }

    @Test
    public void testIfCreateAllRowsGeneratesFixedBoard() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        assertTrue(sudoku.getFixedBoard()[0][3]);
        assertFalse(sudoku.getFixedBoard()[0][0]);
    }

    @Test
    public void testIfAssertionIsThrownWhenInvalidInputIsUsed() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        String invalidIStringTooShort = "1234";
        String invalidStringChar = "12sa32b";
        Throwable thrownTooShort = assertThrows(IllegalArgumentException.class,
                () -> sudoku.createAllRows(invalidIStringTooShort));
        Throwable thrownChar = assertThrows(IllegalArgumentException.class,
                () -> sudoku.createAllRows(invalidStringChar));
        assertEquals("Input is not valid", thrownTooShort.getMessage());
        assertEquals("Input is not valid", thrownChar.getMessage());
    }

    @Test
    public void testIfValidateInputStringReturnsFalseWhenStringIsTooShortOrHasChar() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        String invalidIStringTooShort = "1234";
        String invalidStringChar = "12sa32b";
        assertFalse(sudoku.validateInputString(invalidIStringTooShort));
        assertFalse(sudoku.validateInputString(invalidStringChar));
    }

    @Test
    public void testIfValidateInputStringReturnsTrueWhenInputIsValid() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        assertTrue(sudoku.validateInputString(input));
    }

    @Test
    public void testIfReturnsTrueWhenCoordinatesAreForEndOfSudokuAndUseToNext() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        Coordinate coordinate = new Coordinate(8, 8);
        coordinate.toNext();
        assertTrue(sudoku.checker(coordinate));
    }

    @Test
    public void testIfCheckerReturnsTrueWhenSolutionIsAlreadyFoundForCoordinate() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        Coordinate coordinate = new Coordinate(0, 3);
        assertTrue(sudoku.checker(coordinate));
    }

    @Test
    public void testIfSudokuTextFileIsSplitIntoFiftyStrings() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        File sudokuFile = new File("./src/main/java/sudokuSolver/domain/sudoku.txt");
        ArrayList<String> sudokuList = sudoku.readSudokuTextFileAndSpilt(sudokuFile);
        assertEquals("003020600", sudokuList.get(0));
        assertEquals(450, sudokuList.size());
    }

    @Test
    public void testIfStringsInSudokuArrayListHaveLengthOf81() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        File sudokuFile = new File("./src/main/java/sudokuSolver/domain/sudoku.txt");
        ArrayList<String> sudokuFromText = sudoku.readSudokuTextFileAndSpilt(sudokuFile);
        ArrayList<String> sudokuList = sudoku.combineLinesToMakeSudoku(sudokuFromText);
        assertEquals(81, sudokuList.get(0).length());
    }

    @Test
    public void testIfFiftySudokusHaveBeenAddedToSudokuArrayList() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        File sudokuFile = new File("./src/main/java/sudokuSolver/domain/sudoku.txt");
        ArrayList<String> sudokuList = sudoku.convertTextFileIntoSudokuStrings(sudokuFile);
        assertEquals(50, sudokuList.size());
    }

    @Test
    public void tetsIfSudokusFromArrayListCanBeUsedInTheSolver() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        File sudokuFile = new File("./src/main/java/sudokuSolver/domain/sudoku.txt");
        ArrayList<String> sudokuList = sudoku.convertTextFileIntoSudokuStrings(sudokuFile);
        Sudoku newSudoku = new Sudoku(sudokuList.get(0));
        Coordinate coordinate = new Coordinate(0, 3);
        assertTrue(newSudoku.checker(coordinate));
    }

    @Test
    public void testIfFirstThreeNrsOfSudokuAreReturned() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        sudoku.checker(new Coordinate(0, 0));
        assertEquals(671, sudoku.determineFirstThreeNumbersOfSolvedSudoku());
    }

    @Test
    public void testIfFirstThreeNrsOfAllSudokusAreReturned() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        File sudokuFile = new File("./src/main/java/sudokuSolver/domain/sudoku.txt");
        int[] sudokuNumberArray = sudoku.determineAllFirstThreeNumbersSolvedSudoku(sudokuFile);
        assertEquals(50, sudokuNumberArray.length);
        assertEquals(483, sudokuNumberArray[0]);
    }

    @Test
    public void testIfSecondSudokuIsValid() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        File sudokuFile = new File("./src/main/java/sudokuSolver/domain/sudoku.txt");
        ArrayList<String> sudokuList = sudoku.convertTextFileIntoSudokuStrings(sudokuFile);
        assertEquals(81, sudokuList.get(1).length());
    }

    @Test
    public void testIfSumOfAllThreeNumbersOfSolvedSudokuIsCalculated() {
        String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
        Sudoku sudoku = new Sudoku(input);
        File sudokuFile = new File("./src/main/java/sudokuSolver/domain/sudoku.txt");
        assertEquals(24702, sudoku.sumOfAllThreeNumbersOfSolvedSudoku(sudokuFile));
    }
}
