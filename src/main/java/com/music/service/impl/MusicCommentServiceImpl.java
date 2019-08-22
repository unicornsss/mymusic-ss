package com.music.service.impl;

import com.music.bean.MusicComment;
import com.music.mapper.MusicCommentMapper;
import com.music.service.MusicCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
@Service
public class MusicCommentServiceImpl implements MusicCommentService {
    @Autowired
    private MusicCommentMapper musicCommentMapper;

    @Override
    public void insertComment(MusicComment musicComment) {
        musicCommentMapper.insertComment(musicComment);
    }

    @Override
    public List<MusicComment> selectBySong(int songId) {
        return musicCommentMapper.selectBySong(songId);
    }

    @Override
    public void addZan(int commentId) {
        musicCommentMapper.updateZan(commentId);
    }

    @Override
    public int selectZanCount(int id) {
        MusicComment musicComment = musicCommentMapper.selectById(id);
        return musicComment.getGiveGood();
    }

}
