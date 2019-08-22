package com.music.controller;


import com.music.bean.*;
import com.music.service.*;
import com.music.util.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 羊 on 2018/11/10.
 */
@Controller
@RequestMapping("/music")
public class userController {
    @Autowired
    private UserService userService;
    @Autowired
    private SpecialService specialService;
    @Autowired
    private SongsService songsService;
    @Autowired
    private SingerService  singerService;
    @Autowired
    private SongListService songListService;


    @RequestMapping("/login")
    public String adminShowLogin(){
        return "user_login";
    }

    @RequestMapping("/doLogin")
    public String adminShowLogin( User user,HttpSession httpSession){
        System.out.println("user"+user);
        User user1 = userService.selectOneUser(user);
        if(user1!=null){
            httpSession.setAttribute("userSession",user1.getId());
            return "redirect:/music/homePage";
        }
        return "admin_error";}

    @RequestMapping("/homePage")
    public String homePage(HttpSession httpSession,Model model){
        User user = userService.selectOneUserById((int) httpSession.getAttribute("userSession"));
        model.addAttribute("userShow",user);
        List<Special> specialList = specialService.selectAllSpe();
        int speCount = specialService.selectSpeCount();
//        Random rand = new Random();
        List<Special> randSpe = new ArrayList<Special>();
        randSpe = MethodUtils.randomFind(speCount, specialList);
        System.out.println(randSpe);
        for (Special special : randSpe) {
            System.out.println(special);
        }
        List<Songs> songsList = songsService.selectAllSong();
        int songCount = songsService.selectSongCount();
//        Random rand = new Random();
        List<Songs> randSong = new ArrayList<Songs>();
        randSong = MethodUtils.randomFind(songCount, songsList);
        System.out.println(randSong);
        for (Songs songs : randSong) {
            System.out.println(songs);
        }
        model.addAttribute("randSpe",randSpe);
        model.addAttribute("randSong",randSong);
        return "homepage";
    }

    @RequestMapping("/register")
    public String register(){
        return "user_register";

    }

    @RequestMapping("/showNotice")
    public String showNotice(HttpSession httpSession,Model model){
        User user = userService.selectOneUserById((int) httpSession.getAttribute("userSession"));
        model.addAttribute("userShow",user);
        List<Integer> resultList = new ArrayList<Integer>();
        if((user.getNoticeSinger()!=null)&&!(user.getNoticeSinger().equals(""))){
            resultList = MethodUtils.getResultList(user.getNoticeSinger());
        }
        List<Singer> showSinger = new ArrayList<Singer>();
        for (Integer i : resultList) {
            showSinger.add(singerService.selectOne(i));
        }
        model.addAttribute("showSinger",showSinger);
        return "user_notice";
    }

    @RequestMapping(value = "/doRegister",method = RequestMethod.POST)
    public String doRegister(User user){
        userService.insertUser(user);
        User user1 = userService.selectOneByAccount(user.getAccountNumber());
        SongList songList = new SongList();
        songList.setListName("我喜欢");
        songList.setUserId(user1.getId());
        songListService.insertuserList(songList);
        return "user_login";
    }

    @RequestMapping("/noticeSingerSub")
    @ResponseBody
    public Map<String,Object> noticeSingerSub(int singerId,HttpSession httpSession,Model model){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        model.addAttribute("userShow",user);
        System.out.println("singerId"+singerId);
        System.out.println("user" + user);
        singerService.updateNoticeSub(singerId);
        List<Integer> removeList = new ArrayList<Integer>();
        removeList.add(singerId);
        List<Integer> resultList = MethodUtils.getResultList(user.getNoticeSinger());
        if(removeList.size()>0){
            resultList.removeAll(removeList);
        }
        userService.updateNoticeSinger(id,resultList.toString());
//        Singer singer = singerService.selectOne(singerId);
//        response.getWriter().print(result);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("name", "subOK");
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        resultMap.put("result", "success");
        return resultMap;
//        return "redirect:/admin/showSinger";
    }

    /*
    * 添加关注歌手 1 得到user singerid可作为属性？property 得到singerid
    * 取出user notice singer 在原基础上加一 在此update
    * */
    @RequestMapping("/noticeSingerAdd")
    @ResponseBody
    public Map<String,Object> noticeSingerAdd(int singerId,HttpSession httpSession){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        System.out.println("singerId"+singerId);

        List<Integer> resultList = new ArrayList<Integer>();
        if(user.getNoticeSinger() != null){
            resultList = MethodUtils.getResultList(user.getNoticeSinger());
        }

        resultList.add(singerId);
        System.out.println("resultList1"+resultList);
        //修改用户的关注
        userService.updateNoticeSinger(id, resultList.toString());
        //粉丝加一
        singerService.updateNoticeAdd(singerId);
        Singer singer = singerService.selectOne(singerId);
        System.out.println(singer);
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        resultMap.put("result", "addSuccess");
        return resultMap;
//        return "redirect:/admin/showSinger";
    }

