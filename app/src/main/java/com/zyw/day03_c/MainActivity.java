package com.zyw.day03_c;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zyw.day03_c.app.MyApp;
import com.zyw.day03_c.bean.UserBean;
import com.zyw.greendaodemo.db.UserBeanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private UserBeanDao userBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userBeanDao = MyApp.getInstance().getDaoSession().getUserBeanDao();
        initPer();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        etPassword.setText("");
    }

    private void initPer() {

        int result = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (result == PackageManager.PERMISSION_GRANTED) {
            //授权了
        } else {
            String[] per = {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };
            ActivityCompat.requestPermissions(this, per, 200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            showToast("用户未授权");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                initLogin();
                break;
            case R.id.btn_register:
                initRegister();
                break;
        }
    }

    private void initRegister() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

        startActivityForResult(intent, 200);

    }

    private void initLogin() {

        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        UserBean unique = userBeanDao.queryBuilder().where(UserBeanDao.Properties.Username.eq(username)).unique();
        if (!TextUtils.isEmpty(username)) {
            if (unique != null) {
                if (!TextUtils.isEmpty(password)) {
                    if (unique.getPassword().equals(password)) {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        showToast("登录成功");
                    } else {
                        showToast("密码错误");
                    }
                } else {
                    showToast("密码不能为空");
                }

            } else {
                showToast("账号不存在");
            }
        } else {
            showToast("账号不能为空");
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 300) {
            String name = data.getStringExtra("name");
            etUsername.setText(name);
            etPassword.setText("");
        }
    }
}
