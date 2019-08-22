package test;

import com.music.util.MethodUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 羊 on 2018/11/20.
 */
public class test4 {
    public static void main(String[] args) {
        List<String> A = new ArrayList<String>();
        A.add("HELLO");
        A.add("HE");
        A.add("HELLO111");
        String aa = "[heiil, 流行, 心情]";
        List<String> strings = MethodUtils.getList(aa);
//        System.out.println(strings);
    }
}
