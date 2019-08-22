package test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 羊 on 2018/11/22.
 */
public class test6 {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"data\":[{\"artist\":\"张三\",\"lrc_name\":\"2017-11-16 12:12:12\",\"mp3file\":\"songs.getSongFile()\",\"songId\":\"1\",\"song_name\":\"12\"},{\"artist\":\"张三1111\",\"lrc_name\":\"11111-11-16 12:12:12\",\"mp3file\":\"songs.getSongFile()\",\"songId\":\"22\",\"song_name\":\"111112\"}]}\n";
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.get("data"));

        JsonNode jsonMid= jsonNode.get("data");
        ObjectMapper objectMapper1 = new ObjectMapper();
        JsonNode jsonNode2 = objectMapper1.readTree(jsonMid.toString());

        List<Integer> list = new ArrayList<Integer>();
        System.out.println(jsonNode2.size());
        for (int i = 0; i < jsonNode2.size(); i++) {
            JsonNode jsonResult = jsonNode2.get(i).get("songId");
//            System.out.println(jsonResult);
//            System.out.println(jsonResult.toString());
            String get = jsonResult.toString().replace("\"", "");
            int intList = Integer.valueOf(get);
            list.add(intList);
        }
        System.out.println(list);

    }
}
