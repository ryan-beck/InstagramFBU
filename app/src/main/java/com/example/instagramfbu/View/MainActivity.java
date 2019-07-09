package com.example.instagramfbu.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.instagramfbu.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button btnLogIn;
    Button btnCreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) { // if user is already signed in, go to home page
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else { // else, show the login screen
            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            btnLogIn = findViewById(R.id.btnLogIn);
            btnCreate = findViewById(R.id.btnCreate);

            btnLogIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String username = etUsername.getText().toString();
                    final String password = etPassword.getText().toString();

                    logIn(username, password);
                }
            });

            btnCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    private void logIn(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null) {
                    Log.d("LoginActivity", "Login Successful");
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
                    Log.e("LoginActivity", "Login failed");
                    e.printStackTrace();
                }

            }
        });
    }

}
