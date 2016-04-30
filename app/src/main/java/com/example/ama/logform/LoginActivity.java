package com.example.ama.logform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText mEditTextLog;
    private  EditText mEditTextPassword;
    private Button mButtonEnter;
    private SharedPreferences mSharedPreferences;
    private static final String APP_SETTINGS = "LogFormSettings";
    private static final String APP_SETTINGS_KEY = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mButtonEnter = (Button) findViewById(R.id.btnEnter);
        mEditTextLog = (EditText) findViewById(R.id.edtLogin);
        mEditTextPassword = (EditText) findViewById(R.id.edtPassword);
        final String login = mEditTextLog.getText().toString();
        final Intent intent = new Intent(LoginActivity.this, AftorizationActivity.class);

        mSharedPreferences = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = mSharedPreferences.edit();

        if (mSharedPreferences.contains(APP_SETTINGS_KEY)){
            intent.putExtra(AftorizationActivity.EXTRA_LOG_NAME,
                            mSharedPreferences.getString(APP_SETTINGS_KEY,""));
            startActivity(intent);
        }else {
            mButtonEnter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString(APP_SETTINGS_KEY, login);
                    intent.putExtra(AftorizationActivity.EXTRA_LOG_NAME, login);
                    startActivity(intent);
                }
            });
        }
    }

    public static String getAppSettings() {
        return APP_SETTINGS;
    }

    public static String getAppSettingsKey() {
        return APP_SETTINGS_KEY;
    }
}
