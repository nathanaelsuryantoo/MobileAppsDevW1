package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import Model.User;

public class InputUserActivity extends AppCompatActivity {
    private TextInputLayout addedit_textView_nama, addedit_textView_umur, addedit_textView_alamat;
    private Button addedit_button_simpan;
    private Toolbar addedit_toolbar;
    ArrayList<User> listUser;
    final LoadingDialog loadingDialog = new LoadingDialog(InputUserActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_user);
        initView();
        setListener();
    }

    private void setListener() {
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        if(intent.getStringExtra("keterangan") != null) {
            listUser = intent.getParcelableArrayListExtra("listUser");
            addedit_textView_nama.getEditText().setText(listUser.get(position).getNama());
            addedit_textView_umur.getEditText().setText(listUser.get(position).getUmur());
            addedit_textView_alamat.getEditText().setText(listUser.get(position).getAlamat());
            addedit_button_simpan.setText("Update Data");
            addedit_toolbar.setTitle("Edit User");
        }
        addedit_button_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                if(intent.getStringExtra("keterangan") != null){

                    String nama = addedit_textView_nama.getEditText().getText().toString().trim();
                    String umur = addedit_textView_umur.getEditText().getText().toString().trim();
                    String alamat = addedit_textView_alamat.getEditText().getText().toString().trim();
                    if (nama != null && alamat != null && umur != null) {
                        loadingDialog.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismissDialog();
                                User userBaru = new User(nama, umur, alamat);
                                listUser.set(position, userBaru);
                                Intent intent = new Intent(getBaseContext(), RecyclerViewActivity.class);
                                intent.putParcelableArrayListExtra("listUser", listUser);
                                startActivity(intent);
                                finish();
                            }
                        }, 2000);

                    }
                }else {
                    intent = getIntent();
                    listUser = intent.getParcelableArrayListExtra("listUser");
                    String nama = addedit_textView_nama.getEditText().getText().toString().trim();
                    String umur = addedit_textView_umur.getEditText().getText().toString().trim();
                    String alamat = addedit_textView_alamat.getEditText().getText().toString().trim();
                    if (nama != null && alamat != null && umur != null) {
                        loadingDialog.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadingDialog.dismissDialog();
                                User userBaru = new User(nama, umur, alamat);
                                listUser.add(userBaru);
                                Intent intent = new Intent(getBaseContext(), RecyclerViewActivity.class);
                                intent.putParcelableArrayListExtra("listUser", listUser);
                                startActivity(intent);
                                finish();
                            }
                        }, 2000);

                    }
                }

            }
        });
        addedit_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }

    private void initView() {
        addedit_textView_nama = findViewById(R.id.addedit_input_nama);
        addedit_textView_umur = findViewById(R.id.addedit_input_umur);
        addedit_textView_alamat = findViewById(R.id.addedit_input_alamat);
        addedit_button_simpan = findViewById(R.id.addedit_button_simpan);
        addedit_toolbar = findViewById(R.id.addedit_toolbar);
        listUser = new ArrayList<User>();

    }
}