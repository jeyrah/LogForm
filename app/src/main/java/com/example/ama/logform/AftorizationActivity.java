package com.example.ama.logform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AftorizationActivity extends AppCompatActivity {
    public static final String EXTRA_LOG_NAME = "Name";
    private TextView mTextViewName;
    private Button mButtonClearAft;
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftorization);

        String name = getIntent().getStringExtra(AftorizationActivity.EXTRA_LOG_NAME);

        mTextViewName = (TextView) findViewById(R.id.txtName);
        mTextViewName.setText(name);

        mSharedPreferences = getSharedPreferences(LoginActivity.getAppSettings(), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = mSharedPreferences.edit();
        mButtonClearAft = (Button) findViewById(R.id.btnClearAft);
        mButtonClearAft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove(LoginActivity.getAppSettingsKey()).apply();
                Intent intent = new Intent(AftorizationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
