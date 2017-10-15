package com.inkubator.adryan.learnarabic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.transitionseverywhere.*;

import com.inkubator.adryan.learnarabic.R;

/**
 * Created by adryanev on 15/10/17.
 */

public class LoginActivity extends AppCompatActivity{

    EditText username,password;
    TextView register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        register = (TextView) findViewById(R.id.tvregister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                 startActivity(i);
                 LoginActivity.this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

            }
        });
        login();
    }

    private void login() {

    }
}
