package com.example.tictactoemvp.ui.main;

import android.view.View;

public interface MainMvpPresenter {
    void onAttach(MainMvpView mainMvpView);

    void onClickButtonCell(View view);

    void resetGame();
}
