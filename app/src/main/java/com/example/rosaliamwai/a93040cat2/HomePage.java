package com.example.rosaliamwai.a93040cat2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity implements OnClickListener{
    private CardView profileCard,workoutsCard,trainersCard,locationsCard;

    private TextView etEmail;
    private Button btnlogout;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){

            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        FirebaseUser user= firebaseAuth.getCurrentUser();

        etEmail=(TextView) findViewById(R.id.etEmail);

        btnlogout=(Button) findViewById(R.id.btn_logout);

        profileCard=(CardView) findViewById(R.id.card_profile);
        workoutsCard=(CardView) findViewById(R.id.card_workouts);
        trainersCard=(CardView) findViewById(R.id.card_trainers);
        locationsCard=(CardView) findViewById(R.id.card_locations);



        profileCard.setOnClickListener(this);
        workoutsCard.setOnClickListener(this);
        trainersCard.setOnClickListener(this);
        locationsCard.setOnClickListener(this);


        btnlogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==btnlogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        Intent i;
        switch (v.getId()) {
        case R.id.card_profile : i= new Intent(this,DisplayUsers.class); startActivity(i);break;
        case R.id.card_workouts: i=new Intent(this,Workoutsessions.class );startActivity(i);break;
        case R.id.card_trainers: i=new Intent(this,Trainers.class);startActivity(i);break;
        default:break;



        }


    }
}
