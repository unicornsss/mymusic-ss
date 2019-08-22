package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 羊 on 2018/11/13.
 */
public class test3 {
    public static List<Integer> getResultList(String lists){
        String aa2 =lists.replaceAll("\\[", "").replaceAll("\\]", "");
        System.out.println(aa2);
        String[] s = aa2.split(",");
        List<Integer> resultList = new ArrayList<Integer>();
        for (String s1 : s) {
//            System.out.println(s1);
            resultList.add(Integer.parseInt(s1));
        }
        System.out.println(resultList);
        return resultList;
    }

    public static void main(String[] args) {
        List<Integer> xx = new ArrayList<Integer>();
        List<Integer> xx2 = new ArrayList<Integer>();
        List<Integer> xx3 = new ArrayList<Integer>();
//        xx3.add();

        System.out.println(xx.size());
    }
}
