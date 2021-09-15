package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Model.User;

public class RecyclerViewActivity extends AppCompatActivity implements onCardListener{

    private RecyclerView recyclerView_recyclerview;
    private FloatingActionButton recyclerView_FAB_add;
    private ArrayList<User> listUser;
    private UserRVadapter adapter;
    private TextView recyclerView_textView_nodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylerview);
        initView();
        loadListUser();
        setupRecyclerView();
        setListener();
    }

    private void loadListUser() {
        Intent intent = getIntent();
        listUser = intent.getParcelableArrayListExtra("listUser");
        if(listUser == null){
            listUser = new ArrayList<User>();
        }

        if(listUser.size() == 0){
            recyclerView_textView_nodata.setVisibility(View.VISIBLE);
        }else{
            recyclerView_textView_nodata.setVisibility(View.INVISIBLE);
        }
        adapter = new UserRVadapter(listUser, this);
    }


    private void setListener() {
        recyclerView_FAB_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), InputUserActivity.class);
                intent.putParcelableArrayListExtra("listUser", listUser);
                startActivity(intent);
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        recyclerView_recyclerview.setLayoutManager(manager);
        recyclerView_recyclerview.setAdapter(adapter);
    }

    private void initView() {
        recyclerView_recyclerview = findViewById(R.id.recyclerView_recyclerview);
        recyclerView_FAB_add = findViewById(R.id.recyclerView_FAB_add);
        recyclerView_textView_nodata = findViewById(R.id.recyclerView_textView_nodata);
        //listUser = new ArrayList<User>();

    }

    @Override
    public void onCardClick(int position) {
        //Intent intent = new Intent(getBaseContext(), DetailUser.class);
        //intent.putExtra("id", position);
        //startActivity(intent);
    }
}