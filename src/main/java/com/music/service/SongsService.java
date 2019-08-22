package com.music.service;

import com.music.bean.PageBean;
import com.music.bean.Songs;

import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface SongsService {
    public void insertSong(Songs songs);
    public List<Songs> selectBySpe(int speId);
    public Songs selectById(int id);
    public void updateLrc(String lrc,int id);
    public List<Songs> selectAllSong();
    public void deleteSong(int id);
    public int selectSongCount();
    public void updateSongPlay(int id);
    public PageBean<Songs> findSongByPage(int currentPage);
    public List<Songs> selectBySinger(int id);
    public List<Songs> selectByKey(String key);
}
