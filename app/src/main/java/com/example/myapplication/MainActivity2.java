package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    EditText name, username, email, pass;
    Button registerButton;
    FirebaseAuth mAuth;
    ProgressBar progbar;
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
        setContentView(R.layout.activity_main2);
        name=(EditText) findViewById(R.id.name);
        username=(EditText) findViewById(R.id.username);
        email=(EditText) findViewById(R.id.email);
        pass=(EditText) findViewById(R.id.pass);
        registerButton=(Button) findViewById(R.id.registerButton);
        mAuth= FirebaseAuth.getInstance();
        progbar=findViewById(R.id.progbar);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progbar.setVisibility(View.VISIBLE);
                String email1,pass1;
                email1=String.valueOf(email.getText());
                pass1=String.valueOf(pass.getText());
                if (name.getText().toString().isEmpty() || username.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                         pass.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the Data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Registration Completed" ,Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(email1, pass1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progbar.setVisibility(View.GONE);
                                    // Sign in success, update UI with the signed-in user's information

                                    Toast.makeText(MainActivity2.this, "Account Created",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(MainActivity2.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}