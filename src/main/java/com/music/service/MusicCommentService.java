package com.music.service;

import com.music.bean.MusicComment;

import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface MusicCommentService {
    public void insertComment(MusicComment musicComment);
    public List<MusicComment> selectBySong(int  songId);
    public void addZan(int commentId);
    public int selectZanCount(int id);
}
