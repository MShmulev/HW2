package com.example.hw2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.hw2.databinding.ActivityMainBinding;
import com.example.hw2.http.user.Result;
import com.example.hw2.http.user.Root;
import com.example.hw2.http.user.UserAPIClient;
import com.example.hw2.http.user.UserService;
import com.example.hw2.room.UserDatabase;
import com.example.hw2.room.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private User CurrentUser;
    private UserDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.button2.setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = view -> {
       //  List<User> users = db.userDao().getUsers();
    };
    public void onNextUserButtonClick(View view) {

        MakeRequest();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MakeRequest();
    }

    private void MakeRequest() {
        Retrofit retrofit = UserAPIClient.getClient();
        UserService service = retrofit.create(UserService.class);
        Call<Root> callAsync = service.getUsers();
        callAsync.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Root root = response.body();
                    Result result = root.results.get(0);

                    String firstName = result.name.first;
                    String lastName = result.name.last;
                    int age = result.dob.age;
                    String email = result.email;
                    String city = result.location.city;
                    String country = result.location.country;
                    String picture = result.picture.large;


                    binding.textViewFirstName.setText(firstName);
                    binding.textViewLastName.setText(lastName);
                    binding.textViewAge.setText(String.valueOf(age));
                    binding.textViewEmail.setText(email);
                    binding.textViewCity.setText(city);
                    binding.textViewCountry.setText(country);
                    Glide.with(MainActivity.this).load(picture).into(binding.imageViewUser);

                    CurrentUser = new User();
                    CurrentUser.firstName = firstName;
                    CurrentUser.lastName = lastName;
                    CurrentUser.city = city;
                    CurrentUser.country = country;
                    CurrentUser.imageURL = result.picture.large;


                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch user data", Toast.LENGTH_SHORT).show();
                    CurrentUser = null;
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Failed to make request", Toast.LENGTH_SHORT).show();
                CurrentUser = null;
            }
        });
    }
}
