package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.User;

public class UserRVadapter extends RecyclerView.Adapter<UserRVadapter.UserViewHolder> {

    private ArrayList<User> listUser;
    protected onCardListener cardListener;

    public UserRVadapter(ArrayList<User> listUser, onCardListener cardListener){
        this.listUser = listUser;
        this.cardListener = cardListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.carduser, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.cardView_nama.setText(listUser.get(position).getNama());
        holder.cardView_umur.setText(listUser.get(position).getUmur());
        holder.cardView_alamat.setText(listUser.get(position).getAlamat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailUser.class);
                //intent.putExtra("nama", listUser.get(position).getNama());
                //intent.putExtra("umur", listUser.get(position).getUmur());
               //intent.putExtra("alamat", listUser.get(position).getAlamat());
                intent.putParcelableArrayListExtra("listUser", listUser);
                intent.putExtra("position", position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView cardView_nama, cardView_umur, cardView_alamat;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView_nama = itemView.findViewById(R.id.cardView_nama);
            cardView_umur = itemView.findViewById(R.id.cardView_umur);
            cardView_alamat = itemView.findViewById(R.id.cardView_alamat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
