package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Model.User;

public class DetailUser extends AppCompatActivity {
    private TextView detail_textView_nama, detail_textView_umur, detail_textView_alamat;
    private FloatingActionButton detail_FAB_edit, detail_FAB_delete;
    private int position;
    private Toolbar detail_toolbar;
    ArrayList<User> listUser;
    final LoadingDialog loadingDialog = new LoadingDialog(DetailUser.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        initView();
        getData();
        setListener();
    }

    private void setListener() {
        detail_FAB_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), InputUserActivity.class);
                intent.putExtra("keterangan", "edit");
                intent.putExtra("position", position);
                intent.putParcelableArrayListExtra("listUser", listUser);
                startActivity(intent);
            }
        });
        detail_FAB_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DetailUser.this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("Konfirmasi " )
                        .setMessage("Are you sure to delete "+ listUser.get(position).getNama()+" data?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listUser.remove(position);
                                loadingDialog.startLoadingDialog();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadingDialog.dismissDialog();
                                        Toast.makeText(DetailUser.this, "Delete Sucesss",
                                                Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getBaseContext(), RecyclerViewActivity.class);
                                        intent.putParcelableArrayListExtra("listUser", listUser);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, 2000);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });
        detail_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData() {
        Intent intent = getIntent();
        listUser = intent.getParcelableArrayListExtra("listUser");
        position = intent.getIntExtra("position", 0);
        detail_textView_nama.setText(listUser.get(position).getNama());
        detail_textView_umur.setText(listUser.get(position).getUmur());
        detail_textView_alamat.setText(listUser.get(position).getAlamat());
    }

    private void initView() {
        detail_textView_nama = findViewById(R.id.detail_textView_nama);
        detail_textView_umur = findViewById(R.id.detail_textView_umur);
        detail_textView_alamat = findViewById(R.id.detail_textView_alamat);
        detail_FAB_edit = findViewById(R.id.detail_FAB_edit);
        detail_FAB_delete = findViewById(R.id.detail_FAB_delete);
        detail_toolbar = findViewById(R.id.detail_toolbar);
    }
}