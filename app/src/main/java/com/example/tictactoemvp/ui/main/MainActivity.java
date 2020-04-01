package com.example.tictactoemvp.ui.main;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tictactoemvp.R;
import com.example.tictactoemvp.model.ChessBoard;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainMvpView {
    MainMvpPresenter mPresenter;
    ArrayList<ImageView> mListZeroIV;
    ArrayList<ImageView> mListCrossIV;
    TextView mNotificationTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawable(null);

        mPresenter = new MainPresenter(this);
        mNotificationTV = findViewById(R.id.notification);
        attachViewToMyArrayList();
    }

    private void attachViewToMyArrayList() {
        mListZeroIV = new ArrayList<>();
        mListCrossIV = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            mListZeroIV.add(getZeroIVAt(i));
            mListCrossIV.add(getCrossIVAt(i));
        }
    }

    private ImageView getZeroIVAt(int i) {
        String stringId = "zero_" + i;
        int realID = getResources().getIdentifier(stringId, "id", getPackageName());
        return findViewById(realID);
    }

    private ImageView getCrossIVAt(int i) {
        String stringId = "cross_" + i;
        int realID = getResources().getIdentifier(stringId, "id", getPackageName());
        return findViewById(realID);
    }

    public void onClickButton(View view) {
        mPresenter.onClickButton(view);
    }

    @Override
    public void setVisibleCrossAtPosition(int i) {
        mListCrossIV.get(i).setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleZeroAtPosition(int i) {
        mListZeroIV.get(i).setVisibility(View.VISIBLE);
    }

    @Override
    public void notifyWinner(int winner) {
        switch (winner) {
            case ChessBoard.ZERO_IN_BOARD: {
                mNotificationTV.setText(R.string.zero_won);
                break;
            }
            case ChessBoard.CROSS_IN_BOARD: {
                mNotificationTV.setText(R.string.cross_won);
                break;
            }
        }
    }

    @Override
    public void notifyDraw() {
        mNotificationTV.setText(R.string.draw);
    }

    public void onResetGame(View view) {
        mNotificationTV.setText(R.string.instruction_reset_game);
        mPresenter.resetGame();
        resetView();
    }

    private void resetView() {
        for (int i = 0; i < 9; i++) {
            mListZeroIV.get(i).setVisibility(View.INVISIBLE);
            mListCrossIV.get(i).setVisibility(View.INVISIBLE);
        }
    }
}
