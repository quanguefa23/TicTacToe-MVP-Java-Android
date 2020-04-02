package com.example.tictactoemvp.model;

public class ChessBoard {
    public static final int NONE_IN_BOARD = 0;
    public static final int ZERO_IN_BOARD = 1;
    public static final int CROSS_IN_BOARD = 2;

    int[][] matrix;
    int mWinner;

    public ChessBoard() {
        matrix = new int[3][3];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                matrix[i][j] = NONE_IN_BOARD;

        mWinner = NONE_IN_BOARD;
    }

    private int getRow(int id) {
        return id / 3;
    }

    private int getCol(int id) {
        return id % 3;
    }

    public boolean isFill(int id) {
        return !(matrix[getRow(id)][getCol(id)] == NONE_IN_BOARD);
    }

    public void fillAsCross(int id) {
        matrix[getRow(id)][getCol(id)] = CROSS_IN_BOARD;
    }

    public void fillAsZero(int id) {
        matrix[getRow(id)][getCol(id)] = ZERO_IN_BOARD;
    }

    public int checkWinner() {
        for (int i = 0; i < 3; i++) {
            int resRaw = checkWinByRaw(i);
            if (resRaw != NONE_IN_BOARD) {
                mWinner = resRaw;
                return resRaw;
            }

            int resCol = checkWinByCol(i);
            if (resCol != NONE_IN_BOARD) {
                mWinner = resCol;
                return resCol;
            }
        }

        int resCross1 = checkByCross1();
        if (resCross1 != NONE_IN_BOARD) {
            mWinner = resCross1;
            return resCross1;
        }

        int resCross2 = checkByCross2();
        if (resCross2 != NONE_IN_BOARD) {
            mWinner = resCross2;
            return resCross2;
        }

        return NONE_IN_BOARD;
    }

    private int checkByCross1() {
        if (matrix[0][0] == matrix[1][1] && matrix[1][1] == matrix[2][2])
            return matrix[1][1];
        return NONE_IN_BOARD;
    }

    private int checkByCross2() {
        if (matrix[0][2] == matrix[1][1] && matrix[1][1] == matrix[2][0])
            return matrix[1][1];
        return NONE_IN_BOARD;
    }

    private int checkWinByCol(int i) {
        if (matrix[0][i] == matrix[1][i] && matrix[1][i] == matrix[2][i])
            return matrix[0][i];
        return NONE_IN_BOARD;
    }

    private int checkWinByRaw(int i) {
        if (matrix[i][0] == matrix[i][1] && matrix[i][1] == matrix[i][2])
            return matrix[i][0];
        return NONE_IN_BOARD;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == NONE_IN_BOARD)
                    return false;
            }
        }
        return true;
    }

    public boolean hasWinner() {
        return mWinner != NONE_IN_BOARD;
    }
}
