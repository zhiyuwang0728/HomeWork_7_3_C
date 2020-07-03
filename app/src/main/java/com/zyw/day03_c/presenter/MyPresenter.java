package com.zyw.day03_c.presenter;

import com.zyw.day03_c.bean.UserBean;
import com.zyw.day03_c.model.MyModel;
import com.zyw.day03_c.view.MyView;
import com.zyw.day03_c.view.ResultCallBack;

import java.util.List;

public class MyPresenter {

    private final MyView view;
    private final MyModel myModel;

    public MyPresenter(MyView view) {
        this.view = view;
        myModel = new MyModel();
    }

    public void onInsert(UserBean userBean) {
        myModel.onInsert(userBean, new ResultCallBack<String>() {

            @Override
            public void onSuccess(String s) {
                view.onInsertSuccess(s);
            }

            @Override
            public void onFail(String msg) {
                view.onFail(msg);
            }
        });

    }

    public void onUpdate(UserBean userBean) {
        myModel.onUpdate(userBean, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String o) {
                view.onUpdateSuccess(o);
            }

            @Override
            public void onFail(String msg) {
                view.onFail(msg);
            }
        });
    }

    public void onDelete(UserBean userBean) {

        myModel.onDelete(userBean, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String o) {
                view.onDeleteSuccess(o);
            }

            @Override
            public void onFail(String msg) {
                view.onFail(msg);
            }
        });
    }

    public void onSearch() {
        myModel.onSearch(new ResultCallBack<List<UserBean>>() {

            @Override
            public void onSuccess(List<UserBean> userBeans) {
                view.onSearchSuucess(userBeans);
            }

            @Override
            public void onFail(String msg) {
                view.onFail(msg);
            }
        });
    }
}
