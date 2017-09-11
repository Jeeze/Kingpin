package com.defunkt.kingpin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button signin;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        signin = (Button) findViewById(R.id.btnSignIn);
        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtPassword);
        register = (TextView)findViewById(R.id.lblRegister);

        Bundle extras = getIntent().getExtras();
        String userName;

        if(extras != null){
            userName = extras.getString("Email");
            email.setText(userName);

        }

        if (mAuth.getCurrentUser() != null) {
            //User not logged in
            finish();
            startActivity(new Intent(getApplicationContext(), SignIn.class));
        }

        /**
         * onClick listeners
         */
        signin.setOnClickListener(this);

        register.setOnClickListener(this);


    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Testing", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("Testing", "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, "Sign in failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Intent i = new Intent(MainActivity.this, Navigation.class);
                            startActivity(i);
                            finish();

                        }

                        // ...
                    }
                });



    }

    public void onClick(View v){
        try {
        if(v == signin){
            String getEmail = email.getText().toString().trim();
            String getPassword = password.getText().toString().trim();
            signIn(getEmail, getPassword);

        }

        else{


            Intent i = new Intent(MainActivity.this, Register.class);
            startActivity(i);
        }
    }
        catch (Exception exception){
            Log.e("Testing", "Exception", exception);
        }
    }
}




