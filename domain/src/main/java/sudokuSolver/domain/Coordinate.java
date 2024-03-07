package sudokuSolver.domain;

public class Coordinate {
    int row;
    int column;

    Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public Coordinate toNext() {
        if (column == 8) {
            if (row == 8) {
                return null;
            }
            return new Coordinate(row + 1, 0);
        }
        return new Coordinate(row, column + 1);
    }

}
