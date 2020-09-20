package com.example.editdelete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private ArrayList<user> mExampleList;
    FloatingActionButton button_add;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExampleList = UserData.DataUser;


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter = new ExampleAdapter(MainActivity.this);
        mAdapter.setmExampleList(mExampleList);

        mRecyclerView.setAdapter(mAdapter);

        button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUser.class);
                startActivity(intent);
            }
        });
    }

        public boolean doubleBackToExitPressedOnce = false;
        @Override
        protected void onResume(){
            super.onResume();
            this.doubleBackToExitPressedOnce = false;
        }

        @Override
        public void onBackPressed(){
            if(doubleBackToExitPressedOnce){
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(a);
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(MainActivity.this, "Press back again to close the app", Toast.LENGTH_SHORT).show();
        }

}