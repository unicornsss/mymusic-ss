package com.music.service.impl;

import com.music.bean.User;
import com.music.mapper.UserMapper;
import com.music.service.UserService;
import com.music.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 羊 on 2018/10/29.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User selectOneUser(User user) {
        return userMapper.selectOneUser(user);
    }

    @Override
    public User selectOneByAccount(String account) {
        return userMapper.selectOneByAccount(account);
    }

    @Override
    public void insertUser(User user) {
        user.setCreateTime(DateUtil.initializeCreateTime());
        user.setUserPic("zzz.jpg");
        userMapper.insertUser(user);
    }

    @Override
    public User selectOneUserById(int id) {
        return userMapper.selectOneUserById(id);
    }

    @Override
    public void updateNoticeSinger(int id,String updateNoticeSinger) {
        User usernew  = new User();
        usernew.setNoticeSinger(updateNoticeSinger);
        usernew.setId(id);
        userMapper.updateNoticeSinger(usernew);
    }

    @Override
    public void updateLikeSpe(int id, String updateSpe) {
        User user = new User();
        user.setId(id);
        user.setLikeSpecial(updateSpe);
        userMapper.updateLikeSpe(user);
    }

    @Override
    public void updateLikeSong(int id, String likeSong) {
        User user = new User();
        user.setId(id);
        user.setLikeSongs(likeSong);
        userMapper.updateLikeSong(user);
    }

}
