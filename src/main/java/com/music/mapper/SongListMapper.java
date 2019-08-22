package com.music.mapper;

import com.music.bean.SongList;

import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface SongListMapper {
    public void insertSongList(SongList songList);
    public List<SongList> selectByUser(int id);
    public void updateLikeDel(int id);
    public void updateLikeSong(SongList songList);
    public void updateListType(SongList songList);
    public void updateListSum(SongList songList);
    public SongList findByNameId(SongList songList);
    public SongList selectListByListId(int id);
}
