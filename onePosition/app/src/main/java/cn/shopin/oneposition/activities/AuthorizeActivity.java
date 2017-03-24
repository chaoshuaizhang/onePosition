package cn.shopin.oneposition.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class AuthorizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onAuthentication();
    }

    public abstract void onAuthentication();
}
