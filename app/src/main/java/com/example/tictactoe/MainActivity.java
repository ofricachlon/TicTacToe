package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Controller controller;
    private Model model;
    private LinearLayout llMain;
    private ImageView imageView;
    boolean turn=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        llMain = findViewById(R.id.mainBoard);
        imageView=findViewById(R.id.imgPlayer);
        model=new Model();
        controller=new Controller();
        controller.startGame();



        // TODO - start controller object//done
        // TODO - imageView still null!!!!//done
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    public void select(View v)
    {
        int loc = Integer.parseInt(v.getTag().toString());
        //Toast.makeText(this, "Click "+loc, Toast.LENGTH_SHORT).show();//מציג את המספר כפתור עליו נלחץ(לא כיף

        Button b = (Button)v; // v is button
        b.setEnabled(false);// can't press this button again
//b.setText(controller.userSelect(loc));// עושה שגיאה~!
        model.setPlace(loc,controller.userSelect(loc));
         if (turn){
            imageView.setImageResource(R.drawable.x);
            turn=false;
        }
        else{

            turn=true;
             model.setPlace(loc,'O');
        }

        if (model.gameOver()){
            Toast.makeText(this, "The Winner is: "+ model.getWinner(), Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,GameOverActivity.class);
            startActivity(intent);
        }
        // TODO - update player image//done
        // TODO - check game over
        // TODO -
    }

    public void startGame(View view) {
        newGame();
    }

    private void newGame() {
        //TODO - clear all button
        //TODO - enable all button
        imageView.setImageResource(R.drawable.x);
        Button b= llMain.findViewWithTag("0");
        b.setEnabled(true);
        b.setText(" ");
    }

}