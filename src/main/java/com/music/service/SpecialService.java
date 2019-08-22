package com.music.service;

import com.music.bean.PageBean;
import com.music.bean.Special;

import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface SpecialService {
    public void insertSpecial(Special special);
    public List<Special> selectAllSpe();
    public Special selectOneSpe(int id);
    public Special selectId(Special special);
    public void deleteSpecial(int id);
    public int selectSpeCount();
    public PageBean<Special> findSpeByPage(int currentPage);
}
