package com.zyw.day03_c.model;

import com.zyw.day03_c.app.MyApp;
import com.zyw.day03_c.bean.UserBean;
import com.zyw.day03_c.view.ResultCallBack;
import com.zyw.greendaodemo.db.DaoSession;
import com.zyw.greendaodemo.db.UserBeanDao;

import java.util.List;

public class MyModel {
    public void onInsert(UserBean userBean, ResultCallBack callBack) {

        UserBeanDao userBeanDao = MyApp.getInstance().getDaoSession().getUserBeanDao();
        userBeanDao.insertOrReplace(userBean);
        UserBean unique = userBeanDao.queryBuilder().where(UserBeanDao.Properties.Id.eq(userBean.getId())).unique();

        if (unique != null) {
            callBack.onSuccess("插入成功");
        } else {
            callBack.onFail("插入失败");
        }

    }

    public void onUpdate(UserBean userBean, ResultCallBack callBack) {

        UserBeanDao daoSession = MyApp.getInstance().getDaoSession().getUserBeanDao();
        daoSession.update(userBean);


        UserBean unique = daoSession.queryBuilder().where(UserBeanDao.Properties.Username.eq(userBean.getUsername())).unique();

        if (unique != null) {
            callBack.onSuccess("修改成功");
        } else {
            callBack.onFail("修改失败");
        }

    }

    public void onDelete(UserBean userBean, ResultCallBack callBack) {
        UserBeanDao userBeanDao = MyApp.getInstance().getDaoSession().getUserBeanDao();
        userBeanDao.delete(userBean);
        UserBean unique = userBeanDao.queryBuilder().where(UserBeanDao.Properties.Id.eq(userBean.getId())).unique();

        if (unique == null) {
            callBack.onSuccess("删除成功");
        } else {
            callBack.onFail("删除失败");
        }
    }

    public void onSearch(ResultCallBack callBack) {
        UserBeanDao userBeanDao = MyApp.getInstance().getDaoSession().getUserBeanDao();
        List<UserBean> list = userBeanDao.queryBuilder().list();

        callBack.onSuccess(list);
        if (list == null) {
            callBack.onFail("查询失败");
        }

    }


}
