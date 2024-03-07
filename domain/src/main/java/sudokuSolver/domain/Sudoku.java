package sudokuSolver.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Sudoku {

    private int[][] board = new int[9][9];
    private boolean[][] fixedBoard = new boolean[9][9];

    public Sudoku(String input) {
        this.createAllRows(input);
    }

    public int[][] getBoard() {
        return this.board;
    }

    public boolean[][] getFixedBoard() {
        return this.fixedBoard;
    }

    public ArrayList<String> readSudokuTextFileAndSpilt(File sudokuFile) {
        ArrayList<String> sudokuList = new ArrayList<String>();
        try {
            // File sudokuFile = new File("./src/main/java/sudokuSolver/domain/sudoku.txt");
            Scanner scanner = new Scanner(sudokuFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Grid") == false) {
                    sudokuList.add(line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return sudokuList;
    }

    public ArrayList<String> combineLinesToMakeSudoku(ArrayList<String> sudokuFromTextFile) {
        ArrayList<String> sudokus = new ArrayList<String>();
        String lineConcat = "";
        int sudokuCount = 0;
        while (sudokuCount < 450) {
            for (int i = 0; i < 9; i++) {
                String line = sudokuFromTextFile.get(i + sudokuCount);
                lineConcat = lineConcat.concat(line);
            }
            sudokus.add(lineConcat);
            lineConcat = "";
            sudokuCount = sudokuCount + 9;
        }
        return sudokus;
    }

    public ArrayList<String> convertTextFileIntoSudokuStrings(File sudokuFile) {
        ArrayList<String> sudokuFromTextFile = readSudokuTextFileAndSpilt(sudokuFile);
        ArrayList<String> sudokus = combineLinesToMakeSudoku(sudokuFromTextFile);
        return sudokus;
    }

    public void createAllRows(String sudokuInputString) {
        String[] allRows = sudokuInputString.split("(?<=\\G.{" + 1 + "})");
        if (validateInputString(sudokuInputString)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int value = Integer.parseInt(allRows[9 * i + j]);
                    this.board[i][j] = value;
                    if (value == 0) {
                        fixedBoard[i][j] = false;
                    } else {
                        fixedBoard[i][j] = true;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Input is not valid");
        }
    }

    public void initialDisplay() {
        System.out.println("Initial State:");
        display();
    }

    public void finalDisplay() {
        System.out.println("Solved:");
        display();
    }

    public void display() {
        String string = "";
        for (int i = 0; i < 9; i++) {
            string += "-------------------\n";
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    string += "| ";
                } else {
                    string += String.format("|%d", board[i][j]);
                }
            }
            string += "|\n";
        }
        string += "-------------------\n";
        System.out.println(string);
    }

    public boolean isValid(int row, int colomn, int number) {
        int rowBlockNumber = row / 3;
        int colBlockNumber = colomn / 3;
        boolean validation = true;
        validation = isValidRowCol(row, colomn, number);
        for (int i = 3 * rowBlockNumber; i < 3 * rowBlockNumber + 3; i++) {
            for (int j = 3 * colBlockNumber; j < 3 * colBlockNumber + 3; j++) {
                if (board[i][j] == number) {
                    validation = false;
                }
            }
        }
        return validation;
    }

    public boolean isValidRowCol(int row, int colomn, int number) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == number || board[i][colomn] == number) {
                return false;
            }
        }
        return true;
    }

    public boolean checker(Coordinate coordinate) {
        if (coordinate == null) {
            return true;
        }
        int row = coordinate.getRow();
        int column = coordinate.getColumn();
        if (this.fixedBoard[row][column]) {
            return checker(coordinate.toNext());
        }
        for (int number = 1; number < 10; number++) {
            if (!isValid(row, column, number)) {
                continue;
            }
            board[row][column] = number;
            if (checker(coordinate.toNext())) {
                return true;
            }
        }
        board[row][column] = 0;
        return false;
    }

    public boolean validateInputString(String sudokuInputString) {
        if (!validateInputLengthString(sudokuInputString)) {
            return false;
        } else if (!validateAllCharactersAreNumbers(sudokuInputString)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validateAllCharactersAreNumbers(String sudokuInputString) {
        boolean validation = true;
        for (int i = 0; i < sudokuInputString.length(); i++) {
            if (!Character.isDigit(sudokuInputString.charAt(i))) {
                validation = false;
            }
        }
        return validation;
    }

    public boolean validateInputLengthString(String sudokuInputString) {
        if (sudokuInputString.length() != 81) {
            return false;
        } else {
            return true;
        }
    }

    public int determineFirstThreeNumbersOfSolvedSudoku() {
        String nr1 = Integer.toString(this.getBoard()[0][0]);
        String nr2 = Integer.toString(this.getBoard()[0][1]);
        String nr3 = Integer.toString(this.getBoard()[0][2]);
        String nr1Andnr2 = nr1.concat(nr2);
        String firstThreeNrs = nr1Andnr2.concat(nr3);
        int firstThreeNrAsInt = Integer.parseInt(firstThreeNrs);
        return firstThreeNrAsInt;
    }

    public int[] determineAllFirstThreeNumbersSolvedSudoku(File sudokuFile) {
        int[] allFirstThreeNumbers = new int[50];
        ArrayList<String> sudokuList = this.convertTextFileIntoSudokuStrings(sudokuFile);
        for (int i = 0; i < 50; i++) {
            String sudokuString = sudokuList.get(i);
            Sudoku sudoku = new Sudoku(sudokuString);
            sudoku.checker(new Coordinate(0, 0));
            allFirstThreeNumbers[i] = sudoku.determineFirstThreeNumbersOfSolvedSudoku();
        }
        return allFirstThreeNumbers;
    }

    public int sumOfAllThreeNumbersOfSolvedSudoku(File sudokuFile) {
        int[] allFirstThreeNumbers = this.determineAllFirstThreeNumbersSolvedSudoku(sudokuFile);
        int sum = IntStream.of(allFirstThreeNumbers).sum();
        return sum;
    }
}
