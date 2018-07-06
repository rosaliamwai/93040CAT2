package com.example.rosaliamwai.a93040cat2;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editFirstname,editLastname,editEmail,editPassword,etage,etgender,etweight,ettargetweight;
    private TextView txtregister;
    private Button btnsubmit;


    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth= FirebaseAuth.getInstance();


        editFirstname= (EditText) findViewById(R.id.etFirstname);
        editLastname=(EditText) findViewById(R.id.etLastname);
        editEmail=(EditText) findViewById(R.id.etEmail);
        editPassword=(EditText)findViewById(R.id.etPassword);
        etage=(EditText) findViewById(R.id.etage);
        etgender=(EditText) findViewById(R.id.etgender);
        etweight=(EditText)findViewById(R.id.etweight);
        ettargetweight=(EditText)findViewById(R.id.ettargetweight);
        btnsubmit=(Button) findViewById(R.id.btn_submit);
        txtregister=(TextView) findViewById(R.id.txtreg);

        btnsubmit.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()!=null){

        }

    }

    private void registerUser() {
        final String firstname = editFirstname.getText().toString().trim();
        final String lastname = editLastname.getText().toString().trim();
        final String email = editEmail.getText().toString().trim();
        final String age = etage.getText().toString().trim();
        final String gender = etgender.getText().toString().trim();
        final String weight = etweight.getText().toString().trim();
        final String targetweight = ettargetweight.getText().toString().trim();
        String password = editPassword.getText().toString().trim();


        if (firstname.isEmpty()) {

            editFirstname.setError(getString(R.string.input_error_firstname));
            editFirstname.requestFocus();
            return;

        }

        if (lastname.isEmpty()) {

            editLastname.setError(getString(R.string.input_error_lastname));
            editLastname.requestFocus();
            return;

        }
        if (email.isEmpty()) {

            editEmail.setError(getString(R.string.input_error_email));
            editEmail.requestFocus();
            return;

        }
        if (password.isEmpty()) {

            editPassword.setError(getString(R.string.input_error_password));
            editPassword.requestFocus();
            return;

        }

        if (age.isEmpty()) {

            etage.setError(getString(R.string.input_error_age));
            etage.requestFocus();
            return;

        }

        if (gender.isEmpty()) {

            etgender.setError(getString(R.string.input_error_gender));
            etgender.requestFocus();
            return;

        }
        if (weight.isEmpty()) {

            etweight.setError(getString(R.string.input_error_weight));
            etweight.requestFocus();
            return;

        }
        if (targetweight.isEmpty()) {

            ettargetweight.setError(getString(R.string.input_error_targetweight));
            ettargetweight.requestFocus();
            return;

        }


        if (password.length() < 8) {
            editPassword.setError(getString(R.string.input_error_password_length));
            editPassword.requestFocus();
            return;
        }


            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        User user= new User(
                                firstname,
                                lastname,
                                email,
                                age,
                                gender,
                                weight,
                                targetweight
                        );


                        FirebaseDatabase.getInstance().getReference("Users_93040")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, getString(R.string.registration_success), Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                                } else {

                                }
                            }
                        });

                        } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                }


            });

        }
    @Override
    public void onClick(View v) {
        if(v == btnsubmit){
            registerUser();
            finish();
            startActivity(new Intent(getApplicationContext(), HomePage.class));

        }

    }
}