    /*
    * show 已经创建的歌单 空白？或者是已经有的
    * 创建歌单
    *点击创建歌单 跳转到创建歌单的页面
    *提交form表单页面 post到后台
    *再跳转到列表展示页面
    * */
    /**
     *展示专辑
     * 点击名字为专辑详细内容
     * 点击图片为播放全部
     * **/
    @RequestMapping("/showList")
    public String addList(HttpSession httpSession,Model model){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        model.addAttribute("userShow",user);
//        System.out.println("singerId"+singerId);
        List<SongList> songLists = songListService.selectListByUser(id);
        List<Integer> numResult = new ArrayList<Integer>();
        for (int i = 0; i < songLists.size(); i++) {
            if(songLists.get(i).getLikeNum() != null) {
                List<Integer> song = MethodUtils.getResultList(songLists.get(i).getLikeNum());
                numResult.add(song.size());
            }else{
                numResult.add(0);
            }
        }
        model.addAttribute("songLists",songLists);
        model.addAttribute("numResult",numResult);
        return "user_Mylist";
    }

    @RequestMapping(value = "/doAddlist",method = RequestMethod.POST)
    public String doAddlist(HttpSession httpSession,Model model,SongList songList){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        model.addAttribute("userShow",user);
        System.out.println("songList"+songList);
        songList.setUserId(id);
        songListService.insertuserList(songList);
        return "redirect:/music/showList";
    }

    /**
     *展示
     * **/
    @RequestMapping("/userPage")
    public String userPage(HttpSession httpSession,Model model){
        User user = userService.selectOneUserById((int) httpSession.getAttribute("userSession"));
        model.addAttribute("userShow",user);
//        List listSong = (List)user.getLikeSongs();
//        model.addAttribute("user",user);
        List<Integer> resultList = new ArrayList<Integer>();
        List<Songs> songlist = new ArrayList<Songs>();
        if((user.getLikeSongs() != null )&& (!user.getLikeSongs().equals(""))){
            resultList = MethodUtils.getResultList(user.getLikeSongs());
//        resultList.add(singerId);
            for (Integer i : resultList) {
                songlist.add(songsService.selectById(i));
            }
            System.out.println("songList"+songlist);
        }
        model.addAttribute("songList",songlist);
        List<SongList> listiter = new ArrayList<SongList>();
        model.addAttribute("listIter",listiter);
//        songList.add("我喜欢");
//        修改用户的关注
//        userService.updateNoticeSinger(id, resultList.toString());
//        粉丝加一
//        singerService.updateNoticeAdd(singerId);
//        Singer singer = singerService.selectOne(singerId);
//        System.out.println(singer);
        return "user_likeSong";
    }

    /**
     * 删除列表
     */
    @RequestMapping("/deleteList")
    @ResponseBody
    public Map<String,Object> deleteList(int listId,HttpSession httpSession){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        System.out.println("singerId"+listId);
        songListService.deleteListById(listId);
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        resultMap.put("result", "delSuccess");
        return resultMap;
//        return "redirect:/admin/showSinger";
    }

    /**
     * 删除喜欢的歌
     */
    @RequestMapping("/deleteLikeSong")
    @ResponseBody
    public Map<String,Object> deleteLikeSong(int songId,HttpSession httpSession){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        System.out.println("songId"+songId);

        List<Integer> removeLike = new ArrayList<Integer>();
        removeLike.add(songId);
        List<Integer> resultList = MethodUtils.getResultList(user.getLikeSongs());
        if(removeLike.size()>0){
            resultList.removeAll(removeLike);
        }
        userService.updateLikeSong(id,resultList.toString());
        SongList likeSong = songListService.findByNameId(id, "我喜欢");
        System.out.println("likeSong"+likeSong);
        songListService.updateListSong(likeSong.getId(), resultList.toString());
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        resultMap.put("result", "updateSuccess");
        return resultMap;
//        return "redirect:/admin/showSinger";
    }


    /**
     * 增加喜欢的歌
     */
    @RequestMapping("/addLikeSong")
    @ResponseBody
    public Map<String,Object> addLikeSong(int songId,HttpSession httpSession){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        System.out.println("songId"+songId);

        List<Integer> addLike = new ArrayList<Integer>();
        addLike.add(songId);
        List<Integer> resultList = new ArrayList<Integer>();
        if((user.getLikeSongs()!=null) && !(user.getLikeSongs().equals(""))){
            resultList = MethodUtils.getResultList(user.getLikeSongs());
        }
        if(addLike.size()>0){
            resultList.addAll(addLike);
        }
        userService.updateLikeSong(id,resultList.toString());
        SongList likeSong = songListService.findByNameId(id, "我喜欢");
        System.out.println("likeSong"+likeSong);
        songListService.updateListSong(likeSong.getId(), resultList.toString());
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        resultMap.put("result", "addSuccess");
        return resultMap;
    }

