package test;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

//import net.sf.json.*;
//import net.sf.json.JSONObject;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

/**
 * Created by 羊 on 2018/11/21.
 */
public class test5 {
    public static void main(String[] args) {
//        JsonUtil jsonUtil = JsonUtil.getJsonUtil();
//        List<String> stringList = new ArrayList<String>();
//        stringList.add("hhh");
//        stringList.add("yyy");
//        String jsonPic = jsonUtil.obj2Json(stringList);
////        System.out.println(jsonPic);

/*
*{"data":[
*
* {"artist":"张三","lrc_name":"2017-11-16 12:12:12","mp3file":"songs.getSongFile()","songId":"songs.getId()","song_name":"12"},
* {"artist":"张三1111","lrc_name":"11111-11-16 12:12:12","mp3file":"songs.getSongFile()","songId":"songs.getId()","song_name":"111112"}]}
*
* */

        JSONArray jsonArray = new JSONArray();
        JSONObject result = new JSONObject();
        JSONObject user1 = new JSONObject();
        if(user1.isEmpty()) {

             user1 = new JSONObject();
            user1.put("song_name", "12");
            user1.put("artist", "张三");
            user1.put("lrc_name", "2017-11-16 12:12:12");
            user1.put("mp3file", "songs.getSongFile()");
            user1.put("songId", "songs.getId()");
            jsonArray.add(0, user1);
        }
        JSONObject user2 = new JSONObject();
        user2.put("song_name", "111112");
        user2.put("artist", "张三1111");
        user2.put("lrc_name", "11111-11-16 12:12:12");
        user2.put("mp3file", "songs.getSongFile()");
        user2.put("songId", "songs.getId()");
        jsonArray.add(1, user2);
        result.put("data", jsonArray);
        System.out.println(result);
        System.out.println(result.toString());
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
//
//        List<Object> list = new ArrayList<Object>();
//        list.add(u1);
//        list.add(u2);
//
//        JSONArray json = JSONArray.fromObject(list);
//        System.out.println(json.toString()); //结果为：[{"enabled":false,"username":"ctf","age":20},{"enabled":false,"username":"","age":0}]
//
    }
}
