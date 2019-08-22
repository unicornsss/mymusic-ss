package com.music.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.music.bean.*;
import com.music.service.*;
import com.music.util.DateUtil;
import com.music.util.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 羊 on 2018/11/13.
 */
@Controller
@RequestMapping("/music")
public class musicController {
    @Autowired
    private UserService userService;
    @Autowired
    private SpecialService specialService;
    @Autowired
    private SongsService songsService;
    @Autowired
    private SingerService singerService;
    @Autowired
    private MusicCommentService musicCommentService;
    @Autowired
    private SongListService songListService;

    @RequestMapping("/showSingers")
    public String showSingers(@RequestParam(value="currentPage",defaultValue="1",required=false)int currentPage,HttpSession httpSession,Model model){
        User user = userService.selectOneUserById((int) httpSession.getAttribute("userSession"));
        model.addAttribute("userShow",user);
//        model.addAttribute("pageSong",songsService.findSongByPage(currentPage));
        model.addAttribute("pageSinger",singerService.findSingerByPage(currentPage));
        return "music_singers";
    }

    /*
    专辑分页
     */
    @RequestMapping("/showSpe")
    public String showSpe(@RequestParam(value="currentPage",defaultValue="1",required=false)int currentPage,HttpSession httpSession,Model model){
        User user = userService.selectOneUserById((int) httpSession.getAttribute("userSession"));
        model.addAttribute("userShow",user);
//        model.addAttribute("pageSong",songsService.findSongByPage(currentPage));
//        model.addAttribute("pageSinger",singerService.findSingerByPage(currentPage));
        System.out.println(specialService.findSpeByPage(currentPage));
        model.addAttribute("pageSpe", specialService.findSpeByPage(currentPage));
        return "music_spe";
    }

    @RequestMapping("/showEverySpe")
    public String showEverySpe(HttpSession httpSession,Model model,int speId){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        model.addAttribute("userShow",user);
//        Singer singer = singerService.selectOne(singerId);
        System.out.println("spe"+speId);

        Special special = specialService.selectOneSpe(speId);
        List<Songs> songsList = songsService.selectBySpe(speId);
        if(user.getLikeSpecial() == null){
            model.addAttribute("noticeSpeFlag",false);
        }else{
            List<Integer> speResultList = MethodUtils.getResultList(user.getLikeSpecial());
            System.out.println(speResultList.contains(speId));
            model.addAttribute("noticeSpeFlag",speResultList.contains(speId));
        }
//        List<Songs> songsList = songsService.selectBySinger(singerId);
//        List<Integer> resultList = getResultList(user.getNoticeSinger());
//        model.addAttribute("singer",singer);
        model.addAttribute("userShow",user);
        model.addAttribute("special",special);
        model.addAttribute("songsList",songsList);
        List<SongList> songList = songListService.selectListByUser(id);
        model.addAttribute("lists",songList);

        return "music_everySpe";
    }

    @RequestMapping("/noticeSpeAdd")
    @ResponseBody
    public Map<String,Object> noticeSingerAdd(int speId,HttpSession httpSession){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        System.out.println("speId"+speId);
        List<Integer> resultList = new ArrayList<Integer>();
        if(user.getLikeSpecial() != null){
            resultList = MethodUtils.getResultList(user.getLikeSpecial());
        }
        System.out.println("resultList"+resultList);
        resultList.add(speId);
        System.out.println("resultList1"+resultList);
//修改用户的likeSongs
        userService.updateLikeSpe(id, resultList.toString());
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        resultMap.put("result", "addSuccess");
        return resultMap;
    }

    @RequestMapping("/noticeSpeSub")
    @ResponseBody
    public Map<String,Object> noticeSpeSub(int speId,HttpSession httpSession){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
//        System.out.println("speId"+speId);
        List<Integer> remove = new ArrayList<Integer>();
        remove.add(speId);
        List<Integer> resultList = new ArrayList<Integer>();
        if(user.getLikeSpecial() != null){
            resultList = MethodUtils.getResultList(user.getLikeSpecial());
        }
        System.out.println("resultList"+resultList);
        if(remove.size()>0){
            resultList.removeAll(remove);
        }
        System.out.println("resultList1"+resultList);
//修改用户的likeSongs
        userService.updateLikeSpe(id, resultList.toString());
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        resultMap.put("result", "SubSuccess");
        return resultMap;
    }

