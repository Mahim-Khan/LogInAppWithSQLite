package com.example.myloginwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper databaseHelper;
    private EditText usernameEditText,passwordEditText;
    private Button signInButton,signUpHereButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper= new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        usernameEditText= findViewById(R.id.signInUsernameEditText);
        passwordEditText= findViewById(R.id.signInPasswordEditText);
        signInButton= findViewById(R.id.signInButtonId);
        signUpHereButton= findViewById(R.id.signUpHereButtonId);
        signInButton.setOnClickListener(this);
        signUpHereButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String username=usernameEditText.getText().toString();
        String password =passwordEditText.getText().toString();

        if (view.getId()==R.id.signInButtonId){
            Boolean result = databaseHelper.findPassword(username,password);
            if (result==true){
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(), "username and password didn't matched", Toast.LENGTH_SHORT).show();
            }


        }
        else if (view.getId()==R.id.signUpHereButtonId){
            Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(intent);

        }

    }
}