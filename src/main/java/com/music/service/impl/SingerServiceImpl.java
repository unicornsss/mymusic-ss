package com.music.service.impl;

import com.music.bean.PageBean;
import com.music.bean.Singer;
import com.music.mapper.SingerMapper;
import com.music.mapper.SongsMapper;
import com.music.mapper.SpecialMapper;
import com.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerMapper singerMapper;
    @Autowired
    private SongsMapper songsMapper;
    @Autowired
    private SpecialMapper specialMapper;

    @Override
    public void insertSinger(Singer singer) {
        singerMapper.insertSinger(singer);
    }

    @Override
    public List<Singer> showAll() {
        return singerMapper.selectAll();
    }

    @Override
    public Singer selectOne(int id) {
        return singerMapper.selectOne(id);
    }

    @Override
    public void updateSinger(Singer singer) {
        singerMapper.updateSinger(singer);
    }

    @Override
    public List<Singer> selectAllSinger() {
        return singerMapper.selectAll();
    }

    @Override
    public void deleteOne(int id) {
        singerMapper.updateOne(id);
        songsMapper.updateBySinger(id);
        specialMapper.updateBySinger(id);
    }

    @Override
    public void updateNoticeAdd(int id) {
        singerMapper.updateNoticeAdd(id);
    }

    @Override
    public void updateNoticeSub(int id) {
        singerMapper.updateNoticeSub(id);
    }

    @Override
    public PageBean<Singer> findSingerByPage(int currentPage) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Singer> pageBean = new PageBean<Singer>();
        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //每页显示的数据
        int pageSize=14;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        //int totalCount = userDao.selectCount();
        int totalCount = singerMapper.selectSingerCount();
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
//        List<Singer> lists = singerMapper.findSingerByPage(map);
        List<Singer> lists = singerMapper.findSingerByPage(map);
        pageBean.setLists(lists);
        return pageBean;
    }

    @Override
    public List<Singer> selectBysingerKey(String keys) {
        return singerMapper.selectBysingerKey(keys);
    }

}
