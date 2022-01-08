package com.example.myloginwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nameEditText,emailEditText,usernameEditText,passwordEditText;
    private Button signUpButton;
    UserDetaills userDetaills;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameEditText=findViewById(R.id.signUpNameEditText);
        emailEditText=findViewById(R.id.signUpEmailEditText);
        usernameEditText=findViewById(R.id.signUpUsernameEditText);
        passwordEditText=findViewById(R.id.signUpPasswordEditText);
        databaseHelper = new DatabaseHelper(this);
        signUpButton=findViewById(R.id.signUpButtonId);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        userDetaills = new UserDetaills();
        userDetaills.setName(name);
        userDetaills.setEmail(email);
        userDetaills.setUsername(username);
        userDetaills.setPassword(password);

        long rowId =databaseHelper.insertData(userDetaills);
        if (rowId>0){
            Toast.makeText(getApplicationContext(), "Row "+rowId+" is successfully inserted", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getApplicationContext(), "Row insertion failed", Toast.LENGTH_SHORT).show();

        }



    }
}