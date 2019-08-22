package com.music.mapper;

import com.music.bean.Singer;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface SingerMapper {
    public void insertSinger(Singer singer);
    public List<Singer> selectAll();
    public Singer selectOne(int id);
    public void updateSinger(Singer singer);
    public void updateOne(int id);
    public void updateNoticeAdd(int id);
    public void updateNoticeSub(int id);
    public int selectSingerCount();
    public List<Singer> findSingerByPage(HashMap<String,Object> map);
    public List<Singer> selectBysingerKey(@Param("keys")String keys);
}
