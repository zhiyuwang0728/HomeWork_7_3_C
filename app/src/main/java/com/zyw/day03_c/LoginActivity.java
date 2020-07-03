package com.zyw.day03_c;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.zyw.day03_c.adapter.DataAdapter;
import com.zyw.day03_c.bean.UserBean;
import com.zyw.day03_c.presenter.MyPresenter;
import com.zyw.day03_c.view.MyView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements MyView {

    @BindView(R.id.mRecycler)
    RecyclerView mRecycler;
    private MyPresenter myPresenter;
    private DataAdapter dataAdapter;

    private UserBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        registerForContextMenu(mRecycler);
        initView();
        initPresenter();

    }

    private void initPresenter() {
        myPresenter = new MyPresenter(this);
        myPresenter.onSearch();
    }

    private void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        dataAdapter = new DataAdapter(this);
        mRecycler.setAdapter(dataAdapter);

        dataAdapter.setOnClickItem(new DataAdapter.OnClickItem() {
            @Override
            public void onClick(UserBean userBean) {
                bean = userBean;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, 1, 1, "删除");
        menu.add(1, 2, 1, "修改");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                initDelete();
                break;
            case 2:
                initUpdate();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void initUpdate() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop, null);
        final EditText et_name = inflate.findViewById(R.id.et_name);
        Button btn_update = inflate.findViewById(R.id.btn_update);
        Button btn_cancel = inflate.findViewById(R.id.btn_cancel);

        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setAnimationStyle(R.style.myPop);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        setAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAlpha(1f);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_name.getText().toString();
                bean.setUsername(username);
                myPresenter.onUpdate(bean);
                myPresenter.onSearch();
                popupWindow.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        popupWindow.showAtLocation(mRecycler, Gravity.CENTER, 100, 100);

    }

    public void setAlpha(float alpha) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = alpha;
        getWindow().setAttributes(attributes);
    }

    private void initDelete() {
        myPresenter.onDelete(bean);
        myPresenter.onSearch();
    }

    @Override
    public void onInsertSuccess(String msg) {

    }

    @Override
    public void onUpdateSuccess(String msg) {
        showToast(msg);
    }

    @Override
    public void onDeleteSuccess(String msg) {
        showToast(msg);
    }

    @Override
    public void onSearchSuucess(List<UserBean> data) {
        dataAdapter.addData(data);
    }

    @Override
    public void onFail(String msg) {
        showToast(msg);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
