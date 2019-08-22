package com.music.service.impl;

import com.music.bean.SongList;
import com.music.mapper.SongListMapper;
import com.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    private SongListMapper songListMapper;

    @Override
    public void insertuserList(SongList songList) {
        songListMapper.insertSongList(songList);
    }

    @Override
    public List<SongList> selectListByUser(int id) {
        return songListMapper.selectByUser(id);
    }

    @Override
    public SongList selectListByListId(int id) {
        return songListMapper.selectListByListId(id);
    }

    @Override
    public void deleteListById(int id) {
        songListMapper.updateLikeDel(id);
    }

    @Override
    public void updateListSong(int id, String updateSong) {
        SongList songList = new SongList();
        songList.setId(id);
        songList.setLikeNum(updateSong);
        songListMapper.updateLikeSong(songList);
    }

    @Override
    public void updateListType(int id, String types) {
        SongList songList = new SongList();
        songList.setId(id);
        songList.setListLabel(types);
        songListMapper.updateListType(songList);
    }

    @Override
    public void updateListSum(int id, String summary) {
        SongList songList = new SongList();
        songList.setId(id);
        songList.setListSummary(summary);
        songListMapper.updateListSum(songList);
    }

    @Override
    public SongList findByNameId(int id, String name) {
        SongList songList = new SongList();
        songList.setUserId(id);
        songList.setListName(name);
        return songListMapper.findByNameId(songList);
    }


}
