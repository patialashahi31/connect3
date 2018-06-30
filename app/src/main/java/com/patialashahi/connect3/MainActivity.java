package com.patialashahi.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0; // 0 for yellow
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // 2 means unplayed
    String winner = "Red";
    boolean gameIsActive = true;
    int[][] winningPositions = {{0,1,2}, {3,4,5} ,{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {
        ImageView imageView5 = (ImageView) view;

        int tappedCounter = Integer.parseInt(imageView5.getTag().toString());
        if (gamestate[tappedCounter] == 2 && gameIsActive==true) {
            gamestate[tappedCounter] = activePlayer;
            imageView5.setTranslationY(-1000f);
            if (activePlayer == 0) {
                imageView5.setImageResource(R.drawable.a);
                activePlayer = 1;
            } else {
                imageView5.setImageResource(R.drawable.b);
                activePlayer = 0;
            }
            imageView5.animate().translationYBy(1000f).rotation(360).setDuration(500);
            for (int[] winningPosition : winningPositions) {
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] &&
                        gamestate[winningPosition[1]] == gamestate[winningPosition[2]] &&
                        gamestate[winningPosition[0]] != 2) {
                    gameIsActive = false;
                    if(gamestate[winningPosition[0]]==0){
                        winner = "Yellow";

                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won");
                   LinearLayout layout = (LinearLayout)findViewById(R.id.play);
                   layout.setVisibility(View.VISIBLE);

                }
                else {
                    boolean isGameIsOver = true;
                    for(int counterState : gamestate){
                        if(counterState==2){
                            isGameIsOver = false;
                        }
                    }
                    if(isGameIsOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("Nobody won");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.play);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }
public void playAgain(View view){
        gameIsActive = true;
    LinearLayout layout = (LinearLayout)findViewById(R.id.play);
    layout.setVisibility(View.INVISIBLE);
    activePlayer = 0;

    for(int i=0;i<9;i++){
        gamestate[i]=2;
    }
    GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
    for(int i = 0;i<gridLayout.getChildCount();i++){
        ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
