package com.example.duan1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.Fragment.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin,btnRegister;
    EditText edtUsername,edtPassword;
    TextInputLayout textInputLayout;
    String username,password;
    FirebaseAuth firebaseAuth;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        edtUsername = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        textInputLayout = findViewById(R.id.pass);
        checkBox = findViewById(R.id.checkbox);
        LoadLogin();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    public void Login(){
        firebaseAuth = FirebaseAuth.getInstance();
        username = edtUsername.getText().toString();
        password = textInputLayout.getEditText().getText().toString();
        final boolean check = checkBox.isChecked();
        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    SaveUser(username,password,check);
                    Intent i2 = new Intent(LoginActivity.this, HomeAdmin.class);
                    startActivity(i2);
                    Toast.makeText(LoginActivity.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void SaveUser(String username, String password, boolean check){
        SharedPreferences preferences=getSharedPreferences("infoUser.dat",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(check){
            editor.putString("username",username);
            editor.putString("password",password);
            editor.putBoolean("check",check);
        }else{
            editor.clear();
        }editor.commit();
    }
    private void LoadLogin() {
        SharedPreferences pref = getSharedPreferences("infoUser.dat", MODE_PRIVATE);
        boolean check = pref.getBoolean("check", false);
        if (check) {
            edtUsername.setText(pref.getString("username", ""));
            edtPassword.setText(pref.getString("password", ""));
            checkBox.setChecked(check);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}