package com.zyw.day03_c;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zyw.day03_c.bean.UserBean;
import com.zyw.day03_c.presenter.MyPresenter;
import com.zyw.day03_c.view.MyView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements MyView {

    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_passwordAgain)
    EditText etPasswordAgain;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private MyPresenter myPresenter;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        imagePath = "/storage/emulated/0/nn.jpg";
        ivImg.setImageURI(Uri.fromFile(new File(imagePath)));

        initPresenter();

    }

    private void initPresenter() {
        myPresenter = new MyPresenter(this);
    }

    private static final String TAG = "RegisterActivity";

    @OnClick(R.id.btn_register)
    public void onViewClicked() {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String passwordAgain = etPasswordAgain.getText().toString();

        if (!TextUtils.isEmpty(username)) {
            if (!TextUtils.isEmpty(password)) {
                if (!TextUtils.isEmpty(passwordAgain)) {

                    if (password.equals(passwordAgain)) {

                        UserBean userBean = new UserBean(username, password, imagePath);
                        myPresenter.onInsert(userBean);

                        Intent intent = new Intent();
                        intent.putExtra("name", username);

                        setResult(300, intent);

                        finish();
                    } else {
                        showToast("两次密码不一致");
                    }

                } else {
                    showToast("密码不能为空");
                }

            } else {
                showToast("密码不能为空");
            }

        } else {
            showToast("用户名不能为空");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInsertSuccess(String msg) {
        showToast(msg);
    }

    @Override
    public void onUpdateSuccess(String msg) {

    }

    @Override
    public void onDeleteSuccess(String msg) {

    }

    @Override
    public void onSearchSuucess(List<UserBean> data) {

    }

    @Override
    public void onFail(String msg) {

    }
}
