package com.music.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.bean.Songs;

import java.io.*;
import java.util.*;

/**
 * Created by 羊 on 2018/11/15.
 */
public class MethodUtils {
    public static  <T> List<T> randomFind (int count,List<T> objectList){
        List<T> list = new ArrayList<>();
        Random rand = new Random();
        int num = count;
        int Random[] = new int[num];
        for(int i = 0 ; i < num ; i++) {   // int ran=-1;
            while(true) {
                int ran = (int)(num*Math.random());
                for(int j = 0 ; j < i ; j++) {
                    if(Random[j] == ran) {
                        ran = -1;
                        break;}}
                if(ran != -1) {
                    Random[i] = ran;
                    break;
                }}}
        for(int i=0; i<5; i++) {
//            System.out.println(rand.nextInt(100) + 1);
//            int random = rand.nextInt(count);
            int random = Random[i];
            System.out.println(random);
            list.add(objectList.get(random));
        }
        return list;
    }
    public static List<Integer> getResultList(String lists){
        String aa2 =lists.replaceAll("\\[", "").replaceAll("\\]", "");
        System.out.println(aa2);
        String[] s = aa2.split(", ");
        List<Integer> resultList = new ArrayList<Integer>();
        for (String s1 : s) {
//            System.out.println(s1);
            resultList.add(Integer.parseInt(s1));
        }
        System.out.println(resultList);
        return resultList;
    }
    public static List<String> getList(String lists){
        String aa2 =lists.replaceAll("\\[", "").replaceAll("\\]", "");
        System.out.println(aa2);
        String[] s = aa2.split(", ");
        List<String> resultList = new ArrayList<String>();
        for (String s1 : s) {
//            System.out.println(s1);
            resultList.add(s1);
        }
        System.out.println(resultList);
        return resultList;
    }
    public static ArrayList<String[]> readTxtFile(String filePath,String encoding) {
        ArrayList<String[]> res = new ArrayList<String[]>();
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 编码格式必须和文件的一致
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (!lineTxt.startsWith("--")) {
                        res.add(lineTxt.split("\t"));}}
                read.close();
            } else {
                System.out.println("指定的文件不存在");
            }
        } catch (Exception e){
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return res;
    }
    public static JSONObject getJson(List<Songs> songs){
//        JSONObject user1 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject result = new JSONObject();
        int i = 0;
        for (Songs sss : songs) {
            JSONObject user1 = new JSONObject();
//            user1.put("song_name", "12");
//            user1.put("artist", "张三");
            user1.put("song_name", sss.getSongName());
            user1.put("artist", sss.getSinger().getSingerName());
            if(sss.getSongText() == null || sss.getSongText().equals("")){
                sss.setSongText("default_lrc.lrc");
            }
            user1.put("lrc_name", sss.getSongText());
            user1.put("mp3file", sss.getSongFile());
            user1.put("songId", sss.getId());
            System.out.println(user1);
            jsonArray.add(i, user1);
            i++;
        }
        result.put("data", jsonArray);
        return result;
    }
//    public static JSONObject getJson(Songs songs){
//        JSONObject user1 = new JSONObject();
//        user1.put("song_name", songs.getSongName());
//        user1.put("artist", songs.getSinger().getSingerName());
//        user1.put("lrc_name", songs.getSongText());
//        user1.put("mp3file", songs.getSongFile());
//        user1.put("songId", songs.getId());
////        user1.put("lrc_name", songs.getSongText());
//        JSONArray jsonArray = new JSONArray();
//        JSONObject result = new JSONObject();
//        jsonArray.add(0, user1);
//        result.put("data", jsonArray);
//        return result;
//    }
    public static List<Integer> getObject(String json) throws IOException {
//        json
        ObjectMapper objectMapper = new ObjectMapper();
//        String json = "{\"data\":[{\"artist\":\"张三\",\"lrc_name\":\"2017-11-16 12:12:12\",\"mp3file\":\"songs.getSongFile()\",\"songId\":\"1\",\"song_name\":\"12\"},{\"artist\":\"张三1111\",\"lrc_name\":\"11111-11-16 12:12:12\",\"mp3file\":\"songs.getSongFile()\",\"songId\":\"22\",\"song_name\":\"111112\"}]}\n";
        JsonNode jsonNode = objectMapper.readTree(json);
//        System.out.println(jsonNode.get("data"));

        JsonNode jsonMid= jsonNode.get("data");
        ObjectMapper objectMapper1 = new ObjectMapper();
        JsonNode jsonNode2 = objectMapper1.readTree(jsonMid.toString());

        List<Integer> list = new ArrayList<Integer>();
//        System.out.println(jsonNode2.size());
        for (int i = 0; i < jsonNode2.size(); i++) {
            JsonNode jsonResult = jsonNode2.get(i).get("songId");
//            System.out.println(jsonResult);
//            System.out.println(jsonResult.toString());
            String get = jsonResult.toString().replace("\"", "");
            int intList = Integer.valueOf(get);
            list.add(intList);
        }
//        System.out.println(list);
        return list;
    }

    // 删除ArrayList中重复元素，保持顺序
    public static List<Integer> removeDuplicateWithOrder(List<Integer> list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println( " remove duplicate " + list);
        return list;
    }

    public static JSONArray getSongJson(List<Songs> songs){
//        JSONObject user1 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject result = new JSONObject();
        int i = 0;
        for (Songs sss : songs) {
            JSONObject user1 = new JSONObject();
//            user1.put("song_name", "12");
//            user1.put("artist", "张三");
            user1.put("songname", sss.getSongName());
            user1.put("songid", sss.getId());
            user1.put("singerid", sss.getSinger().getId());
            user1.put("singername", sss.getSinger().getSingerName());
//            System.out.println(user1);
            jsonArray.add(i, user1);
            i++;
        }
//        result.put("data", jsonArray);
        return jsonArray;
    }

}
