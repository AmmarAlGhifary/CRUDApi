package com.blogspot.yourfavoritekaisar.crudapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.yourfavoritekaisar.crudapi.api.ApiClient;
import com.blogspot.yourfavoritekaisar.crudapi.api.ApiInterface;
import com.blogspot.yourfavoritekaisar.crudapi.model.Login.LoginBody;
import com.blogspot.yourfavoritekaisar.crudapi.model.Login.LoginResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtEmail)
    EditText edtEmail;

    private ProgressDialog progressDialog;
    private LoginBody loginBody;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        showProgress();
        getData();
        login();
    }

    private void login() {
         // Membuat object apiInterface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> call = apiInterface.postLogin(loginBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();

                if (response.body().getError() == null && response.body().getToken() != null){
                    // Menampilkan response api berupa token kedalam toast
                    Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                }

                // Menampilkan response message
                Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();


                // Menampilkan response api berupa token ke dalam toast
                Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();

                // menampilkan response message
                Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();

                // Berpindah halaman ke mainActivity
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getData() {
        // Buat object login body
        loginBody = new LoginBody();
        // Mengisi loginbody
        loginBody.setEmail(edtEmail.getText().toString());
        loginBody.setPassword(edtPassword.getText().toString());
    }

    private void showProgress() {
        // Membuat object progress dialog
        progressDialog = new ProgressDialog(LoginActivity.this);
        // Menambahkan message pada loading
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Harap Tunggu");
        progressDialog.setMessage("Loading ...");
        // Menampilkan progress dialog
        progressDialog.show();
    }
}
