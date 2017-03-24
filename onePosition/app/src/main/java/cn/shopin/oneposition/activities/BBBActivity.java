package cn.shopin.oneposition.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.shopin.oneposition.R;

public class BBBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbb);
        startActivity(new Intent(this, AAAActivity.class));
    }

    public void btnClick(View view) {
        startActivity(new Intent(this, AAAActivity.class));
    }
}
