package com.example.cuttcoot;

import android.os.Bundle;
import android.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 1: x  ... 2: o  ... 3:empty
    int x=1,flag=0;
    int[] gameState = {3,3,3,3,3,3,3,3,3};
    int [][] winPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void Reset(View view) {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView img = (ImageView) gridLayout.getChildAt(i);
            img.setTranslationY(-1500);
            img.setImageDrawable(null);
        }

        x=1;
        flag=0;
        for(int z=0;z<9;z++) {
            gameState[z] = 3;
        }
    }

    public void dropin (View view) {
        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(counter.getTag().toString());
        if(gameState[tag]==3 && flag==0) {
            counter.setTranslationY(-1500);
            if(x==1) {
                counter.setImageResource(R.drawable.x);
                x=2;
                gameState[tag] = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                x=1;
                gameState[tag] = 2;
            }
            counter.animate().translationYBy(1500).alpha(1).setDuration(1000);
            for(int[] winPo : winPos) {
                if(gameState[winPo[0]] == gameState[winPo[1]] && gameState[winPo[1]] == gameState[winPo[2]] && gameState[winPo[0]]!=3) {
                    Toast.makeText(this,"You Won",Toast.LENGTH_LONG).show();
                    flag = 1;
                    break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}