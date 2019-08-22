package com.music.mapper;

import com.music.bean.Special;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface SpecialMapper {
    public void insertSpecial(Special special);
    public List<Special> selectAllSpe();
    public Special selectOneSpe(int id);
    public Special selectId(Special special);
    public void updateBySinger(int id);
    public void updateByDelete(int id);
    public int selectCount();
    public List<Special> findSpeByPage(HashMap<String,Object> map);
}