    /**
     *更改歌单内容
     */
    @RequestMapping(value = "/updateMyList")
    public String updateMyList(HttpSession httpSession,Model model,int songListId){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        model.addAttribute("userShow",user);
        System.out.println("songListId"+songListId);
        SongList songList = songListService.selectListByListId(songListId);
        List<Integer> resultSong = new ArrayList<Integer>();
        if(songList.getLikeNum()!= null){
        resultSong = MethodUtils.getResultList(songList.getLikeNum());
        }
        List<Songs> songses = new ArrayList<Songs>();
        if (resultSong.size() != 0){
            for (int i = 0; i < resultSong.size(); i++) {
                Songs songget = songsService.selectById(resultSong.get(i));
                if(songget != null){
                songses.add(songget);
                System.out.println(songget);
                }
            }
        }
        List<String> types = new ArrayList<String>();
        if(songList.getListLabel()!= null){
            types = MethodUtils.getList(songList.getListLabel());
        }
        model.addAttribute("types",types);
        model.addAttribute("songList",songList);
        model.addAttribute("allSong",songses);
        return "user_updList";
    }

    @RequestMapping("/deleteListSong")
    @ResponseBody
    public Map<String,Object> deleteListSong(int songId,HttpSession httpSession,int listId){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        System.out.println("songId"+songId);
        System.out.println("listId"+listId);

        List<Integer> removeLike = new ArrayList<Integer>();
        removeLike.add(songId);
        SongList songList = songListService.selectListByListId(listId);
        List<Integer> resultList = MethodUtils.getResultList(songList.getLikeNum());
        if(removeLike.size()>0){
            resultList.removeAll(removeLike);
        }
//        userService.updateLikeSong(id,resultList.toString());
        songListService.updateListSong(listId,resultList.toString());
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        resultMap.put("result", "updateSuccess");
        return resultMap;
//        return "redirect:/admin/showSinger";
    }

    @RequestMapping("/updateListType")
    @ResponseBody
    public Map<String,Object> updateListType(@RequestParam(value="types") String[] types,HttpSession httpSession,int listId){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        List<String> typeList = new ArrayList<String>();
        for (String type : types) {
            System.out.println(type);
            typeList.add(type);
        }
        songListService.updateListType(listId,typeList.toString());
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        resultMap.put("result", "updateSuccess");
        return resultMap;
//        return "redirect:/admin/showSinger";
    }

    @RequestMapping("/updateListSum")
    @ResponseBody
    public Map<String,Object> updateListSum(String summary,HttpSession httpSession,int listId){
        int id = (int) httpSession.getAttribute("userSession");
        User user = userService.selectOneUserById(id);
        System.out.println("summary"+summary);
        songListService.updateListSum(listId,summary);
        //songListService.updateListType(listId,typeList.toString());
        Map<String,Object> resultMap = new HashMap<String, Object>();
//        return jsonObject;
        resultMap.put("result", "updateSuccess");
        return resultMap;
//        return "redirect:/admin/showSinger";
    }

    @RequestMapping("/showUser")
    public String showUser(int userId,HttpSession httpSession,Model model){
        int id = (int) httpSession.getAttribute("userSession");
        User userMyself = userService.selectOneUserById(id);
        model.addAttribute("userShow",userMyself);

        User user = userService.selectOneUserById(userId);

        List<Integer> resultList = new ArrayList<Integer>();
        List<Songs> songlist = new ArrayList<Songs>();
        if(user.getLikeSongs() != null){
            resultList = MethodUtils.getResultList(user.getLikeSongs());
//        resultList.add(singerId);
            for (Integer i : resultList) {
                songlist.add(songsService.selectById(i));
            }
            System.out.println("songList"+songlist);
        }

        List<Integer> userList = new ArrayList<Integer>();
        if((user.getNoticeSinger() != null) && !(user.getNoticeUser().equals(""))){
            userList = MethodUtils.getResultList(user.getNoticeUser());

        }
        System.out.println(userList.contains(userId));
        model.addAttribute("noticeFlag",resultList.contains(userId));
        model.addAttribute("userFind",user);
        model.addAttribute("songList",songlist);
//        List<SongList> listiter = new ArrayList<SongList>();
//        model.addAttribute("listIter",listiter);
        return "user_home";
    }

    //注销方法
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session){
        //通过session.invalidata()方法来注销当前的session
//        session.invalidate();
        session.removeAttribute("userSession");
        session.removeAttribute("result");
        return "user_login";
    }
    @RequestMapping("/outPlay")
    public String outPlay(HttpSession session){
        //通过session.invalidata()方法来注销当前的session
        session.removeAttribute("result");
        return "user_login";
    }
}

