package com.defunkt.kingpin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by blair on 8/16/17.
 */

public class Register extends AppCompatActivity {
    private Button submit;
    private FirebaseAuth mAuth;
    private EditText email, emailV, password, passwordV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();

        submit = (Button)findViewById(R.id.btnSubmit);
        email = (EditText)findViewById(R.id.txtEmailR);
        emailV = (EditText)findViewById(R.id.txtEmailVerify);
        password = (EditText)findViewById(R.id.txtPasswordR);
        passwordV = (EditText)findViewById(R.id.txtPasswordVerify);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = email.getText().toString().trim();
                String getPassword = password.getText().toString().trim();
                String verifyEmail = emailV.getText().toString().trim();
                String verifyPassword = passwordV.getText().toString().trim();

                if(getEmail.equals(verifyEmail) && getPassword.equals(verifyPassword)) {
                    createAccount(getEmail, getPassword);



                }
            }
        });
    }

    private void createAccount(final String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Testing", "createUserWithEmail:onComplete:" + task.isSuccessful());


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Register.this,"Registration failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Register.this, MainActivity.class);
                            i.putExtra("Email", email);
                            startActivity(i);
                        }
                    }
                });

    }
}
