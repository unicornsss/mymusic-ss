package com.music.service;

import com.music.bean.SongList;

import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface SongListService {
    public void insertuserList(SongList songList);
    public List<SongList> selectListByUser(int id);
    public SongList selectListByListId(int id);
    public void deleteListById(int id);
    public void updateListSong(int id,String updateSong);
    public void updateListType(int id,String types);
    public void updateListSum(int id,String summary);
    public SongList findByNameId(int id,String name);
}
