package com.example.editdelete;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserDetail extends AppCompatActivity {

    private ArrayList<user> mExampleList = UserData.DataUser;
    Intent intent;
    Button button_delete;
    Button button_edit;
    int list;
    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        toolbar = findViewById(R.id.toolbar_detail);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetail.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        });

        intent = getIntent();
        list = intent.getIntExtra("position", 0);

        mExampleList.get(list).getName();
        mExampleList.get(list).getAge();
        mExampleList.get(list).getAddress();

        TextView set_name = (TextView) findViewById(R.id.name_detail);
        set_name.setText(mExampleList.get(list).getName());
        TextView set_age = (TextView) findViewById(R.id.age_detail);
        set_age.setText(mExampleList.get(list).getAge());
        TextView set_address = (TextView) findViewById(R.id.address_detail);
        set_address.setText(mExampleList.get(list).getAddress());

        button_delete = findViewById(R.id.button_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmdelete();
            }
        });

        button_edit = findViewById(R.id.button_edit);
        button_edit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetail.this, AddUser.class);
                intent.putExtra("mExampleList", mExampleList.get(list));
                intent.putExtra("position", list);
                startActivity(intent);
                finish();
            }
        });
    }

    public void confirmdelete(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:

                        mExampleList.remove(list);
                        Log.d("test", String.valueOf(list));

                        Toast.makeText(UserDetail.this, "Delete Success",
                                Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(UserDetail.this, MainActivity.class);
                        startActivity(intent);

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialogInterface.dismiss();
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to delete " + mExampleList.get(list).getName() + " data?")
                .setIcon(R.drawable.ic_baseline_account_circle_24)
                .setTitle("Confirmation")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public boolean doublebackToExitPressedOnce = false;
    @Override
    protected void onResume(){
        super.onResume();
        this.doublebackToExitPressedOnce = false;
    }

    @Override
    public void onBackPressed() {
        if (doublebackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
        }
        this.doublebackToExitPressedOnce = true;
        Toast.makeText(this, "Press back once again to exit the apps!", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doublebackToExitPressedOnce = false;
            }
        }, 5000);
    }
}