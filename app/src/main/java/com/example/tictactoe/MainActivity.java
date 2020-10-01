package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Controller controller;
    private Model model;
    private LinearLayout llMain;
    private FirebaseAuth mAuth;
    private ImageView imageView;
    boolean turn=true;
    private static final String TAG = "MainActivity";
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);
        registerForContextMenu(tv);

        llMain = findViewById(R.id.mainBoard);
        imageView = findViewById(R.id.imgPlayer);
        model = new Model();
        controller = new Controller();
        controller.startGame();


            // TODO - start controller object//done
            // TODO - imageView still null!!!!//done
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }
    public  boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        int id=item.getItemId();
        if (id==R.id.action_register){
            Toast.makeText(this,"You selected register",Toast.LENGTH_LONG).show();
        }
        else if(id==R.id.action_login){
            Toast.makeText(this,"You selected login",Toast.LENGTH_LONG).show();
        }
        else if (id==R.id.action_start){
            Toast.makeText(this,"You selected start",Toast.LENGTH_LONG).show();
        }
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.overlay,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if (item.getItemId()==R.id.firstLine){
            Toast.makeText(this,"You selected first line",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (item.getItemId()==R.id.secondLine){
            Toast.makeText(this,"You selected second line",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
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