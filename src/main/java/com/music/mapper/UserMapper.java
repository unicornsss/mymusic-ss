package com.music.mapper;

import com.music.bean.User;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface UserMapper {
    public User selectOneUser(User user);
    public void insertUser(User user);
    public User selectOneUserById(int id);
    public void updateNoticeSinger(User user);
    public void updateLikeSong(User user);
    public void updateLikeSpe(User user);
    public User selectOneByAccount(String account);
}
