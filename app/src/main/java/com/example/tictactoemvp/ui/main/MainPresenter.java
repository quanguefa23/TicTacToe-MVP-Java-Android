package com.example.tictactoemvp.ui.main;

import android.view.View;

import com.example.tictactoemvp.model.ChessBoard;

public class MainPresenter implements MainMvpPresenter{
    MainMvpView mView;
    ChessBoard mChessBoard;
    int mTurn;

    public MainPresenter(MainMvpView mainMvpView) {
        mView = mainMvpView;
        mTurn = ChessBoard.CROSS_IN_BOARD;
        mChessBoard = new ChessBoard();
    }

    @Override
    public void onAttach(MainMvpView mainMvpView) {
        mView = mainMvpView;
    }

    @Override
    public void onClickButton(View view) {
        String fullId = getStringId(view);
        int id = Integer.parseInt(fullId.split("_")[1]);

        if (mChessBoard.isFill(id))
            return;

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

        if (mChessBoard.checkWinner() == ChessBoard.ZERO_IN_BOARD) {
            mView.notifyWinner(ChessBoard.ZERO_IN_BOARD);
        }
        else if (mChessBoard.checkWinner() == ChessBoard.CROSS_IN_BOARD) {
            mView.notifyWinner(ChessBoard.CROSS_IN_BOARD);
        }
        else if (mChessBoard.isFull()) {
            mView.notifyDraw();
        }
    }

    @Override
    public void resetGame() {
        mTurn = ChessBoard.CROSS_IN_BOARD;
        mChessBoard.resetBoard();
    }

    private String getStringId(View view) {
        if (view.getId() == View.NO_ID)
            return "no-id";
        String[] id = view.getResources().getResourceName(view.getId()).split("/");
        return id[1];
    }
}
