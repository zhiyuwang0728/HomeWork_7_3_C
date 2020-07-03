package com.zyw.day03_c.view;

import com.zyw.day03_c.bean.UserBean;
import java.util.List;
public interface MyView {

    void onInsertSuccess(String msg);

    void onUpdateSuccess(String msg);

    void onDeleteSuccess(String msg);

    void onSearchSuucess(List<UserBean> data);

    void onFail(String msg);
}
