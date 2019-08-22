package com.music.mapper;

import com.music.bean.MusicComment;

import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface MusicCommentMapper {
    public void insertComment(MusicComment musicComment);
    public List<MusicComment> selectBySong(int id);
    public void updateZan(int id);
    public MusicComment selectById(int id);
//    public MusicComment selectcount(int id);
}
