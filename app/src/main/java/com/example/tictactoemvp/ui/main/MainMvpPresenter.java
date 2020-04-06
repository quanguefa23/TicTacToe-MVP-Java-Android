package com.example.tictactoemvp.ui.main;

import android.view.View;

public interface MainMvpPresenter {

    void resetGame();

    void onClickButtonCell(String fullId);
}
