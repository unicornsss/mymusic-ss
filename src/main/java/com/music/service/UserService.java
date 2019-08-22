package com.music.service;

import com.music.bean.User;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface UserService {
    public User selectOneUser(User user);
    public User selectOneByAccount(String account);
    public void insertUser(User user);
    public User selectOneUserById(int id);
    public void updateNoticeSinger(int id,String updateNoticeSinger);
    public void updateLikeSpe(int id,String updateSpe);
    public void updateLikeSong(int id,String likeSong);
}
