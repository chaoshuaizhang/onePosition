package cn.shopin.oneposition.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.activities.main.MainActivity;

public class LoginActivity extends AuthorizeActivity {

    @Override
    public void onAuthentication() {
        setContentView(R.layout.activity_login);
    }

    public void btnClick(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}
