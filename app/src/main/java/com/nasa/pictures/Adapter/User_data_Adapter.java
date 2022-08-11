package com.nasa.pictures.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nasa.pictures.Model.User_Model;
import com.nasa.pictures.R;
import com.nasa.pictures.ShowfullDetailPic;
import com.nasa.pictures.databinding.ItemlistBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class User_data_Adapter extends RecyclerView.Adapter<User_data_Adapter.User_list_VewHolder> {


    Context context;
    ArrayList<User_Model> list;

    public User_data_Adapter(Context context, ArrayList<User_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public User_list_VewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemlist, null);
        return new User_list_VewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull User_list_VewHolder holder, int position) {

        User_Model userModel = list.get(position);

        holder.binding.descrip.setText(userModel.getName());

        Glide.with(context)
                .load(userModel.getImage())
                .placeholder(R.drawable.fl)
                .into(holder.binding.image);


        holder.binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowfullDetailPic.class);
                intent.putExtra("name", userModel.getName());
                intent.putExtra("image", userModel.getImage());
                intent.putExtra("date", userModel.getDate());
                intent.putExtra("copyright", userModel.getCopyright());
                intent.putExtra("descriptions", userModel.getDescriptions());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class User_list_VewHolder extends RecyclerView.ViewHolder {
        ItemlistBinding binding;

        public User_list_VewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binding = ItemlistBinding.bind(itemView);

        }
    }
}
