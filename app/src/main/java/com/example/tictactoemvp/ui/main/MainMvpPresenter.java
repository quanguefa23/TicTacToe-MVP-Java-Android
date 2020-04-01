package com.example.tictactoemvp.ui.main;

import android.view.View;

public interface MainMvpPresenter {
    void onAttach(MainMvpView mainMvpView);

    void onClickButton(View view);

    void resetGame();
}
