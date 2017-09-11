package com.defunkt.kingpin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class personal_score extends Fragment {

    private TextView lblPersonalScore;
    private FirebaseAuth mAuth;

    public personal_score(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = (FirebaseAuth) savedInstanceState.getParcelable("Auth");
        // Inflate the layout for this fragment
        lblPersonalScore = (TextView) getView().findViewById(R.id.lblPersonalScore);
        lblPersonalScore.setText(mAuth.getCurrentUser().toString());
        return inflater.inflate(R.layout.fragment_personal_score, container, false);
    }
}
