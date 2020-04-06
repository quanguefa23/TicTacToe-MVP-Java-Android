package com.example.tictactoemvp.ui.main;

import com.example.tictactoemvp.model.ChessBoard;

public class MainPresenter implements MainMvpPresenter{
    MainMvpView mView;
    ChessBoard mChessBoard; //model
    int mTurn;

    public MainPresenter(MainMvpView mainMvpView) {
        mView = mainMvpView;
        mTurn = ChessBoard.CROSS_IN_BOARD;
        mChessBoard = new ChessBoard();
    }

    @Override
    public void onClickButtonCell(String fullId) {
        //return if game has a winner
        if (mChessBoard.hasWinner())
            return;

        int id = Integer.parseInt(fullId.split("_")[1]);

        //return if this cell is filled
        if (mChessBoard.isFill(id))
            return;

        //trigger view and change model
        if (mTurn == ChessBoard.CROSS_IN_BOARD) {
            mChessBoard.fillAsCross(id);
            mView.setVisibleCrossAtPosition(id);
            mTurn = ChessBoard.ZERO_IN_BOARD;
        }
        else {
            mChessBoard.fillAsZero(id);
            mView.setVisibleZeroAtPosition(id);
            mTurn = ChessBoard.CROSS_IN_BOARD;
        }

        //check winner
        if (mChessBoard.checkWinner() == ChessBoard.ZERO_IN_BOARD) {
            mView.notifyWinner(ChessBoard.ZERO_IN_BOARD);
        }
        else if (mChessBoard.checkWinner() == ChessBoard.CROSS_IN_BOARD) {
            mView.notifyWinner(ChessBoard.CROSS_IN_BOARD);
        }
        else if (mChessBoard.isFull()) { //check draw
            mView.notifyDraw();
        }
    }

    @Override
    public void resetGame() {
        mTurn = ChessBoard.CROSS_IN_BOARD;
        mChessBoard.resetBoard();
    }
}
