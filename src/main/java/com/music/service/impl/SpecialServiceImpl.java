package com.music.service.impl;

import com.music.bean.PageBean;
import com.music.bean.Special;
import com.music.mapper.SongsMapper;
import com.music.mapper.SpecialMapper;
import com.music.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
@Service
public class SpecialServiceImpl implements SpecialService {
    @Autowired
    private SpecialMapper specialMapper;
    @Autowired
    private SongsMapper songsMapper;
    @Override
    public void insertSpecial(Special special) {
        specialMapper.insertSpecial(special);
    }

    @Override
    public List<Special> selectAllSpe() {
        return specialMapper.selectAllSpe();
    }

    @Override
    public Special selectOneSpe(int id) {
        return specialMapper.selectOneSpe(id);
    }

    @Override
    public Special selectId(Special special) {
        return specialMapper.selectId(special);
    }

    @Override
    public void deleteSpecial(int id) {
        specialMapper.updateByDelete(id);
        songsMapper.updateBySpe(id);
    }

    @Override
    public int selectSpeCount() {
        return specialMapper.selectCount();
    }

    @Override
    public PageBean<Special> findSpeByPage(int currentPage) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Special> pageBean = new PageBean<Special>();
        //封装当前页数
        pageBean.setCurrPage(currentPage);

        //每页显示的数据
        int pageSize=10;
        pageBean.setPageSize(pageSize);

        //封装总记录数
        //int totalCount = userDao.selectCount();
        int totalCount = specialMapper.selectCount();
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
        List<Special> lists = specialMapper.findSpeByPage(map);
        pageBean.setLists(lists);
        return pageBean;

    }
}
