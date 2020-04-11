package com.example.tictactoemvp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
        mPresenter.start();
        mNotificationTV = findViewById(R.id.notification);

        //find all imageViews which represents Zero or Cross, attach them to ArrayList
        attachViewToMyArrayList();
    }

    @Override
    protected void onDestroy() {
        mPresenter.stop();
        super.onDestroy();
    }

    private void attachViewToMyArrayList() {
        mListZeroIV = new ArrayList<>();
        mListCrossIV = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            mListZeroIV.add(getZeroIVAt(i));
            mListCrossIV.add(getCrossIVAt(i));
        }
    }

    //find imageView which represents the Zero at i-th position
    private ImageView getZeroIVAt(int i) {
        String stringId = "zero_" + i;
        int realID = getResources().getIdentifier(stringId, "id", getPackageName());
        return findViewById(realID);
    }

    //find imageView which represents the Cross at i-th position
    private ImageView getCrossIVAt(int i) {
        String stringId = "cross_" + i;
        int realID = getResources().getIdentifier(stringId, "id", getPackageName());
        return findViewById(realID);
    }

    public void onClickButtonCell(View view) {
        String fullId = getStringId(view);
        //delegate for presenter
        mPresenter.onClickButtonCell(fullId);
    }

    //get id of View in String format (ex: "button_1")
    private String getStringId(View view) {
        if (view.getId() == View.NO_ID)
            return "no-id";
        String[] id = view.getResources().getResourceName(view.getId()).split("/");
        return id[1];
    }

    @Override
    public void setVisibleCrossAtPosition(int i) {
        //change visibility
        mListCrossIV.get(i).setVisibility(View.VISIBLE);
    }

    @Override
    public void setVisibleZeroAtPosition(int i) {
        //change visibility
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

    //trigger when RESET button is clicked
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
