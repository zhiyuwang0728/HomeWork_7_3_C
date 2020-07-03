package com.zyw.day03_c.view;

public interface ResultCallBack<T> {

    void onSuccess(T t);

    void onFail(String msg);
}
