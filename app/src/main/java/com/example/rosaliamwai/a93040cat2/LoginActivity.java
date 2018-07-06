package com.example.rosaliamwai.a93040cat2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private EditText etEmail;
    private EditText etPassword;
    private TextView txtlogin;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth= FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){

            finish();
            startActivity(new Intent(getApplicationContext(),HomePage.class));


        }

        btn_login= (Button) findViewById(R.id.btn_login);
        etEmail=(EditText) findViewById(R.id.etEmail);
        etPassword=(EditText) findViewById(R.id.etPassword);
        txtlogin=(TextView)findViewById(R.id.txtlog);

        btn_login.setOnClickListener(this);

    }

    private void LoginUser(){
        String email=etEmail.getText().toString().trim();
        String password=etEmail.getText().toString().trim();

        if (email.isEmpty()) {

            etEmail.setError(getString(R.string.input_error_email));
            etEmail.requestFocus();
            return;

        }
        if (password.isEmpty()) {

            etPassword.setError(getString(R.string.input_error_password));
            etPassword.requestFocus();
            return;

        }

            firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),HomePage.class));

                        }
                    }
                });

    }


    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()!=null) {

            finish();
            startActivity(new Intent(getApplicationContext(), HomePage.class));


        }
        }

    @Override
    public void onClick(View v) {
        if(v==btn_login){

            LoginUser();
        }
    }
}
