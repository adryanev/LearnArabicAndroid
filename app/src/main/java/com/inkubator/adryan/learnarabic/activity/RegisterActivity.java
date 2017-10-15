package com.inkubator.adryan.learnarabic.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.inkubator.adryan.learnarabic.R;
import com.inkubator.adryan.learnarabic.rest.ApiClient;
import com.inkubator.adryan.learnarabic.rest.ApiInterface;
import com.inkubator.adryan.learnarabic.utils.StringEncryption;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by adryanev on 15/10/17.
 */

public class RegisterActivity extends AppCompatActivity{

    TextView login;
    EditText nama, email, username, password, etTanggal;
    Button register, btnTanggal;
    private int mYear, mMonth, mDay;
    ApiInterface apiService;
    Context context;
    ProgressDialog loading;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        context = this;
        apiService = ApiClient.getClient().create(ApiInterface.class);
        login = (TextView) findViewById(R.id.tv_login);
        nama = (EditText)findViewById(R.id.namaLengkap);
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.etusername);
        password = (EditText) findViewById(R.id.etpassword);
        etTanggal = (EditText) findViewById(R.id.in_date);
        register = (Button) findViewById(R.id.register_button);
        btnTanggal = (Button) findViewById(R.id.btn_date);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(context,null, "Sedang mendaftarkan user...",true,false);
                try {
                    requestRegister();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
        btnTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == btnTanggal){
                    //Get Current Date
                    final Calendar today = Calendar.getInstance();
                    mYear = today.get(Calendar.YEAR);
                    mMonth = today.get(Calendar.MONTH);
                    mDay = today.get(Calendar.DAY_OF_MONTH);

                    showDialog(DATE_DIALOG_ID);


                }
        }});

    }

    private void requestRegister() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String stringNama = nama.getText().toString();
        final String stringUsername = username.getText().toString();
        String stringPassword = StringEncryption.SHA1(password.getText().toString());
        String stringEmail = email.getText().toString();
        String stringTanggalLahir = etTanggal.getText().toString();
        apiService.registerRequest(stringNama,stringUsername,stringPassword,stringEmail,stringTanggalLahir).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Log.i("debug", "onResponse: BERHASIL");
                    loading.dismiss();
                    try {
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if(jsonResult.getString("status").equals("success")){
                            Toast.makeText(context,"Sukses Registrasi "+ stringUsername,Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context,LoginActivity.class));
                            RegisterActivity.this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);


                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else{
                    Log.i("debug", "onResponse: GA BERHASIL");
                    loading.dismiss();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(context, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateDate() {
        this.etTanggal.setText(new StringBuilder().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            updateDate();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }
}
