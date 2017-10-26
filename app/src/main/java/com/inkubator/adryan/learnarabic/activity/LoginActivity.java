package com.inkubator.adryan.learnarabic.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inkubator.adryan.learnarabic.database.DbHelper;
import com.inkubator.adryan.learnarabic.model.User;
import com.inkubator.adryan.learnarabic.response.ResponseLogin;
import com.inkubator.adryan.learnarabic.rest.ApiClient;
import com.inkubator.adryan.learnarabic.rest.ApiInterface;
import com.inkubator.adryan.learnarabic.utils.SessionManager;
import com.inkubator.adryan.learnarabic.utils.StringEncryption;
import com.transitionseverywhere.*;

import com.inkubator.adryan.learnarabic.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adryanev on 15/10/17.
 */

public class LoginActivity extends AppCompatActivity{

    EditText username,password;
    TextView register;
    Button loginButton;
    ProgressDialog loading;
    Context context;
    ApiInterface apiService;
    SessionManager session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        register = (TextView) findViewById(R.id.tvregister);
        context = this;
        apiService = ApiClient.getClient().create(ApiInterface.class);
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(context,null,"Sedang login...",true,false);
                try {

                    login();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                 startActivity(i);
                 LoginActivity.this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

            }
        });
    }

    private void login() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        try {
            requestLogin();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void requestLogin() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        final String stringUsername = username.getText().toString();
        String stringPassword = StringEncryption.SHA1(password.getText().toString());

        apiService.loginRequest(stringUsername, stringPassword).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    Log.i("Login", "onResponse: Success ");
                    loading.dismiss();

                    String success = response.body().getStatus();
                    List<User> user = response.body().getData();
                    if(success.equals("success")){
                        session.createLoginSession(user.get(0).getIdUser().toString(),user.get(0).getUsername(),user.get(0).getPassword(),
                                user.get(0).getNama(),user.get(0).getEmail(),user.get(0).getTanggalLahir());

                        Toast.makeText(context,"Sukses Login "+ user.get(0).getNama(),Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(context,MainActivity.class));

                        LoginActivity.this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);


                    }else{
                        Toast.makeText(context,"Gagal Login User",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Log.i("debug", "onResponse: GA BERHASIL");
                    loading.dismiss();

                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(context, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

}
