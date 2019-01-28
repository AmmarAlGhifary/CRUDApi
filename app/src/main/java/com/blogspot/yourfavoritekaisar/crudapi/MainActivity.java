package com.blogspot.yourfavoritekaisar.crudapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.blogspot.yourfavoritekaisar.crudapi.Adapter.userAdapter;
import com.blogspot.yourfavoritekaisar.crudapi.api.ApiClient;
import com.blogspot.yourfavoritekaisar.crudapi.api.ApiInterface;
import com.blogspot.yourfavoritekaisar.crudapi.model.User.UserData;
import com.blogspot.yourfavoritekaisar.crudapi.model.User.UserResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    // TODO 1 Membuat variable yg dibutuhkan
    // Membuat variable List
    private List<UserData>userDataList;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        userDataList = new ArrayList<>();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        showProgress();

        // TODO 2 Mengambil data dari Server Rest API
        getData();

    }

    private void showProgress() {
        // Membuat object progress dailog untuk  kita gunakan
        progressDialog = new ProgressDialog(MainActivity.this);
        // Mengatur Message
        progressDialog.setMessage("Loading CUkk");
        progressDialog.show();

    }

    private void getData() {
        // Membuat object Api User Interface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        // Membuat object call
        retrofit2.Call<UserResponse> call = apiInterface.getUser(12);
        //Enqueue
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(retrofit2.Call<UserResponse> call, Response<UserResponse> response) {
                // Respon sukses, hilangkan progress dialog
                progressDialog.dismiss();

                // Menghilangkan Icon refresh
                swipeRefresh.setRefreshing(false);

                // Mengambil Body Object Untuk response
                UserResponse userResponse = response.body();

                // Mengabil json aray dgn nama data
                userDataList = userResponse.getData();

                // Mensetting layout recyclerview
                rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                // Memasukan context dan data ke adapter
                rvUser.setAdapter(new userAdapter(MainActivity.this, userDataList));

            }

            @Override
            public void onFailure(retrofit2.Call<UserResponse> call, Throwable t) {
                // Respon sukses, hilangkan progress dialog
                progressDialog.dismiss();

                // Menghilangkan icon refresh
                swipeRefresh.setRefreshing(false);

                // Menampilkan error dari server
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}

