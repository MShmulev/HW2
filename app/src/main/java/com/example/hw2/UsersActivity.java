package com.example.hw2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2.databinding.ActivityUsersBinding;
import com.example.hw2.recyclerview.UserRecyclerViewAdapter;
import com.example.hw2.room.User;
import com.example.hw2.room.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {
    private UserDatabase db;

    @Override
    protected void onResume() {
        super.onResume();
        List<User> all = db.userDao().getAll();
        List<com.example.hw2.User> users = new ArrayList<>();
        for(int i = 0; i < all.size(); i++) {
            User user = all.get(i);
            com.example.hw2.User tmp = new com.example.hw2.User(
                    user.firstName,user.lastName,-1,"",user.city,user.country,user.imageURL
            );
            users.add(tmp);
        }
        UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(users);
        binding.recyclerView.setAdapter(adapter);


    }

    ActivityUsersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = UserDatabase.getInstance(this);
        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        );
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}