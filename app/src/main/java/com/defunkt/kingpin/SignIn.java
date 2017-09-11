package com.defunkt.kingpin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by blair on 8/16/17.
 */

public class SignIn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnSignOut;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        btnSignOut = (Button) findViewById(R.id.btnSignOut);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

        if(mAuth.getCurrentUser() == null){

            //user not logged in
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

    }
}
