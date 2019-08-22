package com.music.mapper;

import com.music.bean.Songs;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface SongsMapper {
    public void insertSong(Songs songs);
    public List<Songs> selectBySpe(int id);
    public Songs selectById(int id);
    public void updateLrc(Songs songs);
    public List<Songs> selectAllSong();
    public void updateBySinger(int id);
    public void updateLikeDe(int id);
    public void updateBySpe(int id);
    public int selectSongCount();
    public void updateSongPlayNum(int id);
    public List<Songs> findSongByPage(HashMap<String,Object> map);
    public List<Songs> selectBySinger(int id);
    public List<Songs> selectByKey(@Param("key")String key);

    /*
    '%${key}%'
    select songs.id,create_time,special_id,singer_id,song_language,song_name ,song_picture,song_style,song_play_num,song_text,song_file,singer.id s_id,singer_name
        from songs,singer where singer_id = singer.id and song_flag = 0;
     */
}
