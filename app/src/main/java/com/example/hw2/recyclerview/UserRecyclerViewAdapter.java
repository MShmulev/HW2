package com.example.hw2.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hw2.R;
import com.example.hw2.User;
import com.example.hw2.databinding.LayoutUserBinding;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final List<User> dataSet;
    private LayoutUserBinding binding;

    public UserRecyclerViewAdapter(@NonNull List<User> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = LayoutUserBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new UserViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = dataSet.get(position);
        Glide.with(holder.itemView).load(user.getProfileImage())
                .apply(new RequestOptions())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(binding.imageViewUserIcon);
        binding.textViewUserFirstName.setText(user.getFirstName());
        binding.textViewUserLastName.setText(user.getLastName());
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        binding.textViewUserCountry.setText(user.getCountry());
        binding.textViewUserCity.setText((user.getCity()));






    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
