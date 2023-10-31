package com.example.bro;
import com.example.bro.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;


public class login_signup extends AppCompatActivity {

    //login
    TextInputEditText user,pswd;
    Button login_btn;

    //sign up
    TextInputEditText editFname, editLname, editNewUssername, editNewPassword, editConfirmPassword;
    RadioButton healthworker, labTech;
    Button signup_btn;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    DatabaseReference databaseReferenceURL = FirebaseDatabase.getInstance().getReferenceFromUrl("https://users-3aaf2-default-rtdb.firebaseio.com/");

    String role;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_login_signup);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        //initialize the components for sign up
        editFname = findViewById(R.id.f_name);
        editLname = findViewById(R.id.l_name);
        editNewUssername = findViewById(R.id.user_register);
        editNewPassword = findViewById(R.id.password_register);
        editConfirmPassword = findViewById(R.id.password_register2);
        signup_btn = findViewById(R.id.btn_signup);
        healthworker = findViewById(R.id.healthworker);
        labTech = findViewById((R.id.lab_technician));

        //initilize for login
         user = findViewById(R.id.user);
         pswd =findViewById(R.id.password);
         login_btn = findViewById(R.id.btn_login);

        //ensures only one box is selected
        healthworker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    role = "healthworker";
                    labTech.setChecked(false); // Uncheck labTech if healthworker is checked
                }
            }
        });

        labTech.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    role = "labTech";
                    healthworker.setChecked(false); // Uncheck healthworker if labTech is checked
                }
            }
        });

        //get user input LOGIN
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password;
                username = String.valueOf(user.getText());
                password = String.valueOf(pswd.getText());

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(login_signup.this, "Missing field! ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    databaseReferenceURL.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if username exists in the firebase database
                            if(snapshot.hasChild(username)) {
                                //if the username exists, make sure it matches the password

                                //gets the pswd from the corresponding user
                                final String getPassURL = snapshot.child(username).child("password").getValue(String.class);

                                if(getPassURL.equals(password)) {
                                    Toast.makeText(login_signup.this, "Sucesssfully logged in!", Toast.LENGTH_SHORT).show();

                                    //logs the user into the main page of the app
                                    Intent intent = new Intent(login_signup.this, main_Menu.class);
                                    startActivity(intent);
                                    finish();




                                }
                                else {
                                    Toast.makeText(login_signup.this, "Wrong Password!", Toast.LENGTH_SHORT).show();

                                }


                            } else {
                                Toast.makeText(login_signup.this, "Wrong Password/Username", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }



            }
        });


        //get user input SIGNUP
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String f_name, l_name, username, password, password2;
                f_name = String.valueOf(editFname.getText());
                l_name = String.valueOf(editLname.getText());
                username = String.valueOf(editNewUssername.getText());
                password = String.valueOf(editNewPassword.getText());
                password2 = String.valueOf(editConfirmPassword.getText().toString());

                //check if fields are empty
                if (TextUtils.isEmpty(f_name)) {
                    Toast.makeText(login_signup.this, "Enter First name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(l_name)) {
                    Toast.makeText(login_signup.this, "Enter Lasat name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(login_signup.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(login_signup.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password2)) {
                    Toast.makeText(login_signup.this, "Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!healthworker.isChecked() && !labTech.isChecked()) {
                    Toast.makeText(login_signup.this, "Choose your role", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (!password.equals(password2)) {
                    Toast.makeText(login_signup.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //since username is the key identifier; checks if account already exsists
                        if (snapshot.hasChild(username)) {
                            Toast.makeText(login_signup.this, "Username already exists, please choose another", Toast.LENGTH_SHORT).show();
                        } else {
                            // Create a user into the database
                            User newUser = new User(f_name, l_name, role, username, password);
                            databaseReference.child("users").child(username).setValue(newUser)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Successfulully saved in database
                                                Toast.makeText(login_signup.this, "Account created successfully!", Toast.LENGTH_SHORT).show();

                                            } else {
                                                // Did not saved in database
                                                Toast.makeText(login_signup.this, "Failed to create account. Try again.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }

                    @Override
                    //if the data dont go in the database
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle possible errors properly.

                        Toast.makeText(login_signup.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}


