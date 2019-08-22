package com.music.controller;

import com.music.bean.Administrator;
import com.music.bean.Singer;
import com.music.bean.Songs;
import com.music.bean.Special;
import com.music.service.AdministratorService;
import com.music.service.SingerService;
import com.music.service.SongsService;
import com.music.service.SpecialService;
import com.music.util.DateUtil;
import com.music.util.MethodUtils;
import com.music.util.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 羊 on 2018/10/30.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private String NOTICE_IMAGE_DIR = "D:/Java/mavenprojects/ssm-new/src/main/webapp/WEB-INF/files";
    private String FILE_MP3_DIR = "D:/Java/mavenprojects/ssm-new/src/main/webapp/WEB-INF/music";
    private String FILE_IMG_DIR = "D:/Java/mavenprojects/ssm-new/src/main/webapp/WEB-INF/img";
    private String FILE_LRC_DIR = "D:/Java/mavenprojects/ssm-new/src/main/webapp/WEB-INF/lrc";
    private String NOTICE_URL = "http://localhost:8080/ssm-new/src/main/webapp/WEB-INF/files";

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private SingerService singerService;
    @Autowired
    private SpecialService specialService;
    @Autowired
    private SongsService songsService;

    @RequestMapping("/login")
    public String adminShowLogin(){
        return "admin_login";
    }

    @RequestMapping("/doLogin")
    public String adminShowLogin(Administrator administrator,HttpSession httpSession){
        System.out.println("admin"+administrator);
        Administrator admin = administratorService.selectAdmin(administrator);
        if(admin!=null){
            httpSession.setAttribute("adminSession",admin);
            return "redirect:/admin/showSinger";
        }
        return "admin_error";
    }

    @RequestMapping("/register")
    public String adminRegister(){
        return "admin_register";
    }
    @RequestMapping("/doRegister")
    public String adminRegister(Administrator administrator){
        administratorService.insertAdministrator(administrator);
        return "admin_login";
    }

    @RequestMapping(value = "/addSinger",method = RequestMethod.GET)
    public String adminAddSinger(HttpSession httpSession){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin==null){
            return "redirect:/admin/login";
        }
        return "admin_addsinger";
    }

    @RequestMapping(value = "/doAddSinger",method = RequestMethod.POST)
    public String admindoAddSinger(Singer singer,@RequestParam("file1")MultipartFile file1,HttpSession httpSession){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin==null){
            return "redirect:/admin/login";
        }
        String newFileName2 = MyFileUtils.fileUpload(file1, NOTICE_IMAGE_DIR, NOTICE_URL);
//        pictureService.insertPic(newFileName2);
        singer.setSingerPicture(newFileName2);
        System.out.println("singer"+singer);
        singerService.insertSinger(singer);
        return "redirect:/admin/showSinger";
    }

    @RequestMapping(value = "/showSinger",method = RequestMethod.GET)
    public String adminShowSinger(HttpSession httpSession,Model model){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin==null){
            return "redirect:/admin/login";
        }
//        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        List<Singer> singerList = singerService.showAll();
        model.addAttribute("singerList", singerList);
        return "admin_showsinger";
    }

    @RequestMapping(value = "/updateSinger",method = RequestMethod.GET)
    public String updateSinger(HttpSession httpSession,Model model,int singerId){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin==null){
            return "redirect:/admin/login";
        }
//        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        System.out.println("singerId"+singerId);
        Singer singer = singerService.selectOne(singerId);
        model.addAttribute("singer",singer );
        return "admin_updateSinger";
    }
    //实际做修改歌手
    @RequestMapping(value = "/doUpdateSinger",method = RequestMethod.POST)
    public String doUpdateSinger(HttpSession httpSession,Model model,Singer singer,@RequestParam("file1")MultipartFile file1){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin==null){
            return "redirect:/admin/login";
        }
        System.out.println();
        if(!file1.isEmpty()){
            String newFileName2 = MyFileUtils.fileUpload(file1, NOTICE_IMAGE_DIR, NOTICE_URL);
            singer.setSingerPicture(newFileName2);
        }
        System.out.println(singer);
        singerService.updateSinger(singer);
        return "redirect:/admin/showSinger";
    }
    //添加专辑 同时添加歌曲？？ 可以 同时添加歌曲
    //歌手通过下拉菜单实现；

    @RequestMapping(value = "/showSpe",method = RequestMethod.GET)
    public String showSpe(HttpSession httpSession,Model model){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin==null){
            return "redirect:/admin/login";
        }
        List<Special> specialList = specialService.selectAllSpe();
        if(specialList==null){
            return "admin_showspe";
        }
        model.addAttribute("specialList",specialList);
        return "admin_showspe";
    }

    @RequestMapping(value = "/addSpe",method = RequestMethod.GET)
    public String addSpe(HttpSession httpSession,Model model){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin==null){
            return "redirect:/admin/login";
        }
