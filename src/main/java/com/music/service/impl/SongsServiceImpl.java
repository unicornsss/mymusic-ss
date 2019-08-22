package com.music.service.impl;

import com.music.bean.PageBean;
import com.music.bean.Songs;
import com.music.mapper.SongsMapper;
import com.music.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
@Service
public class SongsServiceImpl implements SongsService {
    @Autowired
    private SongsMapper songsMapper;
    @Override
    public void insertSong(Songs song) {
        songsMapper.insertSong(song);
    }

    @Override
    public List<Songs> selectBySpe(int speId) {
        return songsMapper.selectBySpe(speId);
    }

    @Override
    public Songs selectById(int id) {
        return songsMapper.selectById(id);
    }

    @Override
    public void updateLrc(String lrc,int id) {
        Songs song =  new Songs();
        song.setId(id);
        song.setSongText(lrc);
        songsMapper.updateLrc(song);
    }

    @Override
    public List<Songs> selectAllSong() {
        return songsMapper.selectAllSong();
    }

    @Override
    public void deleteSong(int id) {
        songsMapper.updateLikeDe(id);
    }

    @Override
    public int selectSongCount() {
        return songsMapper.selectSongCount();
    }

    @Override
    public void updateSongPlay(int id) {
        songsMapper.updateSongPlayNum(id);
    }

    @Override
    public PageBean<Songs> findSongByPage(int currentPage) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Songs> pageBean = new PageBean<Songs>();
        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //每页显示的数据
        int pageSize=6;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        //int totalCount = userDao.selectCount();
        int totalCount = songsMapper.selectSongCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageBean.getPageSize());
//        map.put("uId",uId);
        //封装每页显示的数据
//        List<Songs> lists = songsMapper.findByPage(map);
        List<Songs> lists = songsMapper.findSongByPage(map);
        pageBean.setLists(lists);
        return pageBean;
//        return null;
    }

    @Override
    public List<Songs> selectBySinger(int id) {
        return songsMapper.selectBySinger(id);
    }

    @Override
    public List<Songs> selectByKey(String key) {
        return songsMapper.selectByKey(key);
    }
}
