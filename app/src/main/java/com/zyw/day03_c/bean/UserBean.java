package com.zyw.day03_c.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserBean {

    @Id
    Long id;
    String username;
    String password;
    String imagePath;

    @Generated(hash = 1331907700)
    public UserBean(Long id, String username, String password, String imagePath) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.imagePath = imagePath;
    }

    public UserBean(String username, String password, String imagePath) {
        this.username = username;
        this.password = password;
        this.imagePath = imagePath;
    }

    @Generated(hash = 1203313951)
    public UserBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


}
