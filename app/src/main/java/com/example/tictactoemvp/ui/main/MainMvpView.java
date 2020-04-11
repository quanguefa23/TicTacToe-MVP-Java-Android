package com.example.tictactoemvp.ui.main;

public interface MainMvpView {

    void setVisibleCrossAtPosition(int id);

    void setVisibleZeroAtPosition(int id);

    void notifyWinner(int winner);

    void notifyDraw();
}