    /**
     * 展示song
     */
    @RequestMapping("/showEverySong")
    public String showEverySong(HttpSession httpSession,Model model,int songId){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        model.addAttribute("userShow",user);
        System.out.println("SongId"+songId);
        Songs songs = songsService.selectById(songId);

        String filePath = "D:\\Java\\mavenprojects\\ssm-new\\src\\main\\webapp\\WEB-INF\\lrc\\"+songs.getSongText();
//        closer_to_me.lrc
        ArrayList<String[]> text=new ArrayList<String[]>();
        text= MethodUtils.readTxtFile(filePath,"utf-8");
        List<String> str = new ArrayList<>();
        for (String[] strings : text) {
            String sss = String.valueOf(strings[0]);
            str.add(sss);
//            System.out.println(strings);
        }
//        System.out.println(str);
        List<Integer> resultList = new ArrayList<Integer>();
        if(user.getLikeSongs()!= null){
        resultList = MethodUtils.getResultList(user.getLikeSongs());
        }
//        int commentcount =musicCommentService.;
        List<MusicComment> comments = musicCommentService.selectBySong(songId);
        System.out.println("commentsNumber"+comments);
        List<Integer> numResult = new ArrayList<Integer>();
        for (int i = 0; i < comments.size(); i++) {
            numResult.add(musicCommentService.selectZanCount(comments.get(i).getId()));
//            if(comments.get(i).getLikeNum() != null) {
//                List<Integer> song = MethodUtils.getResultList(comments.get(i).getLikeNum());
//                numResult.add(song.size());
//            }else{
//                numResult.add(0);
//            }
        }
        System.out.println(numResult);
        model.addAttribute("commentsNumber",comments.size());
        model.addAttribute("comments",comments);
        model.addAttribute("numResult",numResult);

        model.addAttribute("LikeFlag",resultList.contains(songId));
//        model.addAttribute("comments",comments);
        model.addAttribute("songShow",songs);
        model.addAttribute("str",str);
//        return "admin_showsongOne";
        return "music_song";
    }

    @RequestMapping("/addComment")
    @ResponseBody
    public Map<String,Object> addComment(int songId,HttpSession httpSession,String text){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
//        model.addAttribute("userShow",user);
        MusicComment musicComment = new MusicComment();
        musicComment.setUserId(id);
        musicComment.setCreateTime(DateUtil.initializeCreateTime());
        musicComment.setContent(text);
        musicComment.setMusicId(songId);
        System.out.println(musicComment);
        musicCommentService.insertComment(musicComment);
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        resultMap.put("result", "insertSuccess");
        return resultMap;
    }

    @RequestMapping("/listAdd")
    @ResponseBody
    public Map<String,Object> listAdd(int songId,HttpSession httpSession,int listId){
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        System.out.println("listId+songId"+listId+"======"+songId);
//        songList.setUserId(id);
//        songList.setId(listId);
        SongList songList1 = songListService.selectListByListId(listId);
        System.out.println("songList1"+songList1);
        List<Integer> listAdd = new ArrayList<Integer>();
        listAdd.add(songId);
        List<Integer> listGet = new ArrayList<Integer>();
        if(songList1.getLikeNum()!= null && !(songList1.getLikeNum().equals(""))){
        listGet = MethodUtils.getResultList(songList1.getLikeNum());
        }
        listGet.addAll(listAdd);
        System.out.println("listGet"+listGet.toString());
        songListService.updateListSong(listId, listGet.toString());

        SongList likeSong = songListService.findByNameId(id, "我喜欢");
        if(listId == likeSong.getId()){
            userService.updateLikeSong(id,listGet.toString());
        }

        resultMap.put("result", "updateSuccess");
        return resultMap;
    }

//    @RequestMapping("/play")
//    public String play(HttpSession httpSession,int songId){
//        //如果不为空
//        if()
//        return "redirect:/admin/showSinger?"+songId;
//    }

