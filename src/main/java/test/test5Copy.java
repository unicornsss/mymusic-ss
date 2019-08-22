package test;


//import net.sf.json.*;
//import net.sf.json.JSONObject;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.music.bean.Songs;
import com.music.util.MethodUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 羊 on 2018/11/21.
 */
public class test5Copy {
    public void Test1(){

        Songs songs = new Songs();
        songs.setId(111);
        songs.setSongText("hhh");

        Songs songs1 = new Songs();
        songs1.setId(111222);
        songs1.setSongText("xxxhhh");


        List<Songs> songses = new ArrayList<Songs>();
        songses.add(songs);
        songses.add(songs1);
        JSONArray jsonArray = new JSONArray();

        JSONObject result = new JSONObject();
        int i = 0;
        for (Songs sss : songses) {
            JSONObject user1 = new JSONObject();
//            user1.put("song_name", "12");
//            user1.put("artist", "张三");
            user1.put("lrc_name", sss.getSongText());
            user1.put("songId", sss.getId());
            jsonArray.add(i, user1);
            i++;
        }
        result.put("data", jsonArray);
        System.out.println(result);
        System.out.println(result.toString());
    }
    public static void main(String[] args) {
//        JsonUtil jsonUtil = JsonUtil.getJsonUtil();
//        List<String> stringList = new ArrayList<String>();
//        stringList.add("hhh");
//        stringList.add("yyy");
//        String jsonPic = jsonUtil.obj2Json(stringList);
////        System.out.println(jsonPic);

        List<Integer> a = new ArrayList<Integer>();
        a.add(222);
        a.add(333);
        a.add(444);
        a.add(222);
        List<Integer> list=MethodUtils.removeDuplicateWithOrder(a);
        for (Integer integer : list) {
            System.out.println(integer);
        }
/*
*{"data":[
*
* {"artist":"张三","lrc_name":"2017-11-16 12:12:12","mp3file":"songs.getSongFile()","songId":"songs.getId()","song_name":"12"},
* {"artist":"张三1111","lrc_name":"11111-11-16 12:12:12","mp3file":"songs.getSongFile()","songId":"songs.getId()","song_name":"111112"}]}
*
* */

//        JSONObject jsonObject = JSONObject.fromObject(new User());
//        boolean[] boolArray = new boolean[] { true, false, true };
//        JSONArray jsonArray1 = JSONArray.fromObject(boolArray);
//        User u1 = new User();
//        u1.setAge(22);
//        u1.setUsername("hzucmj");
//        u1.setEnabled(true);
//        User u2 = new User();
//        u2.setAge(20);
//        u2.setUsername("ctf");
//        u2.setEnabled(true);
//        List<Object> list = new ArrayList<Object>();
//        list.add(u1);
//        list.add(u2);
//        JSONArray json = JSONArray.fromObject(list);
//        System.out.println(json.toString()); //结果为：[{"enabled":false,"username":"ctf","age":20},{"enabled":false,"username":"","age":0}]
    }
}
