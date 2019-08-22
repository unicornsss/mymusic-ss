package com.music.service;

import com.music.bean.PageBean;
import com.music.bean.Singer;

import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface SingerService {
    public void insertSinger(Singer singer);
    public List<Singer> showAll();
    public Singer selectOne(int id);
    public void updateSinger(Singer singer);
    public List<Singer> selectAllSinger();
    public void deleteOne(int id);
    public void updateNoticeAdd(int id);
    public void updateNoticeSub(int id);
    public PageBean<Singer> findSingerByPage(int currentPage);
    public List<Singer> selectBysingerKey(String keys);
}