    //播放 先要生成session
    @RequestMapping("/play")
    public String play(HttpSession httpSession,int songId){
        System.out.println("hello");
        JSONObject result = new JSONObject();
        JSONObject object = new JSONObject();
        List<Songs> songses = new ArrayList<Songs>();
//        session.getAttribute("name")!=null&&session.getAttribute("name")!=""
        System.out.println("111");
        System.out.println(httpSession.getAttribute("result") == null);
        if ( httpSession.getAttribute("result") == null){
            System.out.println(songId);
            Songs songs = songsService.selectById(songId);
            songses.add(songs);
        }else{
//            httpSession.setAttribute("result", result);
            object = (JSONObject) httpSession.getAttribute("result");
            System.out.println("object"+object);
            //包含了第一首歌
            try {
                List<Integer> list = MethodUtils.getObject(object.toString());
                List<Integer> addList = new ArrayList<Integer>();
                //刷新一次就会传递一个song 的初始值
                addList.add(songId);
                list.addAll(addList);
                System.out.println(list);
                //去重
                List<Integer> newList = MethodUtils.removeDuplicateWithOrder(list);
                for (Integer i : newList) {
                    songses.add(songsService.selectById(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        result = MethodUtils.getJson(songses);
        System.out.println("result"+result);
        httpSession.setAttribute("result", result);
        return "music_play";
    }

    @RequestMapping("/doResearch")
    @ResponseBody
    public void doResearch(String key,HttpServletResponse resp) throws IOException {
        System.out.println("已经成功进入AjaxController");
        System.out.println("key"+key);
//        String username = user.getUsername();
        List<Songs> songsList = songsService.selectByKey(key);
        System.out.println(songsList);
        JSONArray object = MethodUtils.getSongJson(songsList);
        System.out.println(object);
//        List<User> UserList=userService.selectByLikeName(username);
//把list实体封装到json对象中
//     JSONArray jArray=JSONArray.fromObject(UserList);
//向客户端输出json对象
        System.out.println("jArray.toString():"+object.toString());
        resp.setContentType("text/html;charset=UTF-8");
//        resp.getWriter().write(String.valueOf(object));
        resp.getWriter().write(object.toString());
    }

    @RequestMapping("/teste")
    public void teste(String key) throws IOException {
        List<Songs> songsList = songsService.selectByKey(key);
        System.out.println(songsList);
//        JSONObject object = (JSONObject) JSONObject.toJSON(songsList);
//        System.out.println(object);
    }

    @RequestMapping(value = "/researchSong",method = RequestMethod.POST)
    public String researchSong(String keys,HttpSession httpSession,Model model){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        model.addAttribute("userShow",user);

        List<Songs> song = songsService.selectByKey(keys);
        List<Singer> singer = singerService.selectBysingerKey(keys);
        model.addAttribute("songsList",song);
        model.addAttribute("singers",singer);

        List<SongList> listsss = songListService.selectListByUser(id);
        model.addAttribute("lists",listsss);
        return "music_search";
    }

    @RequestMapping("/addCommentZan")
    @ResponseBody
    public  Map<String,Object> researchComment(int  commentId,HttpSession httpSession){
        musicCommentService.addZan(commentId);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", "zanSuccess");
        return resultMap;
    }

    @RequestMapping("/showEverySinger")
    public String showEverySinger(HttpSession httpSession,int singerId,Model model){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        model.addAttribute("userShow",user);
        Singer singer = singerService.selectOne(singerId);
        List<Songs> songsList = songsService.selectBySinger(singerId);

        List<Integer> resultList = new ArrayList<Integer>();
        if((user.getNoticeSinger() != null ) && (!user.getNoticeSinger().equals(""))){
            resultList =  MethodUtils.getResultList(user.getNoticeSinger());
        }
        System.out.println(resultList.contains(singerId));
        model.addAttribute("noticeFlag",resultList.contains(singerId));

        model.addAttribute("singer",singer);
        model.addAttribute("songsList",songsList);

        List<SongList> lists = songListService.selectListByUser(id);
        model.addAttribute("lists",lists);
        return "music_everySinger";
    }

}