//        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
//        System.out.println("singerId"+singerId);
//        Singer singer = singerService.selectOne(singerId);
//        model.addAttribute("singer",singer );
        List<Singer> singerList = singerService.selectAllSinger();
        model.addAttribute("singerList",singerList);
        return "admin_addspe";
    }

    @RequestMapping(value = "/doAddSpe",method = RequestMethod.POST)
    public String doAddSpe(HttpSession httpSession,@RequestParam(value = "filePic")MultipartFile filePic,Special special,@RequestParam(value = "songName")String songName[],@RequestParam(value = "file1")MultipartFile file1[],@RequestParam(value = "file2")MultipartFile file2[] ){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin==null){
            return "redirect:/admin/login";
        }
        System.out.println("special"+special);
        String newFileNamePic = MyFileUtils.fileUpload(filePic, NOTICE_IMAGE_DIR, NOTICE_URL);
        special.setSpecialPicture(newFileNamePic);
        special.setCreateTime(DateUtil.initializeCreateTime());
        specialService.insertSpecial(special);

        Special specialnew = new Special();
        specialnew.setSingerId(special.getSingerId());
        specialnew.setSpecialName(special.getSpecialName());
        System.out.println(specialService.selectId(specialnew));
        int newId = specialService.selectId(specialnew).getId();
        for (int i = 0; i < file1.length; i++) {
//            System.out.println(file1);
            String newFileName1 = MyFileUtils.fileUpload(file1[i], FILE_MP3_DIR, NOTICE_URL);
            System.out.println("newFileName1"+newFileName1);
            String newFileName2 = MyFileUtils.fileUpload(file2[i], FILE_IMG_DIR, NOTICE_URL);
            System.out.println("newFileName2"+newFileName2);
            Songs song  = new Songs();
            song.setCreateTime(DateUtil.initializeCreateTime());
            song.setSingerId(special.getSingerId());
            String sName = songName[i];
            System.out.println(sName);
            song.setSongName(sName);
            song.setSongText("default_lrc.lrc");
            song.setSongLanguage(special.getSpecialLanguage());
            song.setSongPicture(newFileName2);
            song.setSongStyle(special.getSpecialStyle());
            song.setSongFile(newFileName1);
            song.setSpecialId(newId);
            songsService.insertSong(song);
        }
        return "redirect:/admin/showSpe";
    }

    @RequestMapping(value = "/showSong",method = RequestMethod.GET)
    public String showSong(HttpSession httpSession,Model model,int sId){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin==null){
            return "redirect:/admin/login";
        }
//        Special special = specialService.selectOneSpe(sId);
//        System.out.println("special.getId()"+special.getId());
        List<Songs> songsList = songsService.selectBySpe(sId);
        System.out.println(songsList);
        model.addAttribute("songsList",songsList);
        return "admin_showsong";
    }

    //显示格列表
//    @RequestMapping(value = "/toshowSongdetail",method = RequestMethod.GET)
//    public String showEvetySong(HttpSession httpSession,Model model,int sId){
//        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
//        if(admin==null){
//            return "redirect:/admin/login";
//        }
//        Special special = specialService.selectOneSpe(sId);
//        System.out.println("special.getId()"+special.getId());
//        List<Songs> songsList = songsService.selectBySpe(special.getId());
//        model.addAttribute("songsList",songsList);
//        return "admin_showsong";
//    }

    //点击显示每一个专辑的歌曲名字  查出歌词并且展示
    @RequestMapping(value = "/toshowSongdetail",method = RequestMethod.GET)
    public String showEvetySong1(HttpSession httpSession,Model model,int songId){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin == null){
            return "redirect:/admin/login";
        }
        System.out.println("songId"+songId);
        Songs song = songsService.selectById(songId);
        model.addAttribute("songShow",song);
        String filePath = "D:\\Java\\mavenprojects\\ssm-new\\src\\main\\webapp\\WEB-INF\\lrc\\"+song.getSongText();
//        closer_to_me.lrc
        ArrayList<String[]> text=new ArrayList<String[]>();
        text= MethodUtils.readTxtFile(filePath,"utf-8");
        List<String> str = new ArrayList<>();
        for (String[] strings : text) {
            String sss = String.valueOf(strings[0]);
            str.add(sss);
            System.out.println(strings);
        }
        System.out.println(str);
        model.addAttribute("str",str);
        return "admin_showsongOne";
    }

    @RequestMapping(value = "/uplrc",method = RequestMethod.POST)
    public String upLrc(HttpSession httpSession, @RequestParam("filename")MultipartFile file1,int songId){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin == null){
            return "redirect:/admin/login";
        }
        if(!file1.isEmpty()){
            String newFileName1 = MyFileUtils.fileUpload(file1, FILE_LRC_DIR, NOTICE_URL);
            System.out.println("newFileName1"+newFileName1);
            songsService.updateLrc(newFileName1,songId);
        }
        return "redirect:/admin/toshowSongdetail?songId="+songId;
    }

    @RequestMapping(value = "/showAllSongs",method = RequestMethod.GET)
    public String showSongdetail(HttpSession httpSession ,Model model){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin == null){
            return "redirect:/admin/login";
        }
        List<Songs> songsList = songsService.selectAllSong();
        model.addAttribute("songsList",songsList);
        return "admin_showsonngs";
    }
    //表面删除歌手 将flag改为1 创建时flag均为0 歌曲 专辑同理
    @RequestMapping(value = "/deleteSinger",method = RequestMethod.GET)
    public String deleteSinger(HttpSession httpSession ,Model model,int singerId){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin == null){
            return "redirect:/admin/login";
        }
        singerService.deleteOne(singerId);
        return "redirect:/admin/showSinger";
    }

    @RequestMapping(value = "/deleteSong",method = RequestMethod.GET)
    public String deleteSong(HttpSession httpSession ,Model model,int songId){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin == null){
            return "redirect:/admin/login";
        }
        songsService.deleteSong(songId);
        return "redirect:/admin/showAllSongs";
    }

    @RequestMapping(value = "/deleteSpecial",method = RequestMethod.GET)
    public String deleteSpecial(HttpSession httpSession ,Model model,int speId){
        Administrator admin = (Administrator)httpSession.getAttribute("adminSession");
        if(admin == null){
            return "redirect:/admin/login";
        }
        specialService.deleteSpecial(speId);
        return "redirect:/admin/showSpe";
    }

    @RequestMapping("/AdminOut")
    public String outLogin(HttpSession session){
        //通过session.invalidata()方法来注销当前的session
//        session.invalidate();
        session.removeAttribute("adminSession");
        return "admin_login";
    }
}
