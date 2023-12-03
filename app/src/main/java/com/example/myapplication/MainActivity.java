package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton,signup;
    TextView signupText;
    FirebaseAuth mAuth;
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
                /*Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);*/
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        signupText  = findViewById(R.id.signupText);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        mAuth= FirebaseAuth.getInstance();
        signup = findViewById(R.id.signup);





        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email2,password2;
                email2=String.valueOf(username.getText());
                password2=String.valueOf(password.getText());

                if (TextUtils.isEmpty(email2)){
                    Toast.makeText(getApplicationContext(), "Enter the Email", Toast.LENGTH_SHORT).show();

              }
                if (TextUtils.isEmpty(password2)){
                    Toast.makeText(getApplicationContext(), "Enter the Password", Toast.LENGTH_SHORT).show();

                }
                mAuth.signInWithEmailAndPassword(email2, password2)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    /*Intent i1 = new Intent(getApplicationContext(), "user interface".class);
                                    startActivity(i1);*/
                                } else {

                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);


            }
        });

    }
}