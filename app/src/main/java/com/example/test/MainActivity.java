package com.example.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author LightDance
 */
public class MainActivity extends AppCompatActivity {

    private Button mBtnLogin, mBtnSignUp;
    private EditText mUsername;
    private EditText mPassword;
    private ImageView mImgLeft;
    private ImageView mImgRight;

    //记住密码相关
    private CheckBox mCbRememberPassword;

    /**本地持久化的类，通常记录少量、轻量级的数据*/
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        initView();
        fillInUserInfo();
        setListener();
    }

    private void fillInUserInfo() {
        //看看记没记住
        boolean isRemember=pref.getBoolean("remember_password",false);
        //记住了，填上
        if (isRemember){
            String username=pref.getString("username","default");
            String password=pref.getString("password","default");
            mUsername.setText(username);
            mPassword.setText(password);
            mCbRememberPassword.setChecked(true);
        }
    }

    /**设置监听器*/
    private void setListener() {
        //登录，跳转界面
        mBtnLogin.setOnClickListener(v -> {
            String username = mUsername.getText().toString();
            String password = mPassword.getText().toString();
            if (username.equals(getResources().getString(R.string.mock_username)) && password.equals(getResources().getString(R.string.mock_password))) {
                if (mCbRememberPassword.isChecked()){
                    //如果选中记住密码则将密码持久化保存下来
                    rememberLoginInfo(username , password);
                }
                //跳转Activity
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                //启动新的activity并销毁登录activity(因为用不着了)
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }

        });

        //妹子捂脸(图像变换)
        mPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                mImgLeft.setImageResource(R.drawable.img_left_hide);
                mImgRight.setImageResource(R.drawable.img_right_hide);
            }else {
                mImgLeft.setImageResource(R.drawable.img_left);
                mImgRight.setImageResource(R.drawable.img_right);
            }
        });
        //注册
        mBtnSignUp.setOnClickListener(v -> {
            Toast.makeText(this, "注册功能敬请期待", Toast.LENGTH_SHORT).show();
        });
    }

    /**记住密码*/
    private void rememberLoginInfo(String username, String password) {
        SharedPreferences.Editor editor = pref.edit();
        if(mCbRememberPassword.isChecked())
        {
            //自动填上信息，并勾选CheckBox
            editor.putBoolean("remember_password",true);
            editor.putString("username",username);
            editor.putString("password",password);
        }else
        {
            editor.clear();
        }
        editor.apply();
    }

    /**初始化界面*/
    private void initView() {
        mUsername = findViewById(R.id.login_username);
        mPassword = findViewById(R.id.login_password);
        mImgLeft = findViewById(R.id.img_left);
        mImgRight = findViewById(R.id.img_right);
        mCbRememberPassword = findViewById(R.id.remember_password);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnSignUp = findViewById(R.id.btn_sign_up);

    }
}
