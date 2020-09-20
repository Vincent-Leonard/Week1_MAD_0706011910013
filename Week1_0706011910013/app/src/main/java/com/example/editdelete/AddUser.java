package com.example.editdelete;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddUser extends AppCompatActivity implements TextWatcher {

    TextInputLayout input_name_var, input_age_var, input_address_var;
    Button button_add_user;
    String name, age, address;
    Toolbar toolbar;
    ProgressDialog progressDialog;
    private ArrayList<user> mExampleList = UserData.DataUser;
    String list;
    Intent intent;
    Intent intent2;
    int daftar;
    user user;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        input_name_var =findViewById(R.id.input_name);
        input_age_var =findViewById(R.id.input_age);
        input_address_var =findViewById(R.id.input_address);
        button_add_user = findViewById(R.id.button_add_user);

        toolbar = findViewById(R.id.toolbar_add);

        input_name_var.getEditText().addTextChangedListener(this);
        input_age_var.getEditText().addTextChangedListener(this);
        input_address_var.getEditText().addTextChangedListener(this);

        intent = getIntent();
        user = intent.getParcelableExtra("mExampleList");

        intent2 = getIntent();
        daftar = intent2.getIntExtra("position", 0);

        if(user == null){
            toolbar.setTitle("Add User");
            button_add_user.setText("Save");
        } else{
            toolbar.setTitle("Edit User");
            button_add_user.setText("Update");
            TextInputLayout set_name = (TextInputLayout) findViewById(R.id.input_name);
            set_name.getEditText().setText(mExampleList.get(daftar).getName());
            TextInputLayout set_age = (TextInputLayout) findViewById(R.id.input_age);
            set_age.getEditText().setText(mExampleList.get(daftar).getAge());
            TextInputLayout set_address = (TextInputLayout) findViewById(R.id.input_address);
            set_address.getEditText().setText(mExampleList.get(daftar).getAddress());
        }

        button_add_user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (user == null){
                    user orang = new user(name, address, age);
                    Intent intent = new Intent(AddUser.this, MainActivity.class);
                    UserData.DataUser.add(new user(name, age, address));
                    intent.putExtra("dataUser", orang);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else{
                    mExampleList.get(daftar).setName(name);
                    mExampleList.get(daftar).setAge(age);
                    mExampleList.get(daftar).setAddress(address);
                    Intent intent = new Intent(AddUser.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null){
                    Intent intent1 = new Intent(AddUser.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                }else{
                    Intent intent1 = new Intent(AddUser.this, UserDetail.class);
                    intent1.putExtra("position", daftar);
                    startActivity(intent1);
                    finish();
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        name = input_name_var.getEditText().getText().toString().trim();
        address = input_address_var.getEditText().getText().toString().trim();
        age = input_age_var.getEditText().getText().toString().trim();

        if(!name.isEmpty() && !address.isEmpty() && !age.isEmpty()){
            button_add_user.setEnabled(true);
        }else{
            button_add_user.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
